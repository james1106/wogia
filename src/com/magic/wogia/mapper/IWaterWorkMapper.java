package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.WaterWorkBean;

/**
 * 
 * 功能：水厂功能接口
 * 编写人员：lzh
 * 时间：2016年9月5日下午1:40:05
 */
public interface IWaterWorkMapper {
	
	/**
	 * 添加水厂
	 * @param record
	 */
	public void addWaterWork(WaterWorkBean record);
	
	/**
	 * 更新水厂
	 * @param record
	 */
	public void updWaterWork(WaterWorkBean record);
	
	/**
	 * 查询水厂
	 * @param map
	 * @return
	 */
	public List<WaterWorkBean> findWaterWork(Map<String,Object> map);
	
	/**
	 * 删除水厂(暂不用)
	 * @param map
	 * @return
	 */
    public void delWaterWork(Map<String,Object> map);

   /**
    *   查询水厂所有字段
    * @param id
    * @return
    */
    WaterWorkBean queryById(@Param("id")Integer id);
   
    
    /**
     * 根据项目id查询水厂
     * @param projectId
     * @return
     */
    public List<WaterWorkBean> findWaterByProId(@Param("projectId")Integer projectId);
}