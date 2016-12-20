<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>沃加后台管理系统</title>

    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=basePath%>resource/resource/assets/img/favicon.png" type="image/x-icon">


    <!--Basic Styles-->
    <link href="<%=basePath%>resource/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->
    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath%>resource/assets/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resource/assets/css/demo.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/animate.min.css" rel="stylesheet" />
    <link id="skin-link" href="<%=basePath%>resource/assets/css/skins/deepblue.min.css" rel="stylesheet" type="text/css" />

	<link href="<%=basePath%>resource/css/common.css" rel="stylesheet" />
    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="<%=basePath%>resource/assets/js/skins.min.js"></script>
    <link href="<%=request.getContextPath()%>/resource/layer/skin/layer.css" rel="stylesheet">
    
    <script>
    	function resizeHeight() {
	
	        var ifm= document.getElementById("iframe");
	        var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
	        if (ifm != null && subWeb != null) {
	            ifm.height = subWeb.body.scrollHeight;
	        }
	    }

    </script>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <!-- Loading Container -->
    <div class="loading-container">
        <div class="loading-progress">
            <div class="rotator">
                <div class="rotator">
                    <div class="rotator colored">
                        <div class="rotator">
                            <div class="rotator colored">
                                <div class="rotator colored"></div>
                                <div class="rotator"></div>
                            </div>
                            <div class="rotator colored"></div>
                        </div>
                        <div class="rotator"></div>
                    </div>
                    <div class="rotator"></div>
                </div>
                <div class="rotator"></div>
            </div>
            <div class="rotator"></div>
        </div>
    </div>
    <!--  /Loading Container -->
    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-inner">
            <div class="navbar-container">
                <!-- Navbar Barnd -->
                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand">
                        <span class="logo-mini"><b>沃加</b></span>
            			<span class="logo-lg">沃加后台管理</span>
                    </a>
                </div>
                <!-- /Navbar Barnd -->

                <!-- Sidebar Collapse -->
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="collapse-icon fa fa-bars"></i>
                </div>
                <!-- /Sidebar Collapse -->
                <!-- Account Area and Settings --->
                <div class="navbar-header pull-right">
                   
                </div>
                <!-- /Account Area and Settings -->
            </div>
        </div>
    </div>
    <!-- /Navbar -->
    <!-- Main Container -->
    <div class="main-container container-fluid">
        <!-- Page Container -->
        <div class="page-container">
            <!-- Page Sidebar -->
            <div class="page-sidebar" id="sidebar">
                <!-- Page Sidebar Header-->
                <div class="sidebar-header-wrapper">功能</div>
                <!-- /Page Sidebar Header -->
                <!-- Sidebar Menu -->
                <ul class="nav sidebar-menu" id="sidebar-menu">
                    <!--Dashboard-->
                    <%-- <li>
                        <a href="<%=basePath%>admin/user/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 用户管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/company/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 公司管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/office/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 办事处管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/water/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 水厂管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/estate/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 物业管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/project/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 项目管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 分区管理</span>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="<%=basePath%>admin/device/synchro.do" target="iframe">
                                    <span class="menu-text">同步分区</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%=basePath%>admin/device/list.do" target="iframe">
                                    <span class="menu-text">查看分区</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%=basePath%>admin/waterPump/add.do" target="iframe">
                                    <span class="menu-text">添加水泵</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/presentation/uploadPresentation.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 水质报告 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/spareParts/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 零件库 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/order/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 订单管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/information/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 资讯管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/banner/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 广告管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/exclusiveService/serviceList.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 专享服务 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/applyContext/add.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 申请内容 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/notice/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 系统公告 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/powermanage/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 权限管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/suggest/list.do" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 意见反馈 </span>
                        </a>
                    </li> --%>
                    <li>
                        <a href="javascript:void(0)" id="loginOutBtn">
                            <i class="menu-icon fa fa-sign-out"></i>
                            <span class="menu-text"> 退出登录 </span>
                        </a>
                    </li>
                    <!--UI Elements-->
                    
                </ul>
                <!-- /Sidebar Menu -->
            </div>
            <!-- /Page Sidebar -->
            <!-- Page Content -->
            <div class="page-content" id="main-content">
            	<iframe id="iframe" name="iframe" onload="resizeHeight()" src="<%=basePath%>admin/default.do" frameborder="0" style="width: 100%;height: 1000px;">
            	</iframe>
            </div>
            <!-- /Page Content -->
        </div>
        <!-- /Page Container -->
        <!-- Main Container -->

    </div>

    <!--Basic Scripts-->
    <script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/assets/js/bootbox/bootbox.js"></script>
	<!-- layer -->
	<script src="<%=request.getContextPath()%>/resource/layer/layer.js"></script>
    <!--Beyond Scripts-->
    <script src="<%=basePath%>resource/assets/js/beyond.min.js"></script>
<!-- 	<script type="text/javascript" src="<%=basePath%>resource/js/index/index.js"></script> -->
	<script>
	
		var roleId = "${user.roleId}";
		initData(roleId);
		//载入菜单
		function initData(roleId){
			$.ajax({
				type : "POST",
				url : getRootPath()+"/web/rolePower/getPower",
				data:{roleId:roleId},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					console.log(json);
					if(json.code == 200){
						var dataList = json.data;
						buildMenu(dataList,roleId);
					}else{
						Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
		}
		//绑定菜单
		function buildMenu(dataList,roleId){
			var liMenu = "";
			for(var i = 0; i < dataList.length; i++){
				if(dataList[i].roleId == roleId){
					if(dataList[i].url=="device"){
						liMenu += deviceMenu();
					}else{
						liMenu += "<li>"+
									"<a href=\""+getRootPath()+"/admin/"+dataList[i].url+"\" target=\"iframe\">"+
										"<i class=\"menu-icon glyphicon glyphicon-home\"></i>"+
										"<span class=\"menu-text\"> "+dataList[i].description+" </span>"+
									"</a>"+
								"</li>";
					}
				}
			}
			liMenu += defaultMenu()
			$("#sidebar-menu").html(liMenu);
		}
		
		//多级菜单--分区菜单
		function deviceMenu(){
			return "<li><a href=\"#\" class=\"menu-dropdown\"><i class=\"menu-icon glyphicon glyphicon-home\"></i><span class=\"menu-text\"> 分区管理</span></a>"+
					"<ul class=\"submenu\">"+
					"<li><a href=\""+getRootPath()+"/admin/device/synchro.do\" target=\"iframe\"><span class=\"menu-text\">同步分区</span></a></li>"+
					"<li><a href=\""+getRootPath()+"/admin/device/list.do\" target=\"iframe\"><span class=\"menu-text\">查看分区</span></a></li>"+
					"</ul></li>";
		}
		
		//默认菜单
		function defaultMenu(){
			var str = "<li><a href=\"javascript:void(0)\" id=\"loginOutBtn\"><i class=\"menu-icon fa fa-sign-out\"></i><span class=\"menu-text\"> 退出登录 </span></a></li>";
			return str;
		}
	
		var id = "${user.id}";
		if(id==""||id==0||id==null){
			bootbox.dialog({
			    message: "<p style='padding-top:10px;'>您还未登录，跳转登录页面</p>",
			    title: "提示框",
			    closeButton:false,
			    buttons: {
			        success: {
			            label: "确认",
			            className: "btn-danger",
			            callback: function () {
			            	window.location.href=getRootPath()+"/login.jsp";
			            }
			        }
			    }
			});
		}
		$(function(){
			$("#loginOutBtn").bind("click",function(){
				loginOut();
			});
		});
		
		function loginOut(){
   			bootbox.dialog({
			    message: "<p style='padding-top:10px;'>确定注销吗？</p>",
			    title: "提示框",
			    buttons: {
			    	"关闭": {
			            className: "btn-default",
			            callback: function () {
			            	
			            }
			      	},
			        success: {
			            label: "确认",
			            className: "btn-danger",
			            callback: function () {
			            	$.ajax({
								type:"POST",
								data:{},
								dataType:"json",
								url:getRootPath()+"/web/user/loginOut",
								success:function(data){
									if(data.code==200){
										window.location.href=getRootPath()+"/login.jsp";
									}
								}
							});
			            }
			        }
			    }
			});
    	}
		
		function getRootPath(){
			//代表访问项目的根目录
			return '<%=request.getContextPath()%>';
		}
		
		if(window.top != window.self){
				window.top.location = window.location;
			}
	    var secthei = document.documentElement.clientHeight || document.body.clientHeight;
	    var headerh = 55;
	    //        document.getElementById('sect').style.height = (secthei-headerh) + "px";
	    document.getElementById("iframe").style.height = (secthei-headerh) + "px";
	    $('#sidebar-collapse').click(function(){
	    	if($(this).hasClass('active')){
	    		$('.navbar-brand .logo-lg').hide();
	    		$('.navbar-brand .logo-mini').show();
	    	}else{
	    		$('.navbar-brand .logo-lg').show();
	    		$('.navbar-brand .logo-mini').hide();
	    	}
	    })
	    
	    $('.sidebar-menu li').each(function(){
	    	var $this=$(this).find('a');
	    	$(this).click(function(){
	    		$('.sidebar-menu li').removeClass('active');
	    		$(this).addClass('active');
	    	})
	    })
	</script>
</body>
<!--  /Body -->
</html>
