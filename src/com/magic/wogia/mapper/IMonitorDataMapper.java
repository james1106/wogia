package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.MonitorDataBean;

/**
 *  监控数据 持久层接口
 * @author QimouXie
 *
 */
public interface IMonitorDataMapper {
	
	/**
	 *  查询指定字段值
	 * @param fileds
	 * @return
	 */
	List<MonitorDataBean> queryMonitorData(@Param("tableName")String tableName);
	
	/**
	 *  查询监控图表数据
	 * @param filed
	 * @param tableName
	 * @return
	 */
	List<MonitorDataBean> queryMonitorChart(
			@Param("ysli")String ysli,
			@Param("filed")String filed,
			@Param("filedFlows")String filedFlows,
			@Param("tableName")String tableName,
			@Param("setPressure")String setPressure,
			@Param("outPressure")String outPressure,
			@Param("pcow")String pcow,
			@Param("te")String te
			);
	
	
	/**
	 *  查询水质监控数据
	 * @param fileds
	 * @param tableName
	 * @return
	 */
	List<MonitorDataBean> queryMonitorWater(@Param("fileds")List<String> fileds,@Param("tableName")String tableName);
}
