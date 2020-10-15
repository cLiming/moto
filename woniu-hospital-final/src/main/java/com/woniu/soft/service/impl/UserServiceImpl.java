package com.woniu.soft.service.impl;

import com.woniu.soft.entity.User;
import com.woniu.soft.mapper.UserMapper;
import com.woniu.soft.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
