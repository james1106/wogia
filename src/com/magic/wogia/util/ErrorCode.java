package com.magic.wogia.util;//

public class ErrorCode {
	public static int Success = 0;
	public static int Fail = 100;// 失败
	public static int Success2 = 101;// 成功
	// public static int = 201;//参加调研次数已超过限制
	public static int USER_ALREADY_EXIST = 301;// 用户名存在
	public static int USER_DONT_EXIST = 302;// 用户名错误
	public static int PASSWORD_ERROR = 303;// 密码错误
	public static int OLD_PASSWORD_ERROR = 304;// 原密码错误
	public static int SQL_ERROR = 401;// SQL执行出错
	// public static int = 402;//调用WCF出错
	public static int INVALID_TIME = 501;// 时间戳验证失败
	public static int MISS_PARAM = 502;// 缺少参数
	public static int INVALID_CALL = 503;// 非法调用
	// public static int = 601;//数据收藏存在
	// public static int = 602;//数据收藏不存在
	public static int MAIL_SEND_FAIL = 701;// 邮件发送失败
	public static int SMS_SEND_FAIL = 702;// 短信发送失败
	public static int NO_DATA = 801;// 数据不存在
}
