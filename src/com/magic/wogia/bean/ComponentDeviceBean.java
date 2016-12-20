package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 功能：分区零件实体
 * 编写人员：lzh
 * 时间：2016年9月13日上午10:27:03
 */
public class ComponentDeviceBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 零件id
     */
    private Integer componentId;
    /**
     * 零件名
     */
    private String componentName;
    /**
     * 分区id
     */
    private Integer deviceId;
    /**
     * 分区名
     */
    private String deviceName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 保养时间
     */
    private Date maintainTime;
    private String maintainTimes;
    /**
     * 零件更换(或开始使用时间)时间
     */
    private Date replaceTime;
    private String replaceTimes;
    /**
     * 零件寿命(天)
     */
    private Integer life;
    /**
     * 零件描述
     */
    private String describe;
    /**
     * 零件是否进入维修/保养倒计时
     * 0：未进入   1：已进入
     */
    private Integer state = 0;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
	 * 项目地址
	 */
	private String projectAddress;
	/**
	 * 供应商
	 */
	private String supplier;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public Date getReplaceTime() {
        return replaceTime;
    }

    public void setReplaceTime(Date replaceTime) {
        this.replaceTime = replaceTime;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getMaintainTimes() {
		return maintainTimes;
	}

	public void setMaintainTimes(String maintainTimes) {
		this.maintainTimes = maintainTimes;
	}

	public String getReplaceTimes() {
		return replaceTimes;
	}

	public void setReplaceTimes(String replaceTimes) {
		this.replaceTimes = replaceTimes;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
    
}