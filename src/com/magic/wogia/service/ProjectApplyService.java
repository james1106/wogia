package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.ProjectApplyBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.IProjectApplyBeanMapper;
import com.magic.wogia.mapper.IProjectMapper;


@Service
public class ProjectApplyService {
	
	@Resource
	private IProjectApplyBeanMapper projectApplyBeanMapper;
	@Resource
	private IProjectMapper projectMapper;
	
	public List<ProjectBean> batchQuery(Integer pageNO,Integer pageSize,UserBean user){
		
		List<ProjectBean> pros = projectMapper.queryProjectByItem((pageNO - 1) * pageSize,pageSize,user.getIdType(), user.getCompanyId());
		List<Integer> ids = new ArrayList<Integer>();
		List<ProjectApplyBean> applys = new ArrayList<ProjectApplyBean>();
		for (ProjectBean projectBean : pros) {
			ids.add(projectBean.getId());
		}
		if(ids.size() > 0){
			applys = projectApplyBeanMapper.batchQueryProjectApply(ids);
		}
		if(null != applys && applys.size() > 0){
			for (ProjectBean project : pros) {
				for (ProjectApplyBean projectApplyBean : applys) {
					if(project.getId().equals(projectApplyBean.getProjectId())){
						project.setApplyId(projectApplyBean.getId());
						project.setApplyStatus(projectApplyBean.getStatus());
						break;
					}
				}
			}
		}
		return pros;
	}
	@Transactional
	public Integer add(ProjectApplyBean projectApplyBean){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(projectApplyBean.getProjectId());
		List<ProjectApplyBean> pros = projectApplyBeanMapper.batchQueryProjectApply(ids);
		if(null != pros && pros.size() == 0){
			projectApplyBeanMapper.add(projectApplyBean);
			return 1;
		}else{
			return 0;
		}
		
	}

	/**
	 * web端申请列表
	 * @param user
	 * @return
	 */
	public List<ProjectApplyBean> findProjectApply(UserBean user,Integer pageNum,Integer pageSize){
		List<Integer> ids = new ArrayList<Integer>();
		List<ProjectApplyBean> applys = new ArrayList<ProjectApplyBean>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userType", user.getIdType());
		map.put("objectId", user.getCompanyId());
		List<ProjectBean> projectList = projectMapper.findProject(map);
		for (ProjectBean projectBean : projectList) {
			ids.add(projectBean.getId());
		}
		if(ids.size() > 0){
			if (pageNum != null && pageSize != null) {
				pageNum = pageNum - 1;
			}
			applys = projectApplyBeanMapper.batchQueryProjectApplys(ids,pageNum,pageSize);
		}
		return applys;
	}
	
	
}
