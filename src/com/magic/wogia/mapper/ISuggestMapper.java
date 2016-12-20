package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.SuggestBean;

/**
 *  投诉建议 持久层接口
 * @author QimouXie
 *
 */
public interface ISuggestMapper {
	
	/**新增建议*/
	Integer add(@Param("suggest")SuggestBean suggest);

	List<SuggestBean> findSuggest(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
}
