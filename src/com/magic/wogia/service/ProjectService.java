package com.magic.wogia.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.CSProjectBean;
import com.magic.wogia.bean.CitysBean;
import com.magic.wogia.bean.DeviceAbnormalStatusBean;
import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.MonitorDataBean;
import com.magic.wogia.bean.ProjectAbnormalStatusBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.ProjectTableBean;
import com.magic.wogia.bean.PumpBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.ICSDeviceMapper;
import com.magic.wogia.mapper.ICSProjectMapper;
import com.magic.wogia.mapper.ICitysMapper;
import com.magic.wogia.mapper.IComponentDeviceMapper;
import com.magic.wogia.mapper.IDeviceInformationMapper;
import com.magic.wogia.mapper.IDeviceMapper;
import com.magic.wogia.mapper.IMonitorDataMapper;
import com.magic.wogia.mapper.IOrderCompoentMapper;
import com.magic.wogia.mapper.IOrderDescribe;
import com.magic.wogia.mapper.IOrderInfoMapper;
import com.magic.wogia.mapper.IOrderMapper;
import com.magic.wogia.mapper.IProjectApplyBeanMapper;
import com.magic.wogia.mapper.IProjectApplyDetailsBeanMapper;
import com.magic.wogia.mapper.IProjectMapper;
import com.magic.wogia.mapper.IProjectTableMapper;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.FiledConstant;
import com.magic.wogia.util.PushMessageUtil;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：项目业务实现
 * 编写人员：lzh
 * 时间：2016年9月6日上午11:23:21
 */
@Service
public class ProjectService {

	@Resource
	private IProjectMapper projectMapper;
	@Resource
	private IProjectTableMapper projectTableMapper;
	@Resource
	private ICSProjectMapper cSProjectMapper;
	@Resource
	private ICitysMapper citysMapper;
	@Resource
	private IDeviceMapper deviceMapper;
	@Resource
	private IMonitorDataMapper monitorDataMapper;
	@Resource
	private IUserMapper userMapper;
	@Resource
	private IComponentDeviceMapper componentDeviceMapper;
	
	@Resource
	private ICSDeviceMapper cSDeviceMapper;
	@Resource
	private IDeviceInformationMapper deviceInformationMapper;
	@Resource
	private IOrderCompoentMapper orderCompoentMapper;
	@Resource
	private IOrderDescribe orderDescribe;
	@Resource
	private IOrderInfoMapper orderInfoMapper;
	@Resource
	private IOrderMapper orderMapper;
	@Resource
	private IProjectApplyBeanMapper projectApplyBeanMapper;
	@Resource
	private IProjectApplyDetailsBeanMapper projectApplyDetailsBeanMapper;
	
	/**
	 * 添加或者更新项目信息
	 * @param project
	 */
	@Transactional
	public void addOrUpdProject(ProjectBean project){
		if (project.getId() != null) {
			projectMapper.updProject(project);
		}else {
			projectMapper.addProject(project);
		}
	}
	
	/**
	 * 查询项目
	 * @param project
	 * @return
	 */
	public List<ProjectBean> findProject(ProjectBean project,Integer pageNum,Integer pageSize,UserBean user){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(project);
		map.put("userType", user.getIdType());
		map.put("objectId", user.getCompanyId());
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<ProjectBean> projectList = projectMapper.findProject(map);
		return projectList;
	}
	
	/**
	 *  同步更新项目
	 */
	public Integer updateProjectData(List<ProjectTableBean> tables) throws Exception{
		
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		List<CSProjectBean> css = cSProjectMapper.queryAll();
		List<CSProjectBean> needAdd = new ArrayList<CSProjectBean>();
		for (ProjectTableBean projectTable : tables) {
			CSProjectBean tempCS = new CSProjectBean();
			tempCS.setTableName(projectTable.getTableName());
			if(!css.contains(tempCS)){
				CSProjectBean cs = new CSProjectBean();
				cs.setTableName(projectTable.getTableName());
				cs.setStatus(StatusConstant.NOT_ALLOT);
				needAdd.add(cs);
			}
		}
		if(needAdd.size() != 0){
			cSProjectMapper.batchAdd(needAdd);
		}
		return needAdd.size();
	}
	
	/**
	 *  查询客户数据库表名
	 * @return
	 */
	public List<ProjectTableBean> queryTables(){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
		return  projectTableMapper.queryTables(DataSourceInstances.DATABASENAME);
	}
	
	/**
	 * 查询未绑定的status=0
	 * @return
	 */
	public List<CSProjectBean> findStatus(){
		return cSProjectMapper.queryStatus();
	}
	
	/**
	 * 更新绑定状态
	 * @param csProject
	 */
	public void updCSProject(CSProjectBean csProject){
		cSProjectMapper.updateStatus(csProject);
	}
	
	/**
	 * 
	 * 功能：返回app端的项目内部类
	 * 编写人员：lzh
	 * 时间：2016年9月8日上午10:09:58
	 */
	public class Project{
		private String projectName;
		private Integer id; //项目id
		private Integer deviceNum;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getDeviceNum() {
			return deviceNum;
		}
		public void setDeviceNum(Integer deviceNum) {
			this.deviceNum = deviceNum;
		}
		
		
	}
	/**
	 * 查询项目  
	 * @param mergerName 地区名
	 * @return
	 */
	public List<Project> findProjects(String mergerName,String projectName,Integer pageNum,Integer pageSize,UserBean user){
		CitysBean citys = citysMapper.findByMergerName(mergerName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		if (!"".equals(projectName)) {
			map.put("projectName", projectName);
		}
		if (citys != null) {
			List<Integer> parentId = new ArrayList<Integer>();
			parentId.add(citys.getId());
			if (citys.getLevelType() != 0) {
				if (citys.getLevelType() == 1) {
					List<Integer> parentId1 = citysMapper.findByParentId(parentId);
					List<Integer> parentId2 = citysMapper.findByParentId(parentId1);
					map.put("parentIds", parentId2);
				}
				if (citys.getLevelType() == 2) {
					List<Integer> parentId1 = citysMapper.findByParentId(parentId);
					map.put("parentIds", parentId1);
				}
				if (citys.getLevelType() == 3) {
					map.put("parentIds", parentId);
				}
			}
		}
		map.put("objectId", user.getCompanyId());
		map.put("userType", user.getIdType());
		//查询设备
		List<ProjectBean> proList = projectMapper.findProjectApp(map);
		List<Project> pList = new ArrayList<ProjectService.Project>();
		for (ProjectBean projectBean : proList) {
			Map<String, Object> devMap = new HashMap<String, Object>();
			devMap.put("projectId", projectBean.getId());
			List<DeviceBean> devList = deviceMapper.findDevice(devMap);
			Project project = new Project();
			project.setId(projectBean.getId());
			project.setDeviceNum(devList.size());
			project.setProjectName(projectBean.getProjectName());
			pList.add(project);
		}
		return pList;
	}
	
	/**
	 *  根据用户查询 该用户下所有的项目
	 * @param userType
	 * @param unitId
	 * @return
	 */
	public List<ProjectBean> queryProjectByUser(Integer userType,Integer unitId,Integer pageNO,Integer pageSize){
		return projectMapper.queryProjectByItem((pageNO - 1) * pageSize,pageSize,userType, unitId);
	}
	
	/**
	 * 盯一盯
	 * @param user
	 * @return
	 */
	public List<ProjectAbnormalStatusBean> stareOneStare(UserBean user){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		//异常项目集合
		List<ProjectAbnormalStatusBean> pasbList = new ArrayList<ProjectAbnormalStatusBean>();
		MonitorDataService mds = new MonitorDataService();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectId", user.getCompanyId());
		map.put("userType", user.getIdType());
		//查询项目
		List<ProjectBean> proList = projectMapper.findProjectApp(map);
		for (ProjectBean projectBean : proList) {
			int abnormalCode = 0;
			ProjectAbnormalStatusBean pasb = new ProjectAbnormalStatusBean();
			pasb.setProjectAddress(projectBean.getProjectAddress());
			pasb.setProjectName(projectBean.getProjectName());
			//最高温度
			double maxTemperature = projectBean.getPumpTemperatureMax();
			Map<String, Object> devMap = new HashMap<String, Object>();
			devMap.put("projectId", projectBean.getId());
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
			List<DeviceBean> devList = deviceMapper.findDevice(devMap);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
			List<MonitorDataBean> datas = monitorDataMapper.queryMonitorData(projectBean.getTableName());
			JSONArray dataJson = JSONArray.fromObject(datas);
			//异常分区集合
			List<DeviceAbnormalStatusBean> dasbList = new ArrayList<DeviceAbnormalStatusBean>();
			for (DeviceBean deviceBean : devList) {
				DeviceAbnormalStatusBean dasb = new DeviceAbnormalStatusBean();
				dasb.setId(deviceBean.getId());
				dasb.setDeviceName(deviceBean.getDeviceName());
				dasb.setSupplier(deviceBean.getSupplier());
				String filed = deviceBean.getTableDevice().split("\\|")[2];
				for (Object obj : dataJson) {
					List<PumpBean> pumps = new ArrayList<PumpBean>();
					// 循环获取泵信息
					JSONObject jsObj = JSONObject.fromObject(obj);
					//稳流罐 0-正常1-故障
					String steadyFlowTank = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_TEMPERATURE);
					Integer sft = 0;
					if (steadyFlowTank != null && steadyFlowTank.trim().length() >0 ) {
						sft = Integer.valueOf(steadyFlowTank);
						if (sft != 0) {
							dasb.setSteadyFlowTankStatus("故障");
							abnormalCode++;
						}
					}
					//泵房-水箱缺水 0-正常2-高液位3-溢流
					String boxStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
					Integer bs = 0;
					if (boxStatus != null && boxStatus.trim().length() >0 ) {
						bs = Integer.valueOf(boxStatus);
						if(bs == 2){
							dasb.setBoxStatus("高液位");
							abnormalCode++;
						}else if(bs == 3){
							dasb.setBoxStatus("溢流");
							abnormalCode++;
						}
					}
					
					//泵房-火险 0-正常1-故障
					String fireStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_FIRE);
					if (fireStatus != null && fireStatus.trim().length() >0 ) {
						Integer fs = Integer.valueOf(fireStatus);
						if (fs!=0) {
							dasb.setFireStatus("火警");
							abnormalCode++;
						}
					}
					
					//泵房-门禁 0-正常1-故障
					String guardStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_ENTRANCE_GUARD);
					if (guardStatus != null && guardStatus.trim().length() >0 ) {
						Integer gs = Integer.valueOf(guardStatus);
						if (gs!=0) {
							dasb.setGuardStatus("故障");
							abnormalCode++;
						}
					}
					
					//泵房-排水系统故障 0-正常1-故障
					String systemStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
					if (systemStatus != null && systemStatus.trim().length() >0 ) {
						Integer ss = Integer.valueOf(systemStatus);
						if (ss!=0) {
							dasb.setSystemStatus("故障");
							abnormalCode++;
						}
					}
					
					//出口压力超高 0-正常1-故障
					String outWaterStatus = jsObj.getString(filed+FiledConstant.OUTLET_PRESSURE_SUPER_HIGH);
					if (outWaterStatus != null && outWaterStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(outWaterStatus);
						if (ows!=0) {
							dasb.setOutWaterStatus("故障");
							abnormalCode++;
						}
					}
					
					//1#变频器故障0-正常1-故障
					String frequencyStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS);
					if (frequencyStatus != null && frequencyStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(outWaterStatus);
						if (ows!=0) {
							dasb.setFrequencyStatus("故障");
							abnormalCode++;
						}
					}
					//2#变频器故障0-正常1-故障
					String frequencyTwoStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_TWO);
					if (frequencyTwoStatus != null && frequencyTwoStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyTwoStatus);
						if (ows!=0) {
							dasb.setFrequencyTwoStatus("故障");
							abnormalCode++;
						}
					}
					//3#变频器故障0-正常1-故障
					String frequencyThreeStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_THREE);
					if (frequencyThreeStatus != null && frequencyThreeStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyThreeStatus);
						if (ows!=0) {
							dasb.setFrequencyThreeStatus("故障");
							abnormalCode++;
						}
					}
					
					//4#变频器故障0-正常1-故障
					String frequencyFourStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_FOUR);
					if (frequencyFourStatus != null && frequencyFourStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyFourStatus);
						if (ows!=0) {
							dasb.setFrequencyFourStatus("故障");
							abnormalCode++;
						}
					}
					
					
					//5#变频器故障0-正常1-故障
					String frequencyFiveStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_FIVE);
					if (frequencyFiveStatus != null && frequencyFiveStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyFiveStatus);
						if (ows!=0) {
							dasb.setFrequencyFiveStatus("故障");
							abnormalCode++;
						}
					}
					//6#变频器故障0-正常1-故障
					String frequencySixStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_SIX);
					if (frequencySixStatus != null && frequencySixStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencySixStatus);
						if (ows!=0) {
							dasb.setFrequencySixStatus("故障");
							abnormalCode++;
						}
					}
					
					
					String pumpOne = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_ONE);
					String temperatureOne = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_ONE);
					if(null != pumpOne && pumpOne.trim().length() != 0 || null != temperatureOne && temperatureOne.trim().length() != 0){
						if (pumpOne.equals("3") || pumpOne.equals("4") || !pumpTemperature(temperatureOne,maxTemperature)) {
							PumpBean p = mds.createPump("1#泵变频",pumpOne);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_ONE),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_ONE));
							pumps.add(p);
							abnormalCode++;
						}
					}
					
					String pumpTwo = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_TWO);
					String temperatureTwo = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_TWO);
					if(null != pumpTwo && pumpTwo.trim().length() != 0 || null != temperatureTwo && temperatureTwo.trim().length() != 0){
						if (pumpTwo.equals("3") || pumpTwo.equals("4") || !pumpTemperature(temperatureTwo,maxTemperature)) {
							PumpBean p = mds.createPump("2#泵变频",pumpTwo);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_TWO),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_TWO));
							pumps.add(p);
							abnormalCode++;
						}
					}
					String pumpThree = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_THREE);
					String temperatureThree = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_THREE);
					if(null != pumpThree && pumpThree.trim().length() != 0 || null != temperatureThree && temperatureThree.trim().length() != 0){
						if (pumpThree.equals("3") || pumpThree.equals("4") || !pumpTemperature(temperatureThree,maxTemperature)) {
							PumpBean p = mds.createPump("3#泵变频",pumpThree);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_THREE),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_THREE));
							pumps.add(p);
							abnormalCode++;
						}
					}
					
					String pumpFour = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FOUR);
					String temperatureFour = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FOUR);
					if(null != pumpFour && pumpFour.trim().length() != 0 || null != temperatureFour && temperatureFour.trim().length() != 0){
						if (pumpFour.equals("3") || pumpFour.equals("4") || !pumpTemperature(temperatureFour,maxTemperature)) {
							PumpBean p = mds.createPump("4#泵变频",pumpFour);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FOUR),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_FOUR));
							pumps.add(p);
							abnormalCode++;
						}
					}
					String pumpFive = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FIVE);
					String temperatureFive = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FIVE);
					if(null != pumpFive && pumpFive.trim().length() != 0 || null != temperatureFive && temperatureFive.trim().length() != 0){
						if (!pumpTemperature(temperatureFive,maxTemperature) || pumpFive.equals("3") || pumpFive.equals("4")) {
							PumpBean p = mds.createPump("5#泵变频",pumpFive);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FIVE),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_FIVE));
							pumps.add(p);
							abnormalCode++;
						}
					}
					String pumpSix = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_SIX);
					String temperatureSix = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_SIX);
					if(null != pumpSix && pumpSix.trim().length() != 0 || null != temperatureSix && temperatureSix.trim().length() != 0){
						if (!pumpTemperature(temperatureSix,maxTemperature)||pumpSix.equals("3") || pumpSix.equals("4")) {
							PumpBean p = mds.createPump("6#泵变频",pumpSix);
							//泵站温度及状态
							mds.pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_SIX),p,maxTemperature);
							//运行时间
							p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_SIX));
							pumps.add(p);
							abnormalCode++;
						}
					}
					if (pumps.size()>0) {
						dasb.setPumpList(pumps);
					}
				}
				if (abnormalCode > 0) {
					dasbList.add(dasb);
				}
				if (dasbList.size()>0) {
					pasb.setDasbList(dasbList);
				}
			}
			if (pasb.getDasbList() != null) {
				pasbList.add(pasb);
			}
		}
		
		return pasbList;
	}
	
	/**
	 * 设置泵站温度
	 * @param temperature
	 * @param p
	 * @param maxTemperature
	 */
	public boolean pumpTemperature(String temperature,double maxTemperature){
		if (temperature != null && temperature.trim().length() > 0) {
			Double temperatureOne = Double.valueOf(temperature);
			if (temperatureOne > maxTemperature) {
				return false;
			}
		}
		return true;
	};
	
	/**
	 * 盯一盯推送
	 * @throws ParseException
	 */
	public void stareOneStareTask()throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		//查询项目
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		List<ProjectBean> proList = projectMapper.findProjectApp(map);
		Set<UserBean> userSet = new HashSet<UserBean>();
		int abnormalCode = 0;
		out:for (ProjectBean projectBean : proList) {
			//最高温度
			double maxTemperature = projectBean.getPumpTemperatureMax();
			Map<String, Object> devMap = new HashMap<String, Object>();
			devMap.put("projectId", projectBean.getId());
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("projectId", projectBean.getId());
			List<UserBean> userList = userMapper.findContacts(userMap);
			List<DeviceBean> devList = deviceMapper.findDevice(devMap);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
			List<MonitorDataBean> datas = monitorDataMapper.queryMonitorData(projectBean.getTableName());
			JSONArray dataJson = JSONArray.fromObject(datas);
			for (DeviceBean deviceBean : devList) {
				String filed = deviceBean.getTableDevice().split("\\|")[2];
				for (Object obj : dataJson) {
					// 循环获取泵信息
					JSONObject jsObj = JSONObject.fromObject(obj);
					//稳流罐 0-正常1-故障
					String steadyFlowTank = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_TEMPERATURE);
					Integer sft = 0;
					if (steadyFlowTank != null && steadyFlowTank.trim().length() >0 ) {
						sft = Integer.valueOf(steadyFlowTank);
						if (sft != 0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					//泵房-水箱缺水 0-正常2-高液位3-溢流
					String boxStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
					Integer bs = 0;
					if (boxStatus != null && boxStatus.trim().length() >0 ) {
						bs = Integer.valueOf(boxStatus);
						if(bs == 2 || bs == 3){
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//泵房-火险 0-正常1-故障
					String fireStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_FIRE);
					if (fireStatus != null && fireStatus.trim().length() >0 ) {
						Integer fs = Integer.valueOf(fireStatus);
						if (fs!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//泵房-门禁 0-正常1-故障
					String guardStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_ENTRANCE_GUARD);
					if (guardStatus != null && guardStatus.trim().length() >0 ) {
						Integer gs = Integer.valueOf(guardStatus);
						if (gs!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//泵房-排水系统故障 0-正常1-故障
					String systemStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
					if (systemStatus != null && systemStatus.trim().length() >0 ) {
						Integer ss = Integer.valueOf(systemStatus);
						if (ss!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//出口压力超高 0-正常1-故障
					String outWaterStatus = jsObj.getString(filed+FiledConstant.OUTLET_PRESSURE_SUPER_HIGH);
					if (outWaterStatus != null && outWaterStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(outWaterStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//1#变频器故障0-正常1-故障
					String frequencyStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS);
					if (frequencyStatus != null && frequencyStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(outWaterStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					//2#变频器故障0-正常1-故障
					String frequencyTwoStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_TWO);
					if (frequencyTwoStatus != null && frequencyTwoStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyTwoStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					//3#变频器故障0-正常1-故障
					String frequencyThreeStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_THREE);
					if (frequencyThreeStatus != null && frequencyThreeStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyThreeStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					//4#变频器故障0-正常1-故障
					String frequencyFourStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_FOUR);
					if (frequencyFourStatus != null && frequencyFourStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyFourStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					
					//5#变频器故障0-正常1-故障
					String frequencyFiveStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_FIVE);
					if (frequencyFiveStatus != null && frequencyFiveStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencyFiveStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					//6#变频器故障0-正常1-故障
					String frequencySixStatus = jsObj.getString(filed+FiledConstant.FREQUENCY_STATUS_SIX);
					if (frequencySixStatus != null && frequencySixStatus.trim().length() >0 ) {
						Integer ows = Integer.valueOf(frequencySixStatus);
						if (ows!=0) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					
					String pumpOne = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_ONE);
					String temperatureOne = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_ONE);
					if(null != pumpOne && pumpOne.trim().length() != 0 || null != temperatureOne && temperatureOne.trim().length() != 0){
						if (!pumpTemperature(temperatureOne,maxTemperature)||pumpOne.equals("3") || pumpOne.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					String pumpTwo = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_TWO);
					String temperatureTwo = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_TWO);
					if(null != pumpTwo && pumpTwo.trim().length() != 0 || null != temperatureTwo && temperatureTwo.trim().length() != 0){
						if (!pumpTemperature(temperatureTwo,maxTemperature)||pumpTwo.equals("3") || pumpTwo.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					String pumpThree = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_THREE);
					String temperatureThree = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_THREE);
					if(null != pumpThree && pumpThree.trim().length() != 0 || null != temperatureThree && temperatureThree.trim().length() != 0){
						if (!pumpTemperature(temperatureThree,maxTemperature)||pumpThree.equals("3") || pumpThree.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					
					String pumpFour = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FOUR);
					String temperatureFour = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FOUR);
					if(null != pumpFour && pumpFour.trim().length() != 0 || null != temperatureFour && temperatureFour.trim().length() != 0){
						if (!pumpTemperature(temperatureFour,maxTemperature)||pumpFour.equals("3") || pumpFour.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					String pumpFive = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FIVE);
					String temperatureFive = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FIVE);
					if(null != pumpFive && pumpFive.trim().length() != 0 || null != temperatureFive && temperatureFive.trim().length() != 0){
						if (!pumpTemperature(temperatureFive,maxTemperature)||pumpFive.equals("3") || pumpFive.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
					String pumpSix = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_SIX);
					String temperatureSix = jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_SIX);
					if(null != pumpSix && pumpSix.trim().length() != 0 || null != temperatureSix && temperatureSix.trim().length() != 0){
						if (!pumpTemperature(temperatureSix,maxTemperature)||pumpSix.equals("3") || pumpSix.equals("4")) {
							userSet.addAll(userList);
							abnormalCode++;
							continue out;
						}
					}
				}
			}
		}
		if (abnormalCode>0) {
			Map<String,String> extendParam = new HashMap<String, String>();
			extendParam.put("info_type", PushMessageUtil.DING_INFO);
			PushMessageUtil.pushMessagesList(userSet, "盯一盯异常", extendParam);
		}
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
	}
	
	
	/**
	 * 删除项目
	 * @param projectId
	 */
	@Transactional
	public void deleteProjectById(Integer projectId){
		//删除分区零件
		componentDeviceMapper.deleteByProjectId(projectId);
		//还原更新分区数据表状态
		cSDeviceMapper.updByProjectId(projectId);
		//根据项目id还原更新项目表状态
		cSProjectMapper.updByProjectId(projectId);
		//删除泵站信息
		deviceInformationMapper.deleteByProjectId(projectId);
		//根据项目id删除订单零件
		orderCompoentMapper.deleteByProjectId(projectId);
		//根据项目id删除 订单描述
		orderDescribe.deleteByProjectId(projectId);
		//根据项目id删除订单 通知信息
		orderInfoMapper.deleteByProjectId(projectId);
		//删除专项服务具体内容
		projectApplyDetailsBeanMapper.deleteByProjectId(projectId);
		//删除专项服务申请 根据项目id
		projectApplyBeanMapper.deleteByProjectId(projectId);
		//删除项目相关订单
		orderMapper.deleteByProjectId(projectId);
		//删除项目相关分区
		deviceMapper.deleteByProjectId(projectId);
		//删除项目
		projectMapper.deleteProjectById(projectId);
		
	}
	
	
	
}
