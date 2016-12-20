package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.List;

import com.magic.wogia.bean.RolePowerBean;

/**
 * 
 * 功能：角色
 * 编写人员：lzh
 * 时间：2016年9月1日下午2:49:07
 */
public class RoleBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 角色名
	 */
	private String role;
	/**
	 * 角色说明
	 */
	private String decript;

	/** 该角色对应的权限*/
	private List<RolePowerBean> powers;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDecript() {
		return decript;
	}

	public void setDecript(String decript) {
		this.decript = decript;
	}

	public List<RolePowerBean> getPowers() {
		return powers;
	}

	public void setPowers(List<RolePowerBean> powers) {
		this.powers = powers;
	}
	
	
}
