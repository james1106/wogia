package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.CSDeviceBean;
import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.ICSDeviceMapper;
import com.magic.wogia.mapper.IDeviceMapper;
import com.magic.wogia.mapper.IDeviceTableMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.Timestamp;

/**
 * 
 * 功能：设备实现
 * 编写人员：lzh
 * 时间：2016年9月6日下午3:52:29
 */
@Service
public class DeviceService {

	@Resource
	private IDeviceMapper deviceMapper;
	@Resource
	private IDeviceTableMapper deviceTableMapper;
	@Resource
	private ICSDeviceMapper cSDeviceMapper;
	
	/**
	 * 添加或者更新设备
	 * @param device
	 */
	@Transactional
	public void addOrUpdDevice(DeviceBean device,Integer csId){
		if (device.getId() != null) {
			deviceMapper.updDevice(device);
		}else{
			deviceMapper.addDevice(device);
			//更新绑定状态
			CSDeviceBean devices = new CSDeviceBean();
			devices.setId(csId);
			devices.setStatus(1);
			cSDeviceMapper.updCsDevice(devices);
		}
	}
	
	
	/**
	 * 查询设备
	 * @param device
	 * @return
	 */
	public List<DeviceBean> findDevice(DeviceBean device,Integer pageNum,Integer pageSize,UserBean user){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(device);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		//用户所属单位级别
		map.put("userType", user.getIdType());
		//用户所属单位id
		map.put("objectId", user.getCompanyId());
		List<DeviceBean> deviceList = deviceMapper.findDevice(map);
		for (DeviceBean deviceBean : deviceList) {
			if (deviceBean.getDeviceCreatetime() != null) {
				deviceBean.setDeviceCreatetimes(Timestamp.DateTimeStamp(deviceBean.getDeviceCreatetime(), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		return deviceList;
	}
	
	/**
	 *  获取 客户数据库中 项目表里的所有字段
	 *   将对这些字段进行加工、
	 *  @param tableName  查询的表名  
	 */
	public List<String> getCSDevice(String dbName,String tableName){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2);
		return deviceTableMapper.queryByTable(dbName, tableName);
	}
	
	/**
	 *  更新添加 本地数据库 设备表
	 * @param fileds 从客户数据库获取到的数据
	 */
	public Integer updateDeviceTable(List<String> fileds,String tableName){
		
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		List<CSDeviceBean> css = cSDeviceMapper.queryDeviceByTableName(tableName, null);
		List<CSDeviceBean> needAdd = new ArrayList<CSDeviceBean>();
		Set<String> set = new TreeSet<String>();
		for (String filed : fileds) {
			if(	filed.indexOf("w") == -1){
				continue;
			}
			set.add(tableName+"|device|"+filed.split("w")[0]);
		}
		
		for (String filed : set) {
			CSDeviceBean temp = new CSDeviceBean();
			temp.setDeviceName(filed);
			if(!css.contains(temp)){
				temp.setStatus(StatusConstant.NOT_ALLOT);
				temp.setTableName(tableName);
				needAdd.add(temp);
			}
		}
		if(needAdd.size() != 0){
			cSDeviceMapper.batchAdd(needAdd);
		}
		return needAdd.size();
	}
	
	/**
	 * 查询所有未绑定的设备
	 * @return
	 */
	public List<CSDeviceBean> findStatus(String tableName){
		return cSDeviceMapper.findStatus(tableName);
	}
	/**
	 * 更新设备绑定状态
	 * @param device
	 */
	public void updCsDevice(CSDeviceBean device){
		cSDeviceMapper.updCsDevice(device);
	}
	
	public List<DeviceBean> queryByProject(Integer projectId){
		return deviceMapper.queryByProject(projectId);
	}
	
}
