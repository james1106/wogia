package com.magic.wogia.bean;

/**
 * 
 * 功能：零件库实体
 * 编写人员：lzh
 * 时间：2016年9月12日下午5:55:06
 */
public class ComponentBean {
	
    private Integer id;
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

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName == null ? null : componentName.trim();
    }
}