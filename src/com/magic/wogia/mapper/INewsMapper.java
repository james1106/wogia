package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.NewsBean;

/**
 *  News Interface
 * @author QimouXie
 *
 */
public interface INewsMapper {
	/**
	 *  Add News Info
	 * @param news
	 * @return
	 */
	
	Integer addNews(@Param("news")NewsBean news);
	/**
	 *  Find News By News ID
	 * @param id
	 * @return
	 */
	NewsBean findById(@Param("id")Integer id);
	/**
	 *  Paging and Querying News
	 * @param typeId
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<NewsBean> findByType(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	/**
	 *  Count News By Type
	 * @param typeId
	 * @return
	 */
	Integer countNewsByType();
	
	/**
	 *  更新新闻
	 * @param news
	 */
	void updateNews(@Param("news")NewsBean news);
	
	/**实则 更新新闻 是否有效状态 并非真正删除*/
	void delNews(@Param("id")Integer id);
	
	
	

}
