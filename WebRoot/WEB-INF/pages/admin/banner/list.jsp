<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
	
	<%@include file="../resource_css.jsp" %>    
  
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">Banner列表</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	         Banner
	        <small>
	            Ads
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看Banner</span>
                <div class="widget-buttons">
                    <a href="<%=request.getContextPath()%>/admin/banner/add.do" target="iframe" class="btn btn-info">添加Banner</a>
                </div>
            </div>
            <div class="widget-body">
            
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>图片</th>
                            <th>链接</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="dataBody">
              			<tr id="loading">
                        	<td colspan="99" align="center" style="padding: 50px 100px;"><img src="<%=request.getContextPath()%>/resource/img/loading.gif"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- 删除模态 -->
    <div class="modal modal-darkorange" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">删除提示</h4>
				</div>
				<div class="modal-body">
					<p>确认删除？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-default" onclick="delAds()" data-dismiss="modal">确认</button>
					<input type="hidden" id="tempADsId" value="0">
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
    
    <%@include file="../resource_js.jsp" %>
    
    <script>
	    $(function(){
			init();
		});
		
		function init(){
			$.ajax({
				type:"POST",
				data:{},
				url:getRootPath()+"/web/news/getBanners",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.code==200){
						if(data.data.lists.length == 0){
							$("#dataBody").html("<tr><td colspan=\"99\" align=\"center\">没有数据</td></tr>");
						}else{
							bindData(data.data.lists);
						}
					}
				}
			});
		}
		//绑定数据
		function bindData(data){
			var str =""
			for (var i = 0; i < data.length; i++) {
				var imgurl = "";
				if(data[i].imgUrl==""||data[i].imgUrl==null){
					imgurl = "无";
				}
				else{
					imgurl = data[i].imgUrl;
				}
				str+="<tr>"+
					"<td><img style='width:200px;height:100px;' src='"+getRootPath()+"/"+data[i].image+"'/></td>"+
					"<td><div class='shear' title='"+imgurl+"'>" +imgurl+ "</div></td>"+
					"<td>"+
						"<a href=\""+getRootPath()+"/admin/banner/add.do?bannerId="+data[i].id+"\" target=\"iframe\" class=\"btn btn-info\">修改</a>"+
						"<a href=\"javascript:void(0)\" onclick=\"deleteBanner("+data[i].id+")\" target=\"iframe\" class=\"btn btn-danger\">删除</a>"+
					"</td>"+
				"</tr>"
			}
			$("#loading").hide();
			$("#dataBody").html(str); 
		}
		
		function deleteBanner(id){
			bootbox.dialog({
			    message: "<p style=\"padding-top:10px;\"><label>确定删除此Banner？</label></p>",
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
								data:{bannerId:id},
								dataType:"json",
								url:getRootPath()+"/web/news/delBanner",
								success:function(data){
									if(data.code==200){
										Notify("添加成功", 'top-right', '5000', 'info', 'fa-desktop', true);
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
<!--  /Body -->
</html>
