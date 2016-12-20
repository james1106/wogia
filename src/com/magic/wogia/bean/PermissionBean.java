package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  权限
 * @author QimouXie
 *
 */
public class PermissionBean implements Serializable {

	private static final long serialVersionUID = 9088429739249006636L;
	
	/**主键*/
	private Integer id;
	
	/**请求路径*/
	private String url;
	
	/**权限的描述*/
	private String description;
	
	/**权限排序号*/
	private Integer sortNumber;
	
	/**角色ID*/
	private Integer roleId;
	
	/**权限对应的角色*/
	private List<RoleBean> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "PermissionBean [id=" + id + ", url=" + url + ", description="
				+ description + ", roles=" + roles + "]";
	}
	
	
	

	
	
}
