package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ProjectApplyDetailsBean;

/**
 * 
 * 功能：专项服务详细接口
 * 编写人员：lzh
 * 时间：2016年9月28日上午10:13:11
 */
public interface IProjectApplyDetailsBeanMapper {
   
	/**
	 * 添加详细
	 * @param beans
	 */
	public void addProjectApplyDetails(ProjectApplyDetailsBean beans);
	
	/**
	 * 更新详细
	 * @param beans
	 */
	public void updProjectApplyDetails(ProjectApplyDetailsBean beans);
	
	/**
	 * 根据applyId 专项服务id 查询
	 * @param applyId
	 * @return
	 */
	public List<ProjectApplyDetailsBean> findProjectApplyDetailsByApplyId(@Param("applyId")Integer applyId,
			@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
	
	/**
	 * 查询最新一条
	 * @param applyId
	 * @return
	 */
	public ProjectApplyDetailsBean findNewestByApplyId(@Param("applyId")Integer applyId);
	/**
	 * 查询单个详情
	 * @param id
	 * @return
	 */
	public ProjectApplyDetailsBean findProjectApplyDetailsById(@Param("id")Integer id);
	
	/**
	 * 删除专项服务具体内容
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}