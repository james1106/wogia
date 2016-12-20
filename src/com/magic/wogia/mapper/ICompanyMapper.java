package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.CompanyBean;

/**
 * 
 * 功能：片区功能接口
 * 编写人员：lzh
 * 时间：2016年9月2日上午10:17:11
 */
public interface ICompanyMapper {

	/**
	 * 添加片区
	 */
	public void addCompany(CompanyBean company);
	
	/**
	 * 更新片区
	 * @param company
	 */
	public void updCompany(CompanyBean company);
	
	/**
	 * 查询片区
	 * @return
	 */
	public List<CompanyBean> findCompany(Map<String,Object> map);
	
	/**
	 *  根据ID 查询 片区所有字段
	 * @param id
	 * @return
	 */
	CompanyBean queryById(@Param("id")Integer id);
	
	/**
	 * 查询地区id
	 * @param map
	 * @return
	 */
	public CompanyBean findCityId(@Param("roleId")Integer roleId ,@Param("objectId")Integer objectId);
	
}
