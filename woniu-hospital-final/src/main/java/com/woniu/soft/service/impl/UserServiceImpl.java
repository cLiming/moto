package com.woniu.soft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.User;
import com.woniu.soft.mapper.UserMapper;
import com.woniu.soft.service.UserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public List<User> selectListByWid(Integer wid) throws Exception {
		// 创建Wrapper对象
		QueryWrapper<User> UserWrapper = new QueryWrapper<User>();
		// 查询所负责病人信息
		UserWrapper.eq("doctor", wid);
		UserWrapper.eq("status", 3);
		UserWrapper.or();
		UserWrapper.eq("status", 4);
		List<User> userList = this.list(UserWrapper);
		return userList;
	}

	@Override
	public boolean updateUserStatusLeaving(Integer uid) throws Exception {
		UpdateWrapper<User> userWrapper = new UpdateWrapper<User>();
		userWrapper.eq("id", uid);
		userWrapper.eq("status", 3);
		userWrapper.set("status", 4);
		boolean update = this.update(userWrapper);
		return update;
	}

	@Override
	public boolean updateUserStatusStay(Integer uid) throws Exception {
		UpdateWrapper<User> userWrapper = new UpdateWrapper<User>();
		userWrapper.eq("id", uid);
		userWrapper.eq("status", 4);
		userWrapper.set("status", 3);
		boolean update = this.update(userWrapper);
		return update;
	}

	@Override
	public List<User> selectAllStayUserInfo() throws Exception {
		QueryWrapper<User> userWrapper = new QueryWrapper<User>();
		userWrapper.eq("status", 3);
		return this.list(userWrapper);
	}

	@Override
	public List<User> selectAllLeavingUserInfo() throws Exception {
		QueryWrapper<User> userWrapper = new QueryWrapper<User>();
		userWrapper.eq("status", 4);
		return this.list(userWrapper);
	}

}
