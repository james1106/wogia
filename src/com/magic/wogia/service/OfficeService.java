package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.OfficeBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.mapper.IOfficeMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：办事处业务实现
 * 编写人员：lzh
 * 时间：2016年9月2日下午6:05:05
 */
@Service
public class OfficeService {
	@Resource
	private IOfficeMapper officeMapper;
	
	/**
	 * 添加或者更新办事处
	 * @param office
	 */
	@Transactional
	public void addOrUpdOffice(OfficeBean office){
		if (office.getId() != null) {
			officeMapper.updOffice(office);
		}else{
			officeMapper.addOffice(office);
		}
	}
	
	
	/**
	 * 查询办事处
	 * @param Office
	 * @return
	 */
	public List<OfficeBean> findOffice(OfficeBean Office,Integer pageNum,Integer pageSize,UserBean user){
		//没有权限
		if (user.getIdType() > StatusConstant.OFFICE_USER) {
			throw new InterfaceCommonException(StatusConstant.NON_ALLOW, ErrorMessage.NON_ALLOW);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//办事处单位人员
		if (user.getIdType() == StatusConstant.OFFICE_USER) {
			map.put("id", user.getCompanyId());
		}
		//片区单位人员
		if (user.getIdType() == StatusConstant.AREA_USER) {
			map.put("companyId", user.getCompanyId());
		}
		map = EntityToMap.setConditionMap(Office);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<OfficeBean> officeList = officeMapper.findOffice(map);
		return officeList;
	}
	
	/**
	 *  查询办事处所有字段
	 * @param id
	 * @return
	 */
	public OfficeBean queryOffice(Integer id){
		return officeMapper.queryById(id);
	}
		
}
