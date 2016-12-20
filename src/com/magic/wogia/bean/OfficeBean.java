package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：办事处实体
 * 编写人员：lzh
 * 时间：2016年9月2日下午5:04:24
 */
public class OfficeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 办事处名称
	 */
	private String officeName;
	/**
	 * 办事处地址
	 */
	private String officeAddress;
	/**
	 * 外键片区id
	 */
	private Integer companyId;
	/**
	 * 片区名
	 */
	private String companyName;
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
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
}
