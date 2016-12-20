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
            <li class="active">发布系统公告</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	系统公告
	        <small>
	            SystemInfo
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">发布系统公告&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="systemInfo" id="systemInfo">
                    
                        <div class="form-group">
                            <label>公告标题</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="title" id="title" type="text" placeholder="请输入标题(必填)">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label>摘要</label>
                         	<textarea class="form-control" rows="3" cols="50" style="resize: none;" id="brief" name="brief" placeholder="请输入摘要"></textarea>
                        </div>
                        
                        <div class="form-group sel" id="waterNumberDiv">
                            <label>公告内容</label>
                         	<div id="editor" name="content" style="width:100%;height:500px;"></div>
                        </div>
                        
                        <div class="form-group">
                            <label>公告类型</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="type" name="type">
                            		<option value='0' selected="selected">系统公告</option>
                            		<option value='1'>停水公告</option>
                            	</select>
                            </span>
                        </div>
                         
                         <input type="hidden" name="publisherId" id="publisherId" value="${user.id}">
                         <input type="hidden" name="id" id="noticeId">
                        <a href="javascript:void(0);" target="iframe" id="submit" class="btn btn-info" onclick="submit()">发布公告</a>
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
  	
	  	//获取参数
		var noticeId = getUrlParam("noticeId");
	  	
		$(function(){
			
			//判断是不是修改进来
			if(noticeId!=null){
				$("#noticeId").val(noticeId);
				$("#submit").html("修改发布公告");
				$.ajax({
	    			type:"POST",
					data:{systemId:noticeId},
					url:getRootPath()+"/web/info/getMsgById",
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.code==200){
							$("#title").val(data.data.title);
							$("#brief").val(data.data.brief);
							editor.ready( function () {
								editor.setContent(data.data.content);
							});
							$("#type option").each(function(){
								if($(this).val()==data.data.type){
									$(this).attr("selected","selected");
								}
							});
						}else{
							Notify("获取失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
						}
					}
	    		});
			}
		});
		
		function submit(){
			var content = UE.getEditor("editor").getContent();
			if($("#title").val()==null||$("#title").val()==""){
				Notify("请输入标题",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if($("#brief").val()==null||$("#brief").val()==""){
				Notify("请输入摘要",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else if(content==null||content==""){
				Notify("请输入内容",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			else{
				$.ajax({
					type : "POST",
					url : getRootPath()+"/web/info/sendNotice",
					data : $("#systemInfo").serialize(),
					dataType :"json",
					success : function(data){
						if(data.code == 200){
							window.location.href = getRootPath()+"/admin/notice/list.do";
						}else{
							Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
						}
					}
				});
			}
		}
  	</script>
  </body>
</html>
