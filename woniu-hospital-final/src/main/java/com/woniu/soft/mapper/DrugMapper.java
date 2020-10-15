package com.woniu.soft.mapper;

import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.DrugEntity;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface DrugMapper extends BaseMapper<Drug> {
	List<DrugEntity> selectDrugEntity(Integer id)throws Exception;
}
