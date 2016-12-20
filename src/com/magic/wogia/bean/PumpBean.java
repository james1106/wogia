package com.magic.wogia.bean;

import java.io.Serializable;

/**
 *  泵信息 业务实体
 * @author QimouXie
 *
 */
public class PumpBean implements Serializable {

	private static final long serialVersionUID = -4602646850668253672L;
	/**泵名称*/
	private String pumpName;
	/**泵状态*/
	private String pumpStatus;
	/**温度*/
	private String temperature;
	/**温度状态*/
	private String temperatureStatus;
	/**累计运行时间*/
	private String runningTime;
	public String getPumpName() {
		return pumpName;
	}
	public void setPumpName(String pumpName) {
		this.pumpName = pumpName;
	}
	public String getPumpStatus() {
		return pumpStatus;
	}
	public void setPumpStatus(String pumpStatus) {
		this.pumpStatus = pumpStatus;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getTemperatureStatus() {
		return temperatureStatus;
	}
	public void setTemperatureStatus(String temperatureStatus) {
		this.temperatureStatus = temperatureStatus;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	
	

}
