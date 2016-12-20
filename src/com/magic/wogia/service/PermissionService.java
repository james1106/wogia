package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.PermissionBean;
import com.magic.wogia.mapper.IPermissionMapper;

/**
 * 
 * 功能：权限业务实现
 * 编写人员：lzh
 * 时间：2016年10月9日下午3:06:01
 */
@Service
public class PermissionService {

	@Resource
	private IPermissionMapper permissionMapper;
	
	public List<PermissionBean> findAll() {
		return permissionMapper.findAll();
	}

	public List<PermissionBean> queryPowerByRole(Integer roleId) {
		return permissionMapper.queryPowerByRole(roleId);
	}

}
