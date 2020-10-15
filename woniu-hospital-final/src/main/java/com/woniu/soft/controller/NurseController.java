package com.woniu.soft.controller;

import javax.annotation.Resource;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Pio;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;
import com.woniu.soft.entity.Workers;
import com.woniu.soft.service.NurseService;
import com.woniu.soft.utils.JSONResult;


@RestController
@RequestMapping("/nurse")
public class NurseController {
	@Resource
	private NurseService nurseService;
	@GetMapping
	//查询该护士负责的所有病人,不管任何状态的病人
	public JSONResult selectLogById() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		return new JSONResult("200","success",nurseService.selectLogById(worker.getId()),null);
	}
	
	@PostMapping
	//aweeeeeeeeeeeeeeeee
	//新增一个护理记录
	public JSONResult insertPio( Pio pio) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		pio.setwId(worker.getId());
		nurseService.insertPio(pio);
		return new JSONResult("200","success",null,null);
	}
	@GetMapping("{id}")
	//通过护理记录id查询护理记录
	public JSONResult selectPioById(@PathVariable("id")Integer id) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		return new JSONResult("200","success",nurseService.selectPioById(id,worker.getId()),null);
	}
	@PutMapping
	//修改病人信息
	public JSONResult updateUserInfo(User user) throws Exception {
		nurseService.updateUserInfo(user);
		return new JSONResult("200","success",null,null);
	}
	@PutMapping("pio")
	//修改护理信息
	public JSONResult updatePioInfo(Pio pio) throws Exception {
		nurseService.updatePioInfo(pio);
		return new JSONResult("200","success",null,null);
	}
	//查询所有提交出院申请的病人,
	@GetMapping("leave")
	public JSONResult selectUserByStatus() throws Exception {
		return new JSONResult("200","success",nurseService.selectUserByStatus(),null);
	}
	//修改用户状态,将提交出院申请的用户更改为已审核
	@PutMapping("leave")
	public JSONResult updateUser(User user) throws Exception {
		user.setStatus(5);
		nurseService.updateUserInfo(user);
		return new JSONResult("200","success",null,null);
	}
	//查询该用户的已完成，未结算的医嘱
	@RequestMapping("spend")
	public JSONResult selectAdvices(Integer id) throws Exception {
		return new JSONResult("200","success",nurseService.selectAdvices(id),null);
	}
	//根据医嘱id查询用户项目的单价，次数，传回一个医嘱对象
	@RequestMapping("projectSpend")
	public JSONResult selectProject(Integer id) throws Exception {
		MedAdvice med = (MedAdvice)nurseService.selectOneAdvice(id);
		System.err.println(med.getAdviceinfo());
		return new JSONResult("200","success",null,nurseService.selectOneAdvice(id));
	}
	
	//根据医嘱id查询用户处方药品的单价，数量,传回一个处方对象
	@RequestMapping("prescriptionSpend")
	public JSONResult selectPrescription(Integer id) throws Exception {
		return new JSONResult("200","success",null,nurseService.selectPrescription(id));
	}
		
	//查询住院详情，0下标封装了住院天数，1下标封装了药房对象，可算出住院费
	@RequestMapping("hospitalization")
	public JSONResult selectHospitalization(Integer id) throws Exception {
		return new JSONResult("200","success",nurseService.selectHospitalization(id),null);
	}
	
	//条件查询病人，
	@RequestMapping("allUser")
	public JSONResult selectAllUser(User user) throws Exception {
		user.setStatus(3);
		return new JSONResult("200","success",nurseService.selectAllUser(user),null);
	}
	//查询该病人的项目费用和药品费用之和,业务层new了一个对象，下标0位置存储该值
	@RequestMapping("allspend")
	public JSONResult selectAllSpend(Integer id) throws Exception {
		return new JSONResult("200","success",nurseService.selectAllSpend(id),null);
	}
	//查询退药申请
	@RequestMapping("returnDrug")
	public JSONResult selectRerurnDrug() throws Exception {
		System.err.println(nurseService.selectRerurnDrug());
		return new JSONResult("200","success",nurseService.selectRerurnDrug(),null);
	}
	
	
	@PutMapping("reviewReturnDrug")
	public JSONResult updateReturnDrug(@RequestBody ReturnApplication returnApplication) throws Exception {
		nurseService.updateReturnDrug(returnApplication);
		return new JSONResult("200","success",null,null);
	}
}
