package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.OrderBean;
import com.magic.wogia.bean.OrderCompoentBean;
import com.magic.wogia.bean.OrderDescribeBean;
import com.magic.wogia.bean.OrderInfoBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.IComponentDeviceMapper;
import com.magic.wogia.mapper.IComponentMapper;
import com.magic.wogia.mapper.IOrderCompoentMapper;
import com.magic.wogia.mapper.IOrderDescribe;
import com.magic.wogia.mapper.IOrderInfoMapper;
import com.magic.wogia.mapper.IOrderMapper;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.util.OrderStatusConStant;
import com.magic.wogia.util.PushMessageUtil;
import com.magic.wogia.util.RoleConstant;

/**
 *  订单模块 业务层
 * @author QimouXie
 *
 */
@Service
public class OrderService {
	
	@Resource
	private IOrderMapper orderMapper;
	@Resource
	private IOrderDescribe orderDescribe;
	@Resource
	private IComponentDeviceMapper componentDeviceMapper;
	@Resource
	private IUserMapper userMapper;
	@Resource
	private IOrderInfoMapper orderInfoMapper;
	@Resource
	private IOrderCompoentMapper orderCompoentMapper;
	@Resource
	private IComponentMapper componentMapper;
	
	/**
	 *  增加订单
	 *  @return 返回新增订单的ID
	 */
	@Transactional
	public Integer addOrder(OrderBean order,UserBean user) throws Exception{
		orderMapper.addOrder(order);
		if(OrderStatusConStant.ORDER_TYPE_MAINTENANCE.equals(order.getOrderType())){
			if(null != order.getComponentId() && order.getComponentId().trim().length() != 0 ){
				String[] coms = order.getComponentId().split(",");
				List<OrderCompoentBean> comList = new ArrayList<OrderCompoentBean>();
				for (int i = 0; i < coms.length; i++) {
					OrderCompoentBean compoen = new OrderCompoentBean();
					compoen.setOrderId(order.getId());
					compoen.setComponentId(Integer.parseInt(coms[i]));
					comList.add(compoen);
				}
				if(comList.size() > 0){
					orderCompoentMapper.addOrderCompoent(comList);
				}
			}
		}
		List<UserBean> users = userMapper.queryUserByItem(RoleConstant.AREA_MANAGER, user.getIdType(), user.getCompanyId());
		List<OrderInfoBean> needAdd = new ArrayList<OrderInfoBean>();
		if(null != users && users.size() > 0){
			Map<String, String> extendsParams = new HashMap<String, String>();
			extendsParams.put("info_type", PushMessageUtil.ORDER_INFO);
			for (UserBean tempUser : users) {
				PushMessageUtil.pushMessages(tempUser, "您有新的订单等待处理", extendsParams);
				OrderInfoBean info = new OrderInfoBean();
				info.setOrderId(order.getId());
				info.setInfoMsg("您有新的订单等待处理");
				info.setOrderNumber(order.getOrderNumber());
				info.setToUserId(tempUser.getId());
				needAdd.add(info);
			}
			if(needAdd.size() > 0){
				orderInfoMapper.batchAddOrderInfo(needAdd);
			}
		}
		return order.getId();
	}
	/**
	 *  更新订单
	 * @param order
	 * @return
	 */
	public Integer updateOrder(OrderBean order){
		return orderMapper.updateOrder(order);
	}
	/**
	 *  分页查询订单
	 *  和当前用户相关
	 *  匹配订单状态
	 * @return
	 */
	public List<OrderBean> queryPageOrder(Integer pageNO,Integer pageSize,UserBean user,
			Integer status,Integer orderType,Integer techOrderFlag,String realName,String orderNumber){
		if (pageNO != null) {
			pageNO = (pageNO - 1)*pageSize;
		}else{
			pageSize = null;
			pageNO = null;
		}
		List<OrderBean> orders = orderMapper.queryOrder(status, user, pageNO, pageSize,orderType,techOrderFlag,realName,orderNumber);
		for (OrderBean order : orders) {
			JSONArray jsOrderData = JSONArray.fromObject(order.getOrderData());
//			order.setJsOrderData(jsOrderData);
			order.setOrderDataBean(JSONObject.fromObject(jsOrderData.get(jsOrderData.size() - 1)));
		}
		return orders ;
	}
	
	public OrderBean queryById(Integer orderId){
		OrderBean order = orderMapper.queryById(orderId);
		if(null != order){
			try {
//				if(OrderStatusConStant.ORDER_TYPE_MAINTENANCE.equals(order.getOrderType()) && null != order.getComponentId() && order.getComponentId().trim().length() != 0){
//					List<ComponentBean>  coms = componentMapper.queryBatch(order.getComponentId());
//					order.setComs(coms);
//				}
				JSONArray obj = JSONArray.fromObject(order.getOrderData());
				order.setJsOrderData(obj);
			} catch (Exception e) {
				return null;
			}
		}
		return order;
	}
	
	/**
	 *  接受任务
	 * @param order
	 * @param describe
	 * @throws Exception
	 */
	@Transactional
	public void acceptOrder(OrderBean order,OrderDescribeBean describe,List<Integer> ids,Date updateTime,List<OrderInfoBean> infos) throws Exception{
		if(null != describe){
			orderDescribe.addOrderDescribe(describe);
		}
		if(null != ids && ids.size() > 0 && null != updateTime){
			componentDeviceMapper.updMaintainTime(ids,updateTime);
		}
		if(null != infos && infos.size() > 0){
			orderInfoMapper.batchAddOrderInfo(infos);
		}
		orderMapper.updateOrder(order);
	}
	
	public List<OrderDescribeBean> getOrderDetail(Integer orderId){
		return orderDescribe.queryByOrder(orderId);
	}

}
