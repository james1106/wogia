package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：(公司)片区实体类
 * 编写人员：lzh
 * 时间：2016年9月2日上午10:14:21
 */
public class CompanyBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 片区名
	 */
	private String companyName;
	/**
	 * 片区地址
	 */
	private String companyAddress;
	/**
	 * 地区外键id
	 */
	private Integer cityId;
	/**
	 * 地区名
	 */
	private String cityName;
	/**
	 * 状态  0：可用  1不可用
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

	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
}
