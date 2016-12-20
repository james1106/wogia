package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.CompanyBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.mapper.ICompanyMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：片区业务实现
 * 编写人员：lzh
 * 时间：2016年9月2日下午12:53:10
 */
@Service
public class CompanyService {

	@Resource
	private ICompanyMapper companyMapper;
	
	/**
	 * 添加或者更新片区信息
	 * @param company
	 */
	@Transactional
	public void addOrUpdCompany(CompanyBean company){
		if (company.getId() != null) {
			companyMapper.updCompany(company);
		}else {
			companyMapper.addCompany(company);
		}
	}
	
	/**
	 * 查询片区
	 * @param company
	 * @return
	 */
	public List<CompanyBean> findCompany(CompanyBean company,Integer pageNum,Integer pageSize,UserBean user){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(company);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		//如果是片区单位人员  查询自己所属片区 
		if (user.getIdType() == StatusConstant.AREA_USER) {
			map.put("id", user.getCompanyId());
		}
		//如果是片区单位以下的人员   无权限查看片区信息
		if (user.getIdType() > StatusConstant.AREA_USER) {
			throw new InterfaceCommonException(StatusConstant.NON_ALLOW, ErrorMessage.NON_ALLOW);
		}
		List<CompanyBean> companyList = companyMapper.findCompany(map);
		return companyList;
	}
	
	public CompanyBean queryCompany(Integer id){
		return companyMapper.queryById(id);
	}
	
	
	
	
	public CompanyBean findCityId(Integer roleId ,Integer objectId){
		
		
		return companyMapper.findCityId(roleId,objectId);
	};
}
