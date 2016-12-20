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
            <li class="active">查看分区</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	分区
	        <small>
	            Device
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加分区&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addDevice" id="addDevice">
                    	
                        <div class="form-group sel" id="deviceNameDiv">
                            <label>分区名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="deviceName" id="deviceName" type="text" placeholder="请输入分区名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="projectDiv">
                            <label>选择所属项目</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="project" name="projectId"></select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="tableNameDiv">
                            <label>选择绑定设备</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="tableName" name="tableDevice">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <input type="hidden" id="csId" name="csId">
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
		$(function(){
			//添加所属项目
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/project/findProject",
				dataType:"json",
				success:function(data){
					console.log(data);
					var str = "<option value='0' selected>--请选择--</option>";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option projectname='"+data.data[i].tableName+"' value='"+data.data[i].id+"'>"+data.data[i].projectName+"</option>";
					}
					$("#project").html(str);
				}
			});
			
			$("#project").bind("change",function(){
				var tableName = $("#project").find("option:selected").attr("projectname");
				//添加外库表名
				$.ajax({
					type:"POST",
					data:{tableName:tableName},
					url:getRootPath()+"/web/device/findStatus",
					dataType:"json",
					success:function(data){
						console.log(data);
						var str = "<option value='0' selected>--请选择--</option>";
						if(data.data.length==0){
							str = "<option value='0' selected>暂无</option>";
						}
						for (var i = 0; i < data.data.length; i++) {
							str += "<option id='"+data.data[i].id+"' value='"+data.data[i].deviceName+"'>"+data.data[i].deviceName+"</option>";
						}
						$("#tableName").html(str);
					}
				});
			});
			
			$("#tableName").bind("change",function(){
				var csId = $("#tableName").find("option:selected").attr("id");
				$("#csId").val(csId);
			});
			
		});
		
		function submit(){
			if($("#deviceName").val()==null||$("#deviceName").val()==""){
				Notify("请填写分区名字",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#tableName").val()==0||$("#tableName").val()==""){
				Notify("请选择所属表名",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#project").val()==0||$("#project").val()==""){
				Notify("请选择所属项目",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:$("#addDevice").serialize(),
					url:getRootPath()+"/web/device/addOrUpdDevice",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/device/list.do";
						}else{
							
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
