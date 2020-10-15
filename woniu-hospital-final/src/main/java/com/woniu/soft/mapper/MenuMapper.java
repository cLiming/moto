package com.woniu.soft.mapper;

import com.woniu.soft.entity.Menu;
import com.woniu.soft.entity.Workers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public interface MenuMapper extends BaseMapper<Menu> {
	List<Menu> selectPermissions(Workers worker);

	List<Menu> selectMenu(Integer id);

	List<Menu> selectBUttonById(@Param("id")Integer id, @Param("pid")Integer pid);
}
