package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  本地数据库 项目表名 实体
 * @author QimouXie
 *
 */
public class CSProjectBean implements Serializable {

	private static final long serialVersionUID = -3142817605210998066L;
	/**主键ID*/
	private Integer id;
	/**表名*/
	private String tableName;
	/**同步时间*/
	private Date synTime = new Date();
	/**是否被绑定 0 否 1 是*/
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSProjectBean other = (CSProjectBean) obj;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	public Date getSynTime() {
		return synTime;
	}
	public void setSynTime(Date synTime) {
		this.synTime = synTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
