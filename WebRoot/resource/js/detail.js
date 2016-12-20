var options = {
				 scrollY: true, //是否竖向滚动
				 scrollX: false, //是否横向滚动
				 startX: 0, //初始化时滚动至x
				 startY: 0, //初始化时滚动至y
				 indicators: false, //是否显示滚动条
				 deceleration:0.0005, //阻尼系数,系数越小滑动越灵敏
				 bounce: true //是否启用回弹
			};
mui('.mui-scroll-wrapper').scroll(options);

/*获得地址后的id号*/
function getUrlValue(key){
    var url = window.location.search; 
    var theRequest = new Object();
   		if (url.indexOf("?") != -1) {
        	var str = url.substr(1); 
        	strs = str.split("&");
        	for(var i = 0; i < strs.length; i++) {
           		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            	if( strs[i].split("=")[0] == key ) {
                	return strs[i].split("=")[1];
            	}
       		}
   		}
   	return theRequest;
}
var addressId = getUrlValue("id");

/*请求数据*/
function getData(){
	$.ajax({
		type:"get",
		url:ipAddress+"/SanghaCloud/news/getNewsList?",
		async:true,
		data:{id:addressId},
		dataType:"json",
		success:function(json){
			$(".top-img").html("<img src='"+json.data.imageUrl+"'/>");
			$(".title").html(json.data.title);
			$(".introduction .time").html(json.data.createTime);
			$(".introduction .clicklike").html('点赞'+json.data.likes);
			$(".introduction .read").html('阅读'+json.data.reads);
			$(".mui-scroll .content").html(json.data.content);
		}
	});
}
getData();
