package com.magic.wogia.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  订单
 * @author QimouXie
 *
 */
public class OrderBean implements Serializable {

	private static final long serialVersionUID = -7108439767925433419L;
	/**主键ID*/
	private Integer id;
	/**订单描述*/
	private String orderDescribe;
	/**订单图片*/	
	private String images;
	/**订单图片JSON数据*/
	private JSONArray jsImages;
	/**订单状态*/
	private Integer status;
	/**订单进度*/
	private String orderData;
	/**订单进度 之 JSON 数组*/
	private JSONArray jsOrderData;
	/**OrderData 最后一组JSON数据*/
	private JSONObject orderDataBean;
	/**创建时间*/
	private Date createTime;
	/**创建人*/
	private Integer userId;
	/**订单号*/
	private String orderNumber;
	/**
	 * 联系人头像
	 */
	private String avatar;
	/**联系人*/
	private String userName;
	/**联系人电话*/
	private String userPhone;
	/**更新时间*/
	private Date updateTime;
	/**是否有效*/
	private Integer isValid;
	/**技术ID*/
	 private Integer techId;
	 /**技术人的信息*/
	 private UserBean tech;
	 /**分配者ID*/
	 private Integer serviceId;
	 /**订单类型 0 表示急修呼叫 1 保养项目*/
	 private Integer orderType;
	 /**设备ID*/
	 private Integer deviceId;
	 /**供货商*/
	 private String supplier;
	 /**零件ID*/
	 private String componentId;
	 /**分配者查看 0 未查看   1 查看*/
	 private Integer serviceCheck;
	 /** 技术人员查看 0  未查看  1 查看*/
	 private Integer techCheck;
	 /**技术是否接受任务 0 未接受  1 已经接受  2 已经开始维修*/
	 private Integer techStatus;
	 /**提交上来时的保修ID 属于业务属性*/
	 private String projectId;
	 /**订单所属的项目名称*/
	 private  String projectName;
	 /**订单所属的设备名称*/
	 private String deviceName;
	 /**项目地址*/
	 private String projectAddress;
	 /**订单零件  一对多*/
	 private List<OrderCompoentBean> ocList; 
	 /**用户实体 一对一*/
	 private UserBean userBean;
	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTechStatus() {
		return techStatus;
	}

	public void setTechStatus(Integer techStatus) {
		this.techStatus = techStatus;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public JSONArray getJsOrderData() {
		return jsOrderData;
	}

	public void setJsOrderData(JSONArray jsOrderData) {
		this.jsOrderData = jsOrderData;
	}

	public JSONObject getOrderDataBean() {
		return orderDataBean;
	}

	public void setOrderDataBean(JSONObject orderDataBean) {
		this.orderDataBean = orderDataBean;
	}

	public Integer getServiceCheck() {
		return serviceCheck;
	}

	public void setServiceCheck(Integer serviceCheck) {
		this.serviceCheck = serviceCheck;
	}

	public Integer getTechCheck() {
		return techCheck;
	}

	public void setTechCheck(Integer techCheck) {
		this.techCheck = techCheck;
	}

	public UserBean getTech() {
		return tech;
	}

	public void setTech(UserBean tech) {
		this.tech = tech;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getOrderDescribe() {
		return orderDescribe;
	}

	public void setOrderDescribe(String orderDescribe) {
		this.orderDescribe = orderDescribe;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public JSONArray getJsImages() {
		return jsImages;
	}

	public void setJsImages(JSONArray jsImages) {
		this.jsImages = jsImages;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrderData() {
		return orderData;
	}

	public void setOrderData(String orderData) {
		this.orderData = orderData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getTechId() {
		return techId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String buildOrderNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmssSSSS");
		return format.format(new Date());
	}

	public List<OrderCompoentBean> getOcList() {
		return ocList;
	}

	public void setOcList(List<OrderCompoentBean> ocList) {
		this.ocList = ocList;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
}






























