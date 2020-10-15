package com.woniu.soft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.mapper.ReturnApplicationMapper;
import com.woniu.soft.service.ReturnApplicationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qinmian
 * @since 2020-10-14
 */
@Service
public class ReturnApplicationServiceImpl extends ServiceImpl<ReturnApplicationMapper, ReturnApplication> implements ReturnApplicationService {

	@Override
	public List<ReturnApplication> selectListByStatusEq1() throws Exception {
		QueryWrapper<ReturnApplication> returnApplicationwrapper = new QueryWrapper<ReturnApplication>();
		returnApplicationwrapper.eq("status", 1);
		return this.list(returnApplicationwrapper);
	}

	@Override
	public void updateStatusEq3(Integer id) throws Exception {
		ReturnApplication returnApplication = this.getById(id);
		returnApplication.setStatus(3);
		this.updateById(returnApplication);
	}

	@Override
	public void removeById(Integer id) throws Exception {
		ReturnApplication returnApplication = this.getById(id);
		returnApplication.setStatus(404);
		this.updateById(returnApplication);
	}
	
}
