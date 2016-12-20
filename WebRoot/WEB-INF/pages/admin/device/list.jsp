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
            <li class="active">查看分区</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	分区
	        <small>
	            Device
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看分区</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/device/add.do" target="iframe" class="btn btn-warning">添加分区</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		           <!-- <label>
		             	<select class="form-control input-sm" name="type"  id="type" >
		              		<option value="0">全部</option>
		              		<option value="1">未绑定</option>
					        <option value="2">已绑定</option>
		              	</select>
		            </label> -->
		            <label>
						<input type="text" class="form-control spinner-input" placeholder="通过分区名字查找" id="name">
		            </label>
		            <label>
		            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
		            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>分区名称</th>
                            <!-- <th>分区类型</th> -->
                            <th>绑定项目名称</th>
                            <th>创建时间</th>
                            <th>所属项目</th>
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
    	$(function() {

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
				data:{deviceName:name},
				url:getRootPath()+"/web/device/findDevice",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	//通过条件筛选数据
    	function callbackMethod(pageNum,pageSize,name){
    		console.log(name);
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,deviceName:name},
				url:getRootPath()+"/web/device/findDevice",
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
    			var isHaveDevice = "";
    			var findDeviceSparePartsList = "";
    			if(data[i].isHaveDevice>0){
    				isHaveDevice = "<a href=\""+getRootPath()+"/admin/waterPump/add.do?id="+data[i].deviceBean.id+"&state=1&projectId="+data[i].deviceBean.projectId+"\" class=\"btn btn-success\">修改泵站信息</a>"
    				findDeviceSparePartsList = "<a href=\""+getRootPath()+"/admin/device/deviceSparePartsList.do?id="+data[i].deviceBean.id+"\" target=\"iframe\" class=\"btn btn-warning\">查看分区零件</a>"
    			}else{
    				isHaveDevice = "<a href=\""+getRootPath()+"/admin/waterPump/add.do?id="+data[i].deviceBean.id+"&state=0&projectId="+data[i].deviceBean.projectId+"\" class=\"btn btn-success\">添加泵站信息</a>"
    				findDeviceSparePartsList = "<a href=\"javascript:void(0)\" onclick=\"layer.msg('请先添加泵站信息');\" target=\"iframe\" class=\"btn btn-warning\">查看分区零件</a>"
    			}
				str+="<tr>"+
					"<td>" +data[i].deviceBean.deviceName+ "</td>"+
					"<td>" +data[i].deviceBean.tableDevice+ "</td>"+
					"<td>" +data[i].deviceBean.deviceCreatetimes+ "</td>"+
					"<td>" +data[i].deviceBean.projectName+ "</td>"+
					"<td>"+
						"<a onclick=\"update("+data[i].deviceBean.id+",'"+data[i].deviceBean.deviceName+"')\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						isHaveDevice+
						findDeviceSparePartsList+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	function update(id,name){
    		bootbox.dialog({
			    message: "<p style=\"padding-top:10px;\"><label>分区名称</label><span style=\"color:#999;margin-left:20px;\">原分区名字："+name+"</span></p><p><input type=\"text\" class=\"form-control\" id=\"deviceName\"/></p>",
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
			            
			            
			            	if($("#deviceName").val()==null||$("#deviceName").val()==""||$("#deviceName").val()=="undefined"){
			            		Notify("请填写分区名字", 'top-right', '5000', 'danger', 'fa-desktop', true);
			            	}else{
			            		$.ajax({
									type:"POST",
									data:{id:id,deviceName:$("#deviceName").val()},
									dataType:"json",
									url:getRootPath()+"/web/device/addOrUpdDevice",
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
