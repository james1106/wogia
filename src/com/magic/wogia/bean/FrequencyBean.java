package com.magic.wogia.bean;

import java.io.Serializable;

/**
 *  变频器 业务实体
 * @author QimouXie
 *
 */
public class FrequencyBean implements Serializable {

	private static final long serialVersionUID = 3868010558933495429L;
	
	/**电流*/
	private String electric;
	/**频率*/
	private String frequency;
	
	public String getElectric() {
		return electric;
	}
	public void setElectric(String electric) {
		this.electric = electric;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

}
