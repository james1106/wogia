package com.magic.wogia.bean;

import java.io.Serializable;
/**
 * 
 * 功能：物业
 * 编写人员：lzh
 * 时间：2016年9月7日下午1:31:57
 */
public class EstateBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    /**
     * 物业地址
     */
    private String estateAddress;

    /**
     * 物业名
     */
    private String estateName;
    
    /**
     * 水厂id
     */
    private Integer waterWorkId;
    /**
     * 水厂名
     */
    private String waterWorkName;
    
    
    /**
     * 是否可用 0：可用  1：不可用
     */
    private Integer state;
    /**
	 * 编号
	 */
	private String number;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstateAddress() {
        return estateAddress;
    }

    public void setEstateAddress(String estateAddress) {
        this.estateAddress = estateAddress == null ? null : estateAddress.trim();
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
    }

    public Integer getWaterWorkId() {
        return waterWorkId;
    }

    public void setWaterWorkId(Integer waterWorkId) {
        this.waterWorkId = waterWorkId;
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

	public String getWaterWorkName() {
		return waterWorkName;
	}

	public void setWaterWorkName(String waterWorkName) {
		this.waterWorkName = waterWorkName;
	}
    
}