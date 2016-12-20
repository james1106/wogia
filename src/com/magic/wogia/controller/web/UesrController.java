package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.UserService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.RoleConstant;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：web端用户控制器
 * 编写人员：lzh
 * 时间：2016年9月2日上午9:17:04
 */
@Controller
@RequestMapping("/web/user")
public class UesrController extends BaseController{

	@Resource
	private UserService userService;
	
	/**
	 * 登录
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ViewData login(HttpServletRequest req){
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		try {
			UserBean user = userService.login(account,pwd);
			if (user != null && user.getId() != null) {
				//账户被冻结
				if (user.getStatus() == StatusConstant.FROZEN) {
					return buildFailureJson(StatusConstant.FROZEN,ErrorMessage.FROZEN);
				}
				if(RoleConstant.PLATFORM_MANAGER.equals(user.getRoleId())){
					user.setUnitName(StatusConstant.HEAD_COMPANY_NAME);
					user.setUnitNumber("9527");
					user.setIdType(StatusConstant.PLATFORM_USER);
				}
				if(RoleConstant.AREA_MANAGER.equals(user.getRoleId()) || RoleConstant.AREA_ASSISTANT_MANAGER.equals(user.getRoleId()) || RoleConstant.AREA_TECH.equals(user.getRoleId())){
					user.setIdType(StatusConstant.AREA_USER);
				}
				if(RoleConstant.OFFICE_WORKER.equals(user.getRoleId())){
					user.setIdType(StatusConstant.OFFICE_USER);
				}
				if(RoleConstant.WATER_FACTORY_WORKER.equals(user.getRoleId())){
					user.setIdType(StatusConstant.WATER_FACTORY_USER);
				}
				if(RoleConstant.ESTATE_WORKER.equals(user.getRoleId())){
					user.setIdType(StatusConstant.ESTATE_USER);
				}
				req.getSession().setAttribute(LoginHelper.USER_SESSION, user);
				//添加进入缓存
//				String token = LoginHelper.addToken(user);
//				user.setToken(token);
				
				userService.addOrUpdUser(user);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, user);
			}else{
				return buildFailureJson(StatusConstant.Fail_CODE,ErrorMessage.LOGIN_FAIL);
			}
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
	}
	
	
	/**
	 * 根据条件查询用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	@ResponseBody
	public ViewData findUser(UserBean userBean,Integer pageNum,Integer pageSize,HttpServletRequest req) {
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			list = userService.findUser(userBean,pageNum,pageSize,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE,ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, list);
	}
	
	/**
	 * 查询联系人
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping("/findContacts")
	@ResponseBody
	public ViewData findContacts(Integer roleId,HttpServletRequest req,Integer waterWorkId,Integer projectId){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			list = userService.findContacts(user, roleId,waterWorkId,projectId,0);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE,ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", list);
	}
	
	/**
	 * 添加用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdUser", method = RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdUser(UserBean userBean){
		try {
			
			userService.addOrUpdUser(userBean);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildFailureJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	
	
	/**
	 *  注销登录
	 * @return
	 */
	@RequestMapping("/loginOut")
	@ResponseBody
	public ViewData loginOut(HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if(null != user){
			req.getSession().setAttribute(LoginHelper.USER_SESSION, null);
			LoginHelper.clearToken();
			UserBean temp = new UserBean();
			temp.setId(user.getId());
			temp.setDeviceToken("");
			userService.addOrUpdUser(temp);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "注销成功");
	}
	
	
	
	
	
}
