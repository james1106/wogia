<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
    <meta charset="utf-8" />
    
    <!-- 引用公用css -->
    <%@include file="../resource_css.jsp" %>
  	<style>
  		
  	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">查看订单</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	订单
	        <small>
	            Order
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看订单</span>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		            <label>
		            	<input type="text" class="form-control spinner-input" placeholder="通过订单号查找" id="orderNumber">
						<input type="text" class="form-control spinner-input" placeholder="通过真实姓名查找" id="realName">
		            </label>
		            <label>
		            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
		            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>订单号</th>
                            <th>订单描述</th>
                            <th>订单进度</th>
                            <th>创建时间</th>
                            <th>创建人</th>
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
    	
    		init(null,null);
    		
    		//搜索
    		$("#findName").bind("click",function(){
				init($("#realName").val(),$("#orderNumber").val());
			});
			//添加enter事件
			$("#name").bind("keyup", function(event) {
				if (event.keyCode == "13") {
					init($("#realName").val(),$("#orderNumber").val());
				}
			});
    		
    	});
    	
    	//初始化数据
		function init(name,orderNumber){
		
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
				callbackMethod(page_index+1,8,name,orderNumber);
				//$("#Pagination").append("当前第 " + (page_index+1) + " 页");
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{pageNum:null,pageSize:null,realName:name,orderNumber:orderNumber},
				url:getRootPath()+"/web/order/findOrderList",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	function callbackMethod(pageNum,pageSize,name,orderNumber){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,realName:name,orderNumber:orderNumber},
				url:getRootPath()+"/web/order/findOrderList",
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
				str+="<tr>"+
					"<td>" +data[i].orderNumber+ "</td>"+
					"<td><div class='shear' title='"+data[i].orderDescribe+"'>" +data[i].orderDescribe+ "</div></td>"+
					"<td>" +data[i].orderDataBean.content+ "</td>"+
					"<td>" +timeStampConversion(data[i].createTime)+ "</td>"+
					"<td>" +data[i].userName+ "</td>"+
					"<td>"+
						"<a onclick=\"details("+data[i].id+")\" target=\"iframe\" class=\"btn btn-info\">订单详情</a>"+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	//详情
    	function details(id){
    		layer.open({
			  type: 2,
			  title: '用户详情',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['50%', '80%'],
			  content: getRootPath()+"/admin/order/detail.do?id="+id
			});
    	}
    	
    </script>
</body>
</html>
