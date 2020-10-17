package com.woniu.soft.service.impl;

import com.woniu.soft.entity.Menu;
import com.woniu.soft.entity.Workers;
import com.woniu.soft.mapper.MenuMapper;
import com.woniu.soft.mapper.WorkersMapper;
import com.woniu.soft.service.WorkersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
public class WorkersServiceImpl extends ServiceImpl<WorkersMapper, Workers> implements WorkersService {

	@Resource
	private MenuMapper menuMapper;
	@Resource
	private WorkersMapper workersMapper;

	@Override
	public List<Menu> selectWorkerButtonByPid(Integer id,Integer pid) {
		
		return menuMapper.selectBUttonById(id,pid);
	}

	@Override
	public List<Menu> selectWokerMenu(Workers worker) {
		//查询出所有的菜单
		List<Menu> list = menuMapper.selectMenu(worker.getId());
		//定义集合保存所有的一级菜单
		ArrayList<Menu> rootList = new ArrayList<Menu>();
		//先取出所有的一级菜单
		for(Menu menu:list) {
			if(menu.getPid()==0) {
				rootList.add(menu);
				menu.setChildren(new ArrayList<Menu>());
			}
		}
		//取出所有的二级菜单
		for(Menu menu:list) {
			if(menu.getPid()!=0) {
				//找到父级菜单对象
				for(Menu root:rootList) {
					if(root.getId()==menu.getPid()) {
						root.getChildren().add(menu);
						break;
					}
				}
			}
		}
		list=null;
		return rootList;
	}

	@Override
	public List<Menu> selectWokerPermissions(Workers worker) {
		return menuMapper.selectPermissions(worker);
	}

	@Override
	public Workers selectByWorkerName(String tel) {
		QueryWrapper<Workers> queryWorker = new QueryWrapper<>();
		queryWorker.eq("tel",tel);
		return workersMapper.selectOne(queryWorker);
	}




	@Override
	public List<Workers> getworker(Workers worker) throws Exception{
		QueryWrapper<Workers> queryWorker = new QueryWrapper<>();
		if(worker!=null&&worker.getName()!=null&&!worker.getName().equals("")) {
			queryWorker.like("name", worker.getName());
			return workersMapper.selectList(queryWorker);
		}else {
			return workersMapper.selectList(null);
		}

	}
	@Override
	public void deleteWorker(Integer id) throws Exception{
		// TODO Auto-generated method stub
		workersMapper.deleteById(id);
	}
	@Override
	public void insertworker(Workers worker) throws Exception{
		//先判断电话是否存在 不存在再进行新增
		if(worker!=null) {
			QueryWrapper<Workers> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("tel", worker.getTel());
			Workers workers = workersMapper.selectOne(queryWrapper);
			if(workers==null) {
				workersMapper.insert(worker);
			}
		}
	}
	@Override
	public Workers getoneworker(Workers worker) throws Exception{
		QueryWrapper<Workers> queryWorker = new QueryWrapper<>();
		if(worker!=null&&worker.getId()!=null&&!worker.getId().equals("")){
			queryWorker.eq("id", worker.getId());
			return workersMapper.selectOne(queryWorker);
		}else {
			return null;
		}
	}
	@Override
	public void updateworkers(Workers worker) throws Exception{
		workersMapper.updateById(worker);

	}

}
