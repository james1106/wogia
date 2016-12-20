package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  订单追加描述
 * @author QimouXie
 *
 */
public class OrderDescribeBean implements Serializable {
	
	private static final long serialVersionUID = 7609549597733071484L;
	/**主键ID*/
	private Integer id;
	/**预计时间*/
	private Date expectTime;
	/**描述内容*/
	private String content;
	/**类型*/
	private Integer type;
	/**订单ID*/
	private Integer orderId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getExpectTime() {
		return expectTime;
	}
	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
