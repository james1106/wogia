package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 *  客户数据库 表操作 持久层接口
 * @author QimouXie
 *
 */
public interface IDeviceTableMapper {
	
	/**
	 *  根据表名 查询表中所有字段的属性
	 * @param tableName
	 * @return
	 */
	List<String> queryByTable(@Param("dbName")String dbName,@Param("tableName")String tableName);

}
