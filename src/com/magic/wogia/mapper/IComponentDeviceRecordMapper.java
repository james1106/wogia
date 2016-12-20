package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import com.magic.wogia.bean.ComponentDeviceRecordBean;

/**
 * 
 * 功能：保养/维修记录功能接口
 * 编写人员：lzh
 * 时间：2016年9月13日下午4:03:06
 */
public interface IComponentDeviceRecordMapper {

	/**
	 * 添加记录
	 * @param record
	 * @return
	 */
    int addComponentDeviceRecord(ComponentDeviceRecordBean record);
    /**
     * 更新记录
     * @param record
     * @return
     */
    int updComponentDeviceRecord(ComponentDeviceRecordBean record);
    /**
     * 查询记录
     * @param map
     * @return
     */
    List<ComponentDeviceRecordBean> findComponentDeviceRecord(Map<String,Object> map);


}