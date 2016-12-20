package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  系统消息 实体
 * @author QimouXie
 *
 */
public class SystemInfoBean implements Serializable {

	private static final long serialVersionUID = 6813911663821750275L;
	
	/**主键ID*/
	private Integer id;
	
	/**Title*/
	private String title;
	
	/**内容*/
	private String content;
	
	/**创建时间*/
	private Date createTime = new Date();
	
	/**摘要*/
	private String brief;
	
	/**发布者ID*/
	private Integer publisherId;
	
	/**发布人*/
	private String publisherName;
	
	/**
	 * 类型：0：系统公告 1：停水公告  2: 到期零件推送消息
	 */
	private Integer type;
	
	/**项目ID集合*/
	private String projectIds;
	/**设备ID集合*/
	private String deviceIds;
	/**零件ID集合*/
	private String comIds;
	/** 当推送零件消息的时候，被推送的人*/
	private Integer toUser;
	
	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public String getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getComIds() {
		return comIds;
	}

	public void setComIds(String comIds) {
		this.comIds = comIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
