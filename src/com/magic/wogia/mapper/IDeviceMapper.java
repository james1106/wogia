package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.DeviceBean;

/**
 * 
 * 功能：设备功能接口
 * 编写人员：lzh
 * 时间：2016年9月6日下午3:38:58
 */
public interface IDeviceMapper {
	
	
	/**
	 * 添加设备
	 * @param record
	 */
	public void addDevice(DeviceBean record);

	/**
	 * 更新设备
	 * @param record
	 */
	public void updDevice(DeviceBean record);
	
	/**
	 * 查询设备
	 * @param map
	 * @return
	 */
	public List<DeviceBean> findDevice(Map<String,Object> map);
	
	/**
	 *  通过项目ID 查询设备 
	 * @param comIds 到期保养的零件ID
	 * @return
	 */
	List<DeviceBean> queryByProjectId(@Param("comIds")List<Integer> comIds,@Param("projectId")Integer projectId);
   
	/**
	 * 根据项目id删除分区
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
	
	/**
	 *  根据项目ID 查询设备信息
	 * @param projectId
	 * @return
	 */
	List<DeviceBean> queryByProject(@Param("projectId")Integer projectId);
	
}