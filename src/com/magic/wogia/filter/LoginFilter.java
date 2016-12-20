package com.magic.wogia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.wogia.bean.UserBean;
import com.magic.wogia.util.LoginHelper;





//@WebFilter("/web/*")
public class LoginFilter implements Filter {
	
	

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		HttpSession session = req.getSession();
		UserBean user = (UserBean)session.getAttribute(LoginHelper.USER_SESSION);
		if(null == user){
			String loginPath = req.getContextPath()+"/"+LoginHelper.LOGIN_PATH;
			resp.sendRedirect(loginPath);
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
