package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.RoleBean;
import com.magic.wogia.bean.RolePowerBean;

public interface IRolePowerMapper {
	/**
	 *  根据 权限 查询角色
	 * @param powerId
	 * @return
	 */
	List<RoleBean> findByPowerId(@Param("powerId")Integer powerId);
	
	/**
	 *  根据 角色 查询 权限
	 * @param roleId
	 * @return
	 */
	List<RolePowerBean> findByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  给角色增加权限
	 * @param roleId
	 * @param powerId
	 * @return
	 */
	Integer updateRolePower(Integer roleId,Integer powerId);

	/**根据角色 删除权限*/
	Integer delRolePower(@Param("roleId")Integer roleId);
	
	/**批量新增*/
	Integer addBatch(@Param("rolePowers")List<RolePowerBean> rolePowers);
	
	
}
