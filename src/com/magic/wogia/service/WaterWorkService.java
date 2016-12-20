package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.magic.wogia.bean.UserBean;
import com.magic.wogia.bean.WaterWorkBean;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.mapper.IWaterWorkMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：水厂功能业务实现
 * 编写人员：lzh
 * 时间：2016年9月5日下午2:26:21
 */
@Service
public class WaterWorkService {

	@Resource
	private IWaterWorkMapper waterWorkMapper;
	
	/**
	 * 添加或者更新水厂
	 * @param office
	 */
	@Transactional
	public void addOrUpdWaterWork(WaterWorkBean waterWork){
		if (waterWork.getId() != null) {
			waterWorkMapper.updWaterWork(waterWork);
		}else{
			waterWorkMapper.addWaterWork(waterWork);
		}
	}
	
	
	/**
	 * 查询水厂
	 * @param waterWork
	 * @return
	 */
	public List<WaterWorkBean> findWaterWork(WaterWorkBean waterWork,Integer pageNum,Integer pageSize,UserBean uesr){
		//没有权限
		if (uesr.getIdType() > StatusConstant.WATER_FACTORY_USER) {
			throw new InterfaceCommonException(StatusConstant.NON_ALLOW, ErrorMessage.NON_ALLOW);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//片区人员查询
		if (uesr.getIdType() == StatusConstant.AREA_USER ) {
			map.put("companyId", uesr.getCompanyId());
		}
		//办事处人员查询
		if (uesr.getIdType() == StatusConstant.OFFICE_USER ) {
			waterWork.setOfficeId(uesr.getCompanyId());
		}
		//查询自己所属水厂
		if (uesr.getIdType() == StatusConstant.OFFICE_USER ) {
			waterWork.setId(uesr.getCompanyId());
		}
		map = EntityToMap.setConditionMap(waterWork);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<WaterWorkBean> waterWorkList = waterWorkMapper.findWaterWork(map);
		return waterWorkList;
	}
	
	
	/**
	 * 游客查询水厂  
	 * @param waterWork
	 * @return
	 */
	public List<WaterWorkBean> findWaterWork(WaterWorkBean waterWork,Integer pageNum,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(waterWork);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<WaterWorkBean> waterWorkList = waterWorkMapper.findWaterWork(map);
		return waterWorkList;
	}
	
	
	/**
	 *  查询水厂所有字段
	 * @param id
	 * @return
	 */
	public WaterWorkBean queryWaterWork(Integer id){
		return waterWorkMapper.queryById(id);
	}
	/**
     * 根据项目id查询水厂
     * @param projectId
     * @return
     */
	public List<WaterWorkBean> findWaterByProId(Integer projectId){
		return waterWorkMapper.findWaterByProId(projectId);
	}
}
