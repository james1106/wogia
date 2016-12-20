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
            <li class="active">查看零件</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	零件
	        <small>
	            SpareParts
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看零件</span>
                <div class="widget-buttons">
                    <a href="javascript:void(0)" onclick="addOrUpdate(null,null);" target="iframe" class="btn btn-warning">添加零件</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		            <!-- <label>
		             	<select class="form-control input-sm" name="type"  id="type" >
		              		<option value="1">用户</option>
					        <option value="2">员工</option>
		              	</select>
		            </label> -->
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
                            <!-- <th>编号</th> -->
                            <th>零件名称</th>
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
				data:{componentName:name},
				url:getRootPath()+"/web/component/findComponent",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	function callbackMethod(pageNum,pageSize,name){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,componentName:name},
				url:getRootPath()+"/web/component/findComponent",
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
    			var state = "";
    			/* if(data[i].state==0){
    				state = "<a onclick=\"frozen("+data[i].id+",1)\" class=\"btn btn-danger\">冻结</a>"
    			}else{
    				state = "<a onclick=\"frozen("+data[i].id+",0)\" class=\"btn btn-danger\">解冻</a>"
    			} */
				str+="<tr>"+
					/* "<td>" +(i+1)+ "</td>"+ */
					"<td>" +data[i].componentName+ "</td>"+
					"<td>"+
						"<a onclick=\"addOrUpdate("+data[i].id+",'"+data[i].componentName+"')\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						state+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	function addOrUpdate(id,name){
    		var msg = "";
    		if(id==null||name==null){
    			msg = "<p style=\"padding-top:10px;\"><label>零件名称</label></p><p><input type=\"text\" class=\"form-control\" id=\"componentName\"/></p>"
    		}else{
    			msg = "<p style=\"padding-top:10px;\"><label>零件名称</label><span style=\"color:#999;margin-left:20px;\">原零件名字："+name+"</span></p><p><input type=\"text\" class=\"form-control\" id=\"componentName\"  placeholder=\"请输入零件名字\"/></p>"
    		}
    		bootbox.dialog({
			    message: msg,
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
			            	if($("#componentName").val()==null||$("#componentName").val()==""||$("#componentName").val()=="undefined"){
			            		Notify("请填写零件名字", 'top-right', '5000', 'danger', 'fa-desktop', true);
			            	}else{
			            		$.ajax({
									type:"POST",
									data:{id:id,componentName:$("#componentName").val()},
									dataType:"json",
									url:getRootPath()+"/web/component/addOrUpdComponent",
									success:function(data){
										if(data.code==200){
											init(null);
										}
									}
								});
			            	}
			            }
			        }
			    }
			});
    	}
    	
    </script>
</body>
</html>
