package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ProjectTableBean;

/**
 *  远程数据库 项目表持久层接口
 * @author QimouXie
 *
 */
public interface IProjectTableMapper {
	
	/**
	 *  查询远程数据库 的表名
	 * @return
	 */
	List<ProjectTableBean> queryTables(@Param("dataBaseName")String dataBaseName);

}
