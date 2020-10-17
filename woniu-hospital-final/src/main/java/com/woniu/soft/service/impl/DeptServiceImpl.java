package com.woniu.soft.service.impl;

import com.woniu.soft.entity.Dept;
import com.woniu.soft.mapper.DeptMapper;
import com.woniu.soft.service.DeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Resource
    private DeptMapper deptMapper;
    @Override
    public List<Dept> getdept() {

        return deptMapper.selectList(null);
    }


}
