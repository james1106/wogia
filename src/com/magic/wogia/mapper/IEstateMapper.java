package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.EstateBean;

/**
 * 
 * 功能：物业接口
 * 编写人员：lzh
 * 时间：2016年9月5日下午5:10:44
 */
public interface IEstateMapper {
	/**
	 * 添加物业
	 * @param record
	 */
	public void addEstate(EstateBean record);

	/**
	 * 更新物业
	 * @param record
	 */
	public void updEstate(EstateBean record);
	
	/**
	 * 查询物业
	 * @param map
	 * @return
	 */
	public List<EstateBean> findEstate(Map<String,Object> map);
	
	/**
	 * 删除物业(暂时不用)
	 * @param map
	 */
	public void delEstate(Map<String,Object> map);
	
	/**
	 *  查询 物业所有字段
	 * @param id
	 * @return
	 */
	EstateBean queryById(@Param("id")Integer id);
   
}