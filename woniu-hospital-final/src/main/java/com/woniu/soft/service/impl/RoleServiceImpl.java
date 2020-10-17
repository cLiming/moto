package com.woniu.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.soft.entity.Menu;
import com.woniu.soft.entity.Role;
import com.woniu.soft.entity.RolePermission;
import com.woniu.soft.mapper.MenuMapper;
import com.woniu.soft.mapper.RoleMapper;
import com.woniu.soft.mapper.RolePermissionMapper;
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
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Role> getRole() {

        return roleMapper.selectList(null);
    }

}
