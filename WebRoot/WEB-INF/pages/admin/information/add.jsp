<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<%@include file="../resource_css.jsp" %>
	
	<style>
		.select{
			width:100px;
			max-width: 100% !important;
			min-width: 100px !important;
			float: left;
			margin-right: 15px;
		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加资讯</li>
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
                <span class="widget-caption">添加资讯&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="informationForm" id="informationForm">
                    
                        <div class="form-group">
                            <label>新闻标题</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="title" id="title" type="text" placeholder="请输入标题">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>内容简介</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="introduction" id="introduction" type="text" placeholder="请输入内容简介">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>资讯图片</label>
                            <div>
							  	<input id="iconUpload" type="file" class="file-loading" >
							  	<input type="hidden" id="uploadUrl" name="imageUrl">
							</div>
                        </div>
                        
                        <div class="form-group">
                        	<label>Banner置顶</label>
                        	<span class="input-icon icon-right" id="isBanner">
                        		<input type="radio" name="isBanner" value="0" checked="checked"/>否
                        		<input type="radio" name="isBanner" value="1"/>是
                        	</span>
                        </div>
                        
                        <!-- <div class="form-group">
                        	<label>新闻是否有效</label>
                        	<span class="input-icon icon-right" id="isValid">
                        		<input type="radio" name="isValid" value="0"/>否
                        		<input type="radio" name="isValid" value="1" checked="checked"/>是
                        	</span>
                        </div> -->
                        
                        <div class="form-group">
                        	<label>跳转第三方链接</label>
                        	<span class="input-icon icon-right" id="isUrl">
                        		<input type="radio" name="isUrl" value="0" checked="checked" id="nonlink"/>否
                        		<input type="radio" name="isUrl" value="1" id="link"/>是
                        	</span>
                        	<span class="input-icon icon-right">
                        		<input class="form-control spinner-input" type="url" style="width: 100%;display: none" name="linkUrl" placeholder="请输入第三方链接地址" id="linkUrl" >
                        	</span>
                        </div>
                        
                        <div class="form-group" id="readsDiv">
                        	<label>设置阅读数</label>
                        	<span class="input-icon icon-right">
                        		<input type="number" id="reads" name="reads" value="0" placeholder="默认为初始值" style="width: 100%"/>
                        	</span>
                        </div>
                        
                        <div class="form-group sel" id="contentDiv">
                            <label>公告内容</label>
                         	<div id="editor" name="content" style="width:100%;height:500px;"></div>
                        </div>
                         
                         <input type="hidden" name="userId" id="userId" value="${user.id}">
                         <input type="hidden" name="id" id="infoId">
                        <a href="javascript:void(0);" target="iframe" id="submit" class="btn btn-info" onclick="submit()">添加资讯</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  	<%@include file="../ueditor.jsp" %>
  	<%@include file="../resource_js.jsp" %>
  	<script>
  	
  		var map = new Map();
  		
  		//实例化编辑器
  		var editor = UE.getEditor("editor");	
  	
	  	//获取参数
		var infoId = getUrlParam("infoId");
	  	
	  	var boolUrl = false;//作为判断
	  	
		$(function(){
			
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
			
			$("#nonlink").bind("click",function(){
				$("#readsDiv").show("slow");
				$("#contentDiv").show("slow");
				$("#linkUrl").hide("slow");
				boolUrl = false;
			});
			
			$("#link").bind("click",function(){
				$("#readsDiv").hide("slow");
				$("#contentDiv").hide("slow");
				$("#linkUrl").show("slow");
				boolUrl = true;
			});
			
			//判断是不是修改进来
			if(infoId!=null){
				$("#infoId").val(infoId);
				$("#submit").val("修改资讯");
				$.ajax({
	    			type:"POST",
					data:{id:infoId},
					url:getRootPath()+"/web/news/findNewsById",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							$("#title").val(data.data.title);
							$("#introduction").val(data.data.introduction);
							
							//重要，需要更新控件的附加参数内容，以及图片初始化显示
				            $("#iconUpload").fileinput('refresh', {
				                initialPreview:["<img src='" +getRootPath()+"/"+ data.data.imageUrl + "' class='file-preview-image' alt='资讯图片' title='资讯图片'>"],
				            });
				            $("#uploadUrl").val(data.data.imageUrl);
				            
				            $("#isBanner input[name='isBanner']:eq("+data.data.isBanner+")").attr("checked","checked");
				            //$("#isValid input[name='isValid']:eq("+data.data.isValid+")").attr("checked","checked");
				            
				            
				            if(data.data.isUrl==0){
				            	$("#isUrl input[name='isUrl']:eq("+data.data.isUrl+")").attr("checked","checked");
				            	$("#readsDiv").show("slow");
								$("#contentDiv").show("slow");
								$("#linkUrl").hide("slow");
								
								$("#reads").val(data.data.reads);
								editor.ready( function () {
									editor.setContent(data.data.content);
								});
								
								boolUrl = false;
				            }else{
				            	$("#isUrl input[name='isUrl']:eq("+data.data.isUrl+")").attr("checked","checked");
				            	$("#readsDiv").hide("slow");
								$("#contentDiv").hide("slow");
								$("#linkUrl").show("slow");
								
								$("#linkUrl").val(data.data.linkUrl);
								boolUrl = true;
				            }
							
						}else{
							Notify("获取失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
						}
					}
	    		});
			}
		});
		
		function submit(){
			
			var array = map.keySet();
			if(array.length>1){
				Notify("资讯图片一次只能上传一张", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return false;
			}
    		var temp ="";
    		for(i in array){
    			temp += map.get(array[i]);
    		}
    		if(temp!=""){
    			$("#uploadUrl").val(temp);
    		}
			var content = UE.getEditor("editor").getContent();
			
			if($("#title").val()==null||$("#title").val()==""){
				Notify("请输入标题",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#introduction").val()==null||$("#introduction").val()==""){
				Notify("请输入内容简介",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(!boolUrl){
				if(content==null||content==""){
					Notify("请输入内容",'top-right','5000','danger','fa-desktop',true);
					return;
				}else{
					addOrUpdate();
				}
			}
			else if(boolUrl){
				if($("#linkUrl").val()==null||$("#linkUrl").val()==""){
					Notify("请输入第三方连接地址",'top-right','5000','danger','fa-desktop',true);
					return;
				}else{
					addOrUpdate();
				}
			}
		}
		
		function addOrUpdate(){
			$.ajax({
				type : "POST",
				url : getRootPath()+"/web/news/addOrUpdNews",
				data : $("#informationForm").serialize(),
				dataType :"json",
				success : function(data){
					if(data.code == 200){
						window.location.href = getRootPath()+"/admin/information/list.do";
					}else{
						Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
					}
				}
			});
		}
		
  	</script>
  </body>
</html>
