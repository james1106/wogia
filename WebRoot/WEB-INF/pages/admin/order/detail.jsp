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
    		.info{
    			padding: 10px;
    			border: 1px solid #999;
    			color: #666 !important;
    		}
    	</style>
  </head>
  
  <body>
  
  		<div class="container">
            <div class="row">
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">订单号：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="orderNumber"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">订单描述：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="orderDescribe"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">订单进度：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="orderData"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">创建时间：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="createTime"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">创建人：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="userName"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">创建人电话：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="userPhone"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">是否有效：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="isValid"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">技术人的名字：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="techUserName"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">技术人的电话：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="techUserMobile"></div>
	            	</div>
            	</div>
            	<div class="col-xs-12 padding">
	            	<div class="col-xs-4" align="right">
	            		<span class="title">供货商：</span>
	            	</div>
	            	<div class="col-xs-8" align="left">
	            		<div class="info" id="supplier"></div>
	            	</div>
            	</div>
            </div>
  		</div>
  		<%@include file="../resource_js.jsp" %>
  		<script type="text/javascript">
  			
  			$(function(){
  				
  				//获取参数
  				var id = getUrlParam("id");
  				var nullDate = "暂无";
				$.ajax({
					type:"POST",
					data:{id:id},
					dataType:"json",
					url:getRootPath()+"/web/order/findOrderById",
					success:function(data){
						console.log(data.data);
						
						if(data.data.orderNumber==null || data.data.orderNumber== ""){
							$("#orderNumber").html(nullDate);
						}else{
							$("#orderNumber").html(data.data.orderNumber);
						}
						if(data.data.orderDescribe==null || data.data.orderDescribe== ""){
							$("#orderDescribe").html(nullDate);
						}else{
							$("#orderDescribe").html(data.data.orderDescribe);
						}
						
						
						if(data.data.jsOrderData==null || data.data.jsOrderData==""){
							$("#orderData").html(nullDate);
						}else{
							$("#orderData").html(data.data.jsOrderData[data.data.jsOrderData.length-1].content);
						}
						if(data.data.createTime==null || data.data.createTime== ""){
							$("#createTime").html(nullDate);
						}else{
							$("#createTime").html(timeStampConversion(data.data.createTime));
						}
						
						if(data.data.userBean.realName==null || data.data.userBean.realName==""){
							$("#userName").html(nullDate);
						}else{
							$("#userName").html(data.data.userBean.realName);
						}
						if(data.data.userBean.mobile==null || data.data.userBean.mobile==""){
							$("#userPhone").html(nullDate);
						}else{
							$("#userPhone").html(data.data.userBean.mobile);
						}
						
						if(data.data.isValid==0){
							$("#isValid").html("有效");
						}else{
							$("#isValid").html("无效");
						}
						if(data.data.tech.realName==null || data.data.tech.realName == ""){
							$("#techUserName").html(nullDate);
						}else{
							$("#techUserName").html(data.data.tech.realName);
						}
						if(data.data.tech.mobile==null || data.data.tech.mobile==""){
							$("#techUserMobile").html(nullDate);
						}else{
							$("#techUserMobile").html(data.data.tech.mobile);
						}
						if(data.data.serviceId==null || data.data.serviceId==""){
							$("#serviceId").html(nullDate);
						}else{
							$("#serviceId").html(data.data.serviceId);
						}
						if(data.data.orderType==null || data.data.orderType==""){
							$("#orderType").html(nullDate);
						}
						else if(data.data.orderType==0){
							$("#orderType").html("急修呼叫");
						}
						else if(data.data.orderType==1){
							$("#orderType").html("保养项目");
						}
					
						if(data.data.supplier==null || data.data.supplier==""){
							$("#supplier").html(nullDate);
						}else{
							$("#supplier").html(data.data.supplier);
						}
						
						
						
						
					}
				});
				
  			});
  			
  		</script>
  </body>
</html>
