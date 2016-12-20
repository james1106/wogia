package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.ComponentBean;

/**
 * 
 * 功能：零件库功能接口
 * 编写人员：lzh
 * 时间：2016年9月13日上午9:06:08
 */
public interface IComponentMapper {
	
	/**
	 *  通过零件的ID 批量查询，其他接口请勿调用
	 * @param ids
	 * @return
	 */
	List<ComponentBean> queryBatch(@Param("ids")String ids);

    /**
     * 添加零件
     * @param record
     * @return
     */
    int addComponent(ComponentBean record);
    /**
     * 更新零件
     * @param record
     * @return
     */
    int updComponent(ComponentBean record);
    
    /**
     * 零件列表
     * @param map
     * @return
     */
    List<ComponentBean> findComponent(Map<String,Object> map);
    
    /**
     *  通过ID 批量 零件
     * @param ids
     * @return
     */
    List<ComponentBean> batchQueryById(@Param("deviceId")Integer deviceId,@Param("ids")List<Integer> ids);
}