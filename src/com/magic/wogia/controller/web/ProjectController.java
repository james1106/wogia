package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ApplyContextBean;
import com.magic.wogia.bean.CSProjectBean;
import com.magic.wogia.bean.ProjectApplyBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ApplyContextService;
import com.magic.wogia.service.ProjectApplyService;
import com.magic.wogia.service.ProjectService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：项目控制器
 * 编写人员：lzh
 * 时间：2016年9月6日上午11:28:16
 */
@Controller
@RequestMapping("/web/project")
public class ProjectController extends BaseController{

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectApplyService projectApplyService;
	@Resource
	private ApplyContextService applyContextService;
	/**
	 * 添加或者更新项目信息
	 * @param project 项目对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdProject",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdProject(ProjectBean project){
		try {
			projectService.addOrUpdProject(project);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}

	@RequestMapping(value="/stareOneStareTask",method=RequestMethod.POST)
	@ResponseBody
	public ViewData stareOneStareTask(ProjectBean project){
		try {
			projectService.stareOneStareTask();
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	/**
	 * 查询项目
	 * @param project 项目对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findProject")
	@ResponseBody
	public ViewData findProject(ProjectBean project,Integer pageNum,Integer pageSize,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			projectList = projectService.findProject(project,pageNum,pageSize,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, projectList);
	}
	
	/**
	 * 查询未绑定的 项目  status=0
	 * @return
	 */
	@RequestMapping("/findStatus")
	@ResponseBody
	public ViewData findStatus(){
		List<CSProjectBean> scList = new ArrayList<CSProjectBean>();
		try { 
			scList = projectService.findStatus();
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
	@RequestMapping(value="/updCSProject",method=RequestMethod.POST)
	@ResponseBody
	public ViewData updCSProject(CSProjectBean csProject){
		try { 
			projectService.updCSProject(csProject);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildFailureJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * web端申请列表
	 * @param user
	 * @return
	 */
	@RequestMapping("/findProjectApply")
	@ResponseBody
	public ViewData findProjectApply(HttpServletRequest req,Integer pageNum,Integer pageSize){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		List<ProjectApplyBean> paList = new ArrayList<ProjectApplyBean>();
		try { 
			paList = projectApplyService.findProjectApply(user,pageNum,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, paList);
	}
	
	
	/**
	 * 查询详情 申请文字
	 * @param user
	 * @return
	 */
	@RequestMapping("/findApplyContext")
	@ResponseBody
	public ViewData findApplyContext(HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		ApplyContextBean beans = new ApplyContextBean();
		try { 
			beans = applyContextService.findApplyContext();
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, beans);
	}
	
	/**
	 * 添加/更新 申请文字
	 * @param user
	 * @return
	 */
	@RequestMapping("/addOrUpdApplyContext")
	@ResponseBody
	public ViewData addOrUpdApplyContext(HttpServletRequest req,ApplyContextBean beans){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		try { 
			applyContextService.addOrUpdApplyContext(beans);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, beans);
	}
	
	
	
	/**
	 * 删除项目
	 * @param user
	 * @return
	 */
	@RequestMapping("/deleteProjectById")
	@ResponseBody
	public ViewData deleteProjectById(HttpServletRequest req,Integer projectId){
		if (projectId==null) {
			return buildFailureJson(StatusConstant.Fail_CODE, "项目id不能为空");
		}
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		try { 
			projectService.deleteProjectById(projectId);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildFailureJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	} 
}
