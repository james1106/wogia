<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<%@include file="../resource_css.jsp" %>    
  
</head>
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">查看意见反馈</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	意见反馈
	        <small>
	            Suggest
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看意见反馈</span>
            </div>
            <div class="widget-body">
            
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>反馈者</th>
                            <th>反馈内容</th>
                            <th>反馈时间</th>
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
    
    <%@include file="../resource_js.jsp" %>
    
    <script>
	    $(function(){
			init();
		});
		
	  //初始化数据
		function init(){
			//默认加载获取总条数
			$.ajax({
    			type:"POST",
				data:{},
				url:getRootPath()+"/web/suggest/getSuggest",
				dataType:"json",
				success:function(data){
					console.log(data);
					var num_entries = data.data.length;
					// 创建分页
					$("#Pagination").pagination(num_entries, {
						num_edge_entries: 1, //边缘页数
						num_display_entries: 4, //主体页数
						items_per_page: 8, //每页显示1项
						prev_text: "上一页",
						next_text: "下一页",
						callback: function(page_index, jq){
							callbackMethod(page_index+1,8);
							return false;
						}
					});
				}
    		});
		
		}
    	
    	//回调方法
    	function callbackMethod(pageNum,pageSize){
    		$.ajax({
    			type:"POST",
				data:{pageNum:pageNum,pageSize:pageSize},
				url:getRootPath()+"/web/suggest/getSuggest",
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
					"<td>" +data[i].userName+ "</td>"+
					"<td>" +data[i].content+ "</td>"+
					"<td>" +timeStampConversion(data[i].createTime)+ "</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str);
    	}
    </script>
</body>
</html>
