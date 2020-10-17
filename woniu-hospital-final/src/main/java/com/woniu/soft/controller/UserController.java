package com.woniu.soft.controller;


import com.woniu.soft.entity.User;
import com.woniu.soft.service.UserService;
import com.woniu.soft.utils.JSONResult;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@RestController 
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //用户登录
    @RequestMapping("login")
    public JSONResult userLogin(User user) throws IncorrectCredentialsException {

        return new JSONResult("200","success",null,userService.userLogin(user));
    }
    //通过姓名查询用户 如果没有用户名 则查询全部
    @RequestMapping("getUser")
    public JSONResult getUser(User user) throws Exception{
        System.err.println(user);
        return new JSONResult("200","success",userService.getUser(user),null);
    }
    //充值页面 给用户充值
    @RequestMapping("updateBalance")
    public JSONResult updateBalance(User user) throws Exception{
        userService.updateBalance(user);
        return new JSONResult("200","success",null,null);
    }
    //查询状态为3或者为4的用户
    @RequestMapping("getUserAdmissionregistration")
    public JSONResult getUserAdmissionregistration(User user) throws Exception{
        return new JSONResult("200","success",userService.getUserAdmissionregistration(user),null);
    }
    //查询部门除了 7 8 9 的所有部门
    @RequestMapping("getdept")
    public JSONResult getdept() throws Exception{
        return new JSONResult("200","success",userService.getdept(),null);
    }
    //预约的时候 将用户的信息修改 状态改为1
    @RequestMapping("updateUserButton")
    public JSONResult updateUserButton(User user) throws Exception{
        userService.updateUserButton(user);
        return new JSONResult("200","success",null,null);
    }
}

