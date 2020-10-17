package com.woniu.soft.service.impl;




import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.mapper.PrescriptionMapper;
import com.woniu.soft.service.PrescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements PrescriptionService {
	@Resource
	private PrescriptionMapper prescriptionMapper;
	@Override
	public Prescription selectByMid(Integer mid) throws Exception {
		QueryWrapper<Prescription> PrescriptionWrapper = new QueryWrapper<Prescription>();
		PrescriptionWrapper.eq("ad_id", mid);
		Prescription prescription = this.getOne(PrescriptionWrapper);
		return prescription;
	}

	@Override
	public void removeByAdId(Integer adId) throws Exception {
		UpdateWrapper<Prescription> PrescriptionUpWrapper = new UpdateWrapper<Prescription>();
		PrescriptionUpWrapper.eq("ad_id", adId);
		this.remove(PrescriptionUpWrapper);
	}

	@Override
	public Prescription selectByMidStatus(Integer mid, Integer status) throws Exception {
		QueryWrapper<Prescription> PrescriptionWrapper = new QueryWrapper<Prescription>();
		PrescriptionWrapper.eq("ad_id", mid);
		PrescriptionWrapper.eq("status", status);
		Prescription prescription = this.getOne(PrescriptionWrapper);
		return prescription;
	}

	@Override
	public void updateStatusEq1(Prescription prescription) throws Exception {
		prescription.setStatus(1);
		this.updateById(prescription);
	}


	@Override
	public Prescription getPresiption(MedAdvice medAdvice) throws Exception{
		QueryWrapper<Prescription> PrescriptionWrapper = new QueryWrapper<Prescription>();
		PrescriptionWrapper.eq("ad_id", medAdvice.getId());
		PrescriptionWrapper.eq("status", 1);
		Prescription prescription = prescriptionMapper.selectOne(PrescriptionWrapper);
		return prescription;
	}
	@Override
	public void updataPrescription(Prescription prescription) throws Exception{
		if(prescription!=null&&prescription.getId()!=null) {
			UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("id", prescription.getId());
			updateWrapper.set("status", 2);
			prescriptionMapper.update(null, updateWrapper);
		}
	}

}
