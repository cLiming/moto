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
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;
import com.woniu.soft.entity.Workers;
import com.woniu.soft.service.NurseService;
import com.woniu.soft.utils.JSONResult;

import java.util.List;


@RestController
@RequestMapping("/nurse")
@SuppressWarnings("all")
public class NurseController {
	@Resource
	private NurseService nurseService;
	@GetMapping
	//查询该护士负责的所有病人,不管任何状态的病人
	public JSONResult selectLogById(User user) {

		Subject subject = SecurityUtils.getSubject();
		Workers worker = (Workers)subject.getPrincipal();
		user.setNurse(worker.getId());
		return new JSONResult("200","success",nurseService.selectLogById(user),null);
	}
	
	@PostMapping
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
	@GetMapping ("leave")
	public JSONResult selectUserByStatus(User user) throws Exception {
		System.err.println(user);
		return new JSONResult("200","success",nurseService.selectUserByStatus(user),null);
	}
	//修改用户状态,将提交出院申请的用户更改为已审核
	@PutMapping("leave")
	public JSONResult updateUser(User user) throws Exception {
		System.err.println(user);
		user.setStatus(5);
		nurseService.updateUserInfo(user);
		return new JSONResult("200","success",null,null);
	}
	//查询该用户的已完成，未结算的医嘱
	@RequestMapping("spend")
	public JSONResult selectAdvices(Integer id) {
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
		//user.setStatus(3);
		return new JSONResult("200","success",nurseService.selectAllUser(user),null);
	}
	//查询该病人的项目费用和药品费用之和,业务层new了一个对象，下标0位置存储该值
	@RequestMapping("allspend")
	public JSONResult selectAllSpend(Integer id) throws Exception {
		return new JSONResult("200","success",nurseService.selectAllSpend(id),null);
	}
	//查询退药申请
	@RequestMapping("returnDrug")
	public JSONResult selectRerurnDrug(ReturnApplication returnApplication) throws Exception {
		return new JSONResult("200","success",nurseService.selectRerurnDrug(),null);
	}
	
	
	@PutMapping("reviewReturnDrug")
	public JSONResult updateReturnDrug(@RequestBody ReturnApplication returnApplication) throws Exception {
		nurseService.updateReturnDrug(returnApplication);
		return new JSONResult("200","success",null,null);
	}

	//护士的入院登记开始。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//显示护士的入院登记功能的页面
	@RequestMapping("Admissionregistration")
	public JSONResult getregistration(User user) throws Exception{
		//System.out.println("aaa"+nurseService.getregistration(user));
		//System.err.println("nurseService.getregistration(user)"+nurseService.getregistration(user));
		return new JSONResult("200","success",nurseService.getregistration(user),null);

	}
	//获取所有医生的信息
	@RequestMapping("getAllDocotor")
	public JSONResult getAllDocotor(User user) throws Exception{
		return new JSONResult("200","success",nurseService.getAllDocotor(),null);
	}

	//获取所有护士的信息
	@RequestMapping("getAllNurse")
	public JSONResult getAllNurse(User user) throws Exception{
		return new JSONResult("200","success",nurseService.getAllNurse(),null);
	}
	//获取所有空闲的床位
	@RequestMapping("getAllBed")
	public JSONResult getAllBed() throws Exception{
		return new JSONResult("200","success",nurseService.getAllBed(),null);
	}
	//选择医生信息
	@RequestMapping("updataUserDocotors")
	public JSONResult updataUserDocotors(User user) throws Exception{
		nurseService.updateUserDocotors(user);
		return new JSONResult("200","success",null,null);
	}
	//修改用户护士信息
	@RequestMapping("updataUserNurse")
	public JSONResult updataUserNurse(User user) throws Exception{
		System.out.println("nurse"+user);
		nurseService.updateUserNurse(user);
		return new JSONResult("200","success",null,null);
	}
	//修改用户床位信息
	@RequestMapping("updataUserBed")
	public JSONResult updataUserBed(User user) throws Exception{
		nurseService.updateUserBed(user);
		return new JSONResult("200","success",null,null);
	}
	//选择了这个床位 将这个床位的状态改为1
	@PostMapping("{id}")
	public JSONResult updataBedStatus(@PathVariable("id") int id) throws Exception{
		nurseService.updateBedStatus(id);
		return new JSONResult("200","success",null,null);
	}
	//按键点击  提交入院登记功能（审核通过）
	@RequestMapping("updataAdmissionRegistration")
	public JSONResult updataAdmissionRegistration(User user) throws Exception{
		nurseService.updateAdmissionRegistration(user);
		return new JSONResult("200","success",null,null);
	}
	//护士的入院登记结束。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//护士模块的医嘱开始。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//查询医嘱详情表、医嘱表、处方表、处方详情表、医疗项目表 显示到用户医嘱处理二级菜单上
	//传入一个user对象 得到医嘱表和医嘱详情表的连查数据
	@RequestMapping("adviceinfo")
	public JSONResult getAdviceinfo(User user) throws Exception{
		List<MedAdvice> getAdviceinfo = nurseService.getAdviceinfo(user);
		return new JSONResult("200","success",getAdviceinfo,null);
	}
}
