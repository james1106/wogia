package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.BannerBean;
import com.magic.wogia.bean.NewsBean;
import com.magic.wogia.mapper.IBannerMapper;
import com.magic.wogia.mapper.INewsMapper;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：新闻资讯业务实现
 * 编写人员：lzh
 * 时间：2016年9月18日下午4:57:20
 */
@Service
public class NewsService{
	
	@Resource
	private INewsMapper newsMapper;
	@Resource
	private IBannerMapper bannerMapper;
	
	/**
	 * 添加或者更新新闻资讯
	 * @param news
	 * @param isBanner
	 * @throws Exception
	 */
	@Transactional
	public void addOrUpdNews(NewsBean news)  throws Exception {
		if (news.getId() != null) {
			newsMapper.updateNews(news);
		}else{
			newsMapper.addNews(news);
			if(news.getIsBanner() == 1){
				BannerBean  banner = new BannerBean();
				banner.setNewsId(news.getId());
				banner.setImage(news.getImageUrl());
				banner.setIsInside(StatusConstant.INSIDE);
				banner.setNewsId(news.getId());
				bannerMapper.addBanner(banner);
			}
		}
	}

	/**
	 * 资讯详情
	 * @param id
	 * @return
	 */
	public NewsBean findById(Integer id) {
		return newsMapper.findById(id);
	}
	/**
	 * 新闻资讯列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<NewsBean> findNews(Integer pageNum,Integer pageSize) {
		if (pageNum != null) {
			pageNum = (pageNum - 1) * pageSize;
		}
		return newsMapper.findByType(pageNum, pageSize);
	}


	
	public void delNews(Integer newsId) throws Exception{
		newsMapper.delNews(newsId);
	}

	
	
}
