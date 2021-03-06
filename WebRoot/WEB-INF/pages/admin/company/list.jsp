﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
            <li class="active">查看片区</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	片区
	        <small>
	            Company
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看片区</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/company/add.do" target="iframe" class="btn btn-warning">添加片区</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
			            <label>
							<input type="text" class="form-control spinner-input" placeholder="通过片区名字查找" id="name">
			            </label>
			            <label>
			            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
			            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>片区编号</th>
                            <th>片区名称</th>
                            <th>片区地址</th>
                            <th>所在地区名</th>
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
    		
    		init(null);
    		
    		//搜索
    		$("#findName").bind("click",function(){
				init($("#name").val());
			});
			//添加enter事件
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
				data:{companyName:name},
				url:getRootPath()+"/web/company/findCompany",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	function callbackMethod(pageNum,pageSize,name){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,companyName:name},
				url:getRootPath()+"/web/company/findCompany",
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
    		var str =""
    		for (var i = 0; i < data.length; i++) {
    			var state = "";
    			if(data[i].state==0){
    				state = "<a onclick=\"frozen("+data[i].id+",1)\" class=\"btn btn-danger\">冻结</a>"
    			}else{
    				state = "<a onclick=\"frozen("+data[i].id+",0)\" class=\"btn btn-danger\">解冻</a>"
    			}
				str+="<tr>"+
					"<td>" +data[i].number+ "</td>"+
					"<td>" +data[i].companyName+ "</td>"+
					"<td>" +data[i].companyAddress+ "</td>"+
					"<td>" +data[i].cityName+ "</td>"+
					"<td>"+
						"<a href=\""+getRootPath()+"/admin/company/add.do?companyId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						/* state+ */
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	//冻结
    	function frozen(id,state){
    		if(state == 1){
				msg = "确认冻结此片区吗？";
			}else{
				msg = "确认解冻此片区吗？";
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
								data:{id:id,state:state},
								url:getRootPath()+"/web/company/addOrUpdCompany",
								dataType:"json",
								success:function(data){
									if(status==200){
			            				Notify(data.msg,'top-right','5000','info','fa-desktop',true);
			            			}else{
			            				Notify(data.msg,'top-right','5000','danger','fa-desktop',true);
			            			}
			            			init(null);
								}
							});
			            }
			        }
			    }
			});
    	}
    	
    </script>
</body>
</html>
