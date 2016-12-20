package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.OrderDescribeBean;

/**
 *  订单追加描述
 * @author QimouXie
 *
 */
public interface IOrderDescribe {
	/**
	 *  新增订单描述
	 * @param orderDescribe
	 * @return
	 */
	Integer addOrderDescribe(@Param("orderDescribe")OrderDescribeBean orderDescribe);
	
	/**
	 *  根据订单ID 查询订单
	 * @param orderId
	 * @return
	 */
	List<OrderDescribeBean> queryByOrder(@Param("orderId")Integer orderId);

	/**
	 * 根据项目id删除 订单描述
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}
