package com.magic.wogia.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.ApplyContextBean;
import com.magic.wogia.mapper.IApplyContextMapper;

/**
 * 
 * 功能：申请文字业务实现
 * 编写人员：lzh
 * 时间：2016年9月28日下午3:53:00
 */
@Service
public class ApplyContextService {

	@Resource
	private IApplyContextMapper applyContextMapper;
	
	/**
	 * 查询详情
	 * @param applyId
	 * @return
	 */
	public ApplyContextBean findApplyContext(){
		return applyContextMapper.findApplyContext();
	};
	/**
	 * 添加/更新
	 * @param beans
	 */
	public void addOrUpdApplyContext(ApplyContextBean beans){
		if (beans.getId()!=null) {
			applyContextMapper.updApplyContext(beans);
		}else {
			applyContextMapper.addApplyContext(beans);
		}
	};
}
