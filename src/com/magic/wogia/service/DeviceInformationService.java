package com.magic.wogia.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.DeviceInformationBean;
import com.magic.wogia.bean.WaterWorkBean;
import com.magic.wogia.mapper.IDeviceInformationMapper;
import com.magic.wogia.mapper.IWaterWorkMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.Timestamp;

/**
 * 
 * 功能：设备信息业务实现
 * 编写人员：lzh
 * 时间：2016年9月12日下午1:51:40
 */
@Service
public class DeviceInformationService {

	@Resource
	private IDeviceInformationMapper deviceInformationMapper;
	@Resource
	private IWaterWorkMapper waterWorkMapper;
	/**
	 * 根据分区id查询设备信息
	 * @param deviceId
	 * @return
	 * @throws ParseException 
	 */
	public DeviceInformationBean findDeviceInformationByDeviceId(Integer deviceId) throws ParseException{
		DeviceInformationBean dib = deviceInformationMapper.findDeviceInformationByDeviceId(deviceId);
		if (null != dib && dib.getManufactureDate() != null) {
			dib.setManufactureDate(Timestamp.TimeStamp2Date(String.valueOf(Timestamp.timesStr(dib.getManufactureDate(), "yyyy-MM-dd")), "yyyy-MM-dd"));
		}
		return dib;
	}
	
	/**
	 * 添加设备信息
	 * @param data
	 */
	@Transactional
	public void addOrUpdDeviceInformation(DeviceInformationBean data,Integer type){
		if (type == 1) {
			deviceInformationMapper.updateTDeviceInformation(data);
		}else{
			deviceInformationMapper.addDeviceInformation(data);
		}
		//更新水厂描述地址
		WaterWorkBean record = new WaterWorkBean();
		record.setAddressDescribe(data.getSuperviseAddress());
		record.setId(data.getSuperviseUnitId());
		waterWorkMapper.updWaterWork(record);
	}
	
	/**
	 * 查询设备数量
	 * @param data
	 * @return
	 */
	public int getDeviceInformationListCount(DeviceInformationBean data){
		Map<String, Object> params = new HashMap<String, Object>();
		params = EntityToMap.setConditionMap(data);
		return deviceInformationMapper.getDeviceInformationListCount(params);
	}
	
}
