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
            <li class="active">添加片区</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	片区
	        <small>
	            Company
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加片区&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addCompany" id="addCompany">
                    	
                    	<div class="form-group sel" id="companyNameDiv">
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
                    
                        <div class="form-group sel" id="companyNameDiv">
                            <label>片区名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="companyName" id="companyName" type="text" placeholder="请输入片区名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="companyNumberDiv">
                            <label>片区编号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="number" id="companyNumber" type="text" placeholder="请输入片区编号" onblur="checkExistNumber()"  onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="companyAddressDiv">
                            <label>片区详细地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="companyAddress" id="companyAddress" type="text" placeholder="请输入片区详细地址">
                                </div>
                            </span>
                        </div>
                         
                        <input type="hidden" id="companyId" name="id">
                        
                        <input type="hidden" id="areaId">
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submitCompany()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  		
  		//获取参数
		var companyId = getUrlParam("companyId");
  		$("#companyId").val(companyId);
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
					console.log(data.data);
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
			if(companyId != null&&companyId != 0){
				$("#companyNumber").attr("disabled","disabled");
				var ad = new Array();
				var cityId;
				$.ajax({
	    			type:"POST",
					data:{id:companyId},
					url:getRootPath()+"/web/company/findCompany",
					dataType:"json",
					success:function(data){
						console.log(data);
						console.log(data.data[0].cityId);
						$("#companyName").val(data.data[0].companyName); 
						$("#companyNumber").val(data.data[0].number);
						$("#companyAddress").val(data.data[0].companyAddress);
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
		
		//验证工号
		var boolNumber = false;
  		function checkExistNumber(){
  			if($("#companyNumber").val()!=""){
  				$.ajax({
					type:"POST",
					data:{number:$("#companyNumber").val()},
					url:getRootPath()+"/web/company/findCompany",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.data.length>0){
							boolNumber = true;
							Notify("该编号已存在了",'top-right','5000','danger','fa-desktop',true);
							return;
						}else{
							boolNumber = false;
						}
					}
				})
			}
  		}
		
		function submitCompany(){
			
			//整数正则
			var reg=/^[-+]?\d*$/;
			
			if($("#province").val()==0||$("#city").val()==0||$("#area").val()==0){
				Notify("请选择地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#companyName").val()==null||$("#companyName").val()==""){
				Notify("请填写片区名字",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#companyNumber").val()==null||$("#companyNumber").val()==""){
				Notify("请填写片区编号",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#companyNumber").val().trim())){
				Notify("对不起，编号必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#companyNumber").val().trim().length>10){
				Notify("对不起，编号长度在18以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if(boolNumber){
  				Notify("该编号已存在了",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
			else if($("#companyAddress").val()==null||$("#companyAddress").val()==""){
				Notify("请填写片区详细地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}else{
				/* {id:id,companyName:$("#companyName").val(),cityId:$("#area").val(),number:$("#companyNumber").val(),companyAddress:$("#companyAddress").val()} */
				$.ajax({
					type:"POST",
					data:$("#addCompany").serialize(),
					url:getRootPath()+"/web/company/addOrUpdCompany",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/company/list.do";
						}else{
							
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
