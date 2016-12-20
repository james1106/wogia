package com.magic.wogia.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.ProjectApplyDetailsBean;
import com.magic.wogia.mapper.IProjectApplyBeanMapper;
import com.magic.wogia.mapper.IProjectApplyDetailsBeanMapper;

/**
 * 
 * 功能：专项服务详细业务实现
 * 编写人员：lzh
 * 时间：2016年9月28日上午11:00:29
 */
@Service
public class ProjectApplyDetailsService {

	@Resource
	private IProjectApplyDetailsBeanMapper applyDetailsBeanMapper;
	@Resource
	private IProjectApplyBeanMapper applyBeanMapper;
	/**
	 * 添加/更新详细
	 * @param beans
	 */
	@Transactional
	public void addOrUpdProjectApplyDetails(ProjectApplyDetailsBean beans){
		if (beans.getId() != null) {
			applyDetailsBeanMapper.updProjectApplyDetails(beans);
		}else {
			applyDetailsBeanMapper.addProjectApplyDetails(beans);
			applyBeanMapper.updProjectApply(beans.getApplyId());
		}
	};
	
	
	/**
	 * 根据applyId 专项服务id 查询
	 * @param applyId
	 * @return
	 */
	public List<ProjectApplyDetailsBean> findProjectApplyDetailsByApplyId(Integer applyId,Integer pageNum,Integer pageSize){
		if (pageNum != null && pageSize != null) {
			pageNum = pageNum - 1;
		}
		return applyDetailsBeanMapper.findProjectApplyDetailsByApplyId(applyId, pageNum, pageSize);
	};
	
	/**
	 * 查询最新一条
	 * @param applyId
	 * @return
	 */
	public ProjectApplyDetailsBean findNewestByApplyId(Integer id){
		return applyDetailsBeanMapper.findNewestByApplyId(id);
	};
	
	/**
	 * 查询单个详情
	 * @param id
	 * @return
	 */
	public ProjectApplyDetailsBean findProjectApplyDetailsById(Integer id){
		return applyDetailsBeanMapper.findProjectApplyDetailsById(id);
	};
	
}
