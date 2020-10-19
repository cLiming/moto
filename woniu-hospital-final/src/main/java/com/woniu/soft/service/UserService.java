package com.woniu.soft.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.soft.entity.Dept;
import com.woniu.soft.entity.User;
import org.apache.shiro.authc.IncorrectCredentialsException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public interface UserService extends IService<User> {
	List<User> selectListByWid(Integer wid) throws Exception;
	boolean updateUserStatusLeaving(Integer uid)	throws Exception;	
	boolean updateUserStatusStay(Integer uid)	throws Exception;
	List<User> selectAllStayUserInfo()throws Exception;
	List<User> selectAllLeavingUserInfo()throws Exception;



	List<User> getUser(User user) throws Exception;

	void updateBalance(User user) throws Exception;

	List<User> getUserAdmissionregistration(User user) throws Exception;

	User updateuserLogin(User user) throws IncorrectCredentialsException;

	List<Dept> getdept();

	void updateUserButton(User user);

}
