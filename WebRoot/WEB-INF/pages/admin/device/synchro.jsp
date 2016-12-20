<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<%@include file="../resource_css.jsp" %>
	
	<style>
		.select{
			width:100px;
			max-width: 100% !important;
			min-width: 100px !important;
			float: left;
			margin-right: 15px;
		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">同步分区</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	同步分区
	        <small>
	            Synchro
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">同步分区&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
            		<a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="tongBuDevice()">同步设备</a>
                    <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="tongBuProject()">同步项目</a>
                    <form role="form" name="addCompany">
                        <div class="form-group sel" id="tongBuDeviceDiv" style="margin-top: 30px;display: none;">
                            <label>选择所属项目同步</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="project">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        <br>
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" style="display: none;" id="submit" onclick="submit()" disabled>提交同步</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
		$(function(){
			$("#project").bind("change",function(){
				if($("#project").val()==0){
					$("#submit").attr("disabled","disabled");
				}else{
					$("#submit").removeAttr("disabled");
				}
			});
		});
		
		//同步设备
		function tongBuDevice(){
			$("#tongBuDeviceDiv").show("slow");
			$("#submit").show("slow");
			$("#project option").remove();
			$("#project").append("<option value='0' selected>--请选择--</option>");
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/project/findProject",
				dataType:"json",
				success:function(data){
					console.log(data);
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].tableName+"'>"+data.data[i].projectName+"</option>";
					}
					$("#project").append(str);
				}
			});
		}
		
		function submit(){
			$.ajax({
				type:"POST",
				data:{tableName:$("#project").val()},
				url:getRootPath()+"/web/synData/synDevice",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.code==200){
						Notify("成功同步 "+data.data+" 数据",'top-right','5000','danger','fa-desktop',true);
					}else{
						Notify(data.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
		}
		
		//同步项目
		function tongBuProject(){
			$("#tongBuDeviceDiv").hide("slow");
			$("#submit").hide("slow");
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/synData/synProject",
				dataType:"json",
				success:function(data){
					console.log(data.data);
					if(data.code==200){
						Notify("成功同步 "+data.data+" 数据",'top-right','5000','danger','fa-desktop',true);
					}else{
						Notify(data.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
		}
		
  	</script>
  </body>
</html>
