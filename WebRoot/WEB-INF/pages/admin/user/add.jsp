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
		.sel{
			display: none;
		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加用户</li>
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
                <span class="widget-caption">添加用户&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addUser" id="addUser">
                        <!-- 角色下拉框 -->
                        <div class="form-group" id="roleDiv">
                            <label>角色</label>
                            <span class="input-icon icon-right">
                            	<select name="role" class="form-control" id="role">
                            		<option value="0">请选择</option>
                                </select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="mobileDiv">
                            <label>手机号码&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="mobile" id="mobile" type="text" onblur="checkExistMobile()" placeholder="请输入手机号码">
                                </div>
                            </span>
                        </div>
                        
                       	<div class="form-group sel" id="nameDiv">
                            <label>用户名</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="userName" name="userName" type="text" onblur="checkExistName()" placeholder="请输入用户名">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel" id="realNameDiv">
                            <label>真实姓名</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="realName" name="realName" type="text"placeholder="请输入真实姓名">
                                </div>
                            </span>
                        </div>
                        
                        <!-- 片区下拉框 -->
                        <div class="form-group sel" id="companyDiv">
                            <label>选择片区</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="companyId"></select>
                            </span>
                        </div>
                        
                        <!-- 办事处下拉框 -->
                        <div class="form-group sel" id="officeDiv">
                            <label>选择办事处</label>
                            <span class="input-icon icon-right">
                            	<select name="officeId" class="form-control" id="officeId"></select>
                            </span>
                        </div>
                        
                        <!-- 水厂下拉框 -->
                        <div class="form-group sel" id="waterWorkDiv">
                            <label>选择水厂</label>
                            <span class="input-icon icon-right">
                            	<select name="waterWorkId" class="form-control" id="waterWorkId"></select>
                            </span>
                        </div>
                        
                        <!-- 物业下拉框 -->
                        <div class="form-group sel" id="estateDiv">
                            <label>选择物业</label>
                            <span class="input-icon icon-right">
                            	<select name="estateId" class="form-control" id="estateId"></select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="jobTitleDiv">
                            <label>担任职位</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="jobTitle" name="jobTitle" type="text" placeholder="请输入担任职位">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel" id="jobNumberDiv">
                            <label>工号&nbsp;&nbsp;<span style="font-size: 12px">(工号只能输入纯数字)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<!-- <input class="form-control" id="jobNumber" name="jobNumber" type="text" onblur="checkExistNumber()"> -->
                            		<input onblur="checkExistNumber()" class="form-control" id="jobNumber" name="jobNumber" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" placeholder="请输入工号">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="qqDiv">
                            <label>QQ&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="qq" name="qq" type="text" placeholder="请输入QQ号">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="emailDiv">
                            <label>邮箱&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="email" type="email" name="email" placeholder="请输入邮箱" required="required">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel" id="pwdDiv">
                            <label>密码</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="pwd" type="password" disabled="disabled" value="111111">
                                </div>
                            </span>
                        </div>
                         
                        <input type="hidden" id="roleId" name="roleId">
                        <input type="hidden" id="unitId" name="companyId">
                        <input type="hidden" id="password" name="pwd">
                        
                        <a href="javascript:void(0)" target="iframe" class="btn btn-info" onclick="submitUser()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
  	
  		$(function(){
  			//加载角色
    		$.ajax({
				type:"GET",
				data:{},
				url:getRootPath()+"/web/role/findRole",
				dataType:"json",
				success:function(data){
					console.log(data);
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						str += "<option value='"+data.data[i].id+"'>"+data.data[i].role+"</option>";
					}
					$("#role").append(str);
				}
			});
  			
  			//加密初始密码
  			$("#password").val($.md5("111111"));
			
			$("#role").bind("change",function(){
				var roleId = $("#role").val();
				$("#roleId").val(roleId);
				if(roleId != 0){
					if(roleId == 2 || roleId == 3 || roleId == 4){
						//片区管理员
						showMethod();
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
						showMethod();
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
						showMethod();
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
						showMethod();
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
				}else{
					$("#mobileDiv").hide("slow");
					$("#nameDiv").hide("slow");
					$("#realNameDiv").hide("slow");
					$("#jobTitleDiv").hide("slow");
					$("#jobNumberDiv").hide("slow");
					$("#qqDiv").hide("slow");
					$("#emailDiv").hide("slow");
					$("#pwdDiv").hide("slow");
					//下拉框
					$("#estateDiv").hide("slow");
					$("#companyDiv").hide("slow");
					$("#officeDiv").hide("slow");
					$("#waterWorkDiv").hide("slow");
				}
				
			});
  		});
  		
  		//抽取隐藏显示某些字段方法
		function showMethod(){
			$("#mobileDiv").show("slow");
			$("#nameDiv").show("slow");
			$("#realNameDiv").show("slow");
			$("#jobTitleDiv").show("slow");
			$("#jobNumberDiv").show("slow");
			$("#qqDiv").show("slow");
			$("#emailDiv").show("slow");
			$("#pwdDiv").show("slow");
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
  		
  		var boolMobile = false;//作为手机号码
  		var boolName = false;//作为用户名
  		var boolJobNumber = false;//作为工号
  		//验证手机号码是否存在
  		function checkExistMobile(){
  			if($("#mobile").val()!=""){
				//手机号码正则
				var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        		var r = $("#mobile").val().match(reg);
				
				if($("#mobile").val().length<11||$("#mobile").val().length>11||r==null){
					boolMobile = true;
					Notify("手机号码格式有误",'top-right','5000','danger','fa-desktop',true);
					return;
				}else{
					$.ajax({
						type:"POST",
						data:{account:$("#mobile").val()},
						url:getRootPath()+"/web/user/findUser",
						dataType:"json",
						success:function(data){
							console.log(data);
							if(data.data.length>0){
								boolMobile = true;
								Notify("该手机号码已存在了",'top-right','5000','danger','fa-desktop',true);
								return;
							}else{
								boolMobile = false;
							}
						}
					})
				}
			}else{
				boolMobile = false;
			}
  		}
  		
  		//验证用户名
  		function checkExistName(){
  			var reg = /^[a-z0-9]+$/;//只能输入小写字母
  			//console.log(reg.test($("#userName").val()));
  			if($("#userName").val()!=""){
				//用户名正则
				if($("#userName").val().length<4||$("#userName").val().length>11){
					boolName = true;
					Notify("用户名请用(4~11)的数字和英文小写字母",'top-right','5000','danger','fa-desktop',true);
					return;
				}
				if ($("#userName").val().length===0 || reg.test($("#userName").val()))
				{
					if(/^\d+$/.test($("#userName").val())){
						boolName = true;
						Notify("用户名不能为纯数字",'top-right','5000','danger','fa-desktop',true);
						return;
					}else{
						$.ajax({
							type:"POST",
							data:{account:$("#userName").val()},
							url:getRootPath()+"/web/user/findUser",
							dataType:"json",
							success:function(data){
								console.log(data);
								if(data.data.length>0){
									boolName = true;
									Notify("该用户名已存在了",'top-right','5000','danger','fa-desktop',true);
									return;
								}else{
									boolName = false;
								}
							}
						});
					}
				} 
				else{
					boolName = true;
					Notify("请用数字或小写字母,但不能纯数字",'top-right','5000','danger','fa-desktop',true);
					return;
				}
			}
  		}
  		
  		//验证工号
  		function checkExistNumber(){
  			//整数正则
			var reg=/^[-+]?\d*$/;
  			
  			if($("#jobNumber").val()!=""){
  				if(!reg.test($("#jobNumber").val().trim())){
  					boolJobNumber = true;
  					Notify("对不起，工号必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
  	    			return;
  				}else{
  					$.ajax({
  						type:"POST",
  						data:{jobNumber:$("#jobNumber").val()},
  						url:getRootPath()+"/web/user/findUser",
  						dataType:"json",
  						success:function(data){
  							console.log(data);
  							if(data.data.length>0){
  								boolJobNumber = true;
  								Notify("该工号已存在了",'top-right','5000','danger','fa-desktop',true);
  								return;
  							}else{
  								boolJobNumber = false;
  							}
  						}
  					})
  				}
			}
  		}
  		
  		//提交添加方法
  		function submitUser(){
  			if($("#role").val()==0){
  				Notify("请选择添加角色",'top-right','5000','danger','fa-desktop',true);
  				return;
  			}
  			else{
  				if($("#userName").val()==null||$("#userName").val()==""){
  	  				Notify("请输入用户名",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  				else if(boolMobile){
					Notify("请检查手机号是否合法",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  				else if(boolName){
  	  				Notify("请检查用户名是否合法",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  	  			else if($("#realName").val()==null||$("#realName").val()==""){
  	  				Notify("请输入真实姓名",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  	  			else if($("#companyId").val()==0||$("#companyId").val()==""){
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
  	  			else if($("#jobTitle").val()==null||$("#jobTitle").val()==""){
  	  				Notify("请输入担任职位",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  	  			else if($("#jobNumber").val()==null||$("#jobNumber").val()==""){
  	  				Notify("请输入工号",'top-right','5000','danger','fa-desktop',true);
  	  				return;
  	  			}
  	  			else if(boolJobNumber){
	  				Notify("请检查工号是否合法",'top-right','5000','danger','fa-desktop',true);
	  				return;
	  			}
  	  			else {
  	  				$.ajax({
  			            url:getRootPath()+"/web/user/addOrUpdUser",
  			            data:$("#addUser").serialize(),
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
  		}
  	</script>
  </body>
</html>
