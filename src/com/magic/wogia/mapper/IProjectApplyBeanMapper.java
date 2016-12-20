package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ProjectApplyBean;

/**
 * 
 * 功能：专项服务申请接口
 * 编写人员：lzh
 * 时间：2016年9月28日上午10:14:01
 */
public interface IProjectApplyBeanMapper {
	
	/**
	 *  批量查询申请项目
	 * @param ids
	 * @return
	 */
	List<ProjectApplyBean> batchQueryProjectApply(@Param("ids")List<Integer> ids);
	
	/**
	 *  增加
	 * @param projectApplyBean
	 * @return
	 */
	Integer add(@Param("projectApplyBean")ProjectApplyBean projectApplyBean);
   
	/**
	 *  批量查询申请项目
	 * @param ids
	 * @return
	 */
	List<ProjectApplyBean> batchQueryProjectApplys(@Param("ids")List<Integer> ids,
			@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
	
	/**
	 * 根据id修改
	 * @param ids
	 */
	void updProjectApply(@Param("id")Integer ids);
	
	/**
	 * 删除专项服务申请  根据项目id
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}