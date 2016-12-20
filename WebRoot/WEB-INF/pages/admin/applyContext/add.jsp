<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- Head -->
<head>

	<%@include file="../resource_css.jsp" %>
    
    <style>
    	#context{
    		width: 500px;
    		height: 250px;
    		max-width: 1024px;
    		max-height: 768px;
    		min-height: 250px;
    		min-width: 500px;
    	}
    </style>
  	
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加/更新申请内容</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	申请内容
	        <small>
	            ApplyContext
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加/更新申请内容</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
                    	<div class="form-group" id="companyNameDiv">
                            <label>申请内容</label>
                         	<div id="editor" name="content" style="width:100%;height:500px;"></div>
                        </div>
                        <a href="javascript:void(0)" target="iframe" onclick="submit()" id="submit" class="btn btn-info">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <%@include file="../ueditor.jsp" %>
   <%@include file="../resource_js.jsp" %>
   
    <script>
    	
  		//实例化编辑器
		var editor = UE.getEditor("editor");
    
	    $.ajax({
			type:"POST",
			data:{},
			dataType:"json",
			url:getRootPath()+"/web/project/findApplyContext",
			success:function(data){
				if(data.code==200){
					editor.addListener("ready", function () {
						//console.log(data.data.context);
			        	// editor准备好之后才可以使用
			        	editor.setContent(data.data.context);
			        });
				}
			}
		});
    	
		function submit(){
			var content = UE.getEditor("editor").getContent();
			if(content!=""&&content!=null){
				$.ajax({
					type : "POST",
					url : getRootPath()+"/web/project/addOrUpdApplyContext",
					data:{id:1,context:content},
					dataType :"json",
					success:function(data){
						if(data.code == 200){
							Notify("操作成功", 'top-right', '5000', 'info', 'fa-desktop', true);
							return true;
						}else{
							Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
							return false;
						}
					}
				});
			}else{
				Notify("请填写申请内容", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return false;
			}
		}
       
    </script>
</body>
<!--  /Body -->
</html>
