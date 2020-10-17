package com.woniu.soft.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

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
	public List<User> selectLogById(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(user.getNurse()!=null,"nurse", user.getNurse());
		queryWrapper.like(user.getName()!="","name",user.getName());
		queryWrapper.eq(user.getSex()!="","sex",user.getSex());
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
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("status",4);

		return userMapper.selectList(wrapper);
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
						Drug drug = drugMapper.selectById(list.get(i).getDrugId());
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
		if(user.getStatus()!=null){
			wrapper.ge(user.getStatus()==3, "status", 3);
			wrapper.lt(user.getStatus()==6, "status", 6);
			wrapper.ge(user.getStatus()==6, "status", 3);
		}


		return userMapper.selectList(wrapper);
	}
	@Override
	public List<Integer> selectAllSpend(Integer id) {
		ArrayList<Integer> list = new ArrayList<>();
		if(id!=null&&id>0) {
			Integer i=medAdviceMapper.selectSumPtotal(id);
			Integer l=medAdviceMapper.selectSumDtotal(id);
			list.add(i);
			list.add(l);
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




	//护士的入院登记功能
	@Override
	public List<User> getregistration(User user) throws Exception{
		QueryWrapper<User> queryWorker = new QueryWrapper<>();
		//通过前端传过来的用户名称查出这个对象的所有信息
		if(user!=null&&user.getName()!=null&&user.getName()!="") {
			queryWorker.like("name", user.getName());
			queryWorker.eq("status", 1);
			return userMapper.selectList(queryWorker);
		}else {
			queryWorker.eq("status", 1);
			return userMapper.selectList(queryWorker);
		}

	}
	//查询所有的医生
	@Override
	public List<Workers> getAllDocotor() throws Exception{
		QueryWrapper<Workers> queryWorker = new QueryWrapper<>();
		queryWorker.eq("r_id", 3);
		queryWorker.or();
		queryWorker.eq("r_id", 4);
		return workersMapper.selectList(queryWorker);



	}
	//查询所有的护士
	@Override
	public List<Workers> getAllNurse() throws Exception{
		// TODO Auto-generated method stub
		QueryWrapper<Workers> queryWorker = new QueryWrapper<>();
		queryWorker.eq("r_id", 5);
		queryWorker.or();
		queryWorker.eq("r_id", 6);
		return workersMapper.selectList(queryWorker);
	}
	//获取所有空闲的床位
	@Override
	public List<Bed> getAllBed() throws Exception{
		QueryWrapper<Bed> queryWorker = new QueryWrapper<>();
		queryWorker.eq("status", 0);
		return bedMapper.selectList(queryWorker);
	}
	//按键点击  提交入院登记功能
	@Override
	public void updataAdmissionRegistration(User user) throws Exception{
		if(user!=null&&user.getId()!=null&&user.getDoctor()!=null&&user.getNurse()!=null) {
			UpdateWrapper<User> wrapper = new UpdateWrapper<>();
			wrapper.eq("id", user.getId());
			wrapper.set("status", 2);
			userMapper.update(null, wrapper);
			DailyList dailyList = new DailyList();
			dailyList.setStatus(0);
			dailyList.setUid(user.getId());
			dailyList.setBedid(user.getBedId());
			dailyListMapper.insert(dailyList);
		}
	}
	//修改床位状态
	@Override
	public void updataBedStatus(int id) {
		UpdateWrapper<Bed> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", id);
		wrapper.set("status", 1);
		bedMapper.update(null, wrapper);
	}
	@Override
	public List<MedAdvice> getAdviceinfo(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updataUserDocotors(User user) {
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("doctor", user.getDoctor());
		userMapper.update(null, wrapper);
	}
	@Override
	public void updataUserNurse(User user) {
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("nurse", user.getNurse());
		userMapper.update(null, wrapper);
	}
	@Override
	public void updataUserBed(User user) {
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("bed_id", user.getBedId());
		//将选择的床位状态改为1
		UpdateWrapper<Bed> bedwrapper = new UpdateWrapper<>();
		bedwrapper.eq("id", user.getBedId());
		bedwrapper.set("status", 1);
		bedMapper.update(null, bedwrapper);
		userMapper.update(null, wrapper);
	}

}
