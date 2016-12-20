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
		.unit{
			position: relative;
			top:5px;
			padding-left: 10px;
		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">录入专享服务</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	专享服务
	        <small>
	            ExclusiveService
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">录入专享服务&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addOffice" id="addProjectApplyDetails">
                    
                        <div class="form-group">
                            <label>改造前功率</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width: 200px;" class="form-control spinner-input" name="frontPower" id="frontPower" type="text" placeholder="请输入改造前功率" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span class="unit">KW</span>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>改造后功率</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width: 200px;" class="form-control spinner-input" name="afterPower" id="afterPower" type="text" placeholder="请输入改造后功率" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span class="unit">KW</span>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>改造前每月电费</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width: 300px;" class="form-control spinner-input" name="frontMoney" id="frontMoney" type="text" placeholder="请输入改造前每月电费" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span class="unit">元</span>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>改造后每月电费</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width: 300px;" class="form-control spinner-input" name="afterMoney" id="afterMoney" type="text" placeholder="请输入改造后每月电费" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span class="unit">元</span>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>季度维保费用</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input style="width: 300px;" class="form-control spinner-input" name="maintenanceMoney" id="maintenanceMoney" type="text" placeholder="请输入季度维保费用" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
                            		<span class="unit">元</span>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>下次缴费时间</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" readonly="readonly" name="nextPayTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="nextPayTime" placeholder="下次缴费时间"/>
                                </div>
                            </span>
                        </div>
                         
                         <input type="hidden" name="applyId" id="applyId">
                         <input type="hidden" name="id" id="detailsId">
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  		var applyId = getUrlParam("applyId");
  		var detailsId = getUrlParam("detailsId");
  		var state = getUrlParam("state");
  		$("#applyId").val(applyId);
		$(function(){
			if(state==1){
				$("#detailsId").val(detailsId);
				$.ajax({
	    			type:"POST",
					data:{id:detailsId},
					url:getRootPath()+"/web/pad/findProjectApplyDetailsById",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							$("#frontPower").val(data.data.frontPower);
							$("#afterPower").val(data.data.afterPower);
							$("#frontMoney").val(data.data.frontMoney);
							$("#afterMoney").val(data.data.afterMoney);
							$("#maintenanceMoney").val(data.data.maintenanceMoney);
							$("#nextPayTime").val(data.data.nextPayTime);
						}
					}
	    		});
			}
		});
		
		function submit(){
			var reg=/^[-\+]?\d+(\.\d+)?$/;
			
			if($("#frontPower").val()==null||$("#frontPower").val()==""){
				Notify("请输入改造前功率",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#frontPower").val().trim())){    
				Notify("对不起，请输入正确的功率!",'top-right','5000','danger','fa-desktop',true);
				return;
	        }
			else if($("#afterPower").val()==null||$("#afterPower").val()==""){
				Notify("请输入改造后功率",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#afterPower").val().trim())){    
	            Notify("对不起，请输入正确的功率!",'top-right','5000','danger','fa-desktop',true);
				return;
	        }
			else if($("#frontMoney").val()==null||$("#frontMoney").val()==""){
				Notify("请输入改造前每月电费",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#frontMoney").val().trim())){    
	            Notify("对不起，请输入正确的电费!",'top-right','5000','danger','fa-desktop',true);
				return;
	        }
			else if($("#afterMoney").val()==null||$("#afterMoney").val()==""){
				Notify("请输入改造后每月电费",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#afterMoney").val().trim())){    
	            Notify("对不起，请输入正确的电费!",'top-right','5000','danger','fa-desktop',true);
				return;
	        }
			else if($("#maintenanceMoney").val()==null||$("#maintenanceMoney").val()==""){
				Notify("请输入季度维保费用",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!reg.test($("#maintenanceMoney").val().trim())){    
	            Notify("对不起，请输入正确的季度维保费用!",'top-right','5000','danger','fa-desktop',true);
				return;
	        }
			else if($("#nextPayTime").val()==null||$("#nextPayTime").val()==""){
				Notify("请选择下次缴费时间",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type:"POST",
					data:$("#addProjectApplyDetails").serialize(),
					url:getRootPath()+"/web/pad/addOrUpdProjectApplyDetails",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/exclusiveService/list.do?applyId="+applyId;
						}else{
							
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
