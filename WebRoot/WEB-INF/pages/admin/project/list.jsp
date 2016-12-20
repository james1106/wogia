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
            <li class="active">查看项目</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	项目
	        <small>
	            Project
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看项目</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/project/add.do" target="iframe" class="btn btn-warning">添加项目</a>
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
							<input type="text" class="form-control spinner-input" placeholder="通过项目名字查找" id="name">
			            </label>
			            <label>
			            	<a href="javascript:void(0);" class="btn btn-info" id="findName">查找</a>
			            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>项目名称</th>
                            <th>绑定项目名称</th>
                            <th>项目地址</th>
                            <th>所属物业</th>
                            <th>泵站最高温度</th>
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
				data:{projectName:name},
				url:getRootPath()+"/web/project/findProject",
				dataType:"json",
				success:initPagination
    		});
		
		}
    	
    	function callbackMethod(pageNum,pageSize,name){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize,projectName:name},
				url:getRootPath()+"/web/project/findProject",
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
					"<td>" +data[i].projectName+ "</td>"+
					"<td>" +data[i].tableName+ "</td>"+
					"<td>" +data[i].projectAddress+ "</td>"+
					"<td>" +data[i].estateName+ "</td>"+
					"<td>" +data[i].pumpTemperatureMax+ "C°</td>"+
					"<td>"+
						"<a href=\""+getRootPath()+"/admin/project/add.do?projectId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						"<a href='"+getRootPath()+"/admin/project/fenqu?projectId="+data[i].id+"'  class='btn btn-info'>查看分区</a>"+
						"<a onclick=\"deleteProject("+data[i].id+",'"+data[i].projectName+"')\" href=\"javascript:void(0)\" target=\"iframe\" class=\"btn btn-danger\">删除</a>"+
						// onclick=\"update("+data[i].id+",'"+data[i].projectName+"','"+data[i].projectAddress+"',"+data[i].estateId+","+data[i].pumpTemperatureMax+")\"
						state+
						
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    	
    	function deleteProject(id,projectName){
    		bootbox.dialog({
			    message: "<p style=\"padding-top:10px;\"><label>确定删除此项目吗？</lable></p>"+
			    		"<p style=\"padding-top:10px;\"><label>项目名称：</label><span style=\"color:#999;margin-left:20px;\">"+projectName+"</span></p>",
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
								data:{projectId:id},
								url:getRootPath()+"/web/project/deleteProjectById",
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
    	
    	function update(id,projectName,projectAddress,estateId,pumpTemperatureMax){
    		$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/estate/findEstate",
				dataType:"json",
				success:function(data){
					var str = "<select id='estate' class='form-control'>";
					for (var i = 0; i < data.data.length; i++) {
						if(data.data[i].id==estateId){
							str += "<option value='"+data.data[i].id+"' selected='selected'>"+data.data[i].estateName+"</option>";
						}else{
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].estateName+"</option>";
						}
					}
					str+="</select>";
					generate(id,projectName,projectAddress,str,pumpTemperatureMax);
				}
			});
    		
    	}
    	
    	function generate(id,projectName,projectAddress,str,pumpTemperatureMax){
    		bootbox.dialog({
			    message: "<p style=\"padding-top:10px;\"><label>项目名称</label></p><p><input type=\"text\" class=\"form-control\" id=\"projectName\" value=\""+projectName+"\"/></p>"+
			    		 "<p style=\"padding-top:10px;\"><label>项目地址</label></p><p><input type=\"text\" class=\"form-control\" id=\"projectAddress\" value=\""+projectAddress+"\"/></p>"+
			    		 "<p style=\"padding-top:10px;\"><label>所属物业</label></p><p>"+str+"</p>"+
			    		 "<p style=\"padding-top:10px;\"><label>泵站最高温度</label></p><p><input style=\"width:200px;\" type=\"text\" class=\"form-control\" id=\"pumpTemperatureMax\" value=\""+pumpTemperatureMax+"\"/><span style=\"position: absolute;margin-top:-30px;margin-left:200px;\">&nbsp;C°</span></p>",
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
			            	if($("#projectName").val()==null||$("#projectName").val()==""){
			            		Notify("请填写项目名字", 'top-right', '5000', 'danger', 'fa-desktop', true);
			            	}
			            	else if($("#projectAddress").val()==null||$("#projectAddress").val()==""){
			            		Notify("请填写项目地址", 'top-right', '5000', 'danger', 'fa-desktop', true);
			            	}
			            	else if($("#pumpTemperatureMax").val()==null||$("#pumpTemperatureMax").val()==""){
			            		Notify("请填写泵站最高温度", 'top-right', '5000', 'danger', 'fa-desktop', true);
			            	}
			            	else{
			            		if($("#pumpTemperatureMax").val()!=null&&$("#pumpTemperatureMax").val()!=""){
				    				var re = /^\d+(?=\.{0,1}\d+$|$)/
				    	            if (!re.test($("#pumpTemperatureMax").val())||parseInt($("#pumpTemperatureMax").val())>100) {
				    	            	Notify("泵站最高温度(0~100)之间",'top-right','5000','danger','fa-desktop',true);
				    	            }else{
				    	            	$.ajax({
											type:"POST",
											data:{id:id,projectName:$("#projectName").val(),projectAddress:$("#projectAddress").val(),estateId:$("#estate").val(),pumpTemperatureMax:$("#pumpTemperatureMax").val()},
											dataType:"json",
											url:getRootPath()+"/web/project/addOrUpdProject",
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
			        }
			    }
			});
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
