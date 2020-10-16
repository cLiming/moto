package com.woniu.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.DrugHistory;
import com.woniu.soft.mapper.DrugHistoryMapper;
import com.woniu.soft.service.DrugHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Service
public class DrugHistoryServiceImpl extends ServiceImpl<DrugHistoryMapper, DrugHistory> implements DrugHistoryService {
	@Override
	public void saveDrugReturn(Integer drugId, Integer number,Integer uid,Integer wId) throws Exception {
		DrugHistory drugHistory = new DrugHistory();
		drugHistory.setDrugId(drugId);
		drugHistory.setNumber(number);
		drugHistory.setuId(uid);
		drugHistory.setStatus(3);
		drugHistory.setwId(wId);
		this.save(drugHistory);
	}
	
	@Override
	public void saveDrugDeliver(Integer drugId, Integer number, Integer uid,Integer wId) throws Exception {
		DrugHistory drugHistory = new DrugHistory();
		drugHistory.setDrugId(drugId);
		drugHistory.setNumber(number);
		drugHistory.setuId(uid);
		drugHistory.setStatus(2);
		drugHistory.setwId(wId);
		this.save(drugHistory);
	}

	@Override
	public void saveDrugInput(Integer drugId, Integer number,Integer wId) throws Exception {
		DrugHistory drugHistory = new DrugHistory();
		drugHistory.setDrugId(drugId);
		drugHistory.setNumber(number);
		drugHistory.setStatus(0);
		drugHistory.setwId(wId);
		this.save(drugHistory);
	}


	@Override
	public void saveDrugoutPut(Integer drugId, Integer number, Integer wid) throws Exception {
		DrugHistory drugHistory = new DrugHistory();
		drugHistory.setDrugId(drugId);
		drugHistory.setNumber(number);
		drugHistory.setStatus(1);
		drugHistory.setwId(wid);
		this.save(drugHistory);
	}

	@Override
	public Page selectDrugHistoryByOption(Integer drugId, Integer status, String minDate, String maxDate, Integer pageIndex, Integer pageNum) throws Exception {
		QueryWrapper<DrugHistory> wrapper = new QueryWrapper<DrugHistory>();
		Page<DrugHistory> page = new Page<DrugHistory>(pageIndex,pageNum);
		if((drugId==null||drugId==0)&&(status==null||status==0)&&(minDate==null||minDate.equals("")&&(maxDate==null||maxDate.equals("")))){

		}
		return null;
	}
}
