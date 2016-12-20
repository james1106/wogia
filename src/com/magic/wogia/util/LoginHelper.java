package com.magic.wogia.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.magic.wogia.bean.UserBean;



public class LoginHelper {
	
	public static final String TOKEN = "token";
	private static final String SESSION_USER = "session_user";
	public static boolean isLogin=false;
	public static final String LOGIN_PATH="login.jsp";
	
	public static final String USER_SESSION = "user";
	
	
	public static UserBean getCurrentUser(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Object obj = req.getSession().getAttribute(SESSION_USER);
		if(null == obj){
			String token = req.getHeader("token");
			UserBean user = (UserBean)MemcachedUtil.getInstance().get(token);
			if(null == user){
				return null;
			}else{
				return user;
			}
		}
		return (UserBean)obj;
	}
	
	public static Object getCurrentUser(String token){
		Object tempObj = MemcachedUtil.getInstance().get(token);
		return tempObj;
	}
	
	public static void clearToken(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Object obj = req.getSession().getAttribute(SESSION_USER);
		if(null != obj ){
			req.getSession().invalidate();
		}
		String token = req.getHeader("token");
		MemcachedUtil.getInstance().delObj(token);
	}
	
	public static String addToken(UserBean user){
//		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		req.getSession().setAttribute(SESSION_USER, user);
		if(user.getToken() != null){
			Object obj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != obj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		UUID.randomUUID();
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}
	
	public static boolean  replaceToken(String token,Object obj){
		return MemcachedUtil.getInstance().replace(token, obj);
	}
	
/*	public static String addToken(GroupUser user){
//		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		req.getSession().setAttribute(SESSION_USER, user);
		if(user.getToken() != null){
			Object obj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != obj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}*/
	
	public static UserBean getCurrentAdmin(HttpServletRequest req){
		Object obj = req.getSession().getAttribute(USER_SESSION);
		if(null == obj){
			return null;
		}
		return (UserBean)obj;
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
}
