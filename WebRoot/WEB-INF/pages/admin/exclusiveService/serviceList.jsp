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
            <li class="active">查看项目申请列表</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	项目申请
	        <small>
	            ProjectApply
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看项目申请</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/applyContext/add.do" target="iframe" class="btn btn-warning">申请内容编辑</a>
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>项目名称</th>
                            <th>申请人</th>
                            <th>申请时间</th>
                            <th>状态</th>
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
    	
    		init();
    		
    	});
    	
    	//初始化数据
		function init(){
		
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
				callbackMethod(page_index+1,8);
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{},
				url:getRootPath()+"/web/project/findProjectApply",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	//回调方法
    	function callbackMethod(pageNum,pageSize){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize},
				url:getRootPath()+"/web/project/findProjectApply",
				dataType:"json",
				success:function(data){
					console.log(data);
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
    			var status = "";
    			var state = "";
    			if(data[i].status==0){
    				status = "已申请";
    				state = "<a href=\""+getRootPath()+"/admin/exclusiveService/add.do?applyId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-warning\">录入专项服务详细</a>"
    			}else{
    				status = "已录入";
    				state = "<a href=\""+getRootPath()+"/admin/exclusiveService/list.do?applyId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">查看专项服务详细</a>"
    			}
				str+="<tr>"+
					"<td>" +data[i].projectName+ "</td>"+
					"<td>" +data[i].userName+ "</td>"+
					"<td>" +timeStampConversion(data[i].applyTime)+ "</td>"+
					"<td>" +status+ "</td>"+
					"<td>"+
						state+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    </script>
</body>
</html>
