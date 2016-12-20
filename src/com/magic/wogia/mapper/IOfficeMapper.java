package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.OfficeBean;

/**
 * 
 * 功能：办事处接口
 * 编写人员：lzh
 * 时间：2016年9月2日下午5:37:48
 */
public interface IOfficeMapper {

	/**
	 * 添加办事处
	 * @param officeBean
	 */
	public void addOffice(OfficeBean officeBean);
	/**
	 * 更新办事处
	 * @param officeBean
	 */
	public void updOffice(OfficeBean officeBean);
	/**
	 * 查询办事处
	 * @param map
	 * @return
	 */
	public List<OfficeBean> findOffice(Map<String,Object> map);
	/**
	 * 删除办事处 (暂不用)
	 * @param map
	 */
	public void delOffice(Map<String,Object> map);
	
	/**
	 *  查询办事处所有字段
	 * @param id
	 * @return
	 */
	OfficeBean queryById(@Param("id")Integer id);
}
