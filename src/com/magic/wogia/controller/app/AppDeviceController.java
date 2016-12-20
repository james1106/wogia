package com.magic.wogia.controller.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.DeviceService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：设备控制器 编写人员：lzh 时间：2016年9月6日下午5:02:05
 */
@Controller
@RequestMapping("/app/device")
public class AppDeviceController extends BaseController {

	@Resource
	private DeviceService deviceService;

	/**
	 * 查询设备
	 * 
	 * @param device
	 *            设备对象
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示多少条
	 * @return
	 */
	@RequestMapping("/findDevice")
	@ResponseBody
	public ViewData findDevice(DeviceBean device, Integer pageNum,
			Integer pageSize) {
		UserBean user = LoginHelper.getCurrentUser();
		List<DeviceBean> estateList = new ArrayList<DeviceBean>();
		try {
			estateList = deviceService.findDevice(device, pageNum, pageSize,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE,ErrorMessage.SUCCESS, estateList);
	}
}
