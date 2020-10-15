package com.woniu.soft.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.soft.entity.Consultation;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public interface ConsultationService extends IService<Consultation> {
	List<Consultation> selectByStatusEq0()throws Exception;
}
