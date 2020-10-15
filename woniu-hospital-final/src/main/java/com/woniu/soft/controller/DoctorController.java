package com.woniu.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.soft.entity.AdmissionRecord;
import com.woniu.soft.entity.Adviceinfo;
import com.woniu.soft.entity.BigInfo;
import com.woniu.soft.entity.CaseHistory;
import com.woniu.soft.entity.Consultation;
import com.woniu.soft.entity.Drug;
import com.woniu.soft.entity.DrugEntity;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.entity.Project;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;
import com.woniu.soft.service.AdmissionRecordService;
import com.woniu.soft.service.AdviceinfoService;
import com.woniu.soft.service.CaseHistoryService;
import com.woniu.soft.service.ConsultationService;
import com.woniu.soft.service.DrugService;
import com.woniu.soft.service.MedAdviceService;
import com.woniu.soft.service.PresDrugService;
import com.woniu.soft.service.PrescriptionService;
import com.woniu.soft.service.ProjectService;
import com.woniu.soft.service.ReturnApplicationService;
import com.woniu.soft.service.UserService;
import com.woniu.soft.utils.JSONResult;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/doc")
public class DoctorController {
	@Resource
	private MedAdviceService maService;
	@Resource
	private AdviceinfoService aiService;
	@Resource
	private PrescriptionService prescriptionService;
	@Resource
	private PresDrugService presDrugService;
	@Resource
	private UserService userService;
	@Resource
	private DrugService drugService;
	@Resource
	private ProjectService proService;
	@Resource
	private AdmissionRecordService arService;
	@Resource
	private CaseHistoryService chService;
	@Resource
	private ConsultationService consultationService;
	@Resource
	private ReturnApplicationService reService;

	// 通过医生id查询所有医嘱
	@RequestMapping("/selectAd")
	public JSONResult selectAdvice(Integer wid, Integer pageIndex, Integer pageNum) throws Exception {
		Page<MedAdvice> page = maService.selectByWidLimit(wid, pageIndex, pageNum);
		List<MedAdvice> list = page.getRecords();
		if (list != null) {
			for (MedAdvice medAdvice : list) {
				List<Adviceinfo> adviceinfos = aiService.selectAdviceinfosByMid(medAdvice.getId());
				Prescription prescription = prescriptionService.selectByMid(medAdvice.getId());
				medAdvice.setInfo(adviceinfos);
				medAdvice.setPrescription(prescription);
				if (prescription != null) {
					List<PresDrug> DrugList = presDrugService.selectListByPid(prescription.getId());
					prescription.setInfo(DrugList);
				}
			}
		}
		return new JSONResult("200", "success", null, page);
	}

	// 通过医生ID查询所有在院负责病人信息
	@RequestMapping("/info")
	public JSONResult selectInfo(Integer wid) throws Exception {
		// 查询所负责病人信息
		List<User> userList = userService.selectListByWid(wid);
		// 查询所有项目信息
		List<Project> proList = proService.list();
		// 查询所有药品信息
		List<Drug> drugList = drugService.list();
		BigInfo info = new BigInfo(userList, drugList, proList);
		return new JSONResult("200", "success", null, info);
	}

	// 删除医嘱
	@RequestMapping("/deleteAd")
	public JSONResult removeMedAdvice(Integer id, Integer wid) throws Exception {
		MedAdvice medAdvice = maService.getById(id);
		if (medAdvice == null) {
			return new JSONResult("800", "医嘱不存在", null, null);
		} else {
			if (medAdvice.getwId() == wid) {
				// 删除医嘱信息
				maService.removeById(id);
				// 删除医嘱详细信息
				aiService.removeByMid(medAdvice.getId());
				// 查询处方id
				Prescription prescription = prescriptionService.selectByMid(medAdvice.getId());
				if (prescription != null) {
					// 删除处方详细信息
					presDrugService.removeByPid(prescription.getId());
					// 删除处方信息
					prescriptionService.removeByAdId(medAdvice.getId());
				}
				return new JSONResult("200", "success", null, null);
			} else {
				return new JSONResult("801", "权限不足", null, null);
			}
		}

	}

	// 新增医嘱
	@PostMapping
	public JSONResult saveMedAdvice(@RequestBody MedAdvice medAdvice) throws Exception {
		maService.save(medAdvice);
		Prescription prescription = medAdvice.getPrescription();
		double pPrice = 0;
		double dPrice = 0;
		if (prescription != null) {
			prescription.setAdId(medAdvice.getId());
			prescriptionService.save(prescription);
			List<PresDrug> drugInfo = prescription.getInfo();
			if (drugInfo != null) {
				for (PresDrug presDrug : drugInfo) {
					if (presDrug.getDrugId() != null || !presDrug.getDrugId().equals("")) {
						presDrug.setPresId(medAdvice.getPrescription().getId());
						presDrugService.save(presDrug);
						Drug drug = drugService.getById(presDrug.getDrugId());
						dPrice += drug.getPrice() * presDrug.getNumber();
					}
				}
			}
		}
		List<Adviceinfo> info = medAdvice.getInfo();
		if (info != null) {
			for (Adviceinfo adviceinfo : info) {
				adviceinfo.setMedAdviceId(medAdvice.getId());
				Project project = proService.getById(adviceinfo.getpId());
				pPrice += project.getPrice();
			}
			aiService.saveBatch(info);
		}
		medAdvice.setpTotal(pPrice);
		medAdvice.setdTotal(dPrice);
		maService.updateById(medAdvice);
		return new JSONResult("200", "success", null, null);
	}

	// 更新医嘱
	@PutMapping
	public JSONResult updateMedAdvice(@RequestBody MedAdvice medAdvice) throws Exception {
		double pPrice = 0;
		double dPrice = 0;
		maService.updateById(medAdvice);
		Prescription prescription = medAdvice.getPrescription();
		if (prescription != null) {
			List<PresDrug> drugInfo = prescription.getInfo();
			if (drugInfo != null) {
				presDrugService.removeByPid(prescription.getId());
				presDrugService.saveBatch(drugInfo);
			}
		}
		List<Adviceinfo> info = medAdvice.getInfo();
		if (info != null) {
			aiService.removeByMid(medAdvice.getId());
			aiService.saveBatch(info);
		}
		return new JSONResult("200", "success", null, null);
	}

	// 查询入院记录
	@RequestMapping("/record")
	public JSONResult selectAdmissionRecord(Integer pageIndex, Integer pageNum) throws Exception {
		Page<AdmissionRecord> page = new Page<AdmissionRecord>(pageIndex, pageNum);
		arService.page(page);
		List<AdmissionRecord> list = page.getRecords();
		for (AdmissionRecord Record : list) {
			Record.setUser(userService.getById(Record.getUid()));
		}
		return new JSONResult("200", "success", null, page);
	}

	// 出院申请（更改status为4）
	@RequestMapping("/leave")
	public JSONResult updateUserStatusLeaving(Integer uid) throws Exception {
		boolean update = userService.updateUserStatusLeaving(uid);
		if (update) {
			return new JSONResult("200", "success", null, null);
		} else {
			return new JSONResult("900", "该病人不是住院状态", null, null);
		}
	}

	// 撤销出院申请（更改status为3）
	@RequestMapping("/stay")
	public JSONResult updateUserStatusStay(Integer uid) throws Exception {
		boolean update = userService.updateUserStatusStay(uid);
		if (update) {
			return new JSONResult("200", "success", null, null);
		} else {
			return new JSONResult("901", "该病人未申请出院", null, null);
		}
	}

	// 获取所有在院病人信息
	@RequestMapping("/allUserStay")
	public JSONResult selectAllStayUserInfo() throws Exception {
		return new JSONResult("200", "success", userService.selectAllStayUserInfo(), null);
	}

	// 获取所有在院病人信息
	@RequestMapping("/allUserLeaving")
	public JSONResult selectAllLeavingUserInfo() throws Exception {
		return new JSONResult("200", "success", userService.selectAllLeavingUserInfo(), null);
	}

	// 查询病案CaseHistory
	@RequestMapping("/selectCase")
	public JSONResult selectCaseHistory() throws Exception {
		return new JSONResult("200", "success", chService.list(), null);
	}

	// 录入病案CaseHistory
	@RequestMapping("/insertCase")
	public JSONResult saveCaseHistory(@RequestBody CaseHistory caseHistory) throws Exception {
		chService.save(caseHistory);
		return new JSONResult("200", "success", null, null);
	}

	// 保存病案CaseHistory
	@RequestMapping("/updateCase")
	public JSONResult updateCaseHistory(@RequestBody CaseHistory caseHistory) throws Exception {
		chService.updateById(caseHistory);
		return new JSONResult("200", "success", null, null);
	}

	// 申请会诊
	@RequestMapping("/insertCon")
	public JSONResult saveConsultation(@RequestBody Consultation consultation) throws Exception {
		consultationService.save(consultation);
		return new JSONResult("200", "success", null, null);
	}

	// 填写会诊情况
	@RequestMapping("/updateCon")
	public JSONResult updateConsultation(@RequestBody Consultation consultation) throws Exception {
		consultationService.updateById(consultation);
		return new JSONResult("200", "success", null, null);
	}

	// 查询申请中的会诊(Status==1)
	@RequestMapping("/selectCon")
	public JSONResult selectConsultationByStatusEq0() throws Exception {
		return new JSONResult("200", "success", consultationService.selectByStatusEq0(), null);
	}

	// 填写退药申请
	@RequestMapping("/returnDrug")
	public JSONResult saveReturnApplication(@RequestBody ReturnApplication returnApplication) throws Exception {
		reService.save(returnApplication);
		return new JSONResult("200", "success", null, null);
	}

	// 查询所有已完成的处方
	@RequestMapping("/selectPre")
	public JSONResult selectAllPhar() throws Exception {
		List<Prescription> list = new ArrayList<Prescription>();
		List<MedAdvice> medList = maService.selectListByStatus(2);
		for (MedAdvice medAdvice : medList) {
			Prescription prescription = prescriptionService.selectByMidStatus(medAdvice.getId(), 2);
			if (prescription != null) {
				if (prescription.getStatus() == 0) {
					List<PresDrug> presList = presDrugService.selectListByPid(prescription.getId());
					for (PresDrug presDrug : presList) {
						Drug drug = drugService.getById(presDrug.getDrugId());
						presDrug.setDrugName(drug.getName());
						presDrug.setBaseNumber(drug.getNumber());
					}
					prescription.setInfo(presList);
					User user = userService.getById(medAdvice.getuId());
					prescription.setUser(user);
					list.add(prescription);
				}
			}
		}
		return new JSONResult("200", "success", list, null);
	}
	
	//退药申请
	@RequestMapping("/selectDrugEntity")
	public JSONResult selectDrugEntity(Integer uid) throws Exception {
		List<DrugEntity> drugEntitys = drugService.selectDrugEntity(uid);
		for (DrugEntity drugEntity : drugEntitys) {
			Drug drug = drugService.getById(drugEntity.getDrugId());
			drugEntity.setDrugName(drug.getName());
			drugEntity.setuId(uid);
		}
		return new JSONResult("200", "success", drugEntitys, null); 
	}
	
	
}
