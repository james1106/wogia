package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.ComponentDeviceRecordBean;
import com.magic.wogia.mapper.IComponentDeviceRecordMapper;
import com.magic.wogia.util.EntityToMap;

/**
 * 
 * 功能：维修/保养业务实现
 * 编写人员：lzh
 * 时间：2016年9月13日下午4:55:32
 */
@Service
public class ComponentDeviceRecordService {

	@Resource
	private IComponentDeviceRecordMapper componentDeviceRecordMapper;
	
	/**
	 * 查询零件维修/保养记录
	 * @param bean
	 * @return
	 */
	public List<ComponentDeviceRecordBean> findComponentDeviceRecord(ComponentDeviceRecordBean bean,Integer pageNum,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(bean);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		return componentDeviceRecordMapper.findComponentDeviceRecord(map);
	}
	
	/**
	 * 添加零件维修/保养记录
	 * @param bean
	 */
	public void addComponentDeviceRecord(ComponentDeviceRecordBean bean){
		componentDeviceRecordMapper.addComponentDeviceRecord(bean);
	}
}
