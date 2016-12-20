package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.CSDeviceBean;
import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.DeviceInformationBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.DeviceInformationService;
import com.magic.wogia.service.DeviceService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：设备控制器
 * 编写人员：lzh
 * 时间：2016年9月6日下午5:02:05
 */
@Controller
@RequestMapping("/web/device")
public class DeviceController extends BaseController{
	
	@Resource
	private DeviceService deviceService;
	@Resource
	private DeviceInformationService deviceInformationService;
	
	/**
	 * 添加或者更新设备信息
	 * @param device 设备对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdDevice",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdDevice(DeviceBean device,Integer csId,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			deviceService.addOrUpdDevice(device,csId);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	/**
	 * 
	 * 功能：返回是否已添加设备的实体 findDevice方法使用
	 * 编写人员：lzh
	 * 时间：2016年9月12日下午4:15:27
	 */
	public class Device{
		//是否已经添加设备
		private Integer isHaveDevice;
		private DeviceBean deviceBean;
		public Integer getIsHaveDevice() {
			return isHaveDevice;
		}

		public void setIsHaveDevice(Integer isHaveDevice) {
			this.isHaveDevice = isHaveDevice;
		}

		public DeviceBean getDeviceBean() {
			return deviceBean;
		}

		public void setDeviceBean(DeviceBean deviceBean) {
			this.deviceBean = deviceBean;
		}
		
		
	}
	
	/**
	 * 查询设备
	 * @param device 设备对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findDevice")
	@ResponseBody
	public ViewData findDevice(DeviceBean device,Integer pageNum,Integer pageSize,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<DeviceBean> estateList = new ArrayList<DeviceBean>();
		List<Device> list = new ArrayList<Device>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			estateList = deviceService.findDevice(device,pageNum,pageSize,user);
			for (DeviceBean deviceBean : estateList) {
				Device d = new Device();
				d.setDeviceBean(deviceBean);
				DeviceInformationBean data = new DeviceInformationBean();
				data.setDeviceId(deviceBean.getId());
				int num = deviceInformationService.getDeviceInformationListCount(data);
				d.setIsHaveDevice(num);
				list.add(d);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, list);
	}
	
	
	/**
	 * 查询未绑定的 设备  status=0
	 * @param tableName 表名
	 * @return
	 */
	@RequestMapping("/findStatus")
	@ResponseBody
	public ViewData findStatus(@RequestParam("tableName")String tableName){
		List<CSDeviceBean> scList = new ArrayList<CSDeviceBean>();
		try { 
			scList = deviceService.findStatus(tableName);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, scList);
	}
	
	/**
	 * 更新绑定状态
	 * @param csProject
	 */
	@RequestMapping(value="/updCSDevice",method=RequestMethod.POST)
	@ResponseBody
	public ViewData updCSDevice(CSDeviceBean device){
		try { 
			deviceService.updCsDevice(device);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildFailureJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
}
