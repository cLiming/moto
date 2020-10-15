package com.woniu.soft.exception;

import javax.validation.ValidationException;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.woniu.soft.utils.JSONResult;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//定义不同的方法处理不同类型的异常
	@ExceptionHandler({BindException.class,ValidationException.class})
	public JSONResult handlerBindException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","参数异常",null,null);
	}
	@ExceptionHandler({IncorrectCredentialsException.class})
	public JSONResult IncorrectCredentialsException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","密码错误",null,null);
	}
	@ExceptionHandler({UnknownAccountException.class})
	public JSONResult handlerUnkownAccountException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","用户名不存在",null,null);
	}
	@ExceptionHandler({AuthorizationException.class})
	public JSONResult handlerAuthorizationException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","权限不足",null,null);
	}
	@ExceptionHandler({Exception.class})
	public JSONResult handlerException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","服务器异常",null,null);
	}
	
}
