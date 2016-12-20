package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 功能：专项服务申请实体
 * 编写人员：lzh
 * 时间：2016年9月28日上午9:53:26
 */
public class ProjectApplyBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目名
     */
    private String projectName;
    /**项目地址*/
    private String projectAddress;
    /**
     * 申请人id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 状态 0：已申请 1：已录入
     */
    private Integer status;
    

   
    public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	
    
}