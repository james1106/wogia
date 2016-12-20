<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!--Basic Scripts-->
<script src="<%=request.getContextPath()%>/resource/assets/js/jquery-2.0.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/assets/js/bootstrap.min.js"></script>

<!--Beyond Scripts-->
<script src="<%=request.getContextPath()%>/resource/assets/js/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/assets/js/toastr/toastr.js"></script>

<!--Fuelux Spinner-->
<script src="<%=request.getContextPath()%>/resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

<script src="<%=request.getContextPath()%>/resource/fileupload/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resource/fileupload/js/fileinput_locale_zh.js" type="text/javascript"></script>
<!--Common Scripts -->
<script src="<%=request.getContextPath()%>/resource/js/common.js"></script>
<%-- <script src="<%=request.getContextPath()%>/resource/js/jqPaginator.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jqPage.js"></script> --%>
<script src="<%=request.getContextPath()%>/resource/assets/js/bootbox/bootbox.js"></script>

<script src="<%=request.getContextPath()%>/resource/laydate/laydate.js"></script>

<script src="<%=request.getContextPath()%>/resource/assets/js/skins.min.js"></script>

<!-- 加密js -->
<script src="<%=request.getContextPath()%>/resource/assets/js/jQuery.md5.js"></script>

<!-- layer -->
<script src="<%=request.getContextPath()%>/resource/layer/layer.js"></script>

<!-- 引用分页 -->
<script src="<%=request.getContextPath()%>/resource/jquery.pagination/jquery.pagination.js"></script>

<!-- 引用angularJs -->
<script src="<%=request.getContextPath()%>/resource/angularJs/angular.min.js"></script>

<!-- 引用my97 -->
<script src="<%=request.getContextPath()%>/resource/My97DatePicker/WdatePicker.js"></script>


<script>
	function getRootPath(){
		//代表访问项目的根目录
		return '<%=request.getContextPath()%>';
	}
	
	//获取url传递的参数
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if (r != null) return unescape(r[2]); return null; //返回参数值
	}
	
	//时间戳转换 return date为年月日
	function timeStampConversion(timestamp){
		var d = new Date(timestamp);    //根据时间戳生成的时间对象
		var date = (d.getFullYear()) + "-" +
		           (d.getMonth() + 1) + "-" +
		           (d.getDate())+ " " + 
		           (d.getHours()) + ":" + 
		           (d.getMinutes()) + ":" + 
		           (d.getSeconds());
		return date;
	}
	
	var userId = "${user.id}";
	if(userId==""||userId==0||userId==null){
		bootbox.dialog({
		    message: "<p style='padding-top:10px;'>您还未登录，跳转登录页面</p>",
		    title: "提示框",
		    closeButton:false,
		    buttons: {
		        success: {
		            label: "确认",
		            className: "btn-danger",
		            callback: function () {
		            	window.location.href=getRootPath()+"/login.jsp";
		            }
		        }
		    }
		});
	}
	
	//封装Map
	function Map(){
  		this.container = new Object();
  		}


  		Map.prototype.put = function(key, value){
  		this.container[key] = value;
  		}


  		Map.prototype.get = function(key){
  		return this.container[key];
  		}


  		Map.prototype.keySet = function() {
  		var keyset = new Array();
  		var count = 0;
  		for (var key in this.container) {
  		// 跳过object的extend函数
  		if (key == 'extend') {
  		continue;
  		}
  		keyset[count] = key;
  		count++;
  		}
  		return keyset;
  		}


  		Map.prototype.size = function() {
  		var count = 0;
  		for (var key in this.container) {
  		// 跳过object的extend函数
  		if (key == 'extend'){
  		continue;
  		}
  		count++;
  		}
  		return count;
  		}


  		Map.prototype.remove = function(key) {
  		delete this.container[key];
  		}


  		Map.prototype.toString = function(){
  		var str = "";
  		for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
  		str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
  		}
  		return str;
  		}
</script>
    
