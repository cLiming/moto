package com.woniu.soft.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

public class LoginFilter extends UserFilter{
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		//响应json数据
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("{\"code\":\"404\",\"message\":\"未登录\"}");
	}
}
