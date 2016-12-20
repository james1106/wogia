package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 功能：设备各种异常状态 编写人员：lzh 时间：2016年9月27日下午3:33:55
 */
public class DeviceAbnormalStatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 分区名
	 */
	private String deviceName;
	/**
	 * 分区id
	 */
	private Integer id;
	
	/**
	 * 稳流罐异常0-正常1-故障
	 */
	private String steadyFlowTankStatus;

	/**
	 * 泵房-水箱缺水 0-正常2-高液位3-溢流
	 */
	private String boxStatus;

	/**
	 * 泵房-火险 0-正常1-故障
	 */
	private String fireStatus;

	/**
	 * 泵房-门禁 0-正常1-故障
	 */
	private String guardStatus;
	/**
	 * 泵房-排水系统故障 0-正常1-故障
	 */
	private String systemStatus;
	/**
	 * 出口压力超高 0-正常1-故障
	 */
	private String outWaterStatus;
	/**
	 * 1#变频器故障0-正常1-故障
	 */
	private String frequencyStatus;
	/**
	 * 2#变频器故障0-正常1-故障
	 */
	private String frequencyTwoStatus;
	/**
	 * 3#变频器故障0-正常1-故障
	 */
	private String frequencyThreeStatus;

	/**
	 * 4#变频器故障0-正常1-故障
	 */
	private String frequencyFourStatus;

	/**
	 * 5#变频器故障0-正常1-故障
	 */
	private String frequencyFiveStatus;

	/**
	 * 6#变频器故障0-正常1-故障
	 */
	private String frequencySixStatus;
	/**
	 * 泵信息
	 */
	private List<PumpBean> pumpList;
	/**
	 * 供应商
	 */
	private String supplier;
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSteadyFlowTankStatus() {
		return steadyFlowTankStatus;
	}

	public void setSteadyFlowTankStatus(String steadyFlowTankStatus) {
		this.steadyFlowTankStatus = steadyFlowTankStatus;
	}

	public String getBoxStatus() {
		return boxStatus;
	}

	public void setBoxStatus(String boxStatus) {
		this.boxStatus = boxStatus;
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

	public String getOutWaterStatus() {
		return outWaterStatus;
	}

	public void setOutWaterStatus(String outWaterStatus) {
		this.outWaterStatus = outWaterStatus;
	}

	public String getFrequencyStatus() {
		return frequencyStatus;
	}

	public void setFrequencyStatus(String frequencyStatus) {
		this.frequencyStatus = frequencyStatus;
	}

	public String getFrequencyTwoStatus() {
		return frequencyTwoStatus;
	}

	public void setFrequencyTwoStatus(String frequencyTwoStatus) {
		this.frequencyTwoStatus = frequencyTwoStatus;
	}

	public String getFrequencyThreeStatus() {
		return frequencyThreeStatus;
	}

	public void setFrequencyThreeStatus(String frequencyThreeStatus) {
		this.frequencyThreeStatus = frequencyThreeStatus;
	}

	public String getFrequencyFourStatus() {
		return frequencyFourStatus;
	}

	public void setFrequencyFourStatus(String frequencyFourStatus) {
		this.frequencyFourStatus = frequencyFourStatus;
	}

	public String getFrequencyFiveStatus() {
		return frequencyFiveStatus;
	}

	public void setFrequencyFiveStatus(String frequencyFiveStatus) {
		this.frequencyFiveStatus = frequencyFiveStatus;
	}

	public String getFrequencySixStatus() {
		return frequencySixStatus;
	}

	public void setFrequencySixStatus(String frequencySixStatus) {
		this.frequencySixStatus = frequencySixStatus;
	}

	public List<PumpBean> getPumpList() {
		return pumpList;
	}

	public void setPumpList(List<PumpBean> pumpList) {
		this.pumpList = pumpList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
