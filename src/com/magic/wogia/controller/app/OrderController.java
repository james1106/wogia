package com.magic.wogia.controller.app;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.OrderBean;
import com.magic.wogia.bean.OrderDescribeBean;
import com.magic.wogia.bean.OrderInfoBean;
import com.magic.wogia.bean.OrderTrackBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.OrderInfoService;
import com.magic.wogia.service.OrderService;
import com.magic.wogia.service.UserService;
import com.magic.wogia.util.DateUtil;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.OrderStatusConStant;
import com.magic.wogia.util.PushMessageUtil;
import com.magic.wogia.util.RoleConstant;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 订单控制器
 * 
 * @author QimouXie
 *
 */
@RequestMapping("/app/order")
@Controller
public class OrderController extends BaseController {

	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;
	@Resource
	private OrderInfoService orderInfoService;

	@RequestMapping("/add")
	@ResponseBody
	public ViewData addOrder(OrderBean order) {

		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.PLATFORM_USER.equals(user.getIdType()) || StatusConstant.AREA_USER.equals(user.getIdType()) || StatusConstant.OFFICE_USER.equals(user.getIdType())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		if (null == order  || null == order.getOrderType()) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if (OrderStatusConStant.ORDER_TYPE_REPAIR.equals(order.getOrderType())) {
			order.setDeviceId(order.getDeviceId());
		}
		if (OrderStatusConStant.ORDER_TYPE_MAINTENANCE.equals(order.getOrderType())) {
			order.setDeviceId(order.getDeviceId());
			order.setComponentId(order.getComponentId());
		}
		order.setUserName(user.getRealName());
		order.setUserPhone(user.getMobile());
		order.setOrderNumber(order.buildOrderNumber());
		order.setCreateTime(new Date());
		order.setUserId(user.getId());
		order.setIsValid(OrderStatusConStant.VALID);
		order.setServiceCheck(OrderStatusConStant.NON_CHECK);
		if (null == order.getUserName() || order.getUserName().trim().length() == 0) {
			order.setUserName(user.getRealName());
		}
		if (null == order.getUserPhone() || order.getUserPhone().trim().length() == 0) {
			order.setUserPhone(user.getMobile());
		}
		OrderTrackBean trackOne = new OrderTrackBean("订单已发出", OrderStatusConStant.SUBMIT_ORDER, new Date().getTime());
//		List<OrderTrackBean> orderData = new ArrayList<OrderTrackBean>();
//		orderData.add();
		order.setOrderData(JSONArray.fromObject(trackOne.createOrderTrack()).toString());
		order.setStatus(OrderStatusConStant.SUSPENDING);
		try {
			orderService.addOrder(order,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}

	/**
	 * 分配订单
	 */
	@RequestMapping("/allotOrder")
	@ResponseBody
	public ViewData allotOrderToTech(Integer techId, Integer orderId) {

		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		
		if(!(RoleConstant.AREA_MANAGER.equals(user.getRoleId()) || RoleConstant.PLATFORM_MANAGER.equals(user.getRoleId()))){
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		if (null == techId || null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean tempOrder = orderService.queryById(orderId);
		if (null == tempOrder) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (!OrderStatusConStant.VALID.equals(tempOrder.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "无效订单");
		}
		if (!OrderStatusConStant.SUSPENDING.equals(tempOrder.getStatus())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "订单已经分配过");
		}
		OrderBean order = new OrderBean();
		order.setId(orderId);
		order.setTechId(techId);
		order.setStatus(OrderStatusConStant.HANDLING);
		order.setTechCheck(OrderStatusConStant.NON_CHECK);
		OrderTrackBean track = new OrderTrackBean("已分配工程师",OrderStatusConStant.ASSIGNED_TECH, new Date().getTime());
		List<Integer> ids = new ArrayList<Integer>();
		List<OrderInfoBean> needAdd = new ArrayList<OrderInfoBean>();
		try {
			JSONArray jsonArray = JSONArray.fromObject(tempOrder.getOrderData());
			jsonArray.add(track.createOrderTrack());
			order.setOrderData(jsonArray.toString());
			order.setUpdateTime(new Date());
			orderService.updateOrder(order);
			ids.add(techId);
			if(null != tempOrder.getUserId()){
				ids.add(tempOrder.getUserId());
			}
			if(ids.size() > 0){
				List<UserBean> users = userService.batchQueryUserToken(ids);
				Map<String, String> extendsParams = new HashMap<String, String>();
				extendsParams.put("info_type", PushMessageUtil.ORDER_INFO);
				for (UserBean userBean : users) {
					PushMessageUtil.pushMessages(userBean, "您有最新的订单消息", extendsParams);
					OrderInfoBean info = new OrderInfoBean();
					info.setOrderId(tempOrder.getId());
					info.setInfoMsg("您有最新的订单消息");
					info.setOrderNumber(tempOrder.getOrderNumber());
					info.setToUserId(userBean.getId());
					needAdd.add(info);
				}
				if(needAdd.size() > 0){
					orderInfoService.batchAdd(needAdd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "数据转换异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}

	/**
	 * 获取订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/getOrders")
	@ResponseBody
	public ViewData getOrders(Integer status, Integer pageNum, Integer pageSize,Integer orderType) {

		if (null == pageNum || null == pageSize) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		// 如果技术请求 待处理中 (参数: 2001)： 订单状态为:2002 + 技术未查看 如果请求技术 待处理(2002) : 2002 + 查看
		Integer techOrderFlag = null;
		if(RoleConstant.AREA_TECH.equals(user.getRoleId())){
			techOrderFlag = 0;
		}
		List<OrderBean> data = orderService.queryPageOrder(pageNum, pageSize,user, status,orderType,techOrderFlag,null,null);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 修改查看操作
	 * 
	 * @param orderId
	 * @return
	 */
	public ViewData updateCheck(Integer orderId) {
		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean order = orderService.queryById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (!OrderStatusConStant.VALID.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "无效订单");
		}

		return null;
	}

	/**
	 * 接受任务 / 开始维修 / 维修完成
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public ViewData acceptOrder(Integer orderId, String expectTime,String content, Integer flag) {
		// flag 0 接受任务 1 开始维修 2 维修完成
		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		if (!RoleConstant.AREA_TECH.equals(user.getRoleId())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		if (null == orderId || null == flag) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean tempOrder = orderService.queryById(orderId);
		if (null == tempOrder) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (!OrderStatusConStant.VALID.equals(tempOrder.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "无效订单");
		}
		OrderBean order = new OrderBean();
		order.setId(orderId);
		if (flag != 2) {
			order.setStatus(OrderStatusConStant.HANDLING);
			order.setTechCheck(OrderStatusConStant.CHECKED);
		} else {
			order.setStatus(OrderStatusConStant.VERIFICATIONPENDING);
		}
		order.setUpdateTime(new Date());
		OrderDescribeBean describe = new OrderDescribeBean();
		describe.setContent(content);
		Date expect = null;
		if(null != expectTime && expectTime.trim().length() != 0){
			try {
				String[] temps = expectTime.split(",");
				Integer days = Integer.parseInt(temps[0]);
				Integer hours = Integer.parseInt(temps[1]);
				Integer mins = Integer.parseInt(temps[2]);
				expect = DateUtil.getNextDate(new Date(), days, hours, mins);
			} catch (Exception e) {
				e.printStackTrace();
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}
		describe.setExpectTime(expect);
		describe.setOrderId(orderId);
		OrderTrackBean track = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
		// flag 0 接受任务 1 开始维修 2 维修完成
		Map<String, String> extendsParams = new HashMap<String, String>();
		extendsParams.put("info_type", PushMessageUtil.ORDER_INFO);
		if (flag == 0) {
			order.setTechStatus(OrderStatusConStant.ACCEPT_ORDER);
			track = new OrderTrackBean("工程师已受理", MessageFormat.format(OrderStatusConStant.WAY_TECH,DateUtil.DateTime(expect, "yyyy-MM-dd HH:mm")), new Date().getTime());
			ids.add(tempOrder.getServiceId());
			describe.setType(OrderStatusConStant.ACCEPTORDER_DECRIBE);
			List<UserBean> users = userService.batchQueryUserToken(ids);
			
			for (UserBean userBean : users) {
				PushMessageUtil.pushMessages(userBean, "您有最新的订单消息", extendsParams);
				OrderInfoBean info = new OrderInfoBean();
				info.setOrderId(tempOrder.getId());
				info.setInfoMsg("您有最新的订单消息");
				info.setOrderNumber(tempOrder.getOrderNumber());
				info.setToUserId(userBean.getId());
				infos.add(info);
			}
		} else if (flag == 1) {
			order.setTechStatus(OrderStatusConStant.FINISH_ORDER);
			describe.setType(OrderStatusConStant.STARTREPAIR_DECRIBE);
			track = new OrderTrackBean("正在维修", MessageFormat.format(OrderStatusConStant.REPAIRING,DateUtil.DateTime(expect, "yyyy-MM-dd HH:mm")), new Date().getTime());
		} else if (flag == 2) {
			describe.setType(OrderStatusConStant.REPAIEDR_DECRIBE);
			track = new OrderTrackBean("维修完成", MessageFormat.format(OrderStatusConStant.QUEST_VERIFI,DateUtil.DateTime(expect, "yyyy-MM-dd HH:mm")), new Date().getTime());
			ids.add(tempOrder.getServiceId());
			ids.add(tempOrder.getUserId());
			List<UserBean> users = userService.batchQueryUserToken(ids);
			for (UserBean userBean : users) {
				PushMessageUtil.pushMessages(userBean, "您有最新的订单消息", extendsParams);
				OrderInfoBean info = new OrderInfoBean();
				info.setOrderId(tempOrder.getId());
				info.setInfoMsg("您有最新的订单消息");
				info.setOrderNumber(tempOrder.getOrderNumber());
				info.setToUserId(userBean.getId());
				infos.add(info);
			}
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(tempOrder.getOrderData());
			jsonArray.add(track.createOrderTrack());
			order.setOrderData(jsonArray.toString());
			orderService.acceptOrder(order, describe,null,null,infos);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}

	/**
	 * 接受拒绝 完成
	 * 
	 * @return
	 */
	@RequestMapping("/verifiOrder")
	@ResponseBody
	public ViewData acceptanceOrder(Integer orderId, Integer flag,String content) {
		// flag 0 拒绝 1 接受
		UserBean user = LoginHelper.getCurrentUser();
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已经被冻结");
		}
		if (!(RoleConstant.WATER_FACTORY_WORKER.equals(user.getRoleId()) || RoleConstant.ESTATE_WORKER.equals(user.getRoleId()))) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		if (null == orderId || null == flag) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean tempOrder = orderService.queryById(orderId);
		if (null == tempOrder) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (!OrderStatusConStant.VALID.equals(tempOrder.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "无效订单");
		}
		OrderBean order = new OrderBean();
		OrderDescribeBean describe = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(tempOrder.getServiceId());
		userIds.add(tempOrder.getTechId());
		Date updateTime = null;
		order.setId(orderId);
		if (flag == 0) {
			// 拒绝
			describe = new OrderDescribeBean();
			order.setStatus(OrderStatusConStant.HANDLING);
			describe.setContent(content);
			describe.setOrderId(orderId);
			describe.setType(OrderStatusConStant.USER_DECRIBE);
		} else {
			order.setStatus(OrderStatusConStant.OVERALLPENGDING);
			if(null != tempOrder.getComponentId() && tempOrder.getComponentId().trim().length() != 0){
				updateTime = tempOrder.getUpdateTime();
				String[] strs = tempOrder.getComponentId().split(",");
				for(int i=0; i < strs.length ; i++){
					ids.add(Integer.parseInt(strs[i]));
				}
			}
		}
		order.setUpdateTime(new Date());
		OrderTrackBean track = new OrderTrackBean("验收完成",OrderStatusConStant.QUEST_OVER, new Date().getTime());
		try {
			JSONArray jsonArray = JSONArray.fromObject(tempOrder.getOrderData());
			if (flag == 0) {
				track = new OrderTrackBean("拒绝验收",OrderStatusConStant.REJECT_ORDER, new Date().getTime());
			} else if (flag == 1) {
				track = new OrderTrackBean("验收完成",OrderStatusConStant.QUEST_OVER, new Date().getTime());
			}
			jsonArray.add(track.createOrderTrack());
			order.setOrderData(jsonArray.toString());
			orderService.acceptOrder(order, describe, ids,updateTime,null);
			List<UserBean> users = userService.batchQueryUserToken(userIds);
			Map<String, String> extendsParams = new HashMap<String, String>();
			extendsParams.put("info_type", PushMessageUtil.ORDER_INFO);
			if(null != users && users.size() > 0){
				List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
				for (UserBean userBean : users) {
					PushMessageUtil.pushMessages(userBean, "您有最新的订单消息", extendsParams);
					OrderInfoBean info = new OrderInfoBean();
					info.setOrderId(tempOrder.getId());
					info.setInfoMsg("您有最新的订单消息");
					info.setOrderNumber(tempOrder.getOrderNumber());
					info.setToUserId(userBean.getId());
					infos.add(info);
				}
				if(infos.size() > 0){
					orderInfoService.batchAdd(infos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	/**
	 *  获取订单追踪数据
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/getOrderData")
	@ResponseBody
	public ViewData getOrderData(Integer orderId){
		if (null == orderId ) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean order = orderService.queryById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", order);
	}
	
	/**
	 *  获取订单详细信息
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public ViewData getOrderDetail(Integer orderId){
		if(null == orderId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean order = orderService.queryById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (!OrderStatusConStant.VALID.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "无效订单");
		}
		List<OrderDescribeBean> decribe = orderService.getOrderDetail(orderId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("order", order);
		data.put("describe", decribe);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
}
