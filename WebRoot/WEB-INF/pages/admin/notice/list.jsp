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
            <li class="active">查看公告</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	公告
	        <small>
	            Notice
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看公告</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/notice/add.do" target="iframe" class="btn btn-warning">发布公告</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
			            <label>
							<select class="form-control" id="type" name="type">
                           		<option value='2' selected="selected">全部</option>
                           		<option value='0'>系统公告</option>
                           		<option value='1'>停水公告</option>
                           	</select>
			            </label>
			            <label>
			            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
			            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>标题</th>
                            <th>内容</th>
                            <th>公告类型</th>
                            <th>创建时间</th>
                            <th>摘要</th>
                            <th>发布人</th>
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
    			if($("#type").val()!=0&&$("#type").val()!=1){
					init(null);
				}else{
					init($("#type").val());
				}
			});
			//添加enter事件
			$("#type").bind("keyup", function(event) {
				if (event.keyCode == "13") {
					if($("#type").val()!=0&&$("#type").val()!=1){
						init(null);
					}else{
						init($("#type").val());
					}
					
				}
			});
    	});
    	
    	//初始化数据
		function init(type){
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
				callbackMethod(page_index+1,8,type);
				return false;
			}
			
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{type:type},
				url:getRootPath()+"/web/info/adminGetInfo",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	//回调方法
    	function callbackMethod(pageNum,pageSize,type){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,type:type},
				url:getRootPath()+"/web/info/adminGetInfo",
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
    			var strType = "";
    			if(data[i].type==0){
    				strType = "系统公告";
    			}else{
    				strType = "停水公告";
    			}
				str+="<tr>"+
					"<td><div class='shear' title='"+data[i].title+"'>" +data[i].title+ "</div></td>"+
					"<td><div class='shear' title='"+data[i].content+"'>" +data[i].content+ "</div></td>"+
					"<td>" +strType+ "</td>"+
					"<td>" +timeStampConversion(data[i].createTime)+ "</td>"+
					"<td><div class='shear' title='"+data[i].brief+"'>" +data[i].brief+ "</div></td>"+
					"<td>" +data[i].publisherName+ "</td>"+
					"<td>"+
						"<a href=\""+getRootPath()+"/admin/notice/add.do?noticeId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">查看/修改</a>"+
						"<a href=\"javascript:void(0)\" onclick=\"deleteNotice("+data[i].id+")\" target=\"iframe\" class=\"btn btn-danger\">删除</a>"+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	function deleteNotice(id){
    		bootbox.dialog({
			    message: "<p style='padding-top:10px;'>确定删除此公告吗？<p>",
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
								data:{systemId:id},
								dataType:"json",
								url:getRootPath()+"/web/info/delSystemInfo",
								success:function(data){
									if(data.code==200){
										init(null);
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
