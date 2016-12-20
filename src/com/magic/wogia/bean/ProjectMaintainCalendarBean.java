package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能：保养日历项目实体
 * 编写人员：lzh
 * 时间：2016年9月30日下午4:41:06
 */
public class ProjectMaintainCalendarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/***
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 项目名
	 */
	private String projectName;
	/**
	 * 项目地址
	 */
	private String projectAddress;
	
	/**
	 * 保养分区list
	 */
	private List<DeviceMaintainCalendarBean> dasbList = new ArrayList<DeviceMaintainCalendarBean>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public List<DeviceMaintainCalendarBean> getDasbList() {
		return dasbList;
	}

	public void setDasbList(List<DeviceMaintainCalendarBean> dasbList) {
		this.dasbList = dasbList;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMaintainCalendarBean other = (ProjectMaintainCalendarBean) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}
	
}
