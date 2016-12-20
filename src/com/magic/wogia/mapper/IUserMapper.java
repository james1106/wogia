package com.magic.wogia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.wogia.bean.UserBean;
/**
 * 
 * 功能：用户功能接口
 * 编写人员：lzh
 * 时间：2016年8月31日下午6:02:38
 */
public interface IUserMapper {

	/**
	 * 查询用户
	 * @param map
	 * @return
	 */
	public List<UserBean> findUser(Map<String,Object> map);
	
	/**
	 * 查询联系人 通讯录
	 * @param map
	 * @return
	 */
	public List<UserBean> findContacts(Map<String,Object> map);
	
	/**
	 * 添加用户
	 * @param userBean
	 * @return
	 */
	public void addUser(UserBean userBean);
	
	/**
	 * 更新用户
	 * @param userBean
	 * @return
	 */
	public void updUser(UserBean userBean);
	
	/**
	 * 更新token
	 * @param token
	 * @param id
	 * @return
	 */
	public Integer updateToken(String token,Integer id);
	
	/**
	 *  登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	UserBean login (@Param("mobile")String mobile,@Param("password")String password);
	
	/**
	 *  根据手机号查询
	 * @param mobile
	 * @return
	 */
	UserBean queryByMobile(@Param("mobile")String mobile);
	
	/**
	 *  通过userName 查询用户	
	 * @param userName
	 * @return
	 */
	UserBean queryByUserName(@Param("userName")String userName);
	
	/**
	 *   按条件统计用户数量
	 * @param mobile
	 * @param userName
	 * @return
	 */
	Integer countUser(@Param("mobile")String mobile,@Param("userName")String userName);
	/**
	 *  查询用户 姓名 电话 字段 适用于 订单
	 * @param userId
	 * @return
	 */
	UserBean queryByIdUserOrder(@Param("userId")Integer userId);
	/**
	 *  查询 辖区内的技术
	 * @return
	 */
	List<UserBean> queryTech(@Param("userType")Integer userType,@Param("unitId")Integer unitId,@Param("orderId")Integer orderId);
	/**
	 *  通过订单查询 订单创建人
	 * @param orderId
	 * @return
	 */
	UserBean queryByOrderId(@Param("orderId")Integer orderId);
	/**
	 *  查询所有用户的token
	 * @return
	 */
	List<UserBean> findAllToToken();
	
	/**
	 *  查询该用户类型所属单位 的 所有该角色的用户
	 *   例如: 水厂员工， 要知道 所直属上级的 所有片区管理员
	 * @param roleId  要查询的角色 (不能为空)
	 * @param idType 当前用户的类型
	 * @param unitId 当前用户的unitId
	 * @return
	 */
	List<UserBean> queryUserByItem(@Param("roleId")Integer roleId,@Param("idType")Integer idType,@Param("unitId")Integer unitId);
	/**
	 *  批量查询用的  设备token字段
	 * @param ids 不能为空 
	 * @return
	 */
	List<UserBean> batchQueryUserToken(@Param("ids")List<Integer> ids);
	
	/**
	 *  批量查询用户 通过 用户名
	 * @param userNames
	 * @return
	 */
	List<UserBean> batchQueryUserInfo(@Param("userNames")List<String> userNames);
	/**
	 *  根据设备 查询 物业员工
	 * @param deviceIds
	 * @return
	 */
	List<UserBean> queryEstateUserByDeviceId(@Param("comIds")List<Integer> comIds);
	/**
	 *  根据设备 查询 水厂员工
	 * @param deviceIds
	 * @return
	 */
	List<UserBean> queryWaterUserByDeviceId(@Param("comIds")List<Integer> comIds);
	
	/**
	 *  根据 物业用户ID集合 和 到期 零件ID集合 查询用户极其用户下面的项目
	 * @param userIds
	 * @param comIds
	 * @return
	 */
	List<UserBean> queryEstateUseAndProject(@Param("userIds")List<Integer> userIds,@Param("comIds")List<Integer> comIds);
	
	/**
	 *  根据 水厂用户ID集合 和 到期 零件ID集合 查询用户极其用户下面的项目
	 * @param userIds
	 * @param comIds
	 * @return
	 */
	List<UserBean> queryWaterUseAndProject(@Param("userIds")List<Integer> userIds,@Param("comIds")List<Integer> comIds);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
