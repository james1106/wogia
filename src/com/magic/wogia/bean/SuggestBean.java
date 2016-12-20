package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  意见反馈
 * @author QimouXie
 *
 */
public class SuggestBean implements Serializable {

	private static final long serialVersionUID = 1015738881517424134L;
	
	/**主键ID*/
	private Integer id;
	
	/**反馈内容*/
	private String content;
	
	/**用户ID*/
	private Integer userId;
	
	/**反馈时间*/
	private Date createTime = new Date();
	/**反馈者*/
	private String userName;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
