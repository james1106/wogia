package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.BannerBean;
import com.magic.wogia.mapper.IBannerMapper;
/**
 * 
 * 功能：轮播图
 * 编写人员：lzh
 * 时间：2016年9月21日上午9:19:11
 */
@Service
public class BannerService {

	@Resource
	private IBannerMapper bannerMapper;
	
	public void addBanner(BannerBean banner) {
		bannerMapper.addBanner(banner);
	}

	public void delBanner(Integer id) {
		bannerMapper.delBanner(id);
	}

	public Integer countBanner() {
		return bannerMapper.countBanner();
	}

	public List<BannerBean> findAll() {
		return bannerMapper.findAll();
	}
	
	public BannerBean findById(Integer id) {
		return bannerMapper.findById(id);
	}

	public void updateBanner(BannerBean banner) {
		bannerMapper.updateBanner(banner);
	}

}
