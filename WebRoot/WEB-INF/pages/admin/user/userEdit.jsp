<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<%@include file="../resource_css.jsp" %>
	
	<style>
		.error-hint{
			margin-left: 40px;
			color: red;
			position: absolute;
			font-size: 12px;
			margin-top: 2px;
		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">编辑用户</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	用户
	        <small>
	            User
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">编辑用户&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="editUser" id="editUser">
                    
                    	<div class="form-group">
                        	<label>是否修改角色</label>
                        	<span class="input-icon icon-right" id="isUpdate">
                        		<input type="radio" checked="checked" name="isUpdate" value="0" id="noUpdate"/>否
                        		<input type="radio" name="isUpdate" value="1" id="yesUpdate"/>是
                        	</span>
                        </div>
                    
                   	 	<!-- 角色下拉框 -->
                        <div class="form-group" id="roleDiv" style="display: none;">
                            <label>角色</label>
                            <span class="input-icon icon-right">
                            	<select name="role" class="form-control" id="role"></select>
                            </span>
                        </div>
                        
                        <!-- 片区下拉框 -->
                        <div class="form-group" id="companyDiv" style="display: none;">
                            <label>选择片区</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="companyId"></select>
                            </span>
                        </div>
                        
                        <!-- 办事处下拉框 -->
                        <div class="form-group" id="officeDiv" style="display: none;">
                            <label>选择办事处</label>
                            <span class="input-icon icon-right">
                            	<select name="officeId" class="form-control" id="officeId"></select>
                            </span>
                        </div>
                        
                        <!-- 水厂下拉框 -->
                        <div class="form-group" id="waterWorkDiv" style="display: none;">
                            <label>选择水厂</label>
                            <span class="input-icon icon-right">
                            	<select name="waterWorkId" class="form-control" id="waterWorkId"></select>
                            </span>
                        </div>
                        
                        <!-- 物业下拉框 -->
                        <div class="form-group" id="estateDiv" style="display: none;">
                            <label>选择物业</label>
                            <span class="input-icon icon-right">
                            	<select name="estateId" class="form-control" id="estateId"></select>
                            </span>
                        </div>
                        
                        <div class="form-group" id="realNameDiv">
                            <label>真实姓名</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="realName" name="realName" type="text" required>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group" id="jobTitleDiv">
                            <label>担任职位</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="jobTitle" name="jobTitle" type="text">
                                </div>
                            </span>
                        </div>
                        
                        <!-- <div class="form-group" id="jobNumberDiv">
                            <label>工号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="jobNumber" type="text" name="jobNumber">
                                </div>
                            </span>
                        </div> -->
                        
                        <div class="form-group" id="qqDiv">
                            <label>QQ</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="qq" name="qq" type="text" >
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group" id="emailDiv">
                            <label>邮箱</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="email" name="email" type="text">
                                </div>
                            </span>
                        </div>
                        
                        <input type="hidden" id="userId" name="id">
                        <input type="hidden" id="roleId" name="roleId">
                        <input type="hidden" id="unitId" name="companyId">
                        <a href="javascript:void(0)" target="iframe" class="btn btn-info" onclick="submit()">提交修改</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  		
  		//获取参数
		var id = getUrlParam("id");
  		$("#userId").val(id);
  		var roleNum;
  		$(function(){
			//加载用户信息
  			$.ajax({
				type:"POST",
				data:{id:id},
				dataType:"json",
				async: false,
				url:getRootPath()+"/web/user/findUser",
				success:function(data){
					console.log(data.data);
					
					$("#userName").val(data.data[0].userName);
					
					$("#realName").val(data.data[0].realName);
					
					$("#mobile").val(data.data[0].mobile);
					
					$("#jobTitle").val(data.data[0].jobTitle);
					
					/* $("#jobNumber").val(data.data[0].jobNumber); */
					
					$("#email").val(data.data[0].email);
					
					$("#qq").val(data.data[0].qq);
					
					$("#roleId").val(data.data[0].roleId);
					roleNum = data.data[0].roleId;
					//findAll(roleNum);
					if(data.data[0].roleId==1){
						$("#role").html("<option value='1'>平台管理员</option>");
					}else{
						//加载角色
			    		$.ajax({
							type:"GET",
							data:{},
							url:getRootPath()+"/web/role/findRole",
							dataType:"json",
							async: false,
							success:function(data){
								console.log(data);
								var str = "";
								for (var i = 0; i < data.data.length; i++) {
									if(data.data[i].id==roleNum){
										str += "<option selected='selected' value='"+data.data[i].id+"'>"+data.data[i].role+"</option>";
									}else{
										str += "<option value='"+data.data[i].id+"'>"+data.data[i].role+"</option>";
									}
								}
								$("#role").html(str);
							}
						});
					}
				}
			});
			
			$("#noUpdate").click(function(){
				$("#roleDiv").hide("slow");
			});
			
			$("#yesUpdate").click(function(){
				$("#roleDiv").show("slow");
			});
			
			$("#role").bind("change",function(){
				var roleId = $("#role").val();
				findAll(roleId);
			});
  		});
  		
  		function findAll(roleId){
  			$("#roleId").val(roleId);
			if(roleId == 2 || roleId == 3 || roleId == 4){
				//片区
				//下拉框
				$("#companyDiv").show("slow");
				$("#officeDiv").hide("slow");
				$("#waterWorkDiv").hide("slow");
				$("#estateDiv").hide("slow");
				
				//每次进来清空其它下拉框
				$("#officeId").html("");
				$("#waterWorkId").html("");
				$("#estateId").html("");
				
				findCompany();
				
				$("#companyId").bind("change",function(){
					$("#unitId").val($("#companyId").val());
				});
	  			
			}else if(roleId == 5){
				//办事处
				//下拉框
				$("#officeDiv").show("slow");
				$("#companyDiv").show("slow");
				$("#waterWorkDiv").hide("slow");
				$("#estateDiv").hide("slow");
				//每次进来清空其它下拉框
				$("#waterWorkId").html("");
				$("#estateId").html("");
				findCompany();
				$("#companyId option").eq(0).attr("selected","selected");
				
				$("#officeId").html("<option value='0'>请先选择片区</option>");
				companyChange();
				
				//选择办事处
				$("#officeId").bind("change",function(){
					$("#unitId").val($("#officeId").val());
				});
				
			}else if(roleId == 6){
				//水厂
				//下拉框
				$("#waterWorkDiv").show("slow");
				$("#companyDiv").show("slow");
				$("#officeDiv").show("slow");
				$("#estateDiv").hide("slow");
				//每次进来清空其它下拉框
				$("#officeId option").eq(0).attr("selected","selected");
				$("#estateId").html("");
				//优先加载片区
				findCompany();
				$("#companyId option").eq(0).attr("selected","selected");
				
				//初始化把办事处设为暂无
				$("#officeId").html("<option value='0'>请先选择片区</option>");
				//水厂
				$("#waterWorkId").html("<option value='0'>请先选择办事处</option>");
				
				//选择片区
				companyChange();
				
				//选择办事处
				$("#officeId").bind("change",function(){
					if($("#waterWorkDiv").css("display")=="block"||$("#estateDiv").css("display")=="block"){
						$("#unitId").val($("#officeId").val());
						if($("#officeId").val()!=0){
							findWaterWork($("#officeId").val());
						}else{
							$("#waterWorkId").html("<option value='0'>请先选择办事处</option>");
							$("#estateId").html("<option value='0'>请先选择水厂</option>");
						}
					}
				});
				
				//选择水厂：存入水厂
				$("#waterWorkId").bind("change",function(){
					$("#unitId").val($("#waterWorkId").val());
				});
				
			}
			else if(roleId == 7){
				//物业
				//下拉框
				$("#estateDiv").show("slow");
				$("#companyDiv").show("slow");
				$("#officeDiv").show("slow");
				$("#waterWorkDiv").show("slow");
				
				//每次进来清空其它下拉框
				$("#officeId option").eq(0).attr("selected","selected");
				$("#waterWorkId option").eq(0).attr("selected","selected");
				//优先加载片区
				findCompany();
				$("#companyId option").eq(0).attr("selected","selected");
				
				//还未选择的时候初始化把办事处设为暂无
				$("#officeId").html("<option value='0'>请先选择片区</option>");
				//水厂
				$("#waterWorkId").html("<option value='0'>请先选择办事处</option>");
				//物业
				$("#estateId").html("<option value='0'>请先选择水厂</option>");
				
				//选择片区
				companyChange();
				
				//选择办事处
				$("#officeId").bind("change",function(){
					if($("#waterWorkDiv").css("display")=="block"||$("#estateDiv").css("display")=="block"){
						$("#unitId").val($("#officeId").val());
						if($("#officeId").val()!=0){
							findWaterWork($("#officeId").val());
						}else{
							$("#waterWorkId").html("<option value='0'>请先选择办事处</option>");
							$("#estateId").html("<option value='0'>请先选择水厂</option>");
						}
					}
				});
				
				//选择水厂
				$("#waterWorkId").bind("change",function(){
					if($("#estateDiv").css("display")=="block"){
						$("#unitId").val($("#waterWorkId").val());
						if($("#waterWorkId").val()!=0){
							findEstate($("#waterWorkId").val());
						}else{
							$("#estateId").html("<option value='0'>请先选择水厂</option>");
						}
					}
				});
				
				//选择物业
				$("#estateId").bind("change",function(){
					$("#unitId").val($("#estateId").val());
				});
				
			}
  		}
  		
  		//选择片区的时候
  		function companyChange(){
  			$("#companyId").change(function(){
  				if($("#officeDiv").css("display")=="block"||$("#waterWorkDiv").css("display")=="block"||$("#estateDiv").css("display")=="block"){
  					$("#unitId").val($("#companyId").val());
  					if($("#companyId").val()!=0){ 
  						findOffice($("#companyId").val());
  					}else{
  						$("#officeId").html("<option value='0'>请先选择片区</option>");
  						$("#waterWorkId").html("<option value='0'>请先选择办事处</option>");
  						$("#estateId").html("<option value='0'>请先选择水厂</option>");
  					}
				}
			});
  		}
  		
  		//查询片区方法
  		function findCompany(){
  			$.ajax({
				type:"GET",
				data:{},
				url:getRootPath()+"/web/company/findCompany",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.data.length==0){
						$("#companyId").html("<option value='0'>暂无</option>");
					}else{
						var str = "<option value='0'>请选择</option>";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].companyName+"</option>";
						}
						$("#companyId").html(str);
					}
				}
			});
  		}
  		
  		//查询办事处方法
  		function findOffice(companyId){
  			$.ajax({
				type:"GET",
				data:{companyId:companyId},
				url:getRootPath()+"/web/office/findOffice",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.data.length==0){
						$("#officeId").html("<option value='0'>暂无</option>");
					}else{
						var str = "<option value='0'>请选择</option>";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].officeName+"</option>";
						}
						$("#officeId").html(str);
					}
				}
			});
  		}
  		
  		//查询水厂方法
  		function findWaterWork(officeId){
  			$.ajax({
				type:"GET",
				data:{officeId:officeId},
				url:getRootPath()+"/web/water/findWaterWork",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.data.length==0){
						$("#waterWorkId").html("<option value='0'>暂无</option>");
					}else{
						var str = "<option value='0'>请选择</option>";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].waterworkName+"</option>";
						}
						$("#waterWorkId").html(str);
					}
				}
			});
  		}
  		
  		//查询物业方法
  		function findEstate(waterWorkId){
  			$.ajax({
				type:"GET",
				data:{waterWorkId:waterWorkId},
				url:getRootPath()+"/web/estate/findEstate",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.data.length==0){
						$("#estateId").html("<option value='0'>暂无</option>");
					}else{
						var str = "<option value='0'>请选择</option>";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].estateName+"</option>";
						}
						$("#estateId").html(str);
					}
				}
			});
  		}
  		
  		//提交
  		function submit(){
  			if($("#companyId").val()==0||$("#companyId").val()==""){
  				Notify("请选择所属片区",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
  			else if($("#officeId").val()==0||$("#officeId").val()==""){
  				Notify("请选择所属办事处",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
  			else if($("#waterWorkId").val()==0||$("#waterWorkId").val()==""){
  				Notify("请选择所属水厂",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
  			else if($("#estateId").val()==0||$("#estateId").val()==""){
  				Notify("请选择所属物业",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
			else if($("#realName").val()==null||$("#realName").val()==""){
				Notify("请填写真实姓名",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#role").val()==0||$("#role").val()==""){
				Notify("请选择角色",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#jobTitle").val()==null||$("#jobTitle").val()==""){
				Notify("请填写担任职位",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			/* else if($("#jobNumber").val()==null||$("#jobNumber").val()==""){
				Notify("请填写工号",'top-right','5000','danger','fa-desktop',true);
				return;
			} */
			else{
				$.ajax({
		            url:getRootPath()+"/web/user/addOrUpdUser",
		            data:$("#editUser").serialize(),
		            type:'post',
		            dataType:'json',
		            success:function(data){
		            	console.log(data);
		            	if(data.code==200){
							//var id = data.data.id;
							window.location.href=getRootPath()+"/admin/user/list.do";
						}else{
							//alert(data.error);
							Notify(data.msg,'top-right','5000','danger','fa-desktop',true);
						}
		            }
		        });	
			}
  		}
  	</script>
  </body>
</html>
