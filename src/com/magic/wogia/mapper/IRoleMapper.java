package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.RoleBean;

/**
 * 
 * 功能：角色功能接口
 * 编写人员：lzh
 * 时间：2016年9月1日下午3:02:56
 */
public interface IRoleMapper {

	/**
	 * 根据条件查询   可查询全部
	 * @return
	 */
	public List<RoleBean> findRole(@Param("id")Integer id);
	
	List<RoleBean> queryByUserId(@Param("id")Integer id);
	
}
