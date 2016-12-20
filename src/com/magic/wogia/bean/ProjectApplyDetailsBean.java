package com.magic.wogia.bean;

import java.util.Date;

/**
 * 
 * 功能：专项服务详细实体
 * 编写人员：lzh
 * 时间：2016年9月28日上午10:13:23
 */
public class ProjectApplyDetailsBean {
    /**
	 * id
	 */
	private Integer id;

	/**
	 * 项目申请表id
	 */
	private Integer applyId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 改造前功率
	 */
	private String frontPower;

	/**
	 * 改造后功率
	 */
	private String afterPower;

	/**
	 * 改造前电费
	 */
	private String frontMoney;

	/**
	 * 改造后电费
	 */
	private String afterMoney;

	/**
	 * 季度维保费用
	 */
	private String maintenanceMoney;

	/**
	 * 下次缴费时间
	 */
	private String nextPayTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFrontPower() {
        return frontPower;
    }

    public void setFrontPower(String frontPower) {
        this.frontPower = frontPower == null ? null : frontPower.trim();
    }

    public String getAfterPower() {
        return afterPower;
    }

    public void setAfterPower(String afterPower) {
        this.afterPower = afterPower == null ? null : afterPower.trim();
    }

    public String getFrontMoney() {
        return frontMoney;
    }

    public void setFrontMoney(String frontMoney) {
        this.frontMoney = frontMoney == null ? null : frontMoney.trim();
    }

    public String getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(String afterMoney) {
        this.afterMoney = afterMoney == null ? null : afterMoney.trim();
    }

    public String getMaintenanceMoney() {
        return maintenanceMoney;
    }

    public void setMaintenanceMoney(String maintenanceMoney) {
        this.maintenanceMoney = maintenanceMoney == null ? null : maintenanceMoney.trim();
    }

    public String getNextPayTime() {
        return nextPayTime;
    }

    public void setNextPayTime(String nextPayTime) {
        this.nextPayTime = nextPayTime;
    }
}