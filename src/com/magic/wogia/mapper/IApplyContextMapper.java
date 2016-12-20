package com.magic.wogia.mapper;


import com.magic.wogia.bean.ApplyContextBean;

/**
 * 
 * 功能：申请文字接口
 * 编写人员：lzh
 * 时间：2016年9月28日下午3:43:56
 */
public interface IApplyContextMapper {

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	public ApplyContextBean findApplyContext();
	/**
	 * 添加
	 * @param beans
	 */
	public void addApplyContext(ApplyContextBean beans);
	/**
	 * 更新
	 * @param beans
	 */
	public void updApplyContext(ApplyContextBean beans);
	
}
