package com.woniu.soft.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.DrugEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public interface DrugService extends IService<Drug> {
	void updateDrugUp(Integer drugId,Integer number)throws Exception;
	void updateDrugDown(Integer drugId,Integer number)throws Exception;
	void updateDrugPrice(Integer drugId,double price)throws Exception;
	List<DrugEntity> selectDrugEntity(Integer id) throws Exception;
	Page selectDrugs(Integer id,String name,Integer pageIndex,Integer PageNum)throws Exception;

	List<Drug> getDrugs();

}
