package com.woniu.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;
import com.woniu.soft.service.DrugHistoryService;
import com.woniu.soft.service.DrugService;
import com.woniu.soft.service.MedAdviceService;
import com.woniu.soft.service.PresDrugService;
import com.woniu.soft.service.PrescriptionService;
import com.woniu.soft.service.ReturnApplicationService;
import com.woniu.soft.service.UserService;
import com.woniu.soft.utils.JSONResult;

@RestController
@RequestMapping("/phar")
public class PharmacyController {
	@Resource
	private PrescriptionService prescriptionService;
	@Resource
	private PresDrugService presDrugService;
	@Resource
	private UserService userService;
	@Resource
	private MedAdviceService maService;
	@Resource
	private DrugService drugService;
	@Resource
	private ReturnApplicationService raService;
	@Resource
	private DrugHistoryService dhService;
	
	
	//查询所有未完成的处方
	@RequestMapping("/all")
	public JSONResult selectAllPhar()throws Exception{
		List<Prescription> list =new ArrayList<Prescription>();
		List<MedAdvice> medList = maService.selectListByStatus(0);
		for (MedAdvice medAdvice : medList) {
			Prescription prescription = prescriptionService.selectByMidStatus(medAdvice.getId(), 0);
			if(prescription!=null) {
				if(prescription.getStatus()==0) {
					List<PresDrug> presList = presDrugService.selectListByPid(prescription.getId());
					for (PresDrug presDrug : presList) {
						Drug drug = drugService.getById(presDrug.getDrugId());
						presDrug.setDrugName(drug.getName());
						presDrug.setBaseNumber(drug.getNumber());
					}
					prescription.setpresDrugs(presList);
					User user = userService.getById(medAdvice.getuId());
					prescription.setUser(user);
					list.add(prescription);
				}
			}
		}
		return new JSONResult("200","success",list,null);
	}
	
	//完成配药
	@RequestMapping("/deliverDrug")
	public JSONResult deliverDrug(@RequestBody Prescription prescription) throws Exception{
		Integer wid=3;
		prescriptionService.updateStatusEq1(prescription);
		List<PresDrug> info = prescription.getpresDrugs();
		for (PresDrug presDrug : info) {
			Integer number = presDrug.getNumber();
			Integer baseNumber = presDrug.getBaseNumber();
			if(baseNumber>number) {
				drugService.updateDrugDown(presDrug.getDrugId(), number);
				dhService.saveDrugDeliver(presDrug.getDrugId(), number,prescription.getUser().getId(),wid);
			}else {
				return new JSONResult("998","编号为:"+presDrug.getDrugId()+"的药品 "+presDrug.getDrugName()+"库存不足!请尽快补充库存",null,null);
			}
		}
		return new JSONResult("200","success",null,null);
	}
	
	//查询所有退药申请（状态为1的）
	@RequestMapping("/selectReturn")
	public JSONResult selectReturnApplication()throws Exception{
		List<ReturnApplication> list = raService.selectListByStatusEq1();
		for (ReturnApplication returnApplication : list) {
			returnApplication.setDrugName(drugService.getById(returnApplication.getDrId()).getName());
			returnApplication.setUser(userService.getById(returnApplication.getuId()));
		}
		return new JSONResult("200","success",list,null);
	}
	
	//药品审核完毕,完成退药并记录
	@RequestMapping("/returnDrug")
	public JSONResult updateDrug(Integer drugId,Integer number,Integer id,Integer uid)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer wid=user.getId();
		drugService.updateDrugUp(drugId, number);
		dhService.saveDrugReturn(drugId, number,uid,wid);
		raService.updateStatusEq3(id);
		return new JSONResult("200","退药完成",null,null);
	}
	
	//药品审核不通过,修改申请状态为404
	@RequestMapping("/deleteReturn")
	public JSONResult removeReturn(Integer id)throws Exception{
		//重写removeById方法 修改状态为404
		raService.removeById(id);
		return new JSONResult("200","success",null,null);
	}
	
	//出库药品
	@RequestMapping("/outputDrug")
	public JSONResult outputDrug(Integer id,Integer number)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer wid=user.getId();
		drugService.updateDrugDown(id, number);
		dhService.saveDrugoutPut(id, number, wid);
		return new JSONResult("200","success",null,null);
	}
	
	//入库药品
	@RequestMapping("/inputDrug")
	public JSONResult inputDrug(Integer id,Integer number)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer wid=user.getId();
		drugService.updateDrugUp(id, number);
		dhService.saveDrugInput(id, number, wid);
		return new JSONResult("200","success",null,null);
	}
	
	//新增药品
	@RequestMapping("/saveDrug")
	public JSONResult changePrice(@RequestBody Drug drug)throws Exception{
		drugService.save(drug);
		return new JSONResult("200","success",null,null);
	}
	
	//分页查询药品信息
	@RequestMapping("/selectDrug")
	public JSONResult selectDrugs(Integer id,String name,Integer pageIndex,Integer pageNum)throws Exception{
		Page page = drugService.selectDrugs(id, name, pageIndex, pageNum);
		return new JSONResult("200","success",null,page);
	}
	
	
	
}
