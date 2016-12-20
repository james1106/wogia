package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能：保养日历分区实体
 * 编写人员：lzh
 * 时间：2016年9月30日下午4:42:12
 */
public class DeviceMaintainCalendarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 分区id
	 */
	private Integer id;
	/**
	 * 分区名
	 */
	private String deviceName;
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 供应商
	 */
	private String supplier;
	/**
	 * 需要保养的零件
	 */
	private List<ComponentDeviceBean> cdList = new ArrayList<ComponentDeviceBean>();;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public List<ComponentDeviceBean> getCdList() {
		return cdList;
	}
	public void setCdList(List<ComponentDeviceBean> cdList) {
		this.cdList = cdList;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DeviceMaintainCalendarBean other = (DeviceMaintainCalendarBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
