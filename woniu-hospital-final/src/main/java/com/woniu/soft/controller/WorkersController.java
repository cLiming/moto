package com.woniu.soft.controller;


import com.woniu.soft.entity.Workers;
import com.woniu.soft.service.DeptService;
import com.woniu.soft.service.RoleService;
import com.woniu.soft.service.WorkersService;
import com.woniu.soft.utils.JSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
	@Resource
	private RoleService roleService;
	@Resource
	private DeptService deptService;
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
	public JSONResult login(Workers worker, boolean rememberMe) throws Exception{
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

	//获取员工
	@RequestMapping("getworker")
	public JSONResult getoneworker(Workers worker) throws Exception{
		System.err.println("worker"+worker.getId());
		return new JSONResult("200","success",workersService.getworker(worker),null);
	}
	//通过id获取员工
	@RequestMapping("getoneworker")
	public JSONResult getworker(Workers worker) throws Exception{
		return new JSONResult("200","success",null,workersService.getoneworker(worker));
	}
	//获取所有角色
	@RequestMapping("getRole")
	public JSONResult getRole() throws Exception{
		return new JSONResult("200","success",roleService.getRole(),null);
	}
	//获取所有部门
	@RequestMapping("getdept")
	public JSONResult getdept() throws Exception{
		System.err.println("deptService.getdept()"+deptService.getdept());
		return new JSONResult("200","success",deptService.getdept(),null);
	}
	//删除一个员工
	@RequestMapping("deleteWorker")
	public JSONResult deleteWorker(Integer id) throws Exception{
		workersService.deleteWorker(id);
		return new JSONResult("200","success",null,null);
	}
	//新增一个员工
	@RequestMapping("insertworkers")
	public JSONResult insertworker(Workers worker) throws Exception{
		//System.err.println(worker);
		workersService.insertworker(worker);
		return new JSONResult("200","success",null,null);
	}
	//修改员工信息
	@RequestMapping("updateworkers")
	public JSONResult updateworkers(Workers worker) throws Exception{
		System.err.println(worker);
		workersService.updateworkers(worker);
		return new JSONResult("200","success",null,null);
	}

	//修改员工role信息
	@RequestMapping("updateWorkerRoleButton")
	public JSONResult updateWorkerRoleButton(Workers worker) throws Exception{
		System.err.println(worker);
		workersService.updateWorkerRoleButton(worker);
		return new JSONResult("200","success",null,null);
	}
}

