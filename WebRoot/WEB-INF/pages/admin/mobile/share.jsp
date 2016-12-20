<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<title>沃加-分享</title>
		<style>
			@media screen and (min-width:1001px ) {
				.container{width: 1000px;margin: 0 auto;}
			}
			@media screen and (max-width:1000px) {
				.container{width: 100%;}
			}
			*{margin: 0;padding: 0;font-family: "微软雅黑";}
			.container{
				min-width: 320px;
				padding: 30px 0;
			}
			.logo{text-align: center;}
			.logo h1{color: #666;font-size: 22px;margin: 10px 0;}
			.edition{padding:0 10px;font-size: 13px;line-height:20px;color:#333;}
			.download-bar{text-align: center;margin-top: 20px;}
			.dropdown-btn{cursor: pointer;display: inline-block;font-size: 14px;font-weight: 500;height: 55px;line-height: 55px;text-decoration: none;width: 220px;margin-bottom: 20px;color: #fff;border-radius: 10000px;}
			.dropdown-btn i{width: 25px;height: 33px;display: inline-block;float: left;position: relative;top:11px;left:30px;}
			.ios-btn{background: #56bc94;}
			.ios-btn i{background: url("<%=request.getContextPath()%>/resource/img/ios-ioc.png");}
			.android-btn{background: #0066ff;margin-bottom: 0;}
			.android-btn i{background: url("<%=request.getContextPath()%>/resource/img/android-ioc.png");width: 28px;}
			.qr-bar{text-align: center;margin-top: 20px;}
			.qr-bar p{font-size: 13px;color: #333;margin-bottom:20px;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="logo">
				<img src="<%=request.getContextPath()%>/resource/img/pgy-logo.png">
				<h1>沃加</h1>
				<div class="edition">
					
				</div>
			</div>
			<div class="download-bar">
				<a href="https://www.pgyer.com/Cmje" class="dropdown-btn ios-btn"><i></i>苹果客户端下载</a><br>
				<a href="https://www.pgyer.com/sjVX" class="dropdown-btn android-btn"><i></i>安卓客户端下载</a>
			</div>
			<div class="qr-bar">
				<p>或者用手机扫描下面的二维码安装</p>
				<img src="<%=request.getContextPath()%>/resource/img/donw-qr.png" style="width: 156px;height: 156px;">
			</div>
		</div>
	</body>
</html>
