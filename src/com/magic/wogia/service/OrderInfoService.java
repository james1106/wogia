package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.OrderInfoBean;
import com.magic.wogia.mapper.IOrderInfoMapper;

/**
 *  订单消息 业务层
 * @author QimouXie
 *
 */
@Service
public class OrderInfoService {
	@Resource
	private IOrderInfoMapper orderInfoMapper;
	
	public void batchAdd(List<OrderInfoBean> infos) throws Exception{
		orderInfoMapper.batchAddOrderInfo(infos);
	}
	
	public List<OrderInfoBean> queryPage(Integer userId,Integer pageNO,Integer pageSize){
		return orderInfoMapper.queryInfoList(userId, (pageNO - 1) *  pageSize, pageSize);
	}

}
