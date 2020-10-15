package com.woniu.soft.mapper;

import com.woniu.soft.entity.MedAdvice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface MedAdviceMapper extends BaseMapper<MedAdvice> {
	Integer selectSumPtotal(Integer id);
	Integer selectSumDtotal(Integer id);	
}
