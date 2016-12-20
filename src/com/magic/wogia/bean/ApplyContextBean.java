package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：申请文字实体
 * 编写人员：lzh
 * 时间：2016年9月28日下午3:40:58
 */
public class ApplyContextBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**
	 * 内容
	 */
	private String context;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
