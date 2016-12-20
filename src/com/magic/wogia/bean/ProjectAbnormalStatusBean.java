package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 功能：异常项目
 * 编写人员：lzh
 * 时间：2016年9月27日下午3:43:35
 */
public class ProjectAbnormalStatusBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 项目名
	 */
	private String projectName;
	/**
	 * 项目地址
	 */
	private String projectAddress;
	/**
	 * 异常分区
	 */
	private List<DeviceAbnormalStatusBean> dasbList;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<DeviceAbnormalStatusBean> getDasbList() {
		return dasbList;
	}

	public void setDasbList(List<DeviceAbnormalStatusBean> dasbList) {
		this.dasbList = dasbList;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}
	
	
}
