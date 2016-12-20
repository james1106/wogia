package com.magic.wogia.util;

public class StatusConstant {
	/**未登录过*/
	public static final Integer NON_LOGINED = 0;
	/**审核通过*/
	public static final Integer PASS = 1;
	/**审核未通过*/
	public static final Integer NOTPASS = 2;
	/**账户被冻结 的状态*/
	public static final Integer FROZEN = 3;
	
	public static final String HEAD_COMPANY_NAME = "四川沃加科技有限公司";
	
	/**有权限*/
	public static final Integer ALLOW = 0x1;
	/**没有权限*/
	public static final Integer NON_ALLOW = 1011;
	
	// 错误代码
	/**获取成功*/
	public static final Integer SUCCESS_CODE = 200;
	/**获取失败*/
	public static final Integer Fail_CODE = 202;
	/**第一次登录*/
	public static final Integer FRTST_LOGIN = 203;
	/**没有权限 错误代码*/
	public static final Integer NOT_AGREE = 1001;
	/**对象不存在*/
	public static final Integer OBJECT_NOT_EXIST = 1002;
	/**字段不能为空*/
	public static final Integer FIELD_NOT_NULL= 1003;
	/**正在审核*/
	public static final Integer PENDING = 1004;
	/**未登录*/
	public static final Integer NOTLOGIN= 1005;
	/**没有数据*/
	public static final Integer  NO_DATA = 1006;
	/**账户被冻结*/
	public static final Integer ACCOUNT_FROZEN = 1007;
	/**订单无效*/
	public static final Integer ORDER_INVALID = 1008;
	/**订单状态异常*/
	public static final Integer ORDER_STATUS_ABNORMITY = 1009;
	/**对象已经存在*/
	public static final Integer OBJECT_EXIST = 1010;
	
	
	/**第一次登录*/
	public static final Integer FRIST_LOGIN = 4001;
	
	
	// 身份标识
	/**平台员工*/
	public static final Integer PLATFORM_USER = 0;
	/**片区员工*/
	public static final Integer AREA_USER = 1; 
	/**办事处员工*/
	public static final Integer OFFICE_USER = 2;
	/**水厂员工*/
	public static final Integer WATER_FACTORY_USER = 3;
	/**物业员工*/
	public static final Integer ESTATE_USER = 4;
	
	
	/**子公司*/
	public static final Integer FILIALE = 1;
	/**总公司*/
	public static final Integer PARENT_COMPANY = 0;
	/**办事处*/
	public static final Integer OFFICE_COMPANY = 2;
	
	//**问题分类
	
	/**软件类*/
	public static final Integer SOFTWARE = 0;
	/**硬件类*/
	public static final Integer HARDWARE = 1;
	
	// 是否接受通知邮件
	
	/**接受邮件通知*/
	public static final Integer ACCEPT_EMAIL = 1;
	
	/**不接受邮件通知*/
	public static final Integer NON_ACCEPT_EMAIL = 0;
	
	// 设备类型
	/**android*/
	public static final Integer ANDROID=0;
	/**ios*/
	public static final Integer IOS = 1;
	
	
	/**当天已经签到*/
	public static final Integer SIGNED = 0;
	/**当天没有签到*/
	public static final Integer NON_SIGNED = 1;
	
	/**isURL字段   不是直链 需要请求文章详细页*/
	public static final Integer NON_URL = 0;
	/**isURL字段 直接跳转到指定的URL*/
	public static final Integer URL = 1;
	
	/**没有投票*/
	public static final Integer VOTE = 0;
	/**有投票*/
	public static final Integer NON_VOVE = 1;
	
	
     
     /**系统消息*/
     public static final Integer SYSTEM_INFO = 0;
     /**订单消息*/
     public static final Integer ORDER_INFO = 1;
     
     // 客服状态
     /**在线*/
     public static final Integer ONLINE = 0;
     /**离线*/
     public static final Integer UNLINE = 1;
     
     // banner链接是否是内部文章
     /**不是链接到内部文章*/
     public static final Integer NON_INSIDE = 0;
     /**是内部文章*/
     public static final Integer INSIDE = 1;
     
     // 积分类型
     /**签到积分*/
     public static final Integer BONUS_TYPE_SIGN = 0;
     /**评论积分*/
     public static final Integer BONUS_TYPE_COMMENT = 1;
     
     // 用户类型 员工类型 0 总公司 1 分公司 2 办事处
     
     /**总公司 员工*/
     public static final Integer HEAD_COMPLANY = 0;
     /**分公司 员工*/
     public static final Integer BRANCH_OFFICE = 1;
     /**办事处 员工*/
     public static final Integer OFFICE = 2;
     
     /**没有分配*/
     public static final Integer NOT_ALLOT = 0;
     /**已经分配*/
     public static final Integer ALLOTED = 1;
     
     /**项目已经申请，但未录入数据*/
     public static final Integer PROJECT_APPLY_NONDATA= 0;
     /**项目已经申请，已录入数据*/
     public static final Integer PROJECT_APPLY_DATA= 1;
}
