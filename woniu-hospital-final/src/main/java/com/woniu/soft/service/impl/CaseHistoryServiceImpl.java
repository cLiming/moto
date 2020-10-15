package com.woniu.soft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.CaseHistory;
import com.woniu.soft.mapper.CaseHistoryMapper;
import com.woniu.soft.service.CaseHistoryService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qinmian
 * @since 2020-10-13
 */
@Service
public class CaseHistoryServiceImpl extends ServiceImpl<CaseHistoryMapper, CaseHistory> implements CaseHistoryService {

	@Override
	public List<CaseHistory> selectCaseHistory(Integer uid) throws Exception {
		QueryWrapper<CaseHistory> caseWrapper = new QueryWrapper<CaseHistory>();
		caseWrapper.eq("uid", uid);
		return this.list(caseWrapper);
	}

}
