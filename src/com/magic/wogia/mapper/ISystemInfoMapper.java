package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.SystemInfoBean;

/**
 *  系统推送消息 持久层接口
 * @author QimouXie
 *
 */
public interface ISystemInfoMapper {
	
	/**
	 *   添加 系统消息
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(@Param("system")SystemInfoBean system);
	/**
	 *  批量新增
	 * @param infos
	 * @return
	 */
	Integer batchAdd(@Param("infos")List<SystemInfoBean> infos);
	
	/**
	 *   分页查询 系统消息数据
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<SystemInfoBean> queryInfoPage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("type")Integer type,@Param("userId")Integer userId);
	
	/**
	 *  统计
	 * @param group
	 * @return
	 */
	Integer countSystemInfo();
	
	SystemInfoBean findById(@Param("id")Integer id);
	/**
	 *  删除
	 * @param id
	 */
	void delSystemInfo(@Param("id")Integer id);
	
	/**
	 *  更新 通知
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(@Param("info")SystemInfoBean info) ;
	
	

}
