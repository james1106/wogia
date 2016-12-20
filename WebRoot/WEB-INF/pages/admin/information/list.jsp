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
            <li class="active">查看资讯</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	资讯
	        <small>
	            Information
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看资讯</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/information/add.do" target="iframe" class="btn btn-warning">添加资讯</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <!-- <div class="col-sm-12">
			            <label>
							<input type="text" class="form-control spinner-input" placeholder="通过标题查找" id="title">
			            </label>
			            <label>
			            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
			            </label>
		          	</div> -->
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>标题</th>
                            <th>内容简介</th>
                            <th>虚拟阅读数</th>
                            <th>真实阅读数</th>
                            <th>创建时间</th>
                            <th>发布者</th>
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
    		/* 
    		//搜索
    		$("#findName").bind("click",function(){
    			init($("#title").val());
			});
			//添加enter事件
			$("#type").bind("keyup", function(event) {
				init($("#title").val());
			}); */
    	});
    	
    	//初始化数据
		function init(title){
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
				callbackMethod(page_index+1,8,title);
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{title:title},
				url:getRootPath()+"/web/news/findNews",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	//回调方法
    	function callbackMethod(pageNum,pageSize,title){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,title:title},
				url:getRootPath()+"/web/news/findNews",
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
    			if(data[i].isValid!=0){
    				str+="<tr>"+
						"<td><div class='shear' title='"+data[i].title+"'>" +data[i].title+ "</div></td>"+
						"<td><div class='shear' title='"+data[i].introduction+"'>" +data[i].introduction+ "</div></td>"+
						"<td>" +data[i].reads+ "</td>"+
						"<td>" +data[i].realReads+ "</td>"+
						"<td>" +timeStampConversion(data[i].createTime)+ "</td>"+
						"<td>" +data[i].userName+ "</td>"+
						"<td>"+
							"<a href=\""+getRootPath()+"/admin/information/add.do?infoId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">查看/修改</a>"+
							"<a href=\"javascript:void(0)\" onclick=\"deleteNotice("+data[i].id+")\" target=\"iframe\" class=\"btn btn-danger\">删除</a>"+
						"</td>"+
					"</tr>"
    			}
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	function deleteNotice(id){
    		bootbox.dialog({
			    message: "<p style='padding-top:10px;'>确定删除此资讯吗？</p>",
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
								data:{newsId:id},
								dataType:"json",
								url:getRootPath()+"/web/news/delNews",
								success:function(data){
									if(data.code==200){
										init();
									}
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
