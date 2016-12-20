package com.magic.wogia.controller.app;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.CompanyBean;
import com.magic.wogia.bean.EstateBean;
import com.magic.wogia.bean.OfficeBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.bean.WaterWorkBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.huanxin.HuanXinUtil;
import com.magic.wogia.service.CompanyService;
import com.magic.wogia.service.EstateService;
import com.magic.wogia.service.OfficeService;
import com.magic.wogia.service.OrderService;
import com.magic.wogia.service.UserService;
import com.magic.wogia.service.WaterWorkService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.PushMessageUtil;
import com.magic.wogia.util.RoleConstant;
import com.magic.wogia.util.SMSCode;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;


@Controller
@RequestMapping("/app/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private CompanyService companyService;
	@Resource
	private OfficeService officeService;
	@Resource
	private WaterWorkService waterWorkService;
	@Resource
	private EstateService estateService;
	@Resource
	private OrderService orderService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 登录app端
	 * @param userBean
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ViewData login(String mobile ,String password,Integer deviceType,String deviceToken){
		if(null == mobile || null == password){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		UserBean user = userService.mobileLogin(mobile, password);
		if(null == user){
			return buildFailureJson(StatusConstant.Fail_CODE, "用户名或者密码错误");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
		}
		
		if(RoleConstant.PLATFORM_MANAGER.equals(user.getRoleId())){
			user.setUnitName(StatusConstant.HEAD_COMPANY_NAME);
			user.setUnitNumber("001");
			user.setIdType(StatusConstant.PLATFORM_USER);
		}
		if(RoleConstant.AREA_MANAGER.equals(user.getRoleId()) || RoleConstant.AREA_ASSISTANT_MANAGER.equals(user.getRoleId()) || RoleConstant.AREA_TECH.equals(user.getRoleId())){
			CompanyBean company = companyService.queryCompany(user.getCompanyId());
			user.setUnitName(company.getCompanyName());
			user.setUnitNumber(company.getNumber());
			user.setIdType(StatusConstant.AREA_USER);
		}
		if(RoleConstant.OFFICE_WORKER.equals(user.getRoleId())){
			OfficeBean office = officeService.queryOffice(user.getCompanyId());
			user.setUnitName(office.getOfficeName());
			user.setUnitNumber(office.getNumber());
			user.setIdType(StatusConstant.OFFICE_USER);
		}
		if(RoleConstant.WATER_FACTORY_WORKER.equals(user.getRoleId())){
			WaterWorkBean water = waterWorkService.queryWaterWork(user.getCompanyId());
			user.setUnitName(water.getWaterworkName());
			user.setUnitNumber(water.getNumber());
			user.setIdType(StatusConstant.WATER_FACTORY_USER);
		}
		if(RoleConstant.ESTATE_WORKER.equals(user.getRoleId())){
			EstateBean estate = estateService.queryEstate(user.getCompanyId());
			user.setUnitName(estate.getEstateName());
			user.setUnitNumber(estate.getNumber());
			user.setIdType(StatusConstant.ESTATE_USER);
		}
		if(StatusConstant.NON_LOGINED.equals(user.getStatus())){
			return buildSuccessViewData(StatusConstant.FRTST_LOGIN, "该用户第一次登录", user);
		}
		user.setDeviceType(deviceType);
		user.setDeviceToken(deviceToken);
		user.setUpdatetime(new Date());
		String token = LoginHelper.addToken(user);
		user.setToken(token);
//		PushMessageUtil.pushMessages(user, "登录成功", null);
		userService.addOrUpdUser(user);
		return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "登录成功", user);
	}
	
	
	
	
	/**
	 * 更新用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value="/updateUser",method = RequestMethod.POST)
	@ResponseBody
	public ViewData updateUser(Integer idType,String realName,String mobile,String userName,String qq,Integer gender,String avatar,String unitName,String unitNumber,Integer deviceType,String deviceToken ){
		if(null == idType || null == userName || null == realName || realName.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = userService.queryByUserName(userName);
		if(null == user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
		}
		UserBean userBean = new UserBean();
		if(null != user.getMobile() && user.getMobile().trim().length() > 10){
			if(!mobile.equals(user.getMobile())){
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}else{
			Integer count = userService.countUser(mobile, null);
			if(count != 0){
				return buildFailureJson(StatusConstant.OBJECT_EXIST, "手机号已经被注册");
			}else{
				userBean.setMobile(mobile);
				user.setMobile(mobile);
			}
		}
		if(null != deviceType){
			userBean.setDeviceType(deviceType);
			user.setDeviceType(deviceType);
		}
		if(null != deviceToken){
			userBean.setDeviceToken(deviceToken);
			user.setDeviceToken(deviceToken);
		}
		if(null != avatar){
			userBean.setAvatar(avatar);
			user.setAvatar(avatar);
		}
		userBean.setId(user.getId());
		userBean.setQq(qq);
		userBean.setGender(gender);
		userBean.setStatus(StatusConstant.PASS);
		userBean.setRealName(realName);
		
		try {
			//添加进入缓存
			user.setRealName(realName);
			user.setQq(qq);
			
			user.setGender(gender);
			user.setUnitName(unitName);
			user.setUnitNumber(unitNumber);
			user.setStatus(StatusConstant.PASS);
			user.setIdType(idType);
			String token = LoginHelper.addToken(user);
			userBean.setToken(token);
			PushMessageUtil.pushMessages(user, "登录成功", null);
			// 注册环信
			boolean isSuccess = HuanXinUtil.register("wogia_" + user.getUserName(), "wogia_huanxin_pwd!123?",user.getRealName());
			if (!isSuccess) {
				return buildFailureJson(StatusConstant.Fail_CODE, "环信注册失败");
			}
			userService.addOrUpdUser(userBean);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE,ErrorMessage.Fail);
		}
		return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "操作成功", user);
	}
	
	/**
	 *  修改用户信息
	 * @param mobile
	 * @param qq
	 * @param gender
	 * @param avatar
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public ViewData updateUserInfo(String mobile,String qq,Integer gender,String avatar,Integer silence){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(user.getStatus().equals(StatusConstant.FROZEN)){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "帐号被冻结");
		}
		UserBean temp = new UserBean();
		temp.setId(user.getId());
		temp.setUpdatetime(new Date());
		if(null != mobile){
			user.setMobile(mobile);
			temp.setMobile(mobile);
		}
		if(null != qq){
			temp.setQq(qq);
			user.setQq(qq);
		}
		if(null != avatar ){
			temp.setAvatar(avatar);
			user.setAvatar(avatar);
		}
		if(null != gender){
			temp.setGender(gender);
			user.setGender(gender);
		}
		if(null != silence){
			temp.setSilence(silence);
			user.setSilence(silence);
		}
		try {
			LoginHelper.replaceToken(user.getToken(), user);
			userService.addOrUpdUser(temp);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "更新成功", user);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "更新失败");
		}
	}
	
	/**
	 * 查询联系人
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping("/findContacts")
	@ResponseBody
	public ViewData findContacts(Integer roleId){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			list = userService.findContacts(user, roleId,null,null,1);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE,ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", list);
	}
	
	
	/**发送验证码*/
	@RequestMapping("/sendCode")
	@ResponseBody
	public ViewData sendCode(String mobile){
		String code = SMSCode.createRandomCode();
		boolean isSuccess = SMSCode.sendMessage(code, mobile);
		if(isSuccess){
			Map<String, String> data = new HashMap<String, String>();
			data.put("valiCode", code);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "发送成功", data);
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "发送失败");
	}
	
	
	/**发送验证码验证重*/
	@RequestMapping("/sendCodeLogin")
	@ResponseBody
	public ViewData sendCodeLogin(String mobile){
		
		if(null == mobile || mobile.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = userService.queryByMobile(mobile);
		if(null != user){
			return buildFailureJson(StatusConstant.OBJECT_EXIST, "手机号已经被注册");
		}
		String code = SMSCode.createRandomCode();
		boolean isSuccess = SMSCode.sendMessage(code, mobile);
		if(isSuccess){
			Map<String, String> data = new HashMap<String, String>();
			data.put("valiCode", code);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "发送成功", data);
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "发送失败");
	}
	
	/***
	 *  找回密码
	 * @return
	 */
	@RequestMapping("/forgetPwd")
	@ResponseBody
	public ViewData forgetPassword(String mobile,String password){
		
		if(null == mobile || null == password ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = userService.mobileLogin(mobile,null);
		if(null == user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "手机号不存在");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
		}
		if(password.equals(user.getPwd())){
			return buildFailureJson(StatusConstant.Fail_CODE, "与原密码相同");
		}
		UserBean tempUser = new UserBean();
		tempUser.setId(user.getId());
		tempUser.setPwd(password);
		userService.addOrUpdUser(tempUser);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}
	
	/**修改密码*/
	@RequestMapping("/updatePwd")
	@ResponseBody
	public ViewData updatePassword(String oldPwd,String newPwd){
		
		if(null == oldPwd || null == newPwd){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(oldPwd.equals(newPwd)){
			return buildFailureJson(StatusConstant.Fail_CODE, "原始密码和新密码相同");
		}
		if(!oldPwd.equals(user.getPwd())){
			return buildFailureJson(StatusConstant.Fail_CODE, "原密码不对");
		}
		UserBean temp = new UserBean();
		temp.setId(user.getId());
//		LoginHelper.clearToken();
		temp.setPwd(newPwd);
//		String token = LoginHelper.addToken(user);
//		user.setToken(token);
		userService.addOrUpdUser(temp);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}
	
	/**
	 *  注销登录
	 * @return
	 */
	@RequestMapping("/loginOut")
	@ResponseBody
	public ViewData loginOut(){
		
		UserBean user = LoginHelper.getCurrentUser();
		if(null != user){
			LoginHelper.clearToken();
			UserBean temp = new UserBean();
			temp.setId(user.getId());
			temp.setDeviceToken("");
			userService.addOrUpdUser(temp);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	@RequestMapping("/getInfo")
	@ResponseBody
	public ViewData getInfo(){
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "未登录");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", user);
	}
	
	/**
	 *  获取技术
	 * @return
	 */
	@RequestMapping("/getTech")
	@ResponseBody
	public ViewData getTech(Integer orderId){
		if(null == orderId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = userService.queryByOrderId(orderId);
		Integer userType = null;
		if(RoleConstant.AREA_MANAGER.equals(user.getRoleId()) || RoleConstant.AREA_ASSISTANT_MANAGER.equals(user.getRoleId()) || 
				RoleConstant.AREA_TECH.equals(user.getRoleId()) || RoleConstant.OFFICE_WORKER.equals(user.getRoleId())){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		if(RoleConstant.WATER_FACTORY_WORKER.equals(user.getRoleId())){
			userType = StatusConstant.WATER_FACTORY_USER;
		}
		if(RoleConstant.ESTATE_WORKER.equals(user.getRoleId())){
			userType = StatusConstant.ESTATE_USER;
		}
		List<UserBean> data = new ArrayList<UserBean>();
		if(null != userType){
			data = userService.queryTech(userType, user.getCompanyId(), orderId);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  获取用户的简单信息，环信使用
	 * @param userName
	 * @return
	 */
	@RequestMapping("/batchGetUserInfo")
	@ResponseBody
	public ViewData getUserInfoToHuanxin(String userNames){
		
		if(null == userNames || userNames.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		try {
			String[] users = userNames.split(",");
			List<String> userStrs = new ArrayList<String>();
			for (int i = 0; i < users.length; i++) {
				userStrs.add(users[i]);
			}
			List<String> userNameList = new ArrayList<String>();
			for (String str : userStrs) {
				userNameList.add(str.split("_")[1]);
			}
			List<UserBean> data = new ArrayList<UserBean>();
			if(userNameList.size() > 0){
				data = userService.batchQueryUserInfo(userNameList);
			}
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "获取失败");
		}
	}
	
}
