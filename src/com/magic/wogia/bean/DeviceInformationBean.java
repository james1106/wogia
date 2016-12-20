package com.magic.wogia.bean;

import java.io.Serializable;

/**
 * 
 * 功能：设备信息实体
 * 编写人员：lzh
 * 时间：2016年9月12日上午11:44:36
 */
public class DeviceInformationBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 建筑面积
	 */
	private String area;

	/**
	 * 总栋数
	 */
	private Integer totalBuild;

	/**
	 * 住户数量
	 */
	private Integer householdNum;

	/**
	 * 二次供水户数
	 */
	private Integer twoWaterHouseholdNum;

	/**
	 * 泵房位置
	 */
	private String pumpHouseAddress;

	/**
	 * 二次供水源
	 */
	private String twoWaterSource;
	private Integer twoWaterSourceId;
	/**
	 * 二次供水方式
	 */
	private String twoWaterMode;
	private Integer twoWaterModeId;
	/**
	 * 生产商
	 */
	private String manufacturer;

	/**
	 * 生产日期
	 */
	private String manufactureDate;

	/**
	 * 进水管网最低压力
	 */
	private String inletPipeLowestPressure;

	/**
	 * 进水管网最高压力
	 */
	private String inletPipePeakPressure;

	/**
	 * 市政小区介入点管径
	 */
	private String municipalDistrictCaliber;

	/**
	 * 给水系统图
	 */
	private String waterSysImgs;

	/**
	 * 开始楼层
	 */
	private Integer startFloor;

	/**
	 * 结束楼层
	 */
	private Integer endFloor;

	/**
	 * 供水能力
	 */
	private String waterAbility;

	/**
	 * 供水压力
	 */
	private String waterPressure;

	/**
	 * 主设备型号
	 */
	private String mainDeviceModel;

	/**
	 * 辅设备型号
	 */
	private String assistDeviceModel;

	/**
	 * 水泵品牌
	 */
	private String pumpBrand;

	/**
	 * 扬程
	 */
	private String lift;

	/**
	 * 流量
	 */
	private String flow;

	/**
	 * 主泵电机
	 */
	private String mainPumpMotor;

	/**
	 * 辅泵电机
	 */
	private String assistPumpMotor;

	/**
	 * 台数
	 */
	private String platformNum;

	/**
	 * 控制柜型号
	 */
	private String controlCabinetModel;

	/**
	 * 控制柜及电路图配置
	 */
	private String cabinetCircuitImgs;

	/**
	 * 稳压罐型号
	 */
	private String surgePumpModel;

	/**
	 * 稳压罐压力等级
	 */
	private String surgePumpPressureLevel;

	/**
	 * 进水主管口径尺寸
	 */
	private String inletMainNozzleCaliber;

	/**
	 * 进水支管口径尺寸
	 */
	private String inletAssistNozzleCaliber;

	/**
	 * 出水主管口径尺寸
	 */
	private String effluentMainNozzleCaliber;

	/**
	 * 出水支管口径尺寸
	 */
	private String effluentAssistNozzleCaliber;

	/**
	 * 进出口主管阀门类别
	 */
	private String importMainTubeValveType;

	/**
	 * 装配图
	 */
	private String assembleImgs;
	/**
	 * 项目负责人
	 */
	private String projectLiablePerson;
	private Integer projectLiablePersonId;
	/**
	 * 项目负责人电话
	 */
	private String liablePersonMobile;
	/**
	 * 监管单位管理人
	 */
	private String administrator;
	private Integer administratorId;
	/**
	 * 监管单位管理人电话
	 */
	private String administratorMobile;
	/**
	 * 监管单位
	 */
	private String superviseUnit;
	private Integer superviseUnitId;
	/**
	 * 单位地址
	 */
	private String superviseAddress;
	/**
	 * 设备阀门类别
	 */
	private String deviceValveType;
	/**
	 * 止回阀门类别
	 */
	private String nonReturnValveType;
	/**
	 * 供应商
	 */
	private String supplier;
	
	/**
	 * 关联分区设备表
	 */
	private Integer deviceId;
	/**
	 * 分区名
	 */
	private String deviceName;
	/**
	 * 项目名
	 */
	private String projectName;
	/**
	 * 项目地址
	 */
	private String projectAddress;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getTotalBuild() {
		return totalBuild;
	}

	public void setTotalBuild(Integer totalBuild) {
		this.totalBuild = totalBuild;
	}

	public Integer getHouseholdNum() {
		return householdNum;
	}

	public void setHouseholdNum(Integer householdNum) {
		this.householdNum = householdNum;
	}

	public Integer getTwoWaterHouseholdNum() {
		return twoWaterHouseholdNum;
	}

	public void setTwoWaterHouseholdNum(Integer twoWaterHouseholdNum) {
		this.twoWaterHouseholdNum = twoWaterHouseholdNum;
	}

	public String getPumpHouseAddress() {
		return pumpHouseAddress;
	}

	public void setPumpHouseAddress(String pumpHouseAddress) {
		this.pumpHouseAddress = pumpHouseAddress;
	}

	public String getTwoWaterSource() {
		return twoWaterSource;
	}

	public void setTwoWaterSource(String twoWaterSource) {
		this.twoWaterSource = twoWaterSource;
	}

	public String getTwoWaterMode() {
		return twoWaterMode;
	}

	public void setTwoWaterMode(String twoWaterMode) {
		this.twoWaterMode = twoWaterMode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getInletPipeLowestPressure() {
		return inletPipeLowestPressure;
	}

	public void setInletPipeLowestPressure(String inletPipeLowestPressure) {
		this.inletPipeLowestPressure = inletPipeLowestPressure;
	}

	public String getInletPipePeakPressure() {
		return inletPipePeakPressure;
	}

	public void setInletPipePeakPressure(String inletPipePeakPressure) {
		this.inletPipePeakPressure = inletPipePeakPressure;
	}

	public String getMunicipalDistrictCaliber() {
		return municipalDistrictCaliber;
	}

	public void setMunicipalDistrictCaliber(String municipalDistrictCaliber) {
		this.municipalDistrictCaliber = municipalDistrictCaliber;
	}

	public String getWaterSysImgs() {
		return waterSysImgs;
	}

	public void setWaterSysImgs(String waterSysImgs) {
		this.waterSysImgs = waterSysImgs;
	}

	public Integer getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(Integer startFloor) {
		this.startFloor = startFloor;
	}

	public Integer getEndFloor() {
		return endFloor;
	}

	public void setEndFloor(Integer endFloor) {
		this.endFloor = endFloor;
	}

	public String getWaterAbility() {
		return waterAbility;
	}

	public void setWaterAbility(String waterAbility) {
		this.waterAbility = waterAbility;
	}

	public String getWaterPressure() {
		return waterPressure;
	}

	public void setWaterPressure(String waterPressure) {
		this.waterPressure = waterPressure;
	}

	public String getMainDeviceModel() {
		return mainDeviceModel;
	}

	public void setMainDeviceModel(String mainDeviceModel) {
		this.mainDeviceModel = mainDeviceModel;
	}

	public String getAssistDeviceModel() {
		return assistDeviceModel;
	}

	public void setAssistDeviceModel(String assistDeviceModel) {
		this.assistDeviceModel = assistDeviceModel;
	}

	public String getPumpBrand() {
		return pumpBrand;
	}

	public void setPumpBrand(String pumpBrand) {
		this.pumpBrand = pumpBrand;
	}

	public String getLift() {
		return lift;
	}

	public void setLift(String lift) {
		this.lift = lift;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getMainPumpMotor() {
		return mainPumpMotor;
	}

	public void setMainPumpMotor(String mainPumpMotor) {
		this.mainPumpMotor = mainPumpMotor;
	}

	public String getAssistPumpMotor() {
		return assistPumpMotor;
	}

	public void setAssistPumpMotor(String assistPumpMotor) {
		this.assistPumpMotor = assistPumpMotor;
	}

	public String getPlatformNum() {
		return platformNum;
	}

	public void setPlatformNum(String platformNum) {
		this.platformNum = platformNum;
	}

	public String getControlCabinetModel() {
		return controlCabinetModel;
	}

	public void setControlCabinetModel(String controlCabinetModel) {
		this.controlCabinetModel = controlCabinetModel;
	}

	public String getCabinetCircuitImgs() {
		return cabinetCircuitImgs;
	}

	public void setCabinetCircuitImgs(String cabinetCircuitImgs) {
		this.cabinetCircuitImgs = cabinetCircuitImgs;
	}

	public String getSurgePumpModel() {
		return surgePumpModel;
	}

	public void setSurgePumpModel(String surgePumpModel) {
		this.surgePumpModel = surgePumpModel;
	}

	public String getSurgePumpPressureLevel() {
		return surgePumpPressureLevel;
	}

	public void setSurgePumpPressureLevel(String surgePumpPressureLevel) {
		this.surgePumpPressureLevel = surgePumpPressureLevel;
	}

	public String getInletMainNozzleCaliber() {
		return inletMainNozzleCaliber;
	}

	public void setInletMainNozzleCaliber(String inletMainNozzleCaliber) {
		this.inletMainNozzleCaliber = inletMainNozzleCaliber;
	}

	public String getInletAssistNozzleCaliber() {
		return inletAssistNozzleCaliber;
	}

	public void setInletAssistNozzleCaliber(String inletAssistNozzleCaliber) {
		this.inletAssistNozzleCaliber = inletAssistNozzleCaliber;
	}

	public String getEffluentMainNozzleCaliber() {
		return effluentMainNozzleCaliber;
	}

	public void setEffluentMainNozzleCaliber(String effluentMainNozzleCaliber) {
		this.effluentMainNozzleCaliber = effluentMainNozzleCaliber;
	}

	public String getEffluentAssistNozzleCaliber() {
		return effluentAssistNozzleCaliber;
	}

	public void setEffluentAssistNozzleCaliber(String effluentAssistNozzleCaliber) {
		this.effluentAssistNozzleCaliber = effluentAssistNozzleCaliber;
	}

	public String getImportMainTubeValveType() {
		return importMainTubeValveType;
	}

	public void setImportMainTubeValveType(String importMainTubeValveType) {
		this.importMainTubeValveType = importMainTubeValveType;
	}

	public String getAssembleImgs() {
		return assembleImgs;
	}

	public void setAssembleImgs(String assembleImgs) {
		this.assembleImgs = assembleImgs;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectLiablePerson() {
		return projectLiablePerson;
	}

	public void setProjectLiablePerson(String projectLiablePerson) {
		this.projectLiablePerson = projectLiablePerson;
	}

	public String getLiablePersonMobile() {
		return liablePersonMobile;
	}

	public void setLiablePersonMobile(String liablePersonMobile) {
		this.liablePersonMobile = liablePersonMobile;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public String getAdministratorMobile() {
		return administratorMobile;
	}

	public void setAdministratorMobile(String administratorMobile) {
		this.administratorMobile = administratorMobile;
	}

	public String getSuperviseUnit() {
		return superviseUnit;
	}

	public void setSuperviseUnit(String superviseUnit) {
		this.superviseUnit = superviseUnit;
	}

	public String getSuperviseAddress() {
		return superviseAddress;
	}

	public void setSuperviseAddress(String superviseAddress) {
		this.superviseAddress = superviseAddress;
	}

	public String getDeviceValveType() {
		return deviceValveType;
	}

	public void setDeviceValveType(String deviceValveType) {
		this.deviceValveType = deviceValveType;
	}

	public String getNonReturnValveType() {
		return nonReturnValveType;
	}

	public void setNonReturnValveType(String nonReturnValveType) {
		this.nonReturnValveType = nonReturnValveType;
	}

	public Integer getTwoWaterSourceId() {
		return twoWaterSourceId;
	}

	public void setTwoWaterSourceId(Integer twoWaterSourceId) {
		this.twoWaterSourceId = twoWaterSourceId;
	}

	public Integer getTwoWaterModeId() {
		return twoWaterModeId;
	}

	public void setTwoWaterModeId(Integer twoWaterModeId) {
		this.twoWaterModeId = twoWaterModeId;
	}

	public Integer getProjectLiablePersonId() {
		return projectLiablePersonId;
	}

	public void setProjectLiablePersonId(Integer projectLiablePersonId) {
		this.projectLiablePersonId = projectLiablePersonId;
	}

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public Integer getSuperviseUnitId() {
		return superviseUnitId;
	}

	public void setSuperviseUnitId(Integer superviseUnitId) {
		this.superviseUnitId = superviseUnitId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	
}
