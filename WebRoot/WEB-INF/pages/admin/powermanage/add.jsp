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
			<li class="active">权限管理</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			权限 <small> Power </small>
		</h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">设置权限<span style="font-size: 12px;color: red">(设置成功后,重新登录后生效)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="powerFrom" id="powerFrom">
                    	
                        <div class="form-group" id="roleDiv">
                            <label>角色</label>
                            <span class="input-icon icon-right">
                            	<select name="roleId" class="form-control" id="role">
                            		<option value="0">请选择</option>
                                </select>
                            </span>
                        </div>
                        
                        <div class="form-group sel" id="projectDiv">
                            <label>设置权限<span style="font-size: 12px;color: red">(已勾选则有权限)</span></label>
                            <table class="table table-striped table-hover" id="simpledatatable">
								<tbody>
									<tr>
										<td id="dataTD"></td>
									</tr>
								</tbody>
							</table>
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
			
			$("#role").bind("change",function(){
				var roleId = $("#role").val();
				$.ajax({
					type : "POST",
					url : getRootPath()+"/web/rolePower/getPower",
					data:{roleId:roleId},
					dataType : "json",
					async : false,
					timeout : 5000,
					success : function(json) {
						if(json.code == 200){
							var dataList = json.data;
							buildTD(dataList,roleId);
						}else{
							Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
						}
					}
				})
			});
		});
		
		function buildTD(dataList,roleId){
//			console.debug(dataList);
			var str = "";
			var len = dataList.length;
			if(len == 0){
				str = "没有数据";
			}else{
				
				for(var i = 0 ; i < len ; i++){
					var obj = dataList[i];
					var checked = "";
					var disable=" ";
					if(roleId == 1 && obj.id==16){
						disable=" disabled='disabled'";
					}
					if(obj.roleId == roleId){
						checked = "checked='checked'";
					}
					str += "<lable class='checkbox-inline'><input type='checkbox' name='powerId' value="+obj.id+" "+checked+disable+">"+obj.description+"</lable>";
				}
			}
			$("#dataTD").html(str);
		}
		
		function submit(){
			var isOK = false;
			var roleId = $("#role").val();
//			console.debug($("#powerFrom").serialize());
//			return false;
			if(roleId == 0){
				Notify("没有选择角色",'top-right','5000','danger','fa-desktop',true);
				return isOK;
			}
			$.ajax({
				type : "POST",
				url : getRootPath()+"/web/rolePower/updateRolePower",
				data:$("#powerFrom").serialize(),
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					if(json.code == 200){
						window.location.href=getRootPath()+"/admin/powermanage/list.do";
						isOK = true;
					}else{
						Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			})
			return isOK;
		}
  	</script>
  </body>
</html>
