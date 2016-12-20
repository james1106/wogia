package com.magic.wogia.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
 * 功能：用户实体
 * 编写人员：lzh
 * 时间：2016年8月31日下午5:39:00
 */
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//更新时间
	private Date updatetime = new Date();
	//更新  明文时间()
	private String updatetimes;
	//创建时间
	private Date createtime = new Date();
	//创建  明文时间
	private String createtimes;
	//真实姓名
	private String realName;
	//密码
	private String pwd;
	
	//（账号）登录时使用
	private String account;
	
	//邮箱
	private String email;
	//手机号or登录帐号
	private String mobile;
	//QQ
	private String qq;
	//头像
	private String avatar;
	//角色 外键id
	private Integer roleId;
	//角色类型名
	private String roleName;
	//状态  0:未登陆过，新账号 1:登陆过，老账号（正常账号） 3:已冻结
	private Integer status;
	//
	private String token;
	//外键  对应 t_user 表中的  unit_id(自己对应的级别单位的id)
	private Integer companyId;
	//设备类型
	private Integer deviceType;
	//
	private String deviceToken;
	//默认为男(0) 女(1)
	private Integer gender;
	//工作编号
	private String jobNumber;
	//工作头衔
	private String jobTitle;
	
	/**用户名*/
	private String userName;
	
	/**单位名称*/
	private String unitName;
	/**
	 * 公司名
	 */
	private String companyName;
	/**单位编号*/
	private String unitNumber;
	
	/**身份类型*/
	private Integer idType;
	/**是否静音 0 否  1 是*/
	private Integer silence;
	
	/**项目ID集合*/
	private String projectIds;
	/**设备ID集合*/
	private String deviceIds;
	/**零件ID集合*/
	private String comIds;
	/**项目名字集合*/
	private String projectName;
	/**零件名字集合*/
	private String componetName;
	/**设备名字集合*/
	private String deviceName;
	
	private ProjectBean project;
	
	
	private Set<ProjectBean> projects;
	
	
	public Set<ProjectBean> getProjects() {
		return projects;
	}
	public void setProjects(Set<ProjectBean> projects) {
		this.projects = projects;
	}
	public ProjectBean getProject() {
		return project;
	}
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	public String getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}
	public String getComIds() {
		return comIds;
	}
	public void setComIds(String comIds) {
		this.comIds = comIds;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getComponetName() {
		return componetName;
	}
	public void setComponetName(String componetName) {
		this.componetName = componetName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getSilence() {
		return silence;
	}
	public void setSilence(Integer silence) {
		this.silence = silence;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getUpdatetimes() {
		return updatetimes;
	}
	public void setUpdatetimes(String updatetimes) {
		this.updatetimes = updatetimes;
	}
	public String getCreatetimes() {
		return createtimes;
	}
	public void setCreatetimes(String createtimes) {
		this.createtimes = createtimes;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
