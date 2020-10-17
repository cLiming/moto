package com.woniu.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.mapper.MedAdviceMapper;
import com.woniu.soft.service.MedAdviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Service
public class MedAdviceServiceImpl extends ServiceImpl<MedAdviceMapper, MedAdvice> implements MedAdviceService {
	@Resource
	private MedAdviceMapper medAdviceMapper;


	@Override
	public Page selectByWidLimit(Integer wid, Integer pageIndex, Integer pageNum) throws Exception {
		QueryWrapper<MedAdvice> MedAdviceWrapper = new QueryWrapper<MedAdvice>();
		Page<MedAdvice> page = new Page<MedAdvice>(pageIndex, pageNum);
		MedAdviceWrapper.eq("w_id", wid);
		MedAdviceWrapper.eq("status", 0);
		return this.page(page, MedAdviceWrapper);
	}
	
	@Override
	public List<MedAdvice> selectListByStatus(Integer status) throws Exception {
		QueryWrapper<MedAdvice> MedAdviceWrapper = new QueryWrapper<MedAdvice>();
		MedAdviceWrapper.eq("status", 0);
		return this.list(MedAdviceWrapper);
	}

	@Override
	public List<MedAdvice> getAdvice(Page<MedAdvice> page) {
		QueryWrapper<MedAdvice> MedAdviceWrapper = new QueryWrapper<MedAdvice>();
		MedAdviceWrapper.eq("status", 0);
		this.page(page, MedAdviceWrapper);
		List<MedAdvice> list = page.getRecords();
		return list;
	}

	@Override
	public void updateAdvice(MedAdvice medAdvice) {
		// TODO Auto-generated method stub
		UpdateWrapper<MedAdvice> updateWrapper = new UpdateWrapper<>();
		if(medAdvice!=null&&medAdvice.getId()!=null) {
			updateWrapper.eq("id", medAdvice.getId());
			updateWrapper.set("status", 1);
			medAdviceMapper.update(null, updateWrapper);
		}
	}
}
