package com.magic.wogia.service;

import java.util.List;

import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.bean.UserBean;

public interface ISystemInfoService {


	/**
	 *   添加 系统消息
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(SystemInfoBean system) throws Exception;
	
	/**
	 *  分页查询
	 * @param userId
	 * @param type 类型：0：系统公告 1：停水公告
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	List<SystemInfoBean> quertPage(Integer pageNO,Integer pageSize,Integer type,UserBean user);
	
	boolean sendNotice(SystemInfoBean system,Integer id);
	
	SystemInfoBean findById(Integer id);
	/**
	 *  删除
	 * @param id
	 * @throws Exception
	 */
	void delSystemInfo(Integer id) throws Exception;
	/**
	 *  更新系统消息
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(SystemInfoBean info) throws Exception;
}
