package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：泵房信息
 * 编写人员：lzh
 * 时间：2016年9月26日下午3:50:33
 */
public class PumpHouseBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**噪音（dB）*/
	private String noise;
	/**泵房-湿度（%RH）*/
	private String humidity;
	/**泵房-温度（℃）*/
	private String temperature;
	/**泵房-火险 0-正常1-故障*/
	private String fireStatus;
	/**泵房-门禁 0-正常1-故障*/
	private String guardStatus;
	/**泵房-排水系统故障 0-正常1-故障*/
	private String systemStatus;
	/**泵房-水箱缺水 0-正常2-高液位3-溢流*/
	private String boxStatus;
	/**稳流罐 0-正常1-故障*/
	private String steadyFlowTank;
	public String getNoise() {
		return noise;
	}
	public void setNoise(String noise) {
		this.noise = noise;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getFireStatus() {
		return fireStatus;
	}
	public void setFireStatus(String fireStatus) {
		this.fireStatus = fireStatus;
	}
	public String getGuardStatus() {
		return guardStatus;
	}
	public void setGuardStatus(String guardStatus) {
		this.guardStatus = guardStatus;
	}
	public String getSystemStatus() {
		return systemStatus;
	}
	public void setSystemStatus(String systemStatus) {
		this.systemStatus = systemStatus;
	}
	public String getBoxStatus() {
		return boxStatus;
	}
	public void setBoxStatus(String boxStatus) {
		this.boxStatus = boxStatus;
	}
	public String getSteadyFlowTank() {
		return steadyFlowTank;
	}
	public void setSteadyFlowTank(String steadyFlowTank) {
		this.steadyFlowTank = steadyFlowTank;
	}
	
	
	
	
}
