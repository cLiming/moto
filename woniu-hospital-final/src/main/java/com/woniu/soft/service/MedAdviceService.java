package com.woniu.soft.service;

import com.woniu.soft.entity.MedAdvice;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface MedAdviceService extends IService<MedAdvice> {
	
	Page selectByWidLimit(Integer wid,Integer pageIndex,Integer pageNum) throws Exception;
	
	List<MedAdvice> selectListByStatus(Integer status) throws Exception;

	List<MedAdvice> getAdvice(Page<MedAdvice> page);
	void updateAdvice(MedAdvice medAdvice);

}
