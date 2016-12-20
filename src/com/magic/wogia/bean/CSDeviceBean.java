package com.magic.wogia.bean;

import java.io.Serializable;

/**
 *  本地数据库 设备实体
 * @author QimouXie
 *
 */
public class CSDeviceBean implements Serializable {

	private static final long serialVersionUID = 3917716246753977900L;
	
	/**主键Id*/
	private Integer id;
	
	/**设备名称 生成规则 表名|device| ***/
	private String deviceName;
	
	/**所属表 名*/
	private String tableName;
	
	/**绑定状态*/
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
				+ ((deviceName == null) ? 0 : deviceName.hashCode());
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
		CSDeviceBean other = (CSDeviceBean) obj;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		return true;
	}

	
}
