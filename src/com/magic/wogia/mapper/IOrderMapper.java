package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.OrderBean;
import com.magic.wogia.bean.UserBean;

/**
 *  订单 持久层接口
 * @author QimouXie
 *
 */
public interface IOrderMapper {
	
	/**
	 *  新增订单 
	 * @param order
	 * @return 新增订单ID
	 */
	Integer addOrder(@Param("order")OrderBean order);
	
	/**
	 *  修改订单信息
	 * @param order
	 * @return
	 */
	Integer updateOrder(@Param("order")OrderBean order);
	
	/**
	 *  根据用户分页查询订单
	 * @param user
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderBean> queryOrder(@Param("status")Integer status,@Param("user")UserBean user,
			@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("orderType")Integer orderType,
			@Param("techOrderFlag")Integer techOrderFlag,@Param("realName")String realName,@Param("orderNumber")String orderNumber);
	
	/**
	 *  查询订单
	 * @param orderId
	 * @return
	 */
	OrderBean queryById(@Param("orderId")Integer orderId);
	
	/**
	 * 删除订单 根据项目id
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}
