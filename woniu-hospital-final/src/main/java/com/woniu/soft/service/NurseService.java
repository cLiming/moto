package com.woniu.soft.service;

import java.util.List;

import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Pio;
import com.woniu.soft.entity.ReturnApplication;
import com.woniu.soft.entity.User;

public interface NurseService {
	
	public List<User> selectLogById(Integer id);

	public void insertPio(Pio pio);

	public void updateUserInfo(User user);

	public void updatePioInfo(Pio pio);

	public List<Pio> selectPioById(Integer uid,Integer wid);

	public List<User> selectUserByStatus();

	public List<MedAdvice> selectAdvices(Integer id);

	public Object selectOneAdvice(Integer id);

	public Object selectPrescription(Integer id);

	public List<Object> selectHospitalization(Integer id);

	public List<User> selectAllUser(User user);

	public List<Integer> selectAllSpend(Integer id);

	public List<ReturnApplication> selectRerurnDrug();

	public void updateReturnDrug(ReturnApplication returnApplication);

}
