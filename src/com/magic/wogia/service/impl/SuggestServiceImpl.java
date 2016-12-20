package com.magic.wogia.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.SuggestBean;
import com.magic.wogia.mapper.ISuggestMapper;
import com.magic.wogia.service.ISuggestService;

@Service
@Transactional(readOnly=true)
public class SuggestServiceImpl implements ISuggestService {

	@Resource
	private ISuggestMapper suggestMapper;
	
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public void add(SuggestBean suggest) throws Exception {
		suggestMapper.add(suggest);
	}

	@Override
	public List<SuggestBean> queryPage(Integer pageNum, Integer pageSize) {
		if (pageNum != null) {
			pageNum = pageNum-1;
		}
		List<SuggestBean>  dataList =  suggestMapper.findSuggest(pageNum, pageSize);
		return dataList;
	}
}
