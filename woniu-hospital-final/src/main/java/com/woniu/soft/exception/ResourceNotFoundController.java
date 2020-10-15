package com.woniu.soft.exception;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
@Controller
public class ResourceNotFoundController implements ErrorController{
	@Override
	public String getErrorPath() {
		//自定义错误映射地址
		return "/error";
	}
	@RequestMapping("error")
	public ModelAndView handler404(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
        	FastJsonJsonView jsonView = new FastJsonJsonView();
        	HashMap<String,String> map = new HashMap<String,String>();
        	map.put("code", "404");
        	map.put("message", "找不到资源");
        	jsonView.setAttributesMap(map);
        	view.setView(jsonView);
        } else {
        	view.setViewName("redirect:/error/404.html");
        }
        return view;
	}
}
