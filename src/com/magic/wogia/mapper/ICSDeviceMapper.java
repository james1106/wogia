package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.CSDeviceBean;

/**
 *  本地数据库 设备表cs_ 持久层接口
 * @author QimouXie
 *
 */
public interface ICSDeviceMapper {
	
	/**
	 *  根据 表名 查询所有的 表下所有的 设备
	 * @param tableName
	 * @return
	 */
	List<CSDeviceBean> queryDeviceByTableName(@Param("tableName")String tableName,@Param("status")String status);
	
	/**
	 *  批量新增设备
	 * @param css
	 * @return
	 */
	Integer batchAdd(@Param("css")List<CSDeviceBean> css);
	
	/**
	 * 查询所有未被绑定的设备 
	 * @return
	 */
	public List<CSDeviceBean> findStatus(String tableName); 

	/**
	 * 更新设备绑定状态
	 * @param device
	 */
	public void updCsDevice(CSDeviceBean device);
	
	/**
	 * 还原更新分区数据表状态 
	 * @param projectId
	 */
	void updByProjectId(@Param("projectId")Integer projectId);
}
