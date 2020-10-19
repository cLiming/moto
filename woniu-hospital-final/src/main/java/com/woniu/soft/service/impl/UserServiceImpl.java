package com.woniu.soft.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.woniu.soft.entity.DailyList;
import com.woniu.soft.entity.Dept;
import com.woniu.soft.mapper.DailyListMapper;
import com.woniu.soft.mapper.DeptMapper;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.soft.entity.User;
import com.woniu.soft.mapper.UserMapper;
import com.woniu.soft.service.UserService;

import javax.annotation.Resource;

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
	@Resource
	private UserMapper userMapper;
	@Resource
	private DailyListMapper dailyListMapper;
	@Resource
	private DeptMapper deptMapper;
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
	@Override
	public List<User> getUser(User user) throws Exception {
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		if (user != null && user.getTel() != null) {
			userQueryWrapper.eq("tel", user.getTel());
			return userMapper.selectList(userQueryWrapper);
		}
		return userMapper.selectList(null);
	}

	@Override
	public void updateBalance(User user) throws Exception{
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		if(user!=null&&user.getId()!=null&&user.getBalance()!=null&&user.getStatus()==4) {
			queryWrapper.eq("id", user.getId());
			User selectOne = userMapper.selectOne(queryWrapper);
			Double balance = selectOne.getBalance();
			updateWrapper.eq("id", user.getId());
			updateWrapper.set("balance", user.getBalance()+balance);
			System.err.println("3"+user.getStatus());
			userMapper.update(null, updateWrapper);
		}
		if(user.getStatus()==3) {
			UpdateWrapper<User> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.set("status", 4);
			updateWrapper1.set("balance", user.getBalance()-40);
			updateWrapper1.eq("id", user.getId());
			userMapper.update(null, updateWrapper1);
			UpdateWrapper<DailyList> dailyListWorker = new UpdateWrapper<>();
			dailyListWorker.eq("uid", user.getId());
			dailyListWorker.set("status", 1);
			dailyListMapper.update(null, dailyListWorker);

		}


	}
	@Override
	public List<User> getUserAdmissionregistration(User user) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		wrapper.in("status",list);
		wrapper.like(user.getName()!=""&&user.getName()!=null,"name",user.getName());
		wrapper.eq(user.getSex()!=""&&user.getSex()!=null,"sex",user.getSex());
		wrapper.eq(user.getIdCard()!=""&&user.getId()!=null,"id_card",user.getIdCard());

		return userMapper.selectList(wrapper);

	}
	@Override
	public User updateuserLogin(User user) throws IncorrectCredentialsException {
		System.err.println("哈哈哈哈");
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		if(user!=null&&user.getTel()!=null&&!user.getTel().equals("")&&user.getPassword()!=null&&!user.getPassword().equals("")) {
			wrapper.eq("tel", user.getTel());
			User selectOne = userMapper.selectOne(wrapper);
			if(selectOne==null) {
				user.setStatus(0);
				userMapper.insert(user);
				QueryWrapper<User> wrapper1 = new QueryWrapper<>();
				wrapper1.eq("tel", user.getTel());
				User selectOne2 = userMapper.selectOne(wrapper1);
				return selectOne2;
			}else if(selectOne!=null&&selectOne.getPassword().equals(user.getPassword())){
				System.err.println("查询到");
				User selectById = userMapper.selectById(selectOne.getId());
				return selectById;
			}
		}
		return null;

	}
	@Override
	public List<Dept> getdept() {
		QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", 1);
		queryWrapper.or();
		queryWrapper.eq("id", 2);
		queryWrapper.or();
		queryWrapper.eq("id", 3);
		queryWrapper.or();
		queryWrapper.eq("id", 4);
		queryWrapper.or();
		queryWrapper.eq("id", 5);
		queryWrapper.or();
		queryWrapper.eq("id", 6);
		return deptMapper.selectList(queryWrapper);
	}
	@Override
	public void updateUserButton(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if(user!=null&&user.getTel()!=null) {
			queryWrapper.eq("tel", user.getTel());
			User selectOne = userMapper.selectOne(queryWrapper);
			if(selectOne!=null&&selectOne.getId()!=null) {
				System.err.println("select"+selectOne);
				user.setId(selectOne.getId());
				user.setPassword(selectOne.getPassword());
				user.setStatus(1);
				userMapper.updateById(user);
			}
		}

	}
}
