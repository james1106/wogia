package com.magic.wogia.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.bean.CitysBean;
import com.magic.wogia.bean.ComponentBean;
import com.magic.wogia.bean.ComponentDeviceBean;
import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.DeviceMaintainCalendarBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.ProjectMaintainCalendarBean;
import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.ICitysMapper;
import com.magic.wogia.mapper.IComponentDeviceMapper;
import com.magic.wogia.mapper.IComponentMapper;
import com.magic.wogia.mapper.IDeviceMapper;
import com.magic.wogia.mapper.ISystemInfoMapper;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.util.DateUtil;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.PushMessageUtil;
import com.magic.wogia.util.Timestamp;

/**
 * 
 * 功能：分区零件业务实现 编写人员：lzh 时间：2016年9月13日上午11:26:54
 */
@Service
public class ComponentDeviceService {

	@Resource
	private IComponentDeviceMapper componentDeviceMapper;
	@Resource
	private IUserMapper userMapper;
	@Resource
	private IDeviceMapper deviceMapper;
	@Resource
	private IComponentMapper componentMapper;
	@Resource
	private ISystemInfoMapper systemInfoMapper;
	@Resource
	private ICitysMapper citysMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 添加或者更新片区零件信息
	 * 
	 * @param record
	 */
	@Transactional
	public void addOrUpdComponentDevice(ComponentDeviceBean record) {
		if (!"".equals(record.getMaintainTimes())
				&& null != record.getMaintainTimes()) {
			record.setMaintainTime(DateUtil.stringToDate(record
					.getMaintainTimes()));
		}
		if (!"".equals(record.getReplaceTimes())
				&& null != record.getReplaceTimes()) {
			record.setReplaceTime(DateUtil.stringToDate(record
					.getReplaceTimes()));
		}
		if (record.getId() != null) {
			componentDeviceMapper.updComponentDevice(record);
		} else {
			componentDeviceMapper.addComponentDevice(record);
		}
	}

	/**
	 * 查询片区零件列表
	 * 
	 * @param record
	 * @return
	 * @throws ParseException
	 */
	public List<ComponentDeviceBean> findComponentDevice(
			ComponentDeviceBean record, Integer pageNum, Integer pageSize,
			UserBean userBean) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(record);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		map.put("userType", userBean.getIdType());
		map.put("objectId", userBean.getCompanyId());
		List<ComponentDeviceBean> recordList = componentDeviceMapper
				.findComponentDevice(map);
		for (ComponentDeviceBean componentDeviceBean : recordList) {
			componentDeviceBean.setState(countDown(componentDeviceBean));
		}

		return recordList;
	}

	/**
	 * 保养日历
	 * 
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @param userBean
	 * @param mergerName
	 * @return
	 * @throws ParseException
	 */
	public List<ProjectMaintainCalendarBean> maintainCalendar(
			ComponentDeviceBean record, Integer pageNum, Integer pageSize,
			UserBean userBean, String mergerName) throws ParseException {
		List<ProjectMaintainCalendarBean> pmcList = new ArrayList<ProjectMaintainCalendarBean>();
		List<DeviceMaintainCalendarBean> dmcList = new ArrayList<DeviceMaintainCalendarBean>();
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(record);
		CitysBean citys = citysMapper.findByMergerName(mergerName);
		if (citys != null) {
			List<Integer> parentId = new ArrayList<Integer>();
			parentId.add(citys.getId());
			if (citys.getLevelType() != 0) {
				if (citys.getLevelType() == 1) {
					List<Integer> parentId1 = citysMapper
							.findByParentId(parentId);
					List<Integer> parentId2 = citysMapper
							.findByParentId(parentId1);
					map.put("cityIds", parentId2);
				}
				if (citys.getLevelType() == 2) {
					List<Integer> parentId1 = citysMapper
							.findByParentId(parentId);
					map.put("cityIds", parentId1);
				}
				if (citys.getLevelType() == 3) {
					map.put("cityIds", parentId);
				}
			}
		}
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		map.put("userType", userBean.getIdType());
		map.put("objectId", userBean.getCompanyId());
		List<ComponentDeviceBean> recordList = componentDeviceMapper
				.maintainCalendar(map);
		for (int i = 0; i < recordList.size(); i++) {
			Integer status = countDown(recordList.get(i));
			if (status != 1) {
				recordList.remove(i);
				i--;
			}
			if (status == 1) {
				// 添加到保养项目
				ProjectMaintainCalendarBean pmc = new ProjectMaintainCalendarBean();
				pmc.setProjectId(recordList.get(i).getProjectId());
				pmc.setProjectAddress(recordList.get(i).getProjectAddress());
				pmc.setProjectName(recordList.get(i).getProjectName());
				pmcList.add(pmc);
				DeviceMaintainCalendarBean dmc = new DeviceMaintainCalendarBean();
				dmc.setId(recordList.get(i).getDeviceId());
				dmc.setSupplier(recordList.get(i).getSupplier());
				dmc.setDeviceName(recordList.get(i).getDeviceName());
				dmcList.add(dmc);
			}
		}

		Set<DeviceMaintainCalendarBean> dmcs = new HashSet<DeviceMaintainCalendarBean>();
		dmcs.addAll(dmcList);
		Set<ProjectMaintainCalendarBean> pmcs = new HashSet<ProjectMaintainCalendarBean>();
		pmcs.addAll(pmcList);
		for (ProjectMaintainCalendarBean projectMaintainCalendarBean : pmcs) {
			for (DeviceMaintainCalendarBean deviceMaintainCalendarBean : dmcs) {
				for (ComponentDeviceBean componentDeviceBean : recordList) {
					if (componentDeviceBean.getProjectId().equals(
							projectMaintainCalendarBean.getProjectId())
							&& componentDeviceBean.getDeviceId().equals(
									deviceMaintainCalendarBean.getId())) {
						deviceMaintainCalendarBean
								.setProjectId(componentDeviceBean
										.getProjectId());
						deviceMaintainCalendarBean.getCdList().add(
								componentDeviceBean);
					}
				}
				if (deviceMaintainCalendarBean.getProjectId() != null
						&& deviceMaintainCalendarBean.getProjectId().equals(
								projectMaintainCalendarBean.getProjectId())) {
					projectMaintainCalendarBean.getDasbList().add(
							deviceMaintainCalendarBean);
				}
			}
		}
		List<ProjectMaintainCalendarBean> sBeans = new ArrayList<ProjectMaintainCalendarBean>();
		sBeans.addAll(pmcs);

		return sBeans;
	}

	/**
	 * 计算是否进入倒计时
	 * 
	 * @return status 0:未进入 1：已进入
	 * @throws ParseException
	 */
	public int countDown(ComponentDeviceBean componentDeviceBean)
			throws ParseException {
		int status = 0;
		// 获取系统时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String time = simpleDateFormat.format(date);
		int timeStemp = Timestamp.timesStr(time, "yyyy-MM-dd");
		if (componentDeviceBean.getReplaceTime() != null) {
			// 转换时间戳
			int replaceTime = Timestamp.timesStr(simpleDateFormat.format(componentDeviceBean.getReplaceTime()), "yyyy-MM-dd");
			if (componentDeviceBean.getLife() > 0) {
				if (componentDeviceBean.getLife() - (timeStemp - replaceTime) / (3600 * 24 * 10) < 10) {
					status = 1;
				}
			}

		}
		componentDeviceBean.setState(status);
		return status;
	}
	
	/**
	 * 计算是否进入倒计时
	 * 
	 * @return status 0:未进入 1：已进入
	 * @throws ParseException
	 */
	public int countDown_(ComponentDeviceBean componentDeviceBean)
			throws ParseException {
		int status = 0;
		// 获取系统时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String time = simpleDateFormat.format(date);

		int timeStemp = Timestamp.timesStr(time, "yyyy-MM-dd");
		if (componentDeviceBean.getReplaceTime() != null) {
			// 转换时间戳
			int replaceTime = Timestamp.timesStr(simpleDateFormat.format(componentDeviceBean.getReplaceTime()), "yyyy-MM-dd");
			if (componentDeviceBean.getLife() > 0) {
					int day = componentDeviceBean.getLife() - (timeStemp - replaceTime)/ (3600 * 24 * 10);
					if (day == 10 || day == 1 ) {
						status = 1;
					}
			}
		}
		componentDeviceBean.setState(status);
		return status;
	}
	
	
	/**
	 * 获取将要到期的零件
	 */
	@Transactional
	public void getAllComponentWillDue() {
		List<ComponentDeviceBean> comments = componentDeviceMapper.queryAll();
		List<Integer> commentIds = new ArrayList<Integer>();
		List<UserBean> finalUsers = new ArrayList<UserBean>();
		for (ComponentDeviceBean comment : comments) {
			try {
				Integer status = countDown_(comment);
				if (status == 1) {
					commentIds.add(comment.getId());
				}
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
		}
		if (commentIds.size() != 0) {
			List<UserBean> estateUsers = userMapper.queryEstateUserByDeviceId(commentIds);
			List<UserBean> waterUsers = userMapper.queryWaterUserByDeviceId(commentIds);
			List<Integer> waterUserIds = new ArrayList<Integer>();
			if (null != estateUsers && estateUsers.size() != 0) {
				List<Integer> estateUserIds = new ArrayList<Integer>();
				for (UserBean user : estateUsers) {
					estateUserIds.add(user.getId());
				}
				if (estateUserIds.size() != 0) {
					List<UserBean> estates = userMapper.queryEstateUseAndProject(estateUserIds, commentIds);
					if (null != estates && estates.size() != 0) {
						for (UserBean userBean : estates) {
							// 装备最终数据
							if (!finalUsers.contains(userBean)) {
								if(null == userBean.getProjects()){
									userBean.setProjects(new HashSet<ProjectBean>());
								}
								userBean.getProjects().add(userBean.getProject());
								finalUsers.add(userBean);
							} else {
								for (UserBean user : finalUsers) {
									if (user.getId().equals(userBean.getId())) {
										user.getProjects().add(userBean.getProject());
									}
								}
							}
						}
					}
				}
			}
			if (null != waterUsers && waterUsers.size() != 0) {
				for (UserBean user : waterUsers) {
					waterUserIds.add(user.getId());
				}
				if (waterUserIds.size() != 0) {
					List<UserBean> waters = userMapper.queryWaterUseAndProject(waterUserIds, commentIds);
					if (null != waters && waters.size() != 0) {
						for (UserBean userBean : waters) {
							// 装备最终数据
							if (!finalUsers.contains(userBean)) {
								if(null == userBean.getProjects()){
									userBean.setProjects(new HashSet<ProjectBean>());
								}
								userBean.getProjects().add(userBean.getProject());
								finalUsers.add(userBean);
							} else {
								for (UserBean user : finalUsers) {
									if (user.getId().equals(userBean.getId())) {
										user.getProjects().add(userBean.getProject());
									}
								}
							}
						}
					}
				}
			}
			List<SystemInfoBean> infos = new ArrayList<SystemInfoBean>();
			if (finalUsers.size() != 0) {
				Map<String,String> extendParam = new HashMap<String, String>();
				extendParam.put("info_type", PushMessageUtil.LINGJIAN_INFO);
				// 生成数据 推送极其保存
				for (UserBean user : finalUsers) {
//					logger.debug("-->>数据大小>>>>>>>---"+user.getId());
					StringBuffer sb = new StringBuffer();
					sb.append("您有零件需要保养 ");
					sb.append("[<br>");
					if (null != user.getProjects()) {
						for (ProjectBean pro : user.getProjects()) {
							sb.append("&nbsp;&nbsp;项目：" + pro.getProjectName()+"<br>");
							List<DeviceBean> devices = deviceMapper.queryByProjectId(commentIds, pro.getId());
							Set<DeviceBean> devicesSet = new HashSet<DeviceBean>();
							for (DeviceBean deviceBean : devices) {
								devicesSet.add(deviceBean);
							}
							if (null != devicesSet && devicesSet.size() != 0) {
								pro.setDevices(devicesSet);
								for (DeviceBean deviceBean : devicesSet) {
									List<ComponentBean> coms = componentMapper.batchQueryById(deviceBean.getId(),commentIds);
									if(null == coms || coms.size() == 0){
										break;
									}
									sb.append("&nbsp;&nbsp;&nbsp;&nbsp;分区："+ deviceBean.getDeviceName()+"<br>");
									if (null != coms && coms.size() != 0) {
										for (ComponentBean componentBean : coms) {
											sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;零件: " + componentBean.getComponentName()+"<br>");
										}
									}
								}
								sb.append(";");
							}
						}
					}
					sb.append("<br>]");
					// 生成通知消息
					SystemInfoBean info = new SystemInfoBean();
					info.setContent(sb.toString());
					info.setBrief(analysisStr(sb.toString()));
					info.setTitle("有零件需要保养");
					info.setType(2);
					info.setCreateTime(new Date());
					info.setToUser(user.getId());
					infos.add(info);
					// 推送
//					logger.debug("推送数据：   ---------"+user.getId()+" --->>>>>>>-"+sb.toString());
 					PushMessageUtil.pushMessages(user, "有零件需要保养", extendParam);
				}
			}
			if (infos.size() != 0) {
				systemInfoMapper.batchAdd(infos);
			}
		}
	}
	
	private String analysisStr(String str){
		str = str.replaceAll("&nbsp;", "");
		str = str.replaceAll("<br>", "");
		return str;
	}
	
	

}
