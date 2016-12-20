package com.magic.wogia.controller.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.ProjectTableBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.DeviceService;
import com.magic.wogia.service.ProjectService;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 *  同步客户数据库，更新数据到本地数据库
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/web/synData")
public class SynDataController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource
	private DeviceService deviceService;
	
	/**
	 *  同步项目
	 * @return
	 */
	@RequestMapping("/synProject")
	@ResponseBody
	public ViewData synProject(){
		try {
			List<ProjectTableBean> tables = projectService.queryTables();
			Integer count = projectService.updateProjectData(tables);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "更新成功", count);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "更新失败");
		}
	}
	
	@RequestMapping("/synDevice")
	@ResponseBody
	public ViewData synDeviceData(String tableName){
		if(null == tableName || tableName.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		try {
			List<String> fileds = deviceService.getCSDevice(DataSourceInstances.DATABASENAME, tableName);
			Integer count = deviceService.updateDeviceTable(fileds, tableName);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "更新成功", count);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "更新失败");
		}
	}
	
	
	
	
	
	
}
