<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    	<%@include file="../resource_css.jsp" %>
    	
    	<style>
    		.container{
    			padding: 30px;
    		}
    		.padding{
    			padding: 10px;
    		}
    		.title{
    			font-size: 14px;
    			color: #666;
    			font-weight: bold;
    		}
    		.head{
    			width: 100px;
    			height: 100px;
    			border-radius:50%;
    			border: 1px solid #666;
    		}
    	</style>
  </head>
  
  <body>
  
  		<div class="container">
            <div class="row">
            	<div class="col-xs-12 padding">
	            	<div align="center">
	            		<img class="head" id="avatar" src="<%=request.getContextPath()%>/resource/img/head.png" alt="">
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">登录名：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="userName"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">真实姓名：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="realName"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">手机号码：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="mobile"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">角色：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="roleName"></div>
	            		<select class="form-control" id="role" style="display: none;">
	                    	<option value='0' selected>全部</option>
	                    </select>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">所属片区：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="companyName"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">担任职位：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="jobTitle"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">工号：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="jobNumber"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">性别：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="gender"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">邮箱：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="email"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">QQ：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="qq"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">注册日期：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="form-control" id="createtimes"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title"></span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<a href="javascript:void(0)" target="iframe" class="btn btn-info" id="updateBtn">修改</a>
	            	</div>
            	</div>
            </div>
  		</div>
  		<%@include file="../resource_js.jsp" %>
  		<script type="text/javascript">
  			
  			$(function(){
  				
  				//加载角色
	    		$.ajax({
					type:"GET",
					data:{},
					url:getRootPath()+"/web/role/findRole",
					dataType:"json",
					success:function(data){
						var str = "";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].role+"</option>";
						}
						$("#role").append(str);
					}
				});
				
				var avatar = $("#avatar");
				var userName = $("#userName");
	  			var realName = $("#realName");
	  			var mobile = $("#mobile");
	  			var roleName = $("#roleName")
	  			var role = $("#role");
	  			var companyName = $("#companyName");
	  			var jobTitle = $("#jobTitle");
	  			var jobNumber = $("#jobNumber");
	  			var gender = $("#gender");
	  			var email = $("#email");
	  			var qq = $("#qq");
	  			var createtimes = $("#createtimes");
  			
  				//获取参数
  				var id = getUrlParam("id");
  				var nullDate = "暂无";
				$.ajax({
					type:"POST",
					data:{id:id},
					dataType:"json",
					url:getRootPath()+"/web/user/findUser",
					success:function(data){
						console.log(data.data);
						//avatar.attr("src",data.data[0].avatar);
						
						if(data.data[0].userName==null){
							userName.html(nullDate);
						}else{
							userName.html(data.data[0].userName);
						}
						
						if(data.data[0].realName==null){
							realName.html(nullDate);
						}else{
							realName.html(data.data[0].realName);
						}
						
						if(data.data[0].companyName==null){
							companyName.html(nullDate);
						}else{
							companyName.html(data.data[0].companyName);
						}
						
						if(data.data[0].mobile==null){
							mobile.html(nullDate);
						}else{
							mobile.html(data.data[0].mobile);
						}
						
						roleName.html(data.data[0].roleName);
						
						if(data.data[0].jobTitle==null){
							jobTitle.html(nullDate);
						}else{
							jobTitle.html(data.data[0].jobTitle);
						}
						
						if(data.data[0].jobNumber==null){
							jobNumber.html(nullDate);
						}else{
							jobNumber.html(data.data[0].jobNumber);
						}
						
						if(data.data[0].gender==0){
							gender.html("男");
						}else{
							gender.html("女");
						}
						
						if(data.data[0].email==null){
							email.html(nullDate);
						}else{
							email.html(data.data[0].email);
						}
						
						if(data.data[0].qq==null||data.data[0].qq==""){
							qq.html(nullDate);
						}else{
							qq.html(data.data[0].qq);
						}
						
						if(data.data[0].createtimes==null||data.data[0].createtimes==""){
							createtimes.html(nullDate);
						}else{
							createtimes.html(data.data[0].createtimes);
						}
						
					}
				});
				
				$("#updateBtn").bind("click",function(){
					window.location.href=getRootPath()+"/admin/user/userEdit.do?id="+id;
				});

  			});
  			
  		</script>
  </body>
</html>
