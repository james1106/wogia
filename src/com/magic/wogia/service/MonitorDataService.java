package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.FrequencyBean;
import com.magic.wogia.bean.MonitorDataBean;
import com.magic.wogia.bean.ProjectBean;
import com.magic.wogia.bean.PumpBean;
import com.magic.wogia.bean.PumpHouseBean;
import com.magic.wogia.mapper.IDeviceMapper;
import com.magic.wogia.mapper.IMonitorDataMapper;
import com.magic.wogia.mapper.IProjectMapper;
import com.magic.wogia.util.FiledConstant;

/**
 *  实时监控数据 业务层
 * @author QimouXie
 *
 */
@Service
public class MonitorDataService {
	
	@Resource
	private IMonitorDataMapper monitorDataMapper;
	@Resource
	private IProjectMapper projectMapper;
	@Resource
	private IDeviceMapper deviceMapper;
	/**
	 *  监控数据
	 * @param deviceName
	 */
	public Map<String,Object>  getMonitorData(String deviceName){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
		List<MonitorDataBean> datas = monitorDataMapper.queryMonitorData(deviceName.split("\\|")[0]);
		List<PumpBean> pumps = new ArrayList<PumpBean>();
		List<FrequencyBean> frequencys = new ArrayList<FrequencyBean>();
		//泵房信息
		PumpHouseBean pumpHouses = new PumpHouseBean();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONArray dataJson = JSONArray.fromObject(datas);
			String tableName = deviceName.split("\\|")[0];
			map.put("tableName", tableName);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
			List<ProjectBean> listProjectBean = projectMapper.findProject(map);
			//最高温度
			double maxTemperature = 0.0;
			for (ProjectBean projectBean : listProjectBean) {
				maxTemperature = projectBean.getPumpTemperatureMax();
			}
			String filed = deviceName.split("\\|")[2];
			
			
			
			for (Object obj : dataJson) {
				// 循环获取泵信息
				JSONObject jsObj = JSONObject.fromObject(obj);
				//泵房噪音
				pumpHouses.setNoise(jsObj.getString(filed+FiledConstant.PUMP_HOUSE_NOISE));
				//泵房湿度
				pumpHouses.setHumidity(jsObj.getString(filed+FiledConstant.PUMP_HOUSE_HUMIDITY));
				//泵房温度
				pumpHouses.setTemperature(jsObj.getString(filed+FiledConstant.PUMP_HOUSE_TEMPERATURE));
				//稳流罐 0-正常1-故障
				String steadyFlowTank = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_TEMPERATURE);
				if (steadyFlowTank != null && steadyFlowTank.trim().length() >0 ) {
					Integer sft = Integer.valueOf(steadyFlowTank);
					if (sft==0) {
						pumpHouses.setSteadyFlowTank("正常");
					}else{
						pumpHouses.setSteadyFlowTank("故障");
					}
				}
				//泵房-火险 0-正常1-故障
				String fireStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_FIRE);
				if (fireStatus != null && fireStatus.trim().length() >0 ) {
					Integer fs = Integer.valueOf(fireStatus);
					if (fs==0) {
						pumpHouses.setFireStatus("正常");
					}else{
						pumpHouses.setFireStatus("火警");
					}
				}
				
				
				//泵房-门禁 0-正常1-故障
				String guardStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_ENTRANCE_GUARD);
				if (guardStatus != null && guardStatus.trim().length() >0 ) {
					Integer gs = Integer.valueOf(guardStatus);
					if (gs==0) {
						pumpHouses.setGuardStatus("正常");
					}else{
						pumpHouses.setGuardStatus("故障");
					}
				}
				
				
				//泵房-排水系统故障 0-正常1-故障
				String systemStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
				if (systemStatus != null && systemStatus.trim().length() >0 ) {
					Integer ss = Integer.valueOf(systemStatus);
					if (ss==0) {
						pumpHouses.setSystemStatus("正常");
					}else{
						pumpHouses.setSystemStatus("故障");
					}
				}
				
				//泵房-水箱缺水 0-正常2-高液位3-溢流
				String boxStatus = jsObj.getString(filed+FiledConstant.PUMP_HOUSE_DRAINAGE_SYSTEM);
				if (boxStatus != null && boxStatus.trim().length() >0 ) {
					Integer bs = Integer.valueOf(boxStatus);
					if (bs==0) {
						pumpHouses.setBoxStatus("正常");
					}else if (bs==2){
						pumpHouses.setBoxStatus("高液位");
					}else{
						pumpHouses.setBoxStatus("溢流");
					}
				}
				
				
				String pumpOne = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_ONE);
				if(null != pumpOne && pumpOne.trim().length() != 0){
					PumpBean p = createPump("1#泵变频",pumpOne);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_ONE),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_ONE));
					pumps.add(p);
				}
				String pumpTwo = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_TWO);
				if(null != pumpTwo && pumpTwo.trim().length() != 0){
					PumpBean p = createPump("2#泵变频",pumpTwo);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_TWO),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_TWO));
					pumps.add(p);
				}
				String pumpThree = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_THREE);
				if(null != pumpThree && pumpThree.trim().length() != 0){
					PumpBean p = createPump("3#泵变频",pumpThree);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_THREE),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_THREE));
					pumps.add(p);
				}
				String pumpFour = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FOUR);
				if(null != pumpFour && pumpFour.trim().length() != 0){
					PumpBean p = createPump("4#泵变频",pumpFour);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FOUR),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_FOUR));
					pumps.add(p);
				}
				String pumpFive = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_FIVE);
				if(null != pumpFive && pumpFive.trim().length() != 0){
					PumpBean p = createPump("5#泵变频",pumpFive);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_FIVE),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_FIVE));
					pumps.add(p);
				}
				String pumpSix = jsObj.getString(filed+FiledConstant.PUMP_SIGNAL_SIX);
				if(null != pumpSix && pumpSix.trim().length() != 0){
					PumpBean p = createPump("6#泵变频",pumpSix);
					//泵站温度及状态
					pumpTemperature(jsObj.getString(filed+FiledConstant.PUMP_TEMPERATURE_SIX),p,maxTemperature);
					//运行时间
					p.setRunningTime(jsObj.getString(filed+FiledConstant.PUMP_RUN_TEIME_SIX));
					pumps.add(p);
				}
				
				String frequencyOne = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_ONE);
				String currentOne = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_ONE);
				if(null != frequencyOne && null !=currentOne  && frequencyOne.trim().length() != 0 && currentOne.trim().length() != 0  ){
					frequencys.add(createFrequency(frequencyOne,currentOne));
				}
				String frequencyTwo = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_TWO);
				String currentTwo = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_TWO);
				if(null != frequencyTwo && null !=currentTwo && frequencyTwo.trim().length() != 0 && currentTwo.trim().length() != 0 ){
					frequencys.add(createFrequency(frequencyTwo,currentTwo));
				}
				String frequencyThree = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_THREE);
				String currentThree = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_THREE);
				if(null != frequencyThree && null !=currentThree  && frequencyThree.trim().length() != 0 && currentThree.trim().length() != 0 ){
					frequencys.add(createFrequency(frequencyThree,currentThree));
				}
				String frequencyFour = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_FOUR);
				String currentFour = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_FOUR);
				if(null != frequencyFour && null !=currentFour  && frequencyFour.trim().length() != 0 && currentFour.trim().length() != 0 ){
					frequencys.add(createFrequency(frequencyFour,currentFour));
				}
				String frequencyFive = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_FIVE);
				String currentFive = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_FIVE);
				if(null != frequencyFive && null !=currentFive && frequencyFive.trim().length() != 0 && currentFive.trim().length() != 0 ){
					frequencys.add(createFrequency(frequencyFive,currentFive));
				}
				String frequencySix = jsObj.getString(filed+FiledConstant.FREQUENCY_CONVERTER_SIX);
				String currentSix = jsObj.getString(filed+FiledConstant.FREQUENCY_CURRENT_SIX);
				if(null != frequencySix && null !=currentSix && frequencySix.trim().length() != 0 && currentSix.trim().length() != 0 ){
					frequencys.add(createFrequency(frequencySix,currentSix));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Object tempObj = null;
		if(null != datas && datas.size() > 0){
			tempObj = datas.get(0);
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("baseData", tempObj);
		data.put("pumpData", pumps);
		data.put("frequencyData", frequencys);
		data.put("pumpHouseData", pumpHouses);
		return data;
	}
	
	
	
	/**
	 * 设置泵站温度
	 * @param temperature
	 * @param p
	 * @param maxTemperature
	 */
	public void pumpTemperature(String temperature,PumpBean p,double maxTemperature){
		p.setTemperature(temperature);
		if (temperature != null && temperature.trim().length() > 0) {
			Double temperatureOne = Double.valueOf(temperature);
			if (temperatureOne > maxTemperature) {
				p.setTemperatureStatus("超高");
			}else{
				p.setTemperatureStatus("正常");
			}
		}
		
	};
	
	public PumpBean createPump(String pumpName,String tempPumpStatus){
		//0-停机 1工频 2-变频 3故障 4检修 5-现场手动
		PumpBean pump = new PumpBean();
		pump.setPumpName(pumpName);
		if(null != tempPumpStatus && tempPumpStatus.trim().length() != 0 ){
			Integer pumpStatus = Integer.parseInt(tempPumpStatus);
			if(pumpStatus == 0){
				pump.setPumpStatus("停机");
			}else if(pumpStatus == 1){
				pump.setPumpStatus("工频");
			}else if(pumpStatus == 2){
				pump.setPumpStatus("变频");
			}else if(pumpStatus == 3){
				pump.setPumpStatus("故障");
			}else if(pumpStatus == 4){
				pump.setPumpStatus("检修");
			}else if(pumpStatus == 5){
				pump.setPumpStatus("现场手动");
			}else{
				pump.setPumpStatus("-");
			}
		}
		return pump;
	}
	
	private FrequencyBean createFrequency(String frequenc,String current){
		FrequencyBean frequency = new FrequencyBean();
		frequency.setElectric(current+"A");
		frequency.setFrequency(frequenc+"Hz");
		return frequency;
	}
	
	/**
	 *  监控图表
	 * @param deviceName
	 */
	public List<MonitorDataBean> getMonitorChart(String deviceName){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
		String filed_before = deviceName.split("\\|")[2];
		String filed = filed_before+FiledConstant.FLOWRATE;
		String filedFlows = filed_before+FiledConstant.CUMULATIVE_FLOWS;
		//设定压力
		String setPressure = filed_before+FiledConstant.SET_PRESSURE;
		//出口压力
		String outPressure = filed_before+FiledConstant.OUT_PRESSURE;
		//耗水电量
		String pcow = filed_before+FiledConstant.POWER_CONSUMPTION_OF_WATER;
		//总电量
		String te= filed_before+FiledConstant.TOTAL_ELECTRICITY;
		List<MonitorDataBean> datas = monitorDataMapper.queryMonitorChart(null,filed,filedFlows,deviceName.split("\\|")[0],setPressure,outPressure,pcow,te);
		return datas;
	}
	
	public Map<String, Object> getMonitorWater(String deviceName){
		Map<String, Object> maps = new HashMap<String, Object>(); 
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
		String filed_before = deviceName.split("\\|")[2];
		String ph = filed_before+FiledConstant.PH;
		String turbidity = filed_before+FiledConstant.TURBIDITY;
		String residual_oxygen = filed_before+FiledConstant.RESIDUAL_OXYGEN;
		String orp = filed_before+FiledConstant.ORP;
		String dissolved_oxygen = filed_before+FiledConstant.DISSOLVED_OXYGEN;
		String chroma = filed_before+FiledConstant.CHROMA;
		String comductivity = filed_before+FiledConstant.CONDUCTIVITY;
		List<String> fileds = new ArrayList<String>();
		fileds.add(ph);
		fileds.add(turbidity);
		fileds.add(residual_oxygen);
		fileds.add(orp);
		fileds.add(dissolved_oxygen);
		fileds.add(chroma);
		fileds.add(comductivity);
		List<MonitorDataBean> datas = monitorDataMapper.queryMonitorWater(fileds,deviceName.split("\\|")[0]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableDevice", deviceName);
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		List<DeviceBean> list =  deviceMapper.findDevice(map);
		for (DeviceBean deviceBean : list) {
			maps.put("imgs", deviceBean.getWaterImg());
		}
		maps.put("monitorData", datas.get(0));
		return maps;
	}
	

}
