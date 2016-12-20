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
			.padding{padding: 10px 10px 0 10px;}
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
		</style>
  </head>	
  
  <body>
  		<div class="container">
  			<div class="row">
  				<div class="col-xs-12 padding">
  					<img class="img" src="<%=request.getContextPath()%>/${news.imageUrl}" alt="资讯图片" title="资讯图片"/>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-12 padding">
  					<span class="title">${news.title}</span>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-12 padding">
  					<span class="time">
  						<fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
  					</span>
  					<span class="reads">阅读&nbsp;${news.reads+news.realReads}</span>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-12 padding">
  					<div class="context">
  						${news.content}
  					</div>
  				</div>
  			</div>
  		</div>
  		<script type="text/javascript">
	  		function getRootPath(){
				//代表访问项目的根目录
				return '<%=request.getContextPath()%>';
			}
  			$.ajax({
  				type:"POST",
  				data:{newsId:"${news.id}"},
  				dataType:"json",
  				url:getRootPath()+"/app/news/increaseReads",
  				success:function(data){}
  			});
  		</script>
  </body>
</html>
