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
            <li><a href="javascript:void(0)" onclick="deviceSpareParts()">查看分区零件</a></li>
            <li class="active"><span class="titleName"></span>分区零件</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	分区零件
	        <small>
	            DeviceSpareParts
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption"><span class="titleName"></span>分区零件&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addDeviceSpareParts" id="addDeviceSpareParts">
                    
                    	<div class="form-group sel" id="componentNameDiv">
                            <label>选择零件</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="componentName">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                    	
                        <!-- <div class="form-group sel" id="deviceNameDiv">
                            <label>保养时间</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" readonly="readonly" name="maintainTimes" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="maintainTimes" placeholder="保养时间"/>
                                </div>
                            </span>
                        </div> -->
                        
                        <div class="form-group sel" id="deviceNameDiv">
                            <label>零件更换(或开始使用时间)时间</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" readonly="readonly" name="replaceTimes" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="replaceTimes" placeholder="更换时间"/>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="deviceNameDiv">
                            <label>零件寿命(天)</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" name="life" id="life" placeholder="请输入零件寿命"/>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="deviceNameDiv">
                            <label>零件描述</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" name="describe" id="describe" placeholder="请输入零件描述"/>
                                </div>
                            </span>
                        </div>
                        
                        <!-- <input name="maintainTimes" type="hidden" id="maintainTimes"> -->
                        
                        <input type="hidden" name="id" id="id">
                        <input type="hidden" name="deviceId" id="deviceId">
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  		/* function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		} 
		$(function(){
			$("#maintainTimes").val(getNowFormatDate());
		}); */
  		//获取当前分区零件id
  		var id = getUrlParam("id");
  		//获取当前分区id
  		var deviceId = getUrlParam("deviceId");
  		$("#deviceId").val(deviceId);
  		if(id==null){
  			$(".titleName").html("绑定");
  		}else{
  			$(".titleName").html("修改");
  			$("#id").val(id);
  		}
  		
  		//加载零件库
		$.ajax({
			type:"POST",
			data:{},
			url:getRootPath()+"/web/component/findComponent",
			dataType:"json",
			success:function(data){
				var str = "<option value='0' selected>--请选择--</option>";
				for (var i = 0; i < data.data.length; i++) {
					str += "<option value='"+data.data[i].id+"'>"+data.data[i].componentName+"</option>";
				}
				$("#componentName").html(str);
			}
		});
  		
		$(function(){
			
			if(id!=null){
				$.ajax({
	    			type:"POST",
					data:{id:id},
					url:getRootPath()+"/web/componentDevice/findComponentDevice",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							$.ajax({
								type:"POST",
								data:{},
								url:getRootPath()+"/web/component/findComponent",
								dataType:"json",
								success:function(dataJson){
									var str = "<option value='0' selected>--请选择--</option>";
									for (var i = 0; i < dataJson.data.length; i++) {
										if(dataJson.data[i].id==data.data[0].componentId){
											str += "<option selected='selected' value='"+dataJson.data[i].id+"'>"+dataJson.data[i].componentName+"</option>";
										}else{
											str += "<option value='"+dataJson.data[i].id+"'>"+dataJson.data[i].componentName+"</option>";
										}
									}
									$("#componentName").html(str);
								}
							});
							
							$("#maintainTimes").val(timeStampConversion(data.data[0].maintainTime));
							$("#replaceTimes").val(timeStampConversion(data.data[0].replaceTime));
							$("#life").val(data.data[0].life);
							$("#describe").val(data.data[0].describe);
							
						}
					}
	    		});
			}
			
		});
		
		function submit(){
			if($("#componentName").val()==0||$("#componentName").val()==""){
				Notify("请选择零件",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#maintainTimes").val()==0||$("#maintainTimes").val()==""){
				Notify("请填写保养时间",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#replaceTimes").val()==0||$("#replaceTimes").val()==""){
				Notify("请填写零件更换时间",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#life").val()==0||$("#life").val()==""){
				Notify("请填写零件的寿命",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#describe").val()==0||$("#describe").val()==""){
				Notify("请填写零件描述",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:{id:id,deviceId:deviceId,componentId:$("#componentName").val(),maintainTimes:$("#maintainTimes").val(),replaceTimes:$("#replaceTimes").val(),life:$("#life").val(),describe:$("#describe").val()},
					url:getRootPath()+"/web/componentDevice/addOrUpdComponentDevice",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/device/deviceSparePartsList.do?id="+deviceId;
						}else{
							
						}
					}
				});
			}
		}
		
		function deviceSpareParts(){
			window.location.href=getRootPath()+"/admin/device/deviceSparePartsList.do?id="+deviceId;
		}
  	</script>
  </body>
</html>
