package com.magic.wogia.controller.app;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ISystemInfoService;
import com.magic.wogia.service.OrderInfoService;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：系统消息控制器
 * 编写人员：lzh
 * 时间：2016年9月18日上午10:23:50
 */
@Controller
@RequestMapping("/app/info")
public class AppInfoCenterController extends BaseController{
	
	@Resource
	private ISystemInfoService systemInfoServiceImpl;
	@Resource
	private OrderInfoService orderInfoService;
	/**
	 *  获取消息
	 * @return
	 */
	@RequestMapping("/getMsg")
	@ResponseBody
	public ViewData getMessage(Integer mold,Integer pageNum,Integer pageSize){
		
		if(null == mold){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		Object data = null;
		if(StatusConstant.SYSTEM_INFO.equals(mold)){
			//0:系统公告
			data = systemInfoServiceImpl.quertPage(pageNum, pageSize,0,user);
		}else if(StatusConstant.ORDER_INFO.equals(mold)){
			//订单消息
			data = orderInfoService.queryPage(user.getId(), pageNum, pageSize);
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "获取失败");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 * 获取单个系统通知的详情
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/getMsgById")
	@ResponseBody
	public ViewData getMsgById(Integer systemId){
		if(null == systemId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		SystemInfoBean info = systemInfoServiceImpl.findById(systemId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", info);
	}
	
/*	*//**
	 *  停水公告
	 * @return
	 *//*
	@RequestMapping("/getStopWaterInfo")
	@ResponseBody
	public ViewData getStopWaterInfo(Integer pageNum,Integer pageSize){
		List<SystemInfoBean> list = new ArrayList<SystemInfoBean>();
		try {
			list = systemInfoServiceImpl.quertPage(pageNum, pageSize,1);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", list);
	}*/
}

