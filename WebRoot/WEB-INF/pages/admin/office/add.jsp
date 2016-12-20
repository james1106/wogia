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
            <li class="active">添加办事处</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	办事处
	        <small>
	            Office
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加办事处&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addOffice" id="addOffice">
                    
                        <div class="form-group sel" id="officeNameDiv">
                            <label>办事处名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="officeName" id="officeName" type="text" placeholder="请输入办事处名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="officeNumberDiv">
                            <label>办事处编号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="number" id="officeNumber" type="text" placeholder="请输入办事处编号" onblur="checkExistNumber()"  onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="officeAddressDiv">
                            <label>办事处地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="officeAddress" id="officeAddress" type="text" placeholder="请输入办事处地址">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="companyDiv">
                            <label>选择所属片区</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="company" name="companyId">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
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
				url:getRootPath()+"/web/company/findCompany",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].companyName+"</option>";
					}
					$("#company").append(str);
				}
			});
			
			$("#company").bind("change",function(){
				$("#companyId").val($("#company").val());
			});
			
		});
		
		//验证工号
		var boolNumber = false;
  		function checkExistNumber(){
  			if($("#officeNumber").val()!=""){
  				$.ajax({
					type:"POST",
					data:{number:$("#officeNumber").val()},
					url:getRootPath()+"/web/office/findOffice",
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
			
			if($("#officeName").val()==null||$("#officeName").val()==""){
				Notify("请填写办事处名字",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#officeNumber").val()==null||$("#officeNumber").val()==""){
				Notify("请填写办事处编号",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#officeNumber").val().trim())){
				Notify("对不起，编号必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#officeNumber").val().trim().length>10){
				Notify("对不起，编号长度在18以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if(boolNumber){
  				Notify("该编号已存在了",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
			else if($("#officeAddress").val()==null||$("#officeAddress").val()==""){
				Notify("请填写地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#company").val()==0||$("#company").val()==""){
				Notify("请选择片区",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:$("#addOffice").serialize(),
					url:getRootPath()+"/web/office/addOrUpdOffice",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/office/list.do";
						}else{
							
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
