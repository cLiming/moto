package com.woniu.soft.service;

import com.woniu.soft.entity.CaseHistory;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface CaseHistoryService extends IService<CaseHistory> {
	List<CaseHistory> selectCaseHistory(Integer uid)throws Exception;
}
