package com.magic.wogia.util;

public class OrderStatusConStant {
	
	/**待处理*/
	public static final Integer SUSPENDING = 2001;
	/**处理中*/
	public static final Integer HANDLING = 2002;
	/**待验证*/
	public static final Integer VERIFICATIONPENDING = 2003;
	/**验证通过*/
	public static final Integer OVERALLPENGDING = 2004;
	
	/**急修订单*/
	public static final Integer ORDER_TYPE_REPAIR = 0;
	/**保养订单*/
	public static final Integer ORDER_TYPE_MAINTENANCE = 1;
	/**订单有效*/
	public static final Integer VALID = 0;
	/**订单无效*/
	public static final Integer NON_VALID = 1;
	// 订单进度 话术
	/**订单已发出*/
	public static final String SUBMIT_ORDER = "管理员正在为您分配工程师,请耐心等待";
	/**已分配工程师*/
	public static final String ASSIGNED_TECH = "等待工程师受理";
	/**工程师已受理*/
	public static final String WAY_TECH = "预计{0} 到达";
	/**正在维修*/
	public static final String REPAIRING = "预计{0} 维修完成";
	/**维修完成 等待用户验收*/
	public static final String QUEST_VERIFI = "订单已处理完成，等待用户验证中";
	/**用户已验收*/
	public static final String QUEST_OVER = "该订单已处理完毕";
	public static final String REJECT_ORDER = "用户拒绝验收订单";
	/**未查看*/
	public static final Integer NON_CHECK = 0;
	/**查看*/
	public static final Integer CHECKED = 1;
	
	/**接受任务描述*/
	public static final Integer ACCEPTORDER_DECRIBE = 0;
	/**开始维修描述*/
	public static final Integer STARTREPAIR_DECRIBE = 1;
	/**维修完成描述*/
	public static final Integer REPAIEDR_DECRIBE = 2;
	/**用户反馈描述*/
	public static final Integer USER_DECRIBE = 3;
	
	/**技术未接受任务下的状态*/
	public static final Integer NON_ACCEPT=0;
	/**技术接受任务的状态*/
	public static final Integer ACCEPT_ORDER=1;
	/**技术完成订单*/
	public static final Integer FINISH_ORDER = 2;

}
