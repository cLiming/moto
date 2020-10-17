package com.woniu.soft.service;

import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Prescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface PrescriptionService extends IService<Prescription> {
		Prescription selectByMid(Integer mid)throws Exception;
	
		Prescription selectByMidStatus(Integer mid,Integer status)throws Exception;
	
		void removeByAdId(Integer adId)throws Exception;
	
		void updateStatusEq1(Prescription prescription)throws Exception;

		Prescription getPresiption(MedAdvice medAdvice) throws Exception;

		void updataPrescription(Prescription prescription) throws Exception;
	
}
