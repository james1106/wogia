package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 功能：设备实体
 * 编写人员：lzh
 * 时间：2016年9月6日下午3:33:15
 */
public class DeviceBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id、
	 */
    private Integer id;
    /**
     * 设备名
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * ..
     */
    private String tableDevice;
    /**
     * 设备创建时间
     */
    private Date deviceCreatetime;
    //明文创建时间
    private String deviceCreatetimes;
    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名
     */
    private String projectName;
    /**
     * 图片地址，逗号隔开
     */
    private String waterImg;
    private List<ComponentDeviceBean> components;
    
    public List<ComponentDeviceBean> getComponents() {
		return components;
	}

	public void setComponents(List<ComponentDeviceBean> components) {
		this.components = components;
	}

    /**
	 * 供应商
	 */
	private String supplier;
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
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getTableDevice() {
        return tableDevice;
    }

    public void setTableDevice(String tableDevice) {
        this.tableDevice = tableDevice == null ? null : tableDevice.trim();
    }

    public Date getDeviceCreatetime() {
        return deviceCreatetime;
    }

    public void setDeviceCreatetime(Date deviceCreatetime) {
        this.deviceCreatetime = deviceCreatetime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

	public String getDeviceCreatetimes() {
		return deviceCreatetimes;
	}

	public void setDeviceCreatetimes(String deviceCreatetimes) {
		this.deviceCreatetimes = deviceCreatetimes;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWaterImg() {
		return waterImg;
	}

	public void setWaterImg(String waterImg) {
		this.waterImg = waterImg;
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
		DeviceBean other = (DeviceBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}
