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
            <li class="active"><span class="projectTitle">添加</span>项目</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	项目
	        <small>
	            Project
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption"><span class="projectTitle">添加</span>项目&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addproject" id="addproject">
                    	
                        <div class="form-group sel" id="projectNameDiv">
                            <label>项目名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="projectName" id="projectName" type="text" placeholder="请输入项目名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="projectAddressDiv">
                            <label>选择地址</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control select" id="province">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            	<select class="form-control select" id="city">
                            		<option value='0' selected>--请选择--</option>
                            		
                            	</select>
                            	<select class="form-control select" id="area" name="cityId">
									<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="projectAddressDiv">
                            <label>项目详细地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="projectAddress" id="projectAddress" type="text" placeholder="请输入项目详细地址">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="estateDiv">
                            <label>选择所属物业</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="estate" name="estateId">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="bindProjectDiv">
                            <label>选择绑定项目&nbsp;&nbsp;<span id="name" style="color:red;font-weight: bold;">注意：项目一但绑定了就不能解绑，请谨慎操作！</span></label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="bindProject" name="csProjectId">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="pumpTemperatureDiv">
                            <label>项目泵站最高温度&nbsp;&nbsp;<span style="color:red;font-weight: bold;">最高温度在（0~100）之间</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width:250px" class="form-control spinner-input" name="pumpTemperatureMax" id="pumpTemperatureMax" type="textbox" placeholder="请输入项目泵站温度" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span>&nbsp;C°</span>
                                </div>
                            </span>
                        </div>
                        
                        <input type="hidden" id="projectId" name="id">
                         
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  	
  		var projectId = getUrlParam("projectId");
		$("#projectId").val(projectId);
  		
		$(function(){
			
			//默认加载省
			$.ajax({
				type:"POST",
				data:{time:new Date()},
				url:getRootPath()+"/web/city/findCitys",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						if(data.data[i].levelType==1){
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
						}
					}
					$("#province").append(str);
					update(data.data);
				}
			});
			
			//添加市
			$("#province").bind("change",function(){
				if($("#province").val()==0||$("#province").val()==""){
					$("#city option").remove();
					$("#area option").remove();
					$("#city").append("<option value='0' selected>--请选择--</option>");
					$("#area").append("<option value='0' selected>--请选择--</option>");
				}else{
					addCity($("#province").val());
				}
			});
			
			//添加区
			$("#city").bind("change",function(){
				if($("#city").val()==0||$("#city").val()==""){
					$("#area option").remove();
					$("#area").append("<option value='0' selected>--请选择--</option>");
				}else{
					addArea($("#city").val());
				}
			});
			
			//默认添加物业
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/estate/findEstate",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].estateName+"</option>";
					}
					$("#estate").append(str);
				}
			});
			//添加绑定项目
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/project/findStatus",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].tableName+"</option>";
					}
					$("#bindProject").append(str);
				}
			});
			
		});
		//抽取添加市的方法
		function addCity(cityId){
			$.ajax({
				type:"POST",
				data:{parentId:cityId},
				url:getRootPath()+"/web/city/findCitys",
				dataType:"json",
				async: false,
				success:function(data){
					//console.log(data.data);
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
					}
					$("#city option").remove();
					$("#city").append("<option value='0' selected>--请选择--</option>");
					$("#city").append(str);
				}
			});
		}
		//抽取添加县/区的方法
		function addArea(cityId){
			$.ajax({
				type:"POST",
				data:{parentId:cityId},
				url:getRootPath()+"/web/city/findCitys",
				dataType:"json",
				async: false,
				success:function(data){
					//console.log(data.data);
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
					}
					$("#area option").remove();
					$("#area").append("<option value='0' selected>--请选择--</option>");
					$("#area").append(str);
				}
			});
		}
		function update(cityList){
			//判断是不是修改进来
			if(projectId != null&&projectId != 0){
				$(".projectTitle").html("修改");
				$("#bindProjectDiv").remove();//修改隐藏绑定项目
				var ad = new Array();
				var cityId;
				$.ajax({
	    			type:"POST",
					data:{id:projectId},
					url:getRootPath()+"/web/project/findProject",
					dataType:"json",
					success:function(data){
						//console.log(data);
						//console.log(data.data[0].cityId);
						$("#projectName").val(data.data[0].projectName);
						$("#projectAddress").val(data.data[0].projectAddress);
						$("#pumpTemperatureMax").val(data.data[0].pumpTemperatureMax);
						$("#estate option").each(function(){
							if($(this).text()==data.data[0].estateName){
								$(this).attr("selected","selected");
							}
						});
						for(var i in cityList){
							if(cityList[i].id == data.data[0].cityId){
								cityId = data.data[0].cityId;
								ad = cityList[i].mergerName.split(",");
							}
							
						}
						for(var i in cityList){
							//选中省
							if(ad[1] == cityList[i].name){
								$("#province option").each(function(){
								    if($(this).text() == cityList[i].name){
								    	$(this).attr("selected","selected");
								    }
								});
								continue;
							}
							//选中市
							if(ad[2] == cityList[i].name){
								
								addCity($("#province").val());
								 
								$("#city option").each(function(){
								    if($(this).text() == cityList[i].name){
								    	$(this).attr("selected","selected");
								    }
								});
								continue;
							} 
							//选中区
							if(ad[3] == cityList[i].name){
								
								addArea($("#city").val());
								
								$("#area option").each(function(){
								    if($(this).text() == cityList[i].name){
								    	$(this).attr("selected","selected");
								    }
								});
								continue;
							}
						}
					}
	    		});
			}
		}
		
		function submit(){
			if(projectId != null&&projectId != 0){
				if($("#projectName").val()==null||$("#projectName").val()==""){
					Notify("请填写项目名字",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#province").val()==0||$("#city").val()==0||$("#area").val()==0){
					Notify("请选择地址",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#projectAddress").val()==null||$("#projectAddress").val()==""){
					Notify("请填写项目详细地址",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#estate").val()==0||$("#estate").val()==""){
					Notify("请选择所属物业",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#pumpTemperatureMax").val()==null||$("#pumpTemperatureMax").val()==""){
					Notify("请输入泵站最高温度",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else{
					if($("#pumpTemperatureMax").val()!=null&&$("#pumpTemperatureMax").val()!=""){
						var re = /^\d+(?=\.{0,1}\d+$|$)/
			            if (!re.test($("#pumpTemperatureMax").val())||parseInt($("#pumpTemperatureMax").val())>100) {
			            	Notify("泵站最高温度(0~100)之间",'top-right','5000','danger','fa-desktop',true);
			                $("#pumpTemperatureMax").val("");
			                $("#pumpTemperatureMax").focus();
			            }else{
			            	$.ajax({
								type:"POST",
								data:$("#addproject").serialize(),
								url:getRootPath()+"/web/project/addOrUpdProject",
								dataType:"json",
								success:function(data){
									console.log(data);
									if(data.code==200){
										window.location.href=getRootPath()+"/admin/project/list.do";
										Notify("操作成功",'top-right','5000','info','fa-desktop',true);
										return;
									}else{
										Notify("操作失败",'top-right','5000','danger','fa-desktop',true);
										return;
									}
								}
							});
			            }
					}
				}
			}else{
				
				if($("#projectName").val()==null||$("#projectName").val()==""){
					Notify("请填写项目名字",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#province").val()==0||$("#city").val()==0||$("#area").val()==0){
					Notify("请选择地址",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#projectAddress").val()==null||$("#projectAddress").val()==""){
					Notify("请填写项目详细地址",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#estate").val()==0||$("#estate").val()==""){
					Notify("请选择所属物业",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#bindProject").val()==0||$("#bindProject").val()==""){
					Notify("请选择绑定项目",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else if($("#pumpTemperatureMax").val()==null||$("#pumpTemperatureMax").val()==""){
					Notify("请输入泵站最高温度",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				else{
					if($("#pumpTemperatureMax").val()!=null&&$("#pumpTemperatureMax").val()!=""){
						var re = /^\d+(?=\.{0,1}\d+$|$)/
			            if (!re.test($("#pumpTemperatureMax").val())||parseInt($("#pumpTemperatureMax").val())>100) {
			            	Notify("泵站最高温度(0~100)之间",'top-right','5000','danger','fa-desktop',true);
			                $("#pumpTemperatureMax").val("");
			                $("#pumpTemperatureMax").focus();
			            }else{
			            	var str='<div id="myModal">'+
							  	'<div class="form-group">'+
							        '<label></label>'+
							        '<span class="input-icon icon-right">'+
							        	'<div class="input-group">'+
							        		'<span id="name" style="color:red;font-weight: bold;">注意：项目一但绑定了就不能解绑！</span>'+
							            '</div><br/>'+
							            '<div class="input-group">'+
							        		'<span id="name" >绑定项目为：'+$("#bindProject option:selected").text()+'</span>'+
							            '</div>'+
							        '</span>'+
							   '</div>'+
							'</div>';
				   			bootbox.dialog({
							    message: str,
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
												data:$("#addproject").serialize(),
												url:getRootPath()+"/web/project/addOrUpdProject",
												dataType:"json",
												success:function(data){
													console.log(data);
													if(data.code==200){
														$.ajax({
															type:"POST",
															data:{id:$("#bindProject").val(),status:1},
															url:getRootPath()+"/web/project/updCSProject",
															dataType:"json",
															success:function(data){
																if(data.code==200){
																	window.location.href=getRootPath()+"/admin/project/list.do";
																}else{
																	
																}
															}
														});
													}else{
														
													}
												}
											});
							            }
							        }
							    }
							});
			            }
					}
				}
			}
		}
  	</script>
  </body>
</html>
