package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.CSProjectBean;

/**
 *  本地数据库 项目表名 持久层接口
 * @author QimouXie
 *
 */
public interface ICSProjectMapper {
	
	/**
	 *  查询全部
	 * @return
	 */
	List<CSProjectBean> queryAll();
	/**
	 *  查询未绑定的  status=0
	 * @return
	 */
	List<CSProjectBean> queryStatus();
	
	/**
	 *  批量新增数据
	 * @param csps
	 * @return
	 */
	Integer batchAdd(@Param("csps")List<CSProjectBean> csps);
	
	/**
	 *  更新数据状态
	 * @param csBean
	 * @return
	 */
	Integer updateStatus(@Param("csBean")CSProjectBean csBean);
	
	/**
	 * 根据项目id还原更新项目表状态
	 * @param projectId
	 */
	void updByProjectId(@Param("projectId")Integer projectId);

}
