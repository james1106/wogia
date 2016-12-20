<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
    <meta charset="utf-8" />
    
    <!-- 引用公用css -->
    <%@include file="../resource_css.jsp" %>
  	<style>
  		/*自定义弹窗*/
  		
  	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">查看用户</li>
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
                <span class="widget-caption">查看用户</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/user/add.do" target="iframe" class="btn btn-warning">添加用户</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		          	<label>
			            <select class="form-control" id="role">
	                    	<option value='0' selected>全部</option>
	                    </select>
                    </label>
		            <label>
						<input type="text" class="form-control spinner-input" placeholder="通过真实姓名查找" id="realName">
		            </label>
		            <label>
		            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
		            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                        	<th>工号</th>
                            <th>真实姓名</th>
                            <th>账号</th>
                            <th>手机号码</th>
                            <th>邮箱</th>
                            <!-- <th>QQ</th> -->
                            <th>性别</th>
                            <th>角色</th>
                            <th>所属片区</th>
                           <th>注册时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="dataBody">
                        <tr id="loading">
                        	<td colspan="99" align="center" style="padding: 50px 100px;"><img src="<%=request.getContextPath()%>/resource/img/loading.gif"/></td>
                        </tr>
                    </tbody>
                </table>
                
                <div id="Pagination" class="pagination"></div>
            </div>
        </div>
    </div>
	
	<!-- 引用公用js -->
	<%@include file = "../resource_js.jsp" %>
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
    	
    		//初始化
    		init(null,null);
    		
    		//搜索
    		$("#findName").bind("click",function(){
				select();
			});
			
			//添加enter事件
			$("#realName").bind("keyup", function(event) {
				if (event.keyCode == "13") {
					select();
				}
			});
			
			//根据角色查找
			$("#role").bind("change", function() {
				select()
			});
    	});
    	
    	//查询
    	function select(){
    		var role = $("#role").val();
    		var realName = $("#realName").val();
    		if(role==0){
    			init(realName,null);
    		}else{
    			init(realName,role);
    		}
    	}
    	
    	//初始化数据
		function init(name,roleId){
			//通过Ajax加载分页元素
			var initPagination = function(data) {
				var num_entries = data.data.length;
				// 创建分页
				$("#Pagination").pagination(num_entries, {
					num_edge_entries: 1, //边缘页数
					num_display_entries: 4, //主体页数
					items_per_page: 8, //每页显示1项
					prev_text: "上一页",
					next_text: "下一页",
					callback: pageselectCallback
				});
			};

			//分页回调
			function pageselectCallback(page_index, jq) {
				//page_index : 当前页码
				//pageSize : 每页显示多少天记录
				//name : 按照名字查询
				callbackMethod(page_index+1,8,name,roleId);
				//$("#Pagination").append("当前第 " + (page_index+1) + " 页");
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{realName:name,roleId:roleId},
				url:getRootPath()+"/web/user/findUser",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	function callbackMethod(pageNum,pageSize,name,roleId){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,realName:name,roleId:roleId},
				url:getRootPath()+"/web/user/findUser",
				dataType:"json",
				success:function(data){
					//console.log(data);
					if(data.code==200){
						if(data.data.length == 0){
							$("#dataBody").html("<tr><td colspan=\"99\" align=\"center\">没有数据</td></tr>");
						}else{
							bindData(data.data);
						}
					}
				}
    		});
    	}
    	//绑定数据
    	function bindData(data){
    		var str =""
    		for (var i = 0; i < data.length; i++) {
    			var state = "";
    			var gender = "";
    			if(data[i].status < 3){
    				state = "<a onclick=\"frozen("+data[i].id+",3)\" class=\"btn btn-danger\">冻结</a>"
    			}else{
    				state = "<a onclick=\"frozen("+data[i].id+",1)\" class=\"btn btn-danger\">解冻</a>"
    			}
    			if(data[i].gender==0){
    				gender = "男";
    			}else{
    				gender = "女";
    			}
    			var mobile = (data[i].mobile==null||data[i].mobile=="")?data[i].mobile="--":data[i].mobile=data[i].mobile;
    			var email = (data[i].email==null||data[i].email=="")?data[i].email="--":data[i].email=data[i].email;
    			var qq = (data[i].qq==null||data[i].qq=="")?data[i].qq="--":data[i].qq=data[i].qq;
    			var company = (data[i].companyName==null||data[i].companyName=="")?data[i].companyName="--":data[i].companyName=data[i].companyName;
				str+="<tr>"+
					"<td>" +data[i].jobNumber+ "</td>"+
					"<td>" +data[i].realName+ "</td>"+
					"<td>" +data[i].userName+ "</td>"+
					"<td>" +mobile+ "</td>"+
					"<td>" +email+ "</td>"+
					/* "<td>" +qq+ "</td>"+ */
					"<td>" +gender+ "</td>"+
					"<td>" +data[i].roleName+ "</td>"+
					"<td>" +company+ "</td>"+
					"<td>" +data[i].createtimes+ "</td>"+
					"<td>"+
						"<a onclick=\"details("+data[i].id+")\" target=\"iframe\" class=\"btn btn-info\">详情</a>"+
						"<a href=\""+getRootPath()+"/admin/user/userEdit.do?id="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						state+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	//冻结
    	function frozen(id,status){
    		if(status == 3){
				msg = "确认冻结此账户吗？";
			}else{
				msg = "确认解冻此账户吗？";
			}
			var str='<div id="myModal">'+
			  	'<div class="form-group">'+
			        '<label></label>'+
			        '<span class="input-icon icon-right">'+
			        	'<div class="input-group">'+
			        		'<span id="name" >'+msg+'</span>'+
			            '</div>'+
			        '</span>'+
			   '</div>'+
			'</div>';
   		
   			bootbox.dialog({
			    message: str,
			    title: "提示框",
			    buttons: {
			    	"关闭": {
			            className: "btn-default",
			            callback: function () {
			            	
			            }
			      	},
			        success: {
			            label: "确认",
			            className: "btn-danger",
			            callback: function () {
			            	$.ajax({
								type:"POST",
								data:{id:id,status:status},
								url:getRootPath()+"/web/user/addOrUpdUser",
								dataType:"json",
								success:function(data){
									if(status==200){
			            				Notify(data.msg,'top-right','5000','info','fa-desktop',true);
			            			}else{
			            				Notify(data.msg,'top-right','5000','danger','fa-desktop',true);
			            			}
			            			init(null,null);
								}
							});
			            }
			        }
			    }
			});
    	}
    	
    	//详情和修改
   		function details(id){
   			layer.open({
			  type: 2,
			  title: '用户详情',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['50%', '80%'],
			  content: getRootPath()+"/admin/user/detail.do?id="+id
			});
   		}
    	
    </script>
</body>
</html>
