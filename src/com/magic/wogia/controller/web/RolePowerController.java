package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.PermissionBean;
import com.magic.wogia.bean.RoleBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.PermissionService;
import com.magic.wogia.service.RolePowerService;
import com.magic.wogia.service.RoleService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 *  角色 权限 控制层
 * @author QimouXie
 *
 */
@RequestMapping("/web/rolePower")
@Controller
public class RolePowerController extends BaseController {
	
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private RolePowerService rolePowerService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/getRolePower")
	@ResponseBody
	public ViewData  getRolePower(HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		List<RoleBean> roles = roleService.queryByUserId(user);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", roles);
	}
	
	@RequestMapping("/getPower")
	@ResponseBody
	public ViewData getPowerByRole(Integer roleId){
		
		if(null == roleId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<PermissionBean> data = permissionService.queryPowerByRole(roleId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	
	/***修改角色对应的权限*/
	@RequestMapping("/updateRolePower")
	@ResponseBody
	public ViewData updatePower(HttpServletRequest req){
		
		String strRoleId = req.getParameter("roleId");
		if(null == strRoleId){
			return buildSuccessCodeJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Integer roleId = Integer.parseInt(req.getParameter("roleId"));
		if(null == roleId || roleId == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "参数异常");
		}
		String[] roleIds = req.getParameterValues("powerId");
		if(null == roleIds){
			roleIds = new String[0];
//			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "没有修改");
		}
		List<Integer> ids = new ArrayList<Integer>();
		/*if(RoleConstant.PLATFORM_MANAGER == roleId){
			ids.add(r);
		}*/
		for (int i = 0; i < roleIds.length; i++) {
			Integer tempRoleId = Integer.parseInt(roleIds[i]);
			ids.add(tempRoleId);
		}
		try {
			rolePowerService.updatePower(roleId, ids);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
