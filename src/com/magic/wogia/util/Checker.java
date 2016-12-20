package com.magic.wogia.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.magic.wogia.mapper.IUserMapper;

/**
 * 
 * 功能：返回错误信息
 * 编写人员：lzh
 * 时间：2016年9月1日上午9:46:11
 */
public class Checker {
	private String errorDetail;
	private boolean ok = true;
	
	public IUserMapper userMapper;
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getErrorDetail() {
		String detail = errorDetail;
		resetResult();// 错误结果仅返回一次
		return detail;
	}
	/** 是否是合格的经纬度*/
	public boolean isPoint(Double longitude,Double latitude)
	{
		if(longitude ==null || longitude<0){
			this.setErrorDetail("经度为空了");
			return false;
		}
		if(latitude ==null || latitude<0){
			this.setErrorDetail("纬度为空了");
			return false;
		}
		return true;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	private void resetResult() {
		ok = true;
		errorDetail = "";
	}
	
	public boolean isMobile(String mobile) {
		resetResult();
		final String reg = "^[1][0-9]{10}$";
		if (mobile == null || !mobile.matches(reg)) {
			errorDetail = "手机号码格式不正确，请您重新录入";

			ok = false;
		}

		return ok;
	}
	public boolean isSex(Integer sex) {
		resetResult();
		if (sex == null) {
			errorDetail = "性别格式不正确，请您重新录入";
			ok = false;
		}
		return ok;
	}
	public boolean isPhone(String phone) {
		resetResult();
		final String reg = "^[1][0-9]{10}$";
		if (phone == null || !phone.matches(reg)) {
			errorDetail = "手机号码格式不正确，请您重新录入";

			ok = false;
		}

		return ok;
	}
	
	public boolean mobileIsPhone(String mobile){
		final String reg = "^[1][0-9]{10}$";
		if (mobile == null || !mobile.matches(reg)){
			return false;
		}
		return true;
	}
	
	public boolean isEmail(String email) {
		resetResult();

		final String reg = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (email != null && !"".equals(email)) {
			if (!email.matches(reg)) {
				errorDetail = "电子邮箱格式不正确，请您重新录入";
				ok = false;
				return ok;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			if (userMapper.findUser(map).size()>0) {
				errorDetail = "电子邮箱["+email+"]已存在，请您重新录入";
				ok = false;
				return ok;
			}
		}
		return ok;
	}
	
	public boolean isNickname(String nickname) {
		resetResult();

		if (nickname == null || nickname.length() > 255 || nickname.length() < 1) {
			errorDetail = "昵称格式不正确，请您重新录入";

			ok = false;
		}

		return ok;
	}
	public boolean isUserId(Integer userId) {
		resetResult();

		if (userId == null || userId<=0 ) {
			errorDetail = "用户ID格式不正确，请您重新录入";

			ok = false;
		}

		return ok;
	}
	public boolean isUserId(String userId) {
		resetResult();

		if (userId == null || userId.length() ==0 ) {
			errorDetail = "用户ID格式不正确，请您重新录入";

			ok = false;
		}

		return ok;
	}
	public boolean isSex(String sex) {
		resetResult();
		if (sex == null || (!"m".equals(sex) && !"f".equals(sex))) {
			errorDetail = "性别不正确，请您重新录入";
			ok = false;
		}

		return ok;
	}

	public boolean isIdCard(String idCard) {
		resetResult();
		final String reg = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
		if (idCard != null && !"".equals(idCard)) {
			if (!idCard.matches(reg)) {
				errorDetail = "身份证号码不正确，请您重新录入";
				ok = false;
			}
		}
		return ok;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public boolean isTime(String time) {
		resetResult();
		if (time == null) {
			errorDetail = "日期不同为空，请您重新录入";
			ok = false;
		} else {

			try {
				Date dateBirthday = new Date(time);
			} catch (Exception e) {
				errorDetail = "日期格式不正确，请您重新录入";
				return ok;
			}
		}

		return ok;
	}


	

	public boolean isNewUser(String mobile) {
		resetResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		if (userMapper.findUser(map).size()>0) {
			errorDetail = "用户名[" + mobile + "]已被注册";
			ok = false;
		}
		if (userMapper.findUser(map).size() <= 0) {
			errorDetail = "用户名[" + mobile + "]还未注册，请您先注册";
			ok = false;
		}
		return ok;
	}
	
	public boolean isNewIdCard(String idCard) {
		resetResult();
		if (idCard != null && !"".equals(idCard)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idCard", idCard);
			if (userMapper.findUser(map).size()>0) {
				errorDetail = "身份证号[" + idCard + "]已被占用，请确认您的信息";
				ok = false;
			}
		}
		return ok;
	}
/*
	public boolean isUserExist(String mobile) {
		resetResult();
		if (userMapper.isUserExist(mobile) <= 0) {
			errorDetail = "用户名[" + mobile + "]还未注册，请您先注册";
			ok = false;
		}
		return ok;
	}	*/

	public boolean isVerifyCodeScenario(int scenario) {
		resetResult();
		//if (scenario < 0 || scenario > 1) {
		if (scenario < 0 || scenario > 0) {
			errorDetail = "不支持的验证码使用场景";
			ok = false;
		}
		return ok;
	}
/*
	public boolean isIllnessType(int illnessType) {
		resetResult();
		if (illnessType < IllnessType.Illness_Tension
				|| illnessType > IllnessType.Illness_Glycaemia) {
			errorDetail = "历史数据类型错误，请您重新录入";

			ok = false;
		}

		return ok;
	}*/


	public static ReturnObject CheckReturnObjectResult(
			String preStringForFailed, ReturnObject ro, String exception) {
		if (ro != null) {
			if (ro.getStatus() != 0) {
				ro.setStatusError();
				ro.setDetail(preStringForFailed + ": " + ro.getDetail());
			} else {
				if (null != exception) {
					ro.setStatusError();
					ro.setDetail(preStringForFailed + ": " + exception);
				}
			}
		}
		return ro;
	}			

	public static ReturnObject CheckReturnObjectResult(
			String preStringForFailed, ReturnObject ro, String exception,
			String tipForSuccess) {
		if (ro != null) {
			ro = CheckReturnObjectResult(preStringForFailed, ro, exception);
			if (ro.getStatus() == 0 && ro.getDetail().isEmpty()) {
				ro.setDetail(tipForSuccess);
			}
		}
		return ro;
	}
	
	
	public static ReturnObject CheckReturnObjectResult(
			String preStringForFailed, ReturnObject ro) {
			ro.setStatusError();	
			ro.setDetail(preStringForFailed);
			return ro;
	}
	


	public boolean isPlatfrom(String platform) {
		resetResult();
		if (platform == null || platform.length() < 1) {
			ok = false;
		} else {
			char p = platform.toLowerCase().toCharArray()[0];
			if (p != 'i' && p != 'a') {
				ok = false;
			}
		}
		if (!ok) {
			errorDetail = "手机平台不正确，请您重新录入";
		}

		return ok;
	}

	public boolean isDate(String date){
		final String reg="^((?:19|20)\\d\\d)-(0[1-9]|1[012]|[1-9])-(0[1-9]|[12][0-9]|3[01]|[1-9])$";
		if(!date.matches(reg)){
			errorDetail="日期格式不正确!";
			ok=false;
		}
		return ok;
	}
	
	public boolean iscompanyName(String companyName){
		if(null==companyName||"".equals(companyName)){
			errorDetail="企业名不能为空!";
			ok=false;
		}else if(companyName.length()<2||companyName.length()>100){
			errorDetail="企业名必需大于2个字符，并且小于100个字符!";
			ok=false;
		}
		return ok;
	}
	
	public boolean isDescription(String description){
		if(null==description||"".equals(description)){
			errorDetail="企业描述不能为空!";
			ok=false;
		}else if(description.length()<10||description.length()>1000){
			errorDetail="企业描述必需大于10个字符，并且小于1000个字符!";
			ok=false;
		}
		return ok;
	}
	
	/**
	 * 通用非空判断
	 * @param params
	 * @param detail
	 * @return
	 */
	public boolean isNotEmpty(Object params,String detail){
		if(null==params||"".equals(params.toString().trim())){
			errorDetail=detail;
			ok=false;
		}
		return ok;
	}
	public boolean isNotEmpty(Object params){
		if(null == params||"".equals(params.toString().trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 通用判断长度
	 * @param params
	 * @param detail
	 * @return
	 */
	public boolean isLength(Object params,int minLength,int maxLength,String minDetail,String maxDetail){
		if(params.toString().length()<minLength){
			errorDetail=minDetail;
			ok=false;
		}
		if(params.toString().length()>maxLength){
			errorDetail=maxDetail;
			ok=false;
		}
		return ok;
	}
	
	/**
	 * 判断企业账号是否被注册
	 * @param userName
	 * @return
	 */
	public void setAllError(){
		setOk(false);
		setErrorDetail("操作失败,如频繁出现该错误,请与管理员联系!");
	}
	
	
	/**
	 * 字符串对比
	 * @return
	 */
	public boolean checkStrEq(String one,String two,String detail){
		if(!one.equals(two)){
			ok = false;
			setErrorDetail(detail);
		}
		return ok;
	}
	
	public boolean checkerGexin(String info){
		if(info.equals("connect failure")){
			ok = false;
			setErrorDetail("链接个推失败!");
		}else if(info.equals("404")){
			ok = false;
			setErrorDetail("该用户没有注册或者没有在手机上登录过!");
		}else if(info.equals("TargetListIsNullOrSizeIs0")){
			ok = false;
			setErrorDetail("目标用户列表为空!");
		}else if(info.equals("PushTotalNumOverLimit")){
			ok = false;
			setErrorDetail("推送总数超限!");
		}else if(info.equals("flow_exceeded")){
			ok = false;
			setErrorDetail("接口流量超限!");
		}else if(info.equals("TokenMD5NoUsers")){
			ok = false;
			setErrorDetail("未查找到用户，列表内无有效用户!");
		}else if(info.equals("AppidError")){
			ok = false;
			setErrorDetail("cid绑定的appId和sdk上传的appId不一致!");
		}else if(info.equals("OtherError")){
			ok = false;
			setErrorDetail("其他未知错误!");
		}
		return ok;
	}


}
