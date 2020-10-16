package com.woniu.soft.service.impl;




import com.woniu.soft.entity.Prescription;
import com.woniu.soft.mapper.PrescriptionMapper;
import com.woniu.soft.service.PrescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


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
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements PrescriptionService {
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
}
