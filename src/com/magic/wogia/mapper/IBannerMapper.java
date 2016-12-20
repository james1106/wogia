package com.magic.wogia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.BannerBean;

/**
 *  Banner 持久层
 * @author QimouXie
 *
 */
public interface IBannerMapper {
	
	/**
	 *  添加banner
	 * @param banner
	 */
	void addBanner(@Param("banner")BannerBean banner);
	
	/**
	 *  删除banner
	 * @param id
	 */
	void delBanner(@Param("id")Integer id);
	
	/**
	 *  统计banner数量
	 * @return
	 */
	Integer countBanner();
	
	/**
	 *  查询所有banner
	 * @return
	 */
	List<BannerBean> findAll();
	
	BannerBean findById(@Param("id")Integer id);
	
	void updateBanner(@Param("banner")BannerBean banner);
	
	void delBannerByNewsId(@Param("newsId")Integer newsId);
	
	void updateBannerByNewsId(@Param("newsId")Integer newsId,@Param("banner")BannerBean banner);
}
