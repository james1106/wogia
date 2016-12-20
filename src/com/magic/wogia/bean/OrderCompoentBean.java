package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：订单零件实体
 * 编写人员：lzh
 * 时间：2016年9月21日下午2:40:19
 */
public class OrderCompoentBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 订单id
	 */
	private Integer orderId;
	/**
	 * 零件id
	 */
	private Integer componentId;
	/**
	 * 零件名
	 */
	private String componentName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getComponentId() {
		return componentId;
	}
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	
}
