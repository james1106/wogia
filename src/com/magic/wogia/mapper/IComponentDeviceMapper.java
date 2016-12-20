package com.magic.wogia.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ComponentDeviceBean;

/**
 * 
 * 功能：分区零件接口
 * 编写人员：lzh
 * 时间：2016年9月13日上午10:26:35
 */
public interface IComponentDeviceMapper {

	/**
	 * 查询分区零件列表
	 * @param map
	 * @return
	 */
	List<ComponentDeviceBean> findComponentDevice(Map<String,Object> map);
	/**
	 * 保养日历
	 * @param map
	 * @return
	 */
	List<ComponentDeviceBean> maintainCalendar(Map<String,Object> map);
	/**
	 * 新增分区零件
	 * @param record
	 * @return
	 */
    int addComponentDevice(ComponentDeviceBean record);
    /**
     * 更新分区零件信息
     * @param record
     * @return
     */
    int updComponentDevice(ComponentDeviceBean record);
    /**
     * 批量更新保养时间
     * @param ids
     * @return
     */
    Integer updMaintainTime(@Param("ids")List<Integer> ids,@Param("updateTime")Date updateTime);
    
    /**
     *  查询所有零件
     * @return
     */
    List<ComponentDeviceBean> queryAll();
    
    /**
     * 删除分区零件
     * @param projectId
     */
    void deleteByProjectId(@Param("projectId")Integer projectId);
    
    
}