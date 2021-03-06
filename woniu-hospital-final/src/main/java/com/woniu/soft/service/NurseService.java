package com.woniu.soft.service;

import java.util.List;

import com.woniu.soft.entity.*;

public interface NurseService {
	
	public List<User> selectLogById( User user);

	public void insertPio(Pio pio);

	public void updateUserInfo(User user);

	public void updatePioInfo(Pio pio);

	public List<Pio> selectPioById(Integer uid,Integer wid);

	public List<User> selectUserByStatus(User user);

	public List<MedAdvice> selectAdvices(Integer id);

	public Object selectOneAdvice(Integer id);

	public Object selectPrescription(Integer id);

	public List<Object> selectHospitalization(Integer id);

	public List<User> selectAllUser(User user);

	public List<Integer> selectAllSpend(Integer id);

	public List<ReturnApplication> selectRerurnDrug();

	public void updateReturnDrug(ReturnApplication returnApplication);




	List<User> getregistration(User user) throws Exception;

	List<Workers> getAllDocotor() throws Exception;

	List<Workers> getAllNurse() throws Exception;

	List<Bed> getAllBed() throws Exception;

	void updateAdmissionRegistration(User user) throws Exception;

	void updateBedStatus(int id);

	List<MedAdvice> getAdviceinfo(User user);

	void updateUserDocotors(User user);

	void updateUserNurse(User user);

	void updateUserBed(User user);

}
