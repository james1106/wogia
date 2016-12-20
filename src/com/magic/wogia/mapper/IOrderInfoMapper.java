package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.OrderInfoBean;

/**
 *
 * @author QimouXie
 *
 */
public interface IOrderInfoMapper {

	/**
	 *  批量新增
	 * @param info
	 * @return
	 */
	Integer batchAddOrderInfo(@Param("infos")List<OrderInfoBean> infos);
	
	/**
	 *  通过UserID 查询 推送消息
	 * @param userId
	 * @param groupUserId
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderInfoBean> queryInfoList(@Param("userId")Integer userId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *  统计
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	Integer countInfo(@Param("userId")Integer userId);
	
	/**
	 * 根据项目id删除订单 通知信息
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}
