package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.CitysBean;

/**
 * 
 * 功能：地区功能接口
 * 编写人员：lzh
 * 时间：2016年9月6日上午9:23:40
 */
public interface ICitysMapper {

	/**
	 * 查询地区
	 * @param map
	 * @return
	 */
	public List<CitysBean> findCitys(Map<String, Object> map);
	
	/**
	 * 根据MergerName查询数据
	 * @param mergerName
	 * @return
	 */
	public CitysBean findByMergerName(@Param("mergerName")String mergerName);
	
	/**
	 * 查询id
	 * @return
	 */
	public List<Integer> findByParentId(@Param("parentIds")List<Integer>  parentIds);
	
	/**
	 * 查询存在项目的地区
	 * @return
	 */
	public List<CitysBean> findCompanyCity(@Param("companyId")Integer companyId);
	
	/**
	 * 根据id查询存在项目的地区
	 * @return
	 */
	public List<CitysBean> findById(@Param("ids")List<Integer>  ids);
	
	/**
	 * 查询所属项目的cityId
	 * @param map
	 * @return
	 */
	public List<CitysBean> findProjectCityId(Map<String,Object> map);
}
