package com.woniu.soft.service.impl;

import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.mapper.PresDrugMapper;
import com.woniu.soft.service.PresDrugService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@Service
public class PresDrugServiceImpl extends ServiceImpl<PresDrugMapper, PresDrug> implements PresDrugService {

	@Override
	public List<PresDrug> selectListByPid(Integer pid) throws Exception {
		QueryWrapper<PresDrug> PresDrugWrapper = new QueryWrapper<PresDrug>();
		PresDrugWrapper.eq("pres_id", pid);
		List<PresDrug> DrugList = this.list(PresDrugWrapper);
		return DrugList;
	}

	@Override
	public void removeByPid(Integer pid) throws Exception {
		UpdateWrapper<PresDrug> PresDrugWrapper = new UpdateWrapper<PresDrug>();
		PresDrugWrapper.eq("pres_id", pid);
		this.remove(PresDrugWrapper);
	}

}
