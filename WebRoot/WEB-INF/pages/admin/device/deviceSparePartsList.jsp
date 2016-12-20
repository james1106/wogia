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
            <li class="active">查看分区零件</li>
            <li class="actice"><span class="name"></span></li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	分区零件
	        <small>
	            DeviceSpareParts
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看分区零件-当前分区名字：<span class="name"></span></span>
                <div class="widget-buttons">
                    <a href="javascript:void(0)" onclick="add()" target="iframe" class="btn btn-warning">绑定分区零件</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		            <label>
						<input type="text" class="form-control spinner-input" placeholder="通过零件名字查找" id="name">
		            </label>
		            <label>
		            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
		            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>零件名称</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <!-- <th>保养时间</th> -->
                            <th>零件更换(或开始使用时间)时间</th>
                            <th>零件寿命(天)</th>
                            <th>零件描述</th>
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
    
    	var deviceId = getUrlParam("id");
    	
    	$(function() {
    	
    		//获取分区名字
    		$.ajax({
    			type:"POST",
				data:{id:deviceId},
				url:getRootPath()+"/web/device/findDevice",
				dataType:"json",
				success:function(data){
					$(".name").html(data.data[0].deviceBean.deviceName);
				}
    		});

			init(null);
			
			$("#findName").bind("click",function(){
				init($("#name").val());
			});
			
			$("#name").bind("keyup", function(event) {
				if (event.keyCode == "13") {
					init($("#name").val());
				}
			});
    		
		});
		
		//初始化数据
		function init(name){
		
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
				callbackMethod(page_index+1,8,name);
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{deviceId:deviceId,componentName:name},
				url:getRootPath()+"/web/componentDevice/findComponentDevice",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	//通过条件筛选数据
    	function callbackMethod(pageNum,pageSize,name){
    		console.log(name);
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,componentName:name,deviceId:deviceId},
				url:getRootPath()+"/web/componentDevice/findComponentDevice",
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
    		console.log(data);
    		var str =""
    		for (var i = 0; i < data.length; i++) {
				str+="<tr>"+
					"<td>" +data[i].componentName+ "</td>"+
					"<td>" +timeStampConversion(data[i].createTime)+ "</td>"+
					"<td>" +timeStampConversion(data[i].updateTime)+ "</td>"+
					/* "<td>" +timeStampConversion(data[i].maintainTime)+ "</td>"+ */
					"<td>" +timeStampConversion(data[i].replaceTime)+ "</td>"+
					"<td>" +data[i].life+ "</td>"+
					"<td>" +data[i].describe+ "</td>"+
					"<td>"+
						"<a href=\""+getRootPath()+"/admin/device/deviceSparePartsAdd.do?id="+data[i].id+"&deviceId="+deviceId+"\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	//跳转添加
    	function add(){
    		window.location.href = getRootPath() + "/admin/device/deviceSparePartsAdd.do?deviceId="+deviceId;
    	}
    </script>
</body>
</html>
