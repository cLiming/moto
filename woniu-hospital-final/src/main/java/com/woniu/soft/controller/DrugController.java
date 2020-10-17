package com.woniu.soft.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniu.soft.entity.Workers;
import com.woniu.soft.service.DrugService;
import com.woniu.soft.utils.JSONResult;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/drug")
public class DrugController {
	@Resource
	private DrugService drugService;
	//获取所有药品
	@RequestMapping("getDrugs")
	public JSONResult getDrugs() throws Exception{
		return new JSONResult("200","success",drugService.getDrugs(),null);
	}
}

