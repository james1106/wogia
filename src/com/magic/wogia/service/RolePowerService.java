package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.RolePowerBean;
import com.magic.wogia.mapper.IRolePowerMapper;

/**
 * 
 * 功能：角色  --- 权限
 * 编写人员：lzh
 * 时间：2016年10月9日下午3:06:45
 */
@Service
public class RolePowerService {

	@Resource
	private IRolePowerMapper rolePowerMapper;
	
	public void updatePower(Integer roleId, List<Integer> ids) throws Exception {
		
		rolePowerMapper.delRolePower(roleId);
		if(ids.size() == 0){
			return;
		}
		List<RolePowerBean> rolePowers = new ArrayList<RolePowerBean>();
		for (Integer powerId : ids) {
			RolePowerBean rolePower = new RolePowerBean();
			rolePower.setRoleId(roleId);
			rolePower.setPowerId(powerId);
			rolePowers.add(rolePower);
		}
		rolePowerMapper.addBatch(rolePowers);
	}

}
