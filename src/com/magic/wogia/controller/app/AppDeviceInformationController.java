package com.magic.wogia.controller.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.DeviceInformationBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.DeviceInformationService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：app端设备信息控制器
 * 编写人员：lzh
 * 时间：2016年9月12日下午1:56:26
 */
@Controller
@RequestMapping("/app/deviceInformation")
public class AppDeviceInformationController extends BaseController{

	@Resource
	private DeviceInformationService deviceInformationService;
	
	/**
	 * 根据分区id查询设备信息
	 * @param deviceId 分区id
	 * @return
	 */
	@RequestMapping(value="/findDeviceInformationByDeviceId")
	@ResponseBody
	public ViewData findDeviceInformationByDeviceId(@RequestParam("deviceId")Integer deviceId){
		DeviceInformationBean di = new DeviceInformationBean();
		try {
			di = deviceInformationService.findDeviceInformationByDeviceId(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, di);
	}
	
	
}
