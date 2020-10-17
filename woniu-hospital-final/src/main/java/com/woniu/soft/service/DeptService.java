package com.woniu.soft.service;

import com.woniu.soft.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface DeptService extends IService<Dept> {
    List<Dept> getdept();
}
