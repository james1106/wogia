<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
	<!-- 引用公用css -->
    <%@include file="../resource_css.jsp" %>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">权限管理</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	权限
	        <small>
	            Power
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看权限</span>
                <div class="widget-buttons">
               <!--     <a href="javascript:void(0)" id="fastAddBtn" class="btn btn-danger">快速</a>-->
                   <a href="<%=request.getContextPath()%>/admin/powermanage/add.do" id="importBtn"  class="btn btn-info">设置权限</a> 
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		           		查看权限
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>角色</th>
                            <th>权限</th>
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
	    	
			init(1,8);
			
		});
		
		//初始化数据
		function init(pageNum,pageSize,status){
		
			//通过Ajax加载分页元素
			var initPagination = function(data) {
				console.log(data);
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
				callbackMethod(page_index+1,8);
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
				type:"POST",
				data:{pageNum:null,pageSize:null},
				url:getRootPath()+"/web/rolePower/getRolePower",
				dataType:"json",
				success:initPagination
			});
		
		}
		
		function callbackMethod(pageNum,pageSize){
			$.ajax({
				type:"POST",
				data:{pageNum:null,pageSize:null},
				url:getRootPath()+"/web/rolePower/getRolePower",
				dataType:"json",
				success:function(data){
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
			console.log(data);
			var str =""
			for (var i = 0; i < data.length; i++) {
				var powerStr = "";
				if(data[i].powers != null && data[i].powers.length > 0){
					for(var j=0 ; j < data[i].powers.length; j++){
						if(data[i].powers[j].power!=null){
							powerStr += data[i].powers[j].power.description+"   |   ";
						}
					}
				}else{
					powerStr = "无";
				}
				str += "<tr><td>"+data[i].role+"</td><td><div class='shear' title='"+powerStr+"'>"+powerStr+"</td></div></tr>";
			}
			$("#loading").hide();
			$("#dataBody").html(str);
		}
    </script>
</body>
<!--  /Body -->
</html>
