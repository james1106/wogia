package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 功能：保养/维修记录实体
 * 编写人员：lzh
 * 时间：2016年9月13日下午3:58:40
 */
public class ComponentDeviceRecordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 保养/维修时间
     */
    private Date createTime;
    /**
     * 保养/维修人员id
     */
    private Integer userId;
    /**
     * 保养/维修人员
     */
    private String  realName;
    /**
     * 类型：  保养 ：1  维修 ：0
     */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
    
}