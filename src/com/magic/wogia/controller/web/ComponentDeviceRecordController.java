package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ComponentDeviceRecordBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.ComponentDeviceRecordService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：零件维修/保养记录控制器
 * 编写人员：lzh
 * 时间：2016年9月13日下午5:08:38
 */
@Controller
@RequestMapping("/web/deviceRecord")
public class ComponentDeviceRecordController extends BaseController{

	@Resource
	private ComponentDeviceRecordService componentDeviceRecordService;
	
	/**
	 * 添加零件维修/保养记录
	 * @param record 
	 * @return
	 */
	@RequestMapping(value="/addComponentDeviceRecord",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addComponentDeviceRecord(ComponentDeviceRecordBean record){
		try {
			componentDeviceRecordService.addComponentDeviceRecord(record);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询零件维修/保养记录
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findComponentDeviceRecord")
	@ResponseBody
	public ViewData findComponentDeviceRecord(ComponentDeviceRecordBean record,Integer pageNum,Integer pageSize,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<ComponentDeviceRecordBean> componentList = new ArrayList<ComponentDeviceRecordBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			componentList = componentDeviceRecordService.findComponentDeviceRecord(record, pageNum, pageSize);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, componentList);
	}
}
