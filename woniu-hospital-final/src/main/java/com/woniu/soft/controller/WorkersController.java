package com.woniu.soft.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniu.soft.entity.Workers;
import com.woniu.soft.service.WorkersService;
import com.woniu.soft.utils.JSONResult;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/workers")
public class WorkersController {
	@Resource
	private WorkersService workersService;
	
	@RequestMapping("button")
	public JSONResult getButton(Integer pid) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		return new JSONResult("200","success",workersService.selectWorkerButtonByPid(worker.getId(),pid),null);
	}

	@RequestMapping("menu")
	public JSONResult getMenu() throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		return new JSONResult("200","success",workersService.selectWokerMenu(worker),null);
	}

	@RequestMapping("login")
	public JSONResult login(Workers worker,boolean rememberMe) throws Exception{
		//String password=MD5Utils.MD5EncodeUtf8(user.getPassword());
		worker.setPassword(worker.getPassword());
		//将登陆操作委托给shiro来完成
		UsernamePasswordToken token = new UsernamePasswordToken(worker.getTel(), worker.getPassword(),rememberMe);
		//调用shiro的login方法
		Subject subject = SecurityUtils.getSubject();
		//在未登陆的情况下才进行登录
		if(!subject.isAuthenticated()&&!subject.isRemembered()) {
			subject.login(token);
		}
		return new JSONResult("200","success",null,(Workers)subject.getPrincipal());
	}
	

}

