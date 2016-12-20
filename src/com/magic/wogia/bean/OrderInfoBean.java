package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   订单 通知信息
 * @author QimouXie
 *
 */
public class OrderInfoBean implements Serializable {

	private static final long serialVersionUID = 5207382019951726103L;

	/**主键D*/
	private Integer id;
	/**订单ID*/
	private Integer orderId;
	/**被推送者*/
	private Integer toUserId;
	/**推送消息*/
	private String infoMsg;
	/**创建时间*/
	private Date createTime = new Date();
	/**订单号*/
	private String orderNumber;

	public OrderInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
