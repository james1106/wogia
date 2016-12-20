package com.magic.wogia.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.ProjectAbnormalStatusBean;
import com.magic.wogia.bean.ProjectApplyBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ProjectApplyService;
import com.magic.wogia.service.ProjectService;
import com.magic.wogia.service.ProjectService.Project;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：app端  项目功能控制
 * 编写人员：lzh
 * 时间：2016年9月7日上午9:07:51
 */
@Controller
@RequestMapping("/app/project")
public class AppProjectController extends BaseController{

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectApplyService projectApplyService;
	
	/**
	 * 查询项目
	 * @param project 项目对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findProjects")
	@ResponseBody
	public ViewData findProjects(String mergerName,String projectName,Integer pageNum,Integer pageSize){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		List<Project> projectList = new ArrayList<Project>();
		
		UserBean user = LoginHelper.getCurrentUser();
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		if(null == mergerName || mergerName.trim().length() == 0){
			mergerName = null;
		}
		if(null == projectName || projectName.trim().length() == 0){
			projectName = null;
		}
		try {
			projectList = projectService.findProjects(mergerName,projectName,pageNum,pageSize,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, projectList);
	}
	
	/**
	 *  通过User获取相关的项目
	 * @return
	 */
	@RequestMapping("/getProjectBySelf")
	@ResponseBody
	public ViewData getProjectsByUser(Integer pageNum,Integer pageSize){
		if(null == pageNum || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		List<ProjectBean> data = new ArrayList<ProjectBean>();
		if(null == user.getIdType()){
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		}
		data = projectService.queryProjectByUser(user.getIdType(), user.getCompanyId(),pageNum,pageSize);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 * 盯一盯
	 * @return
	 */
	@RequestMapping("/stareOneStare")
	@ResponseBody
	public ViewData stareOneStare(){
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		List<ProjectAbnormalStatusBean> pasbList = new ArrayList<ProjectAbnormalStatusBean>();
		try {
			pasbList = projectService.stareOneStare(user);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", pasbList);
	}
	
	/**
	 *  专享服务获取项目列表
	 * @return
	 */
	@RequestMapping("/getApplyProjects")
	@ResponseBody
	public ViewData getProjectApply(){
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		try {
			List<ProjectBean> data = projectApplyService.batchQuery(1, 1000, user);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "获取失败");
		}
	}
	/**
	 *  申请项目
	 * @return
	 */
	@RequestMapping("/applyProject")
	@ResponseBody
	public ViewData applyProject(Integer projectId,String token){
		
		if(null == projectId|| null == token || token.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = (UserBean)LoginHelper.getCurrentUser(token);
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		ProjectApplyBean projectApplyBean = new ProjectApplyBean();
		projectApplyBean.setProjectId(projectId);
		projectApplyBean.setApplyTime(new Date());
		projectApplyBean.setUserId(user.getId());
		projectApplyBean.setStatus(StatusConstant.PROJECT_APPLY_NONDATA);
		Integer isSuccess = projectApplyService.add(projectApplyBean);
		if(isSuccess == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "项目已经申请,请等待资料录入");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "申请成功");
	}
	
	
	
	
	
	
	
	
}
