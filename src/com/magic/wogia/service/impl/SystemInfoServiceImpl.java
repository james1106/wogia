package com.magic.wogia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.ISystemInfoMapper;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.service.ISystemInfoService;
import com.magic.wogia.util.PushMessageUtil;

/**
 *  系统消息 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class SystemInfoServiceImpl implements ISystemInfoService {

	@Resource
	private ISystemInfoMapper systemInfoMapper;
	@Resource
	private IUserMapper userMapper;
	
	@Override
	public Integer addSystemInfo(SystemInfoBean system) throws Exception {
		if (system.getId() != null) {
			systemInfoMapper.updateSystemInfo(system);
		}else{
			systemInfoMapper.addSystemInfo(system);
		}
		return system.getId();
	}

	@Override
	public List<SystemInfoBean> quertPage(Integer pageNO,Integer pageSize,Integer type,UserBean user) {
		if (pageNO != null || pageSize != null) {
			pageNO = (pageNO - 1) * pageSize;
		}
		Integer userId = null;
		if (user != null) {
			userId = user.getId();
		}
		List<SystemInfoBean> dataList = systemInfoMapper.queryInfoPage(pageNO, pageSize,type,userId);
		return dataList;
	}

	@Override
	public boolean sendNotice(SystemInfoBean system,Integer id) {
		if(id == null ){
			return false;
		}
//		List<UserBean> users = null;
//		users = userMapper.findAllToToken();
		Map<String,String> extendParam = new HashMap<String, String>();
		extendParam.put("info_type", PushMessageUtil.SYSTEM_INFO);
		PushMessageUtil.pushMessages(null, system.getBrief(), extendParam);
//		if(null != users){
//			for (UserBean userBean : users) {
//				
//			}
//		}
		return true;
	}

	@Override
	public SystemInfoBean findById(Integer id) {
		return systemInfoMapper.findById(id);
	}

	@Override
	public void delSystemInfo(Integer id) throws Exception {
		systemInfoMapper.delSystemInfo(id);
	}

	@Override
	public Integer updateSystemInfo(SystemInfoBean info) {
		return systemInfoMapper.updateSystemInfo(info);
	}
}
