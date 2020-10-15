package com.woniu.soft.service;

import com.woniu.soft.entity.Adviceinfo;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface AdviceinfoService extends IService<Adviceinfo> {
	List<Adviceinfo> selectAdviceinfosByMid(Integer mid)throws Exception;
	void removeByMid(Integer mid)throws Exception;
}
