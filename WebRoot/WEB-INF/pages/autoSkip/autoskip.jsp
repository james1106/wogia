<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	
	<h2>正在跳转(如无法跳转，请用浏览器打开)...</h2>
	<script type="text/javascript">
	var browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return { //移动终端浏览器版本信�?
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端 
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览�?
				iPhone : u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览�?
				iPad : u.indexOf('iPad') > -1, //是否iPad 
			};
		}(),
	}
	if (browser.versions.iPhone || browser.versions.iPad
			|| browser.versions.ios) {
		window.location.href = "https://www.pgyer.com/Cmje";
	}
	if (browser.versions.android) {
		window.location.href = "https://www.pgyer.com/sjVX";
	}
	</script>
</body>

	</html>
