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
            <li class="active">添加物业</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	物业
	        <small>
	            Estate
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加物业&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addEstate" id="addEstate">
                    
                        <div class="form-group sel" id="estateNameDiv">
                            <label>物业名字</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="estateName" id="estateName" type="text" placeholder="请输入物业名字">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="estateNumberDiv">
                            <label>物业编号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="number" id="estateNumber" type="text" placeholder="请输入物业编号" onblur="checkExistNumber()"  onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="estateAddressDiv">
                            <label>物业地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="estateAddress" id="estateAddress" type="text" placeholder="请输入物业地址">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="companyDiv">
                            <label>选择所属水厂</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="water" name="waterWorkId">
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
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/water/findWaterWork",
				dataType:"json",
				success:function(data){
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].waterworkName+"</option>";
					}
					$("#water").append(str);
				}
			});
			
		});
		
		//验证工号
		var boolNumber = false;
  		function checkExistNumber(){
  			if($("#estateNumber").val()!=""){
  				$.ajax({
					type:"POST",
					data:{number:$("#estateNumber").val()},
					url:getRootPath()+"/web/estate/findEstate",
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
			
			if($("#estateName").val()==null||$("#estateName").val()==""){
				Notify("请填写物业名字",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#estateNumber").val()==null||$("#estateNumber").val()==""){
				Notify("请填写物业编号",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#estateNumber").val().trim())){
				Notify("对不起，编号必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#estateNumber").val().trim().length>10){
				Notify("对不起，编号长度在18以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if(boolNumber){
  				Notify("该编号已存在了",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
			else if($("#estateAddress").val()==null||$("#estateAddress").val()==""){
				Notify("请填写地址",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#water").val()==0||$("#water").val()==""){
				Notify("请选择水厂",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:$("#addEstate").serialize(),
					url:getRootPath()+"/web/estate/addOrUpdEstate",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/estate/list.do";
						}else{
							
						}
					}
				});
			}
		}
		
  	</script>
  </body>
</html>
