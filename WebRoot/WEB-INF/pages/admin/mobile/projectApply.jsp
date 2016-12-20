<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML>
<html>
  <head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="<%=request.getContextPath()%>/resource/assets/css/bootstrap.min.css" rel="stylesheet" />
		<script src="<%=request.getContextPath()%>/resource/assets/js/jquery-2.0.3.min.js"></script>
		<style>
			.padding{padding: 15px 15px 0 15px;}
			.title{
				color: #4d4d4d;
			    font-size: 18px;
			    font-weight: bold;
			    line-height: 26px;
			    padding: 0 2px;
			    text-align: left;
			}
			.time{
				color: #8a8a8a;
			    display: inline-block;
			    font-size: 12px;
			}
			.img{max-width: 100%;}
			.reads{
				color: #8a8a8a;
			    display: inline-block;
			    font-size: 12px;
			    float: right;
			}
			.context img{
				max-width: 100%;
			}
			.applyBtn{
				background: #F38001;
				color: white;
				font-weight: bold;
				font-size:16px;
				text-align: center;
				padding: 15px;
				width: 80%;
			}
			.applyBtn:HOVER {
				color: white;
			}
			.applyBtn:focus {
				outline: none;
				-moz-outline: none;
				color: white;
				background: #F38001;
			}
		</style>
  </head>	
  
  <body>
  		<div class="container">
 			<div class="row">
  				<div class="col-xs-12 padding">
  					<div class="context" id="context">${context}</div>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-12 padding" style="text-align: center;padding-bottom: 15px;">
  					<input type="button" class="btn applyBtn" onclick="submit()" value="立即申请"/>
  				</div>
  			</div>
  		</div>
  		<script type="text/javascript">
  			
	  		function getRootPath(){
				//代表访问项目的根目录
				return '<%=request.getContextPath()%>';
			}
	  		//获取url传递的参数
			function getUrlParam(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
				var r = window.location.search.substr(1).match(reg); //匹配目标参数
				if (r != null) return unescape(r[2]); return null; //返回参数值
			}
  		
  			var projectId = "${projectId}";
  			var token = "${token}";
  			//token = "db507450698e4eae855ff8e77f0a8191";
  			//projectId = 1;
  			if(token.trim()==null||token.trim()==""){
  				$(".applyBtn").attr("disabled","disabled");
  			}
  			
  			/* $(function(){
  				$.ajax({
  					type:"POST",
  					data:{projectId:projectId,token:token},
  					dataType:"json",
  					url:getRootPath()+"/app/project/applyProject",
  					success:function(data){
  						if(data.msg!=null&&data.msg!=""){
  							$(".applyBtn").val(data.msg);
  						}
  					}
  				});
  			}); */
  			
  			function submit(){
  				$.ajax({
  					type:"POST",
  					data:{projectId:projectId,token:token},
  					dataType:"json",
  					url:getRootPath()+"/app/project/applyProject",
  					success:function(data){
  						$(".applyBtn").val(data.msg);
  					}
  				});
  			}
  			
  		</script>
  </body>
</html>
