package com.magic.wogia.bean;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 *  订单追踪 数据
 * @author QimouXie
 *
 */
public class OrderTrackBean implements Serializable {

	private static final long serialVersionUID = -2598651186904724624L;
	
	private String title;
	
	private String content;
	
	private Long createDate;
	
	

	public OrderTrackBean(String title, String content, Long createDate) {
		super();
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}

	public String getStatus() {
		return title;
	}

	public void setStatus(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	public JSONObject createOrderTrack(){
		JSONObject temp = new JSONObject();
		temp.put("title", title);
		temp.put("content", content);
		temp.put("createtime", createDate);
		return temp;
	}
	
	

}
