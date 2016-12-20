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
            <li class="active">添加水厂</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	水厂
	        <small>
	            Water
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加水厂&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addWater" id="addWater">
                    
                        <div class="form-group sel" id="waterNameDiv">
                            <label>水厂名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="waterworkName" id="waterName" type="text" placeholder="请输入水厂名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="waterNumberDiv">
                            <label>水厂编号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="number" id="waterNumber" type="text" placeholder="请输入水厂编号" onblur="checkExistNumber()"  onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="waterAddressDiv">
                            <label>水厂地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="waterworkAddress" id="waterAddress" type="text" placeholder="请输入水厂地址">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="officeDiv">
                            <label>选择所属办事处</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="office" name="officeId">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="waterAddressDiv">
                            <label>水厂官网链接地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input id="url" class="form-control" name="waterUrl" type="text" placeholder="可选(不填则不跳转)  格式: http://www.wogia.com">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="addressDescribeDiv">
                            <label>水厂描述地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input id="addressDescribe" class="form-control" name="addressDescribe" type="text" placeholder="请输入水厂描述地址">
                                </div>
                            </span>
                        </div>
                         
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
		$(function(){
			//默认片区
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/office/findOffice",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].officeName+"</option>";
					}
					$("#office").append(str);
				}
			});
		});
		
		//验证工号
		var boolNumber = false;
  		function checkExistNumber(){
  			if($("#waterNumber").val()!=""){
  				$.ajax({
					type:"POST",
					data:{number:$("#waterNumber").val()},
					url:getRootPath()+"/web/water/findWaterWork",
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
		
		function submit(){
			
			//整数正则
			var reg=/^[-+]?\d*$/;
			
			if($("#waterName").val()==null||$("#waterName").val()==""){
				Notify("请填写水厂名字",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#waterNumber").val()==null||$("#waterNumber").val()==""){
				Notify("请填写水厂编号",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#waterNumber").val().trim())){
				Notify("对不起，编号必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#waterNumber").val().trim().length>10){
				Notify("对不起，编号长度在18以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if(boolNumber){
  				Notify("该编号已存在了",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
			else if($("#waterAddress").val()==null||$("#waterAddress").val()==""){
				Notify("请填写地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#office").val()==0||$("#office").val()==""){
				Notify("请选择办事处",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#addressDescribe").val()==null||$("#addressDescribe").val()==""){
				Notify("请填写描述地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:$("#addWater").serialize(),
					url:getRootPath()+"/web/water/addOrUpdWaterWork",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/water/list.do";
						}else{
							
						}
					}
				});
			}
		}
		
  	</script>
  </body>
</html>
