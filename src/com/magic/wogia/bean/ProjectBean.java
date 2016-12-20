package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 
 * 功能：项目实体
 * 编写人员：lzh
 * 时间：2016年9月6日上午10:40:59
 */
public class ProjectBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id、
	 */
    private Integer id;
    
    private Integer applyId;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 姓名地址
     */
    private String projectAddress;

    /**
     * 物业id
     */
    private Integer estateId;
    /**
     * 物业名
     */
    private String estateName;

    /**
     * 项目表  外数据源
     */
    private Integer csProjectId;
    /**
     * 项目表名
     */
    private String tableName;

    /**
     * 泵站最高温度
     */
    private double pumpTemperatureMax;
    
    /**项目申请状态 0：已申请 1：已录入 2: 未申请*/
    private Integer applyStatus = 2;
    /***
     * 城市id
     */
    private Integer cityId;
    
    private Set<DeviceBean> devices;
    
    public Set<DeviceBean> getDevices() {
		return devices;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public void setDevices(Set<DeviceBean> devices) {
		this.devices = devices;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

	public Integer getCsProjectId() {
		return csProjectId;
	}

	public void setCsProjectId(Integer csProjectId) {
		this.csProjectId = csProjectId;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public double getPumpTemperatureMax() {
		return pumpTemperatureMax;
	}

	public void setPumpTemperatureMax(double pumpTemperatureMax) {
		this.pumpTemperatureMax = pumpTemperatureMax;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

    
}
