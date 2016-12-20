<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>沃加后台管理系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<!-- 域名logo -->
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resource/assets/img/favicon.png">
    <link href="<%=request.getContextPath()%>/resource/css/login/login.css" rel="stylesheet">
    <style>
		body{height:100%;background:#16a085;overflow:hidden;}
		canvas{z-index:-1;position:absolute;}
	</style>
	<script src="<%=request.getContextPath()%>/resource/assets/js/jquery-2.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/angularJs/angular.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/login/verificationNumbers.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/login/Particleground.js"></script>
	
	<link href="<%=request.getContextPath()%>/resource/layer/skin/layer.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/resource/layer/layer.js"></script>
	
	<script src="<%=request.getContextPath()%>/resource/assets/js/jQuery.md5.js"></script>
	
	<script>
	$(document).ready(function() {
	  //粒子背景特效
	  $('body').particleground({
	    dotColor: '#5cbdaa',
	    lineColor: '#5cbdaa'
	  });
	});
	</script>
</head>
<body ng-app="app" ng-controller="controller">
	<form name="form">
		<dl class="admin_login">
		 <dt>
		  <strong>沃加后台管理系统</strong>
		  <em>Management System</em>
		 </dt>
		 <dd class="user_icon">
		  <input type="text" name="account" id="account" placeholder="账号" class="login_txtbx" required ng-keyup="enter($event)"/>
		 </dd>
		 <dd class="" style="height: 10px;">
		 	&nbsp;
		 </dd>
		 <dd class="pwd_icon">
		  <input type="password" name="pwd" id="pwd" placeholder="密码" class="login_txtbx" required ng-keyup="enter($event)"/>
		 </dd>
		 <dd class="" style="height: 10px;">
		 	&nbsp;
		 </dd>
		 <dd>
		  <input type="button" value="立即登录" class="submit_btn" ng-click="login()" ng-disabled="form.$invalid" ng-keyup="enter($event)"/>
		 </dd>
		</dl>
	</form>
	<script type="text/javascript">
		
		if(window.top != window.self){
			window.top.location = window.location;
		}
	
		function getRootPath(){
			//代表访问项目的根目录
			return '<%=request.getContextPath()%>';
		}
		new angular.module("app", []).controller("controller", function($scope,$http) {
			
			/*Enter事件*/
			$scope.enter=function(e){
				 var keycode = window.event?e.keyCode:e.which;
		         if(keycode==13&&!form.$invalid){
		         	//layer.msg('请输入', {offset: 0,shift: 6});
		         	$scope.login();
		         }
			};
			
			$scope.login=function(){
				$(".submit_btn").val("登录中...");
				var account = $("#account").val();
				var pwd = $.md5($("#pwd").val());
				//console.debug(pwd)
				if(account != "" && pwd!=""){
					$.ajax({
			            url:getRootPath()+"/web/user/login",
			            data:{account:account,pwd:pwd},
			            type:'post',
			            dataType:'json',
			            success:function(data){
			            	if(data.code==200){
								//var id = data.data.id;
								window.location.href=getRootPath()+"/admin/index.do";
							}else{
								layer.msg(data.msg, {offset: 0,shift: 6});
								$(".submit_btn").val("立即登录");
							}
			            }
			        });
		        }
				
			};
		});
	</script>
</body>
</html>

