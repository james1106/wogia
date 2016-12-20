package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.UserBean;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.Timestamp;

/**
 * 
 * 功能：用户业务 编写人员：lzh 时间：2016年9月1日上午9:05:12
 */
@Service
public class UserService {

	@Autowired
	private IUserMapper userMapper;

	/**
	 * 查询用户
	 * 
	 * @return
	 */
	public List<UserBean> findUser(UserBean userBean, Integer pageNum,
			Integer pageSize, UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(userBean);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		if (user != null) {
			map.put("userType", user.getIdType());
			map.put("objectId", user.getCompanyId());
		}
		List<UserBean> userList = userMapper.findUser(map);
		for (UserBean userBean2 : userList) {
			if (userBean2.getCreatetime() != null) {
				userBean2.setCreatetimes(Timestamp.DateTimeStamp(
						userBean2.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
			}
			if (userBean2.getUpdatetime() != null) {
				userBean2.setUpdatetimes(Timestamp.DateTimeStamp(
						userBean2.getUpdatetime(), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		return userList;
	}
	
	
	public UserBean queryByMobile(String mobile){
		return userMapper.queryByMobile(mobile);
	}

	/**
	 * 查询联系人
	 * 
	 * @param user
	 * @param roleId
	 * @return
	 */
	public List<UserBean> findContacts(UserBean user, Integer roleId,
			Integer waterWorkId, Integer projectId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("userType", user.getIdType());
		map.put("objectId", user.getCompanyId());
		map.put("waterWorkId", waterWorkId);
		map.put("projectId", projectId);
		if (type == 1) {
			map.put("userId", user.getId());
		}
		return userMapper.findContacts(map);
	}

	/**
	 * 登录
	 * 
	 * @param userBean
	 * @return
	 */
	public UserBean login(String account, String pwd) {
		UserBean userBean = new UserBean();
		userBean.setAccount(account);
		List<UserBean> userList = findUser(userBean, null, null, null);
		if (userList.size() > 0) {
			if (userList.size() > 1) {
				throw new InterfaceCommonException(StatusConstant.Fail_CODE,
						ErrorMessage.Fail);
			}
			for (UserBean userBean2 : userList) {
				if (userBean2 != null && pwd.equals(userBean2.getPwd())) {
					userBean = userBean2;
				} else {
					throw new InterfaceCommonException(
							StatusConstant.Fail_CODE,
							ErrorMessage.lOGIN_PWD_FAIL);
				}
			}
		} else {
			throw new InterfaceCommonException(StatusConstant.Fail_CODE,
					ErrorMessage.lOGIN_ACCOUNT_FAIL);
		}
		return userBean;
	}

	/**
	 * 获取单个用户的详细信息
	 * 
	 * @param userBean
	 * @return
	 */
	public UserBean findOnlyUser(UserBean userBean) {
		List<UserBean> userList = findUser(userBean, null, null, null);
		for (UserBean userBean2 : userList) {
			if (userBean2 != null) {
				userBean = userBean2;
			}
		}
		return userBean;
	}

	/**
	 * 添加用户 或者 更新用户
	 * 
	 * @param userBean
	 * @return
	 */
	@Transactional
	public void addOrUpdUser(UserBean userBean) {
		// 判断密码是否为空
		/*
		 * if (userBean.getPwd() != null && !"".equals(userBean.getPwd())) {
		 * userBean.setPwd(MD5Util.MD5(userBean.getPwd())); }
		 */
		if (userBean.getId() != null) {
			userMapper.updUser(userBean);
		} else {
			userMapper.addUser(userBean);
			
		}
	}

	public UserBean mobileLogin(String mobile, String password) {
		return userMapper.login(mobile, password);
	}

	/**
	 * 通过userName 查询用户(All Field)
	 * 
	 * @param userName
	 * @return
	 */
	public UserBean queryByUserName(String userName) {
		return userMapper.queryByUserName(userName);
	}

	/** 按条件统计查询用户数量 */
	public Integer countUser(String mobile, String userName) {
		return userMapper.countUser(mobile, userName);
	}

	/**
	 * 查询技术
	 * 
	 * @return
	 */
	public List<UserBean> queryTech(Integer userType, Integer unitId,
			Integer orderId) {
		return userMapper.queryTech(userType, unitId, orderId);
	}

	/**
	 * 查询订单的创建人
	 * 
	 * @param orderId
	 * @return
	 */
	public UserBean queryByOrderId(Integer orderId) {
		return userMapper.queryByOrderId(orderId);
	}

	public List<UserBean> batchQueryUserToken(List<Integer> ids) {
		return userMapper.batchQueryUserToken(ids);
	}

	/**
	 * 批量查询用户信息 通过用户名
	 * 
	 * @return
	 */
	public List<UserBean> batchQueryUserInfo(List<String> userNames) {
		return userMapper.batchQueryUserInfo(userNames);
	}

}
