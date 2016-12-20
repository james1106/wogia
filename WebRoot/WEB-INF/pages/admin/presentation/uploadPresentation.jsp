<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
    <meta charset="utf-8" />
    
    <!-- 引用公用css -->
    <%@include file="../resource_css.jsp" %>
  	<style>
  		.preview{width: 100%;height: 100%;}
  		.previewDiv{
  			width: 200px;
  			height: 100px;
  			border-radius:6px;
  			border: 1px solid #e1e1e1;
  			box-shadow: 1px 1px 50px rgba(0, 0, 0, 0.3);
  			margin: 10px 0 0 10px;
  			float: left;
  			position: relative;
  		}
  		.previewDiv .removeImg{
  			position: absolute;
  			margin-top: -10px;
  			right:-10px;
  			display: inline-block;
  			background: #fff;
  			border-radius:50px;
  			padding: 5px;
  			font-size: 16px;
  			color: blue;
  			cursor: pointer;
  		}
  		.previewDiv .removeImg:HOVER{
  			color: black;
  		}
  		.previewDiv img{
  			width: 100%;
  			height: 100%;
  		}
  	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">上传水质报告</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	水质报告
	        <small>
	            Presentation
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">上传水质报告&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="addCompany">
                    
                    	<div class="form-group">
                            <label>选择项目</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="project">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                    
                        <div class="form-group">
                            <label>选择设备</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="device">
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        
                        <div class="form-group">
                        	<input type="button" class="btn btn-info" value="预览图片" id="preview">
                        </div>
                         
                         <div class="form-group">
                            <label>上传报告图片</label>
                            <div>
							  	<input id="iconUpload" name="res" type="file" class="file-loading" >
							  	<input type="hidden" id="uploadUrl">
							</div>
                        </div> 
                         
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">提交</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<!-- 引用公用js -->
	<%@include file = "../resource_js.jsp" %>	
    <script>
    	//全局变量
   		var map = new Map();
    	$(function(){
    		//initPortrait("iconUpload",index,getRootPath()+"/"+getRootPath()+"/res/upload");
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
			
			//载入项目
			$.ajax({
    			type:"POST",
				data:{},
				url:getRootPath()+"/web/project/findProject",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.code==200){
						var str = "";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option value='"+data.data[i].id+"'>"+data.data[i].projectName+"</option>";
						}
						$("#project").append(str);
					}
				}
    		});
			
			//载入分区
			$("#project").bind("change",function(){
				$.ajax({
	    			type:"POST",
					data:{projectId:$("#project").val()},
					url:getRootPath()+"/web/device/findDevice",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							var str = "<option value='0' selected>--请选择--</option>";
							for (var i = 0; i < data.data.length; i++) {
								str += "<option value='"+data.data[i].deviceBean.id+"'>"+data.data[i].deviceBean.deviceName+"</option>";
							}
							$("#device").html(str);
						}
					}
	    		});
			});
			
			$("#preview").bind("click",function(){
				console.log($("#device").val());
				if($("#device").val()!=0){
					$.ajax({
		    			type:"POST",
						data:{id:$("#device").val()},
						url:getRootPath()+"/web/device/findDevice",
						dataType:"json",
						success:function(data){
							console.log(data);
							if(data.code==200){
								if(data.data[0].deviceBean.waterImg==null||data.data[0].deviceBean.waterImg==""){
									Notify("该设备暂无上传报告图片", 'top-right', '5000', 'danger', 'fa-desktop', true);
									return;
								}else{
									
									//将原来已经上传的放在隐藏框里
									$("#uploadUrl").val(data.data[0].deviceBean.waterImg);
									
									var msg="<div class='col-xs-12' style='padding:15px 0;'>";
									var img = data.data[0].deviceBean.waterImg.split(",");
									console.log(img);
									for (var i = 0; i < img.length-1; i++) {
										msg += "<div class='previewDiv'><i class='glyphicon glyphicon-remove removeImg' onclick='removeImg(this,\"" +img[i]+ "\");'></i>"+
											"<img src='" +getRootPath()+"/"+ img[i] + "' class='file-preview-image' alt='报告图片' title='报告图片'>"+
											"</div>";
									}
									msg+="</div>";
									bootbox.dialog({
									    message: msg,
									    title: "提示框",
									    buttons: {
									    	"关闭": {className: "btn-default",callback: function () {}},
									    }
									});
								}
							}
						}
		    		});
				}else{
					Notify("请先选择设备预览", 'top-right', '5000', 'danger', 'fa-desktop', true);
					return;
				}
			});
    		
    	});
    	
    	function removeImg(obj,img){
    		var imgArray = $("#uploadUrl").val().split(",");
    		var temp = "";
    		for (var i = 0; i < imgArray.length-1; i++) {
				if(imgArray[i]===img){
					imgArray.splice(i,1);//从下标为i的元素开始，连续删除1个元素
			        i--;//因为删除下标为i的元素后，该位置又被新的元素所占据，所以要重新检测该位置
					$(obj).parent().hide("slow");
				}else{
					temp += imgArray[i]+",";
				}
			}
    		$("#uploadUrl").val(imgArray);
    		
    	}
    	
    	function submit(){
    		
    		var array = map.keySet();
    		var temp ="";
    		for(i in array){
    			temp += map.get(array[i])+",";
    		}
    		
    		temp+=$("#uploadUrl").val();
    		$("#uploadUrl").val(temp);
    		if($("#project").val()==0||$("#project").val()==""){
    			Notify("请选择项目", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
    		else if($("#device").val()==0||$("#device").val()==""){
    			Notify("请选择设备", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
    		else if($("#uploadUrl").val()==""||$("#uploadUrl").val()==null){
    			Notify("请上传报告图", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
    		else{ 
    			$.ajax({
					type:"POST",
					data:{id:$("#device").val(),waterImg:$("#uploadUrl").val()},
					url:getRootPath()+"/web/device/addOrUpdDevice",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							bootbox.dialog({
							    message: "<p style='padding-top:10px;'>提交"+data.msg+"</p>",
							    title: "提示框",
							    closeButton:false,
							    buttons: {
							    	"关闭": {
							            className: "btn-default",
							            callback: function () {
							            	//window.location.href=getRootPath()+"/admin/device/list.do";
							            }
							      	},
							        success: {
							            label: "确认",
							            className: "btn-danger",
							            callback: function () {
							            	window.location.href=getRootPath()+"/admin/device/list.do";
							            }
							        }
							    }
							});
						}else{
							
						}
					}
				});
    		}
    	}
    </script>
</body>
</html>
