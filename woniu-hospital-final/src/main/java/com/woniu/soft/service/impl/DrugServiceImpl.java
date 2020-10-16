package com.woniu.soft.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.DrugEntity;
import com.woniu.soft.mapper.DrugMapper;
import com.woniu.soft.service.DrugService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements DrugService {
	@Resource
	private DrugMapper drugMapper;

	@Override
	public void updateDrugDown(Integer drugId, Integer number) throws Exception {
		Drug drug = this.getById(drugId);
		drug.setNumber(drug.getNumber()-number);
		boolean update = this.updateById(drug);
		
	}


	@Override
	public void updateDrugUp(Integer drugId, Integer number) throws Exception {
		Drug drug = this.getById(drugId);
		drug.setNumber(drug.getNumber()+number);
		this.updateById(drug);
		
	}


	@Override
	public void updateDrugPrice(Integer drugId, double price) throws Exception {
		UpdateWrapper<Drug> wrapper = new UpdateWrapper<Drug>();
		wrapper.eq("id", drugId);
		wrapper.eq("price", price);
		this.update(wrapper);
	}


	@Override
	public List<DrugEntity> selectDrugEntity(Integer id) throws Exception {
		return drugMapper.selectDrugEntity(id);
	}


	@Override
	public Page selectDrugs(Integer id, String name, Integer pageIndex, Integer PageNum) throws Exception {
		QueryWrapper<Drug> wrapper = new QueryWrapper<Drug>();
		if(id==0&&name.equals("")) {
			Page<Drug> page = new Page<Drug>(pageIndex,PageNum);
			return this.page(page);
		}else {
			if(id!=0) {
				wrapper.eq("id", id);
			}
			if(!name.equals("")) {
				wrapper.likeRight(true,"name", name);
			}
			Page<Drug> page = new Page<Drug>(pageIndex,PageNum);
			return this.page(page, wrapper);
		}
	}



	
}
