package com.woniu.soft.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.soft.entity.Adviceinfo;
import com.woniu.soft.entity.Bed;
import com.woniu.soft.entity.DailyList;
import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Pio;
import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.entity.Project;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;
import com.woniu.soft.entity.Workers;
import com.woniu.soft.mapper.AdviceinfoMapper;
import com.woniu.soft.mapper.BedMapper;
import com.woniu.soft.mapper.DailyListMapper;
import com.woniu.soft.mapper.DrugMapper;
import com.woniu.soft.mapper.MedAdviceMapper;
import com.woniu.soft.mapper.PioMapper;
import com.woniu.soft.mapper.PresDrugMapper;
import com.woniu.soft.mapper.PrescriptionMapper;
import com.woniu.soft.mapper.ProjectMapper;
import com.woniu.soft.mapper.ReturnApplicationMapper;
import com.woniu.soft.mapper.UserMapper;
import com.woniu.soft.mapper.WorkersMapper;
import com.woniu.soft.service.NurseService;
@Service
public class NurseServiceImpl implements NurseService{
	@Resource
	private UserMapper userMapper;
	@Resource
	private PioMapper pioMapper;
	@Resource
	private MedAdviceMapper medAdviceMapper;
	@Resource
	private AdviceinfoMapper adviceinfoMapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private PrescriptionMapper prescriptionMapper;
	@Resource
	private PresDrugMapper presDrugMapper;
	@Resource
	private DrugMapper drugMapper;
	@Resource
	private BedMapper bedMapper;
	@Resource
	private DailyListMapper dailyListMapper;
	@Resource
	private ReturnApplicationMapper returnApplicationMapper;
	@Resource
	private WorkersMapper workersMapper;
	@Override
	public List<User> selectLogById(Integer id) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("nurse", id);
		return userMapper.selectList(queryWrapper);
	}
	@Override
	public void insertPio(Pio pio) {
		pioMapper.insert(pio);
	}
	@Override
	public void updateUserInfo(User user) {
		userMapper.updateById(user);
	}
	@Override
	public void updatePioInfo(Pio pio) {
		pioMapper.updateById(pio);	
	}
	@Override
	public List<Pio> selectPioById(Integer uid, Integer wid) {
		QueryWrapper<Pio> wrapper = new QueryWrapper<>();
		wrapper.eq("uid", uid);
		wrapper.eq("w_id",wid );
		return pioMapper.selectList(wrapper);
	}
	@Override
	public List<User> selectUserByStatus() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status",4);
		return userMapper.selectList(queryWrapper);
	}
	@Override
	public List<MedAdvice> selectAdvices(Integer id) {
		QueryWrapper<MedAdvice> wrapper = new QueryWrapper<>();
		wrapper.eq("u_id", id);
		wrapper.eq("status", 1); //已完成，未结算的医嘱
		return medAdviceMapper.selectList(wrapper);
	}
	//将项目对象封装到医嘱详情中，将医嘱详情集合封装到医嘱对象中
	@Override
	public MedAdvice selectOneAdvice(Integer id) {
		MedAdvice medAdvice = medAdviceMapper.selectById(id);
		if(medAdvice!=null) {
			QueryWrapper<Adviceinfo> wrapper = new QueryWrapper<>();
			wrapper.eq("med_advice_id", medAdvice.getId());
			List<Adviceinfo> list = adviceinfoMapper.selectList(wrapper);
			if(list.size()>0) {
				for(Adviceinfo ad:list) {
					Project project = projectMapper.selectById(ad.getpId());
					ad.setProject(project);
				}
			}
			medAdvice.setAdviceinfo(list);
		}
		return medAdvice;
	}
	//将处方药品对象集合封装到处方对象，将药品对象封装到处方药品对象
	@Override
	public Object selectPrescription(Integer id) {
			QueryWrapper<Prescription> wrapper = new QueryWrapper<>();
			wrapper.eq("ad_id", id);
			Prescription prescription = prescriptionMapper.selectOne(wrapper);
			if(prescription!=null&&prescription.getAdId()!=null) {
				QueryWrapper<PresDrug> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("pres_id", prescription.getId());
				List<PresDrug> list = presDrugMapper.selectList(queryWrapper);
				if(list.size()>0) {
					for(int i=0;i<list.size();i++) {
						Drug drug = drugMapper.selectById(list.get(i).getDrId());
						list.get(i).setDrug(drug);
						//list.set(i,list.get(i));
					}
				}
				prescription.setPresDrugs(list);
			}else {
				return null;
			}
		return prescription;
	}
	@Override
	public List<Object> selectHospitalization(Integer id) {
		DailyList daily = new DailyList();
		Integer bedid=0;
		ArrayList<Object> arrayList = new ArrayList<>();
		daily.setUid(id);
		
		arrayList.add(1);
		QueryWrapper<DailyList> wrapper = new QueryWrapper<>();
		wrapper.eq("uid", id);
		List<DailyList> list = dailyListMapper.selectList(wrapper);
		if(list.size()>0) {
			bedid = list.get(0).getBedid();
		}
		Bed bed = bedMapper.selectById(bedid);
		arrayList.add(bed);
		return arrayList;
	}
	@Override
	public List<User> selectAllUser(User user) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like(user.getName()!="", "name", user.getName());
		wrapper.eq(user.getSex()!="", "sex", user.getSex());
		wrapper.eq(user.getIdCard()!="", "id_card", user.getIdCard());
		wrapper.eq(user.getDoctor()!=null, "doctor", user.getDoctor());
		wrapper.ge(user.getStatus()!=null, "status", user.getStatus());
		return userMapper.selectList(wrapper);
	}
	@Override
	public List<Integer> selectAllSpend(Integer id) {
		ArrayList<Integer> list = new ArrayList<>();
		if(id!=null&&id>0) {
			Integer i=medAdviceMapper.selectSumPtotal(id);
			Integer l=medAdviceMapper.selectSumDtotal(id);
			list.add(i+l);
		}
		return list;
	}
	@Override
	public List<ReturnApplication> selectRerurnDrug() {
		QueryWrapper<ReturnApplication> Wrapper = new QueryWrapper<>();
		Wrapper.eq("status", 0);
		List<ReturnApplication> list = returnApplicationMapper.selectList(Wrapper);
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				Drug drug = drugMapper.selectById(list.get(i).getDrId());
				Workers worker=workersMapper.selectById(list.get(i).getwId());
				User user=userMapper.selectById(list.get(i).getuId());
				list.get(i).setDrug(drug);
				list.get(i).setWorker(worker);
				list.get(i).setUser(user);
			}
		}
		return list;
	}
	@Override
	public void updateReturnDrug(ReturnApplication returnApplication) {
		returnApplicationMapper.updateById(returnApplication);
	}
}
