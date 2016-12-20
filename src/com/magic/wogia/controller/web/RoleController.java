package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.RoleBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.RoleService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：角色控制器
 * 编写人员：lzh
 * 时间：2016年9月5日上午11:06:21
 */
@Controller
@RequestMapping("/web/role")
public class RoleController extends BaseController{

	@Resource
	private RoleService roleService;
	
	/**
	 * 查询所有角色
	 * @return
	 */
	@RequestMapping(value="/findRole",method=RequestMethod.GET)
	@ResponseBody
	public ViewData findRole(HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<RoleBean> roleList = new ArrayList<RoleBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			roleList = roleService.findRole(user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, roleList);
	}
	
}
