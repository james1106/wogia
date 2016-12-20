package com.magic.wogia.util;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;




import com.magic.wogia.bean.UserBean;
import com.magicbeans.push.bean.DeviceType;
import com.magicbeans.push.bean.Message;
import com.magicbeans.push.bean.NotificationType;
import com.magicbeans.push.queue.PushUtil;

public class PushMessageUtil {
	private static Logger logger = Logger.getLogger(PushMessageUtil.class);
	public static final String APP_TYPE = "member";
	
	/**系统消息*/
	public static final String SYSTEM_INFO = "1";
	/**订单消息*/
	public static final String ORDER_INFO = "2";
	/**盯一盯推送*/
	public static final String DING_INFO = "3";
	/**零件定时推送*/
	public static final String LINGJIAN_INFO = "4";

	public static void pushMessages(Object obj, String content, Map<String, String> extend){
		Message message = new Message();
		//设备类型
		Integer type = null;
		String deviceToken = null;
		if(null == obj){
			type = 2;
		}else if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			type = user.getDeviceType();
			deviceToken = user.getDeviceToken();
//			if(null )
			if(user.getSilence() == 1){
				message.setSound("");
			}
		}else{
			logger.debug("推送失败");
			return;
		}
		
		if (type != null) {
			if (type == 1) {
				message.setDeviceType(DeviceType.ios);
			} else if (type == 0) {
				message.setDeviceType(DeviceType.android);
			}else {
				message.setDeviceType(DeviceType.all);
			}
		}
		//token
		
		
		if (deviceToken != null) {
			message.setDeviceId(deviceToken);
		}
		//透传信息
		message.setNotificationType(NotificationType.notification_passthrough);
//		NotificationType.notification
		//推送内容
		if (content != null) {
			message.setContent(content);
		}
		//用户类型
		message.setAppType(APP_TYPE);
		// 设置扩展参数
		if (extend != null) {
			message.setExtend(extend);
		}
		try {
			PushUtil.pushMessage(message);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推动消息失败!",e);
		}

	}
	public static void pushMessagesList(Set<UserBean> userList, String content, Map<String, String> extend){
		
		//设备类型
		Integer type = null;
		String deviceToken = null;
		for (UserBean userBean : userList) {
			Message message = new Message();
			type = userBean.getDeviceType();
			deviceToken = userBean.getDeviceToken();
			if(userBean.getSilence() == 1){
				message.setSound("");
			}
			if (type != null) {
				if (type == 1) {
					message.setDeviceType(DeviceType.ios);
				} else if (type == 0) {
					message.setDeviceType(DeviceType.android);
				}else {
					message.setDeviceType(DeviceType.all);
				}
			}
			//token
			
			
			if (deviceToken != null) {
				message.setDeviceId(deviceToken);
			}else{
				continue;
			}
			//透传信息
			message.setNotificationType(NotificationType.notification_passthrough);
//			NotificationType.notification
			//推送内容
			if (content != null) {
				message.setContent(content);
			}
			//用户类型
			message.setAppType(APP_TYPE);
			// 设置扩展参数
			if (extend != null) {
				message.setExtend(extend);
			}
			try {
				PushUtil.pushMessage(message);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("推动消息失败!",e);
			}
		}
		
	}
	
}
