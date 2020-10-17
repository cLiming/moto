package com.woniu.soft.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.entity.Prescription;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public interface PresDrugService extends IService<PresDrug> {
	List<PresDrug> selectListByPid(Integer pid)throws Exception;
	
	void removeByPid(Integer pid)throws Exception;

	List<PresDrug> getpresDrug(Prescription presiption);
}
