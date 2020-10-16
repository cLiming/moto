package com.woniu.soft.service;

import com.woniu.soft.entity.DrugHistory;
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
public interface DrugHistoryService extends IService<DrugHistory> {
	void saveDrugReturn(Integer drugId,Integer number,Integer uid,Integer wid)throws Exception;
	void saveDrugDeliver(Integer drugId,Integer number,Integer uid,Integer wid)throws Exception;
	void saveDrugInput(Integer drugId,Integer number,Integer wid)throws Exception;
	void saveDrugoutPut(Integer drugId,Integer number,Integer wid)throws Exception;
	Page selectDrugHistoryByOption(Integer drugId,Integer status,String minDate,String maxDate,Integer pageIndex,Integer pageNum)throws Exception;
	Page selectDrugHistoryAll(Integer pageIndex,Integer pageNum)throws Exception;
}
