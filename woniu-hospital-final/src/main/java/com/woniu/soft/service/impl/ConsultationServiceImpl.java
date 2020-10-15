package com.woniu.soft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.Consultation;
import com.woniu.soft.mapper.ConsultationMapper;
import com.woniu.soft.service.ConsultationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@Service
public class ConsultationServiceImpl extends ServiceImpl<ConsultationMapper, Consultation> implements ConsultationService {

	@Override
	public List<Consultation> selectByStatusEq0() throws Exception {
		QueryWrapper<Consultation> queryWrapper = new QueryWrapper<Consultation>();
		queryWrapper.eq("status", 0);
		return 	this.list(queryWrapper);
	}

}
