package com.woniu.soft.service;

import com.woniu.soft.entity.ReturnApplication;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface ReturnApplicationService extends IService<ReturnApplication> {
	List<ReturnApplication> selectListByStatusEq1()throws Exception;
	void updateStatusEq3(Integer id)throws Exception;
}
