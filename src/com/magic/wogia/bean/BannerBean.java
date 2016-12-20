package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  Banner 
 * @author QimouXie
 *
 */
public class BannerBean implements Serializable {

	private static final long serialVersionUID = -2604916255741930900L;
	
	/**主键id*/
	private Integer id;
	
	/**banner*/
	private String image;
	
	/**banner link URL*/
	private String imgUrl;
	
	/** Create Time of Banner*/
	private Date createTime  = new Date();
	
	/**是否是内部文章 0 表示不是  1 表示是*/
	private Integer isInside;
	
	/**如果是内部文章，则内部文章的Id*/
	private Integer newsId;
	
	/**banner 对应的资讯*/
	private NewsBean news;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public NewsBean getNews() {
		return news;
	}

	public void setNews(NewsBean news) {
		this.news = news;
	}

	public Integer getIsInside() {
		return isInside;
	}

	public void setIsInside(Integer isInside) {
		this.isInside = isInside;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
