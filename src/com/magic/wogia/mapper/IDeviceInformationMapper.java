package com.magic.wogia.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.DeviceInformationBean;

/**
 * 
 * 功能：设备信息接口
 * 编写人员：lzh
 * 时间：2016年9月12日上午11:47:38
 */
public interface IDeviceInformationMapper {

	/**
	 * 获取设备信息列表
	 */
	List<DeviceInformationBean> getDeviceInformationList(Map<String, Object> params);

	/**
	 * 获取设备信息数量
	 */
	int getDeviceInformationListCount(Map<String, Object> params);

	/**
	 * 删除设备信息
	 */
	void deleteTDeviceInformation(String id);

	/**
	 * 添加设备信息
	 */
	void addDeviceInformation(DeviceInformationBean data);

	/**
	 * 编辑设备信息
	 */
	void updateTDeviceInformation(DeviceInformationBean data);

	/**
	 * 查询设备信息
	 */
	DeviceInformationBean findDeviceInformationByDeviceId(@Param("deviceId")Integer deviceId);
	/**
	 * 删除泵站信息
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
	
	
}

