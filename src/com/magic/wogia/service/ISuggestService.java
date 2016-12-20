package com.magic.wogia.service;

import java.util.List;

import com.magic.wogia.bean.SuggestBean;

/**
 *  意见 业务层接口
 * @author QimouXie
 *
 */
public interface ISuggestService {
	
	void add(SuggestBean suggest) throws Exception;

	List<SuggestBean> queryPage (Integer pageNum,Integer pageSize);
}
