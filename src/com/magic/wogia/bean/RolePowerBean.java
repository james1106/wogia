package com.magic.wogia.bean;

import java.io.Serializable;

/**
 *  角色 - 权限
 * @author QimouXie
 *
 */
public class RolePowerBean implements Serializable {

	private static final long serialVersionUID = 990215162955493463L;
	
	/**主键*/
	private Integer id;
	
	/**角色
	private RoleBean role;*/
	
	/**权限*/
	private PermissionBean power;
	
	private Integer roleId;
	
	private Integer powerId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	//	public RoleBean getRole() {
//		return role;
//	}
//	public void setRole(RoleBean role) {
//		this.role = role;
//	}
	public PermissionBean getPower() {
		return power;
	}
	public void setPower(PermissionBean power) {
		this.power = power;
	}
	@Override
	public String toString() {
		return "RolePowerBean [id=" + id + ", power=" + power + "]";
	}
	
	
	
	
	

}
