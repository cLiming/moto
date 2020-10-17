package com.woniu.soft.controller;


import com.woniu.soft.service.RoleService;
import com.woniu.soft.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("getRolePermisssion")
    public JSONResult getRolePermission(){
        System.err.println(roleService.selectRolePermission());
        return new JSONResult("200","success",roleService.selectRolePermission(),null);
    }
}

