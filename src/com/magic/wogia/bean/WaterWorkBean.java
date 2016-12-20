package com.magic.wogia.bean;

import java.io.Serializable;


/**
 * 
 * 功能：水厂实体类
 * 编写人员：lzh
 * 时间：2016年9月5日下午1:36:50
 */
public class WaterWorkBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 水厂名
     */
    private String waterworkName;
    /**
     * 水厂地址
     */
    private String waterworkAddress;
    /**
     * 办事处id
     */
    private Integer officeId;
    /**
     * 办事处名
     */
    private String officeName;
    /**
	 * 状态  0：可用  1不可用
	 */
	private Integer state;
	/**
	 * 编号
	 */
	private String number;
	/**
	 * 水厂官网地址
	 */
	private String waterUrl;
	/**
	 * 地址描述
	 */
	private String addressDescribe;
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWaterworkName() {
        return waterworkName;
    }

    public void setWaterworkName(String waterworkName) {
        this.waterworkName = waterworkName == null ? null : waterworkName.trim();
    }

    public String getWaterworkAddress() {
        return waterworkAddress;
    }

    public void setWaterworkAddress(String waterworkAddress) {
        this.waterworkAddress = waterworkAddress == null ? null : waterworkAddress.trim();
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getWaterUrl() {
		return waterUrl;
	}

	public void setWaterUrl(String waterUrl) {
		this.waterUrl = waterUrl;
	}

	public String getAddressDescribe() {
		return addressDescribe;
	}

	public void setAddressDescribe(String addressDescribe) {
		this.addressDescribe = addressDescribe;
	}
    
}