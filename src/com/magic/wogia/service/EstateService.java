package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.EstateBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.IEstateMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：物业实现
 * 编写人员：lzh
 * 时间：2016年9月5日下午5:27:24
 */
@Service
public class EstateService {

	@Resource
	private IEstateMapper estateMapper;
	
	/**
	 * 添加或者更新物业
	 * @param office
	 */
	@Transactional
	public void addOrUpdEstate(EstateBean estate){
		if (estate.getId() != null) {
			estateMapper.updEstate(estate);
		}else{
			estateMapper.addEstate(estate);
		}
	}
	
	
	/**
	 * 查询物业
	 * @param Office
	 * @return
	 */
	public List<EstateBean> findEstate(EstateBean estate,Integer pageNum,Integer pageSize,UserBean user){
		Map<String, Object> map = new HashMap<String, Object>();
		//查询自己所属物业 
		if (user.getIdType() == StatusConstant.ESTATE_USER) {
			estate.setId(user.getCompanyId());
		}
		//水厂人员查询
		if (user.getIdType() == StatusConstant.WATER_FACTORY_USER) {
			estate.setWaterWorkId(user.getCompanyId());
		}
		//办事处人员查询
		if (user.getIdType() == StatusConstant.OFFICE_USER) {
			map.put("officeId", user.getCompanyId());
		}
		//片区人员查询
		if (user.getIdType() == StatusConstant.AREA_USER) {
			map.put("companyId", user.getCompanyId());
		}
		map = EntityToMap.setConditionMap(estate);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<EstateBean> estateList = estateMapper.findEstate(map);
		return estateList;
	}
	
	public EstateBean queryEstate(Integer id){
		return estateMapper.queryById(id);
	}
	
}
