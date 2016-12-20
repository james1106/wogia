package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.magic.wogia.bean.RoleBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.IRoleMapper;
import com.magic.wogia.mapper.IRolePowerMapper;

/**
 * 
 * 功能：查询角色
 * 编写人员：lzh
 * 时间：2016年9月5日上午11:00:54
 */
@Service
public class RoleService {

	@Resource
	private IRoleMapper roleMapper;
	@Resource
	private IRolePowerMapper rolePowerMapper;
	public List<RoleBean> findRole(UserBean user){
		List<RoleBean> roleList = roleMapper.findRole(user.getId());
		return roleList;
	}
	
	public List<RoleBean> queryByUserId(UserBean user){
		List<RoleBean> roleList = roleMapper.queryByUserId(user.getId());
		/*for (RoleBean roleBean : roleList) {
			roleBean.getPowers().addAll(rolePowerMapper.findByRoleId(roleBean.getId()));
		}*/
		return roleList;
	}
	
}
