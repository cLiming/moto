package com.woniu.soft.service.impl;

import com.woniu.soft.entity.Role;
import com.woniu.soft.mapper.RoleMapper;
import com.woniu.soft.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRole() {

        return roleMapper.selectList(null);
    }
}
