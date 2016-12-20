package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.OrderCompoentBean;

/**
 * 
 * 功能：订单零件接口
 * 编写人员：lzh
 * 时间：2016年9月21日下午2:42:47
 */
public interface IOrderCompoentMapper {

	/**
	 * 新增订单零件
	 * @param bean
	 */
	public void addOrderCompoent(@Param("comList")List<OrderCompoentBean> beans);
	
	/**
	 * 查询
	 * @param orderId
	 * @return
	 */
	public List<OrderCompoentBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 * 根据项目id删除订单零件
	 * @param projectId
	 */
	void deleteByProjectId(@Param("projectId")Integer projectId);
}
