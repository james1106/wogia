package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ProjectBean;

/**
 * 
 * 功能：项目功能接口
 * 编写人员：lzh
 * 时间：2016年9月6日上午11:12:54
 */
public interface IProjectMapper {
	
	/**
	 * 添加项目
	 * @param project
	 */
	public void addProject(ProjectBean project);
	
	/**
	 * 更新项目
	 * @param project
	 */
	public void updProject(ProjectBean project);
	
	/**
	 * 查询项目
	 * @param map
	 * @return
	 */
	public List<ProjectBean> findProject(Map<String,Object> map);
	
	/**
	 * 查询项目 app
	 * @param map
	 * @return
	 */
	public List<ProjectBean> findProjectApp(Map<String,Object> map);
	
	/**
	 *   根据用户 公司类型 查询 项目
	 * @param userType
	 * @param unitId
	 * @return
	 */
	List<ProjectBean> queryProjectByItem(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("userType")Integer userType,@Param("unitId")Integer unitId);
	
	/**通过项目ID 查询项目*/
	ProjectBean queryById(@Param("id")Integer id);
	
	/**
	 * 删除项目
	 * @param id
	 */
	void deleteProjectById(@Param("id")Integer id);
}
