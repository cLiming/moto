package com.woniu.soft.service;

import com.woniu.soft.entity.Menu;
import com.woniu.soft.entity.Workers;

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
public interface WorkersService extends IService<Workers> {

	List<Menu> selectWorkerButtonByPid(Integer id,Integer pid);

	List<Menu> selectWokerMenu(Workers worker);

	List<Menu> selectWokerPermissions(Workers worker);

	Workers selectByWorkerName(String name);


	List<Workers> getworker(Workers worker) throws Exception;

	void deleteWorker(Integer id) throws Exception;

	void insertworker(Workers worker) throws Exception;

	Workers getoneworker(Workers worker) throws Exception;

	void updateworkers(Workers worker) throws Exception;
}
