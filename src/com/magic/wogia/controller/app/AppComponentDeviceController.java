package com.magic.wogia.controller.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ComponentDeviceBean;
import com.magic.wogia.bean.ProjectMaintainCalendarBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.ComponentDeviceService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：分区零件控制器
 * 编写人员：lzh
 * 时间：2016年9月13日上午11:42:44
 */
@Controller
@RequestMapping("/app/componentDevice")
public class AppComponentDeviceController extends BaseController{

	@Resource
	private ComponentDeviceService componentDeviceService;
	
	/**
	 * 添加或者更新片区零件信息
	 * @param record 片区零件对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdComponentDevice",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdComponentDevice(ComponentDeviceBean record){
		try {
			
			componentDeviceService.addOrUpdComponentDevice(record);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询片区零件
	 * @param record 片区零件对象
	 * @param mergerName 地区
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findComponentDevice")
	@ResponseBody
	public ViewData findComponentDevice(ComponentDeviceBean record,Integer pageNum,Integer pageSize){
		UserBean user = LoginHelper.getCurrentUser();
		List<ComponentDeviceBean> componentList = new ArrayList<ComponentDeviceBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			componentList = componentDeviceService.findComponentDevice(record, pageNum, pageSize,user);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, componentList);
	}
	
	/**
	 * 查询片区单个零件
	 * @param id 片区零件id
	 * @return
	 */
	@RequestMapping("/findComponentDeviceById")
	@ResponseBody
	public ViewData findComponentDeviceById(@RequestParam("id")Integer id){
		UserBean user = LoginHelper.getCurrentUser();
		List<ComponentDeviceBean> componentList = new ArrayList<ComponentDeviceBean>();
		ComponentDeviceBean record = new ComponentDeviceBean();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			record.setId(id);
			componentList = componentDeviceService.findComponentDevice(record, null, null,user);
			for (ComponentDeviceBean componentDeviceBean : componentList) {
				record = componentDeviceBean;
			}
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, record);
	}
	

	
	/**
	 * 保养日历
	 * @param record 片区零件对象
	 * @param mergerName 地区
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/maintainCalendar")
	@ResponseBody
	public ViewData maintainCalendar(ComponentDeviceBean record,Integer pageNum,Integer pageSize,String mergerName){
		UserBean user = LoginHelper.getCurrentUser();
		List<ProjectMaintainCalendarBean> pmcList = new ArrayList<ProjectMaintainCalendarBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			pmcList = componentDeviceService.maintainCalendar(record, pageNum, pageSize,user,mergerName);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, pmcList);
	}
}
