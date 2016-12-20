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
            <li class="active">配置联系电话</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	联系电话
	        <small>
	            ContactUs
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">联系电话&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
                        <div class="form-group">
                            <label>联系电话</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="phone" id="phone" type="text" placeholder="请输入联系电话" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                </div>
                            </span>
                        </div>
                        <a href="javascript:void(0);" target="iframe" id="submit" class="btn btn-info" onclick="submit()">提交</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  	<%@include file="../resource_js.jsp" %>
  	<script>
	  	
		$(function(){
			$.ajax({
				type : "POST",
				url : getRootPath()+"/res/readContactUs",
				data : {},
				dataType :"json",
				success : function(data){
					console.log(data);
					if(data.code == 200){
						$("#phone").val(data.data);
					}else{
						Notify(data.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
					}
				}
			});
		});
		
		function submit(){
			//整数正则
			var reg=/^[-+]?\d*$/;
			if($("#phone").val()==null||$("#phone").val()==""){
				Notify("请输入联系电话",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#phone").val().trim())){
				Notify("对不起，请输入正确的联系方式!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#phone").val().trim().length>11){
				Notify("对不起，联系方式长度在11以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else{
				$.ajax({
					type : "POST",
					url : getRootPath()+"/res/saveContactUs",
					data : {phone:$("#phone").val()},
					dataType :"json",
					success : function(data){
						if(data.code == 200){
							Notify(data.msg, 'top-right', '5000', 'info', 'fa-desktop', true);
						}else{
							Notify(data.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
