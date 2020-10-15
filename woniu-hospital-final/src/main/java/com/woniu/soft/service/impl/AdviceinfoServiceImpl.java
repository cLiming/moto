package com.woniu.soft.service.impl;

import com.woniu.soft.entity.Adviceinfo;
import com.woniu.soft.mapper.AdviceinfoMapper;
import com.woniu.soft.service.AdviceinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class AdviceinfoServiceImpl extends ServiceImpl<AdviceinfoMapper, Adviceinfo> implements AdviceinfoService {
	@Override
	public List<Adviceinfo> selectAdviceinfosByMid(Integer mid) throws Exception {
		QueryWrapper<Adviceinfo> AdviceinfoWrapper = new QueryWrapper<Adviceinfo>();
		AdviceinfoWrapper.eq("med_advice_id", mid);
		List<Adviceinfo> adviceinfos = this.list(AdviceinfoWrapper);
		return adviceinfos;
	}

	@Override
	public void removeByMid(Integer mid) throws Exception {
		// 删除医嘱详细信息
		UpdateWrapper<Adviceinfo> AdviceinfoWrapper = new UpdateWrapper<Adviceinfo>();
		AdviceinfoWrapper.eq("med_advice_id", mid);
		this.remove(AdviceinfoWrapper);
	}
}
