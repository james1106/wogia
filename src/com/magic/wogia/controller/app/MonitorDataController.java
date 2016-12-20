package com.magic.wogia.controller.app;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.MonitorDataBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.MonitorDataService;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 *  实时监控数据 接口
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/app/monitorData")
public class MonitorDataController extends BaseController {
	
	@Resource
	private MonitorDataService monitorDataService;
	
	/**
	 *  监控图表
	 * @return
	 */
	@RequestMapping("/monitorChart")
	@ResponseBody
	public ViewData monitorChart(String deviceName){
		if(null == deviceName){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<MonitorDataBean> data = monitorDataService.getMonitorChart(deviceName);
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE,"获取成功", data);
	}
	
	/**
	 *  流量数据
	 * @param deviceName
	 * @return
	 */
	@RequestMapping("/monitorData")
	@ResponseBody
	public ViewData monitorData(String deviceName){
		
		if(null == deviceName){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Map<String,Object> data = monitorDataService.getMonitorData(deviceName);
		if(null == data){
			return buildFailureJson(StatusConstant.Fail_CODE, "获取数据失败");
		}
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE,"获取成功", data);
	}
	
	/**
	 *  水质监控
	 * @param deviceName
	 * @return
	 */
	@RequestMapping("/monitorWater")
	@ResponseBody
	public ViewData monitorWater(String deviceName){
		
		if(null == deviceName){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Map<String, Object> map = monitorDataService.getMonitorWater(deviceName);
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		if(null != map && map.size() > 0){
			return buildSuccessJson(StatusConstant.SUCCESS_CODE,"获取成功", map);
		}
		return buildFailureJson(StatusConstant.NO_DATA, "没有数据");
	}
	

}
