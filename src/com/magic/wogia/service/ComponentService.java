package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.ComponentBean;
import com.magic.wogia.mapper.IComponentMapper;
import com.magic.wogia.util.EntityToMap;

/**
 * 
 * 功能：零件库业务实现
 * 编写人员：lzh
 * 时间：2016年9月13日上午9:48:21
 */
@Service
public class ComponentService {

	@Resource
	private IComponentMapper componentMapper;
	
	/**
	 * 添加或者更新零件
	 * @param record
	 */
	@Transactional
	public void addOrUpdComponent(ComponentBean record){
		if (record.getId() != null) {
			componentMapper.updComponent(record);
		}else{
			componentMapper.addComponent(record);
		}
		
	}
	
	/**
	 * 零件列表
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ComponentBean> findComponent(ComponentBean record,Integer pageNum,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(record);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		return componentMapper.findComponent(map);
	}
}
