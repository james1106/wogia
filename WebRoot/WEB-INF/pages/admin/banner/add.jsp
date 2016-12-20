<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>

	<%@include file="../resource_css.jsp" %>
    
    <style>
    	#filePath{
    		box-shadow: none !important;
    		-webkit-box-shadow: none !important;
    		background: #EEEEEE;
    	}
    </style>
  	
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加Banner</li>
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
                <span class="widget-caption">添加Banner</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
                    	<div class="form-group">
                    		<input type="hidden" name="filePath" id="files" value="" >
                            <label>图片(建议尺寸比例  9:5)</label>
                            <div>
							  	<input id="iconUpload" name="res" type="file" class="file-loading" >
							</div>
                        </div> 
                    	<div class="form-group">
                            <label>Banner链接地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input id="imgUrl" class="form-control"  type="text" placeholder="可选(不填则不跳转)  格式: http://www.wogia.com">
                                </div>
                            </span>
                        </div>
                        
                        <a href="javascript:void(0)" target="iframe" onclick="submit()" id="submit" class="btn btn-info">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
   <%@include file="../resource_js.jsp" %>
   
    <script>
    	
    	var bannerId = getUrlParam("bannerId");
    	
    	var map = new Map();
    	
	    $("#iconUpload").fileinput({
			language:'zh',
		    uploadUrl: getRootPath()+"/res/upload", // 图片上传接口
		    showPreview : true,
		    showRemove: false,
		    showUpload : false,
		    maxFileSize : 850,  //上传图片的最大限制  50KB
		    allowedFileExtensions: ["jpg", "png"],
		    initialCaption: "请选择图片",
		});
		$("#iconUpload").on("fileuploaded", function (event, data, previewId, index) {
			if (null != data) {
				map.put(data.filenames[0],data.response.data.url);
				//点击删除按钮移出图片
		    	$(".kv-file-remove").click(function(){
		    		var filenames = $(this).closest(".file-thumbnail-footer").children(".file-footer-caption").text();
		    		map.remove(filenames);
		    	});
			}
		});
		
		$(function(){
			if(bannerId!=null){
				$("#submit").html("修改轮播");
				$.ajax({
					type : "GET",
					url : getRootPath()+"/web/news/getBannersById",
					data:{id:bannerId},
					dataType :"json",
					async : false,
					timeout : 5000,
					success:function(data){
						if(data.code == 200){
							$("#iconUpload").fileinput('refresh', {
				                initialPreview:["<img src='" +getRootPath()+"/"+ data.data.image + "' class='file-preview-image' alt='Banner图片' title='Banner图片'>"],
				            });
							$("#files").val(data.data.image);
							
							$("#imgUrl").val(data.data.imgUrl);
						}else{
							Notify("获取失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
							return false;
						}
					}
				});
			}
		});
		
		function submit(){
			var array = map.keySet();
			if(array.length>1){
				Notify("Banner一次只能上传一张", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return false;
			}
    		var temp ="";
    		for(i in array){
    			temp += map.get(array[i]);
    		}
    		if(temp!=""){
    			$("#files").val(temp);
    		}
			
			var rex = /^(upload)\S{1,}$/;
			
			if(null == $("#files").val() || !rex.test($("#files").val())){
				Notify("请上传图片", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return false;
			}
			console.log(bannerId);
			$.ajax({
				type : "GET",
				url : getRootPath()+"/web/news/addOrUpdBanner",
				data:{id:bannerId,image:$("#files").val(),imgUrl:$("#imgUrl").val()},
				dataType :"json",
				async : false,
				timeout : 5000,
				success:function(respObj){
					if(respObj.code == 200){
						Notify("添加成功", 'top-right', '5000', 'info', 'fa-desktop', true);
						window.location.href = getRootPath()+"/admin/banner/list.do"
						return true;
					}else{
						Notify("添加失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
						return false;
					}
				}
			});
		}
        
       
    </script>
</body>
<!--  /Body -->
</html>
