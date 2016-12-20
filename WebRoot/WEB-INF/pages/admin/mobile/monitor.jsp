<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    	<meta charset="utf-8">
		<title>流量监控</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="<%=request.getContextPath()%>/resource/assets/css/bootstrap.min.css" rel="stylesheet" />
		<style>
			body{font-family: '微软雅黑';}
			.monitor-div{padding-top: 15px;}
			.monitor-font{font-size: 13px;font-weight: bold;color: #666;text-align: left;padding: 15px 0 15px 30px;}
			.title{padding: 15px;padding-bottom:0px;text-align: center;font-size: 13px;color: #666;}
			.shade{width:100%;height:100%;position: fixed;z-index:200;background: rgba(255,255,255,0.8);}
			.loading{position: fixed;width: 200px;height: 200px;z-index: 201;text-align: center;}
		</style>
  </head>	
  
  <body>
  		<div class="shade">
  			
  		</div>
  		<div class="loading">
  			<img src="<%=request.getContextPath()%>/resource/img/loading-min.gif">
  		</div>
  		<div class="container">
  			<!-- 流量监控 -->
  			<div class="row" style="display: none;">
  				<div class="col-xs-12 monitor-div">
 					<div id="flow" style="width: 100%;height: 300px;border: 1px solid #50C0E8;"></div>
 					<div class="col-xs-12" style="background: #A8E0F4;border: 1px solid #50C0E8;border-top: none;">
	  					<div class="row">
			  				<div class="col-xs-12 monitor-font" style="text-align: center;">
			  					总用水：<span id="currentFlow"></span><span style="padding-left: 5px">T</span>
			  				</div>
			  			</div>
	  				</div>
	  				<div class="row">
	  					<div class="col-xs-12 title">
	  						流量曲线
	  					</div>
	  				</div>
  				</div>
  			</div>
  			<!-- 水压监控 -->
  			<div class="row">
  				<div class="col-xs-12 monitor-div">
  					<div id="water" style="width: 100%;height: 300px;border: 1px solid #50C0E8;"></div>
  					<div class="col-xs-12" style="background: #A8E0F4;border: 1px solid #50C0E8;border-top: none;">
	  					<div class="row">
			  				<div class="col-xs-6 monitor-font">
			  					当前压力：<span style="color:#50C0E8;" id="currentPressure"></span>
			  				</div>
			  				<div class="col-xs-6 monitor-font">
			  					设定压力：<span id="setUpPressure"></span>
			  				</div>
			  			</div>
	  				</div>
	  				<div class="row">
	  					<div class="col-xs-12 title">
	  						压力曲线
	  					</div>
	  				</div>
  				</div>
  			</div>
  			<!-- 能耗监控--水耗电量 -->
  			<div class="row">
  				<div class="col-xs-12 monitor-div" style="padding-bottom: 10px;">
  					<div id="energyConsumption" style="width: 100%;height: 300px;border: 1px solid #DDA490;"></div>
  					<div class="col-xs-12" style="background: #A4E7D8;border: 1px solid #DDA490;border-top: none;">
	  					<div class="row">
			  				<div class="col-xs-6 monitor-font">
			  					累计用电：<span id="currentElectricCurrent"></span><span style="padding-left: 5px">KWh</span>
			  				</div>
			  				<div class="col-xs-6 monitor-font">
			  					累计用水：<span id="totalElectricCurrent"></span><span style="padding-left: 5px">T</span>
			  				</div>
			  			</div>
	  				</div>
	  				<div class="row">
	  					<div class="col-xs-12 title" style="padding-bottom: 10px;">
	  						能耗曲线
	  					</div>
	  				</div>
  				</div>
  			</div>
  		</div>
  		
  		<script src="<%=request.getContextPath()%>/resource/assets/js/jquery-2.0.3.min.js"></script>
		<!-- 引用echarts.js -->
		<script src="<%=request.getContextPath()%>/resource/js/echarts/echarts.min.js"></script>
		<script>
			$(function(){
				var _top=($(window).height()-$(".loading").height())/2;
				var _left=($(window).width()-$(".loading").width())/2;
				$(".loading").css({top:_top,left:_left});
			});
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
				var date = /* (d.getFullYear()) + "年" +  */
				           (d.getMonth() + 1) + "月" +
				           (d.getDate())+"日";/* + " " + 
				           (d.getHours()) + ":" + 
				           (d.getMinutes()) + ":" + 
				           (d.getSeconds()); */
				return date;
			}
			
			//图表刻度
			var max = 0;
			var max2 = 0;
			
			//获取参数
			var deviceName = "${deviceName}";
			//临时用
			//deviceName = "WOGIA_0007|device|a";
			if(deviceName==null||deviceName==""){
				
			}else{
				//取参数值最后一位
				var str = deviceName.substring(deviceName.length-1,deviceName.length);
				//声明存放日期的数组
				var arrayDate=new Array();
				//声明存放流量值得数组
				var arrayNumber = new Array();
				//设定压力
				var setUp = 0;
				//存放出水压力的数组
				var arrayHydraulicPressure = new Array();
				//存放水耗电量的数组
				var arrayConsumePower = new  Array();
				
				//默认加载监控信息
				$.ajax({
					type:"POST",
					data:{deviceName:deviceName},
					url:getRootPath()+"/app/monitorData/monitorChart",
					dataType:"json",
					success:function(data){
						console.log(data);
						//拼接监控数据
						for(var i = 0;i < data.data.length; i++){
							//存入时间
							arrayDate[i] = timeStampConversion(data.data[i].time);
							$.each(data.data[i],function(name,value) {
								//获取流量监控数据
								if(name==str+"w6"){
									//console.log(i+"----"+name+"----"+value);
									if(value==null){
										arrayNumber[i] = 0;
									}else{
										arrayNumber[i] = value;
									}
								}
								//第一条默认为当前
								if(i==0){
									//显示当前流量
									if(name==str+"w5"){
										$("#currentFlow").html(value);
									}
									//取设定压力
									if(name==str+"w2"){
										if(value!=null){
											setUp = value;
											$("#setUpPressure").html(setUp);
										}
									}
									//取当前压力
									if(name==str+"w3"){
										if(value==null){
											$("#currentPressure").html("--");
										}else{
											$("#currentPressure").html(value);
										}
									}
									//取当前电流
									if(name==str+"w7"){
										if(value==null){
											$("#currentElectricCurrent").html("--");
										}else{
											$("#currentElectricCurrent").html(value);
										}
									}
									//总用水（就是累计流量）
									if(name==str+"w6"){
										if(value==null){
											$("#totalElectricCurrent").html("--");
										}else{
											$("#totalElectricCurrent").html(value);
										}
									}
								}
								//获取出水压力监控数据
								if(name==str+"w3"){
									if(value==null){
										arrayHydraulicPressure[i] = 0;
									}else{
										arrayHydraulicPressure[i] = value;
										
										if(max < arrayHydraulicPressure[i]){
											max = arrayHydraulicPressure[i];
										}
									}
								}
								//获取水耗电量监控数据
								if(name==str+"w9"){
									if(value==null){
										arrayConsumePower[i] = 0;
									}else{
										arrayConsumePower[i] = value;
									}
								}
								
								
							});
						}
						//流量
						flowInit(arrayDate,arrayNumber);
						//出水压力
						hydraulicPressureInit(arrayDate,arrayHydraulicPressure,max);
						//水耗电量
						energyConsumption(arrayDate,arrayConsumePower);
					}
				});
			}
			
			
			//流量监控
			function flowInit(arrayDate,arrayNumber){
				//流量监控
				var flowChart = echarts.init(document.getElementById("flow"));
				//指定图表的配置项和数据
				flowOption = {
					/* backgroundColor: '#50C0E8',//背景色  */
					color: [
						'#69CAEB'
					],
				    //鼠标悬停
				    tooltip : {
				        trigger: 'axis'
				    },
				    grid: {
				    	top: '2%',
				        left: '0%',
				        right: '0%',
				        bottom: '0%',
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : arrayDate
				        }
				    ],
				    yAxis : [
				        {
				        	show : false,
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'流量监控',
				            type:'line',
				            stack: '总量',
				            areaStyle: {normal: {}},
				            data:arrayNumber
				        }
				    ]
				};
				flowChart.setOption(flowOption);
			}
			
			//水压监控
			function hydraulicPressureInit(arrayDate,arrayHydraulicPressure,max){
				$(".shade").fadeOut("slow");
				$(".loading").fadeOut("slow");
				console.log("max:"+max);
				max = max + 20;
				//水压监控
				var waterChart = echarts.init(document.getElementById("water"));
				//指定图表的配置项和数据
				waterOption = {
					color: [
						'#99BBFF', '#50C0E8'
					],
				    tooltip : {
				        trigger: 'axis'
				    },
				    grid: {
				    	top: '0%',
				        left: '0%',
				        right: '0%',
				        bottom: '0%',
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : arrayDate,
				            precision: 0.0001,//小数精度
				            power: 1,//整数精度
				            //splitNumber: 4,//分割段数 默认5
				            scale: true,//脱离0值比例，放大聚焦到最终_min，_max区间
				        }
				    ],
				    yAxis : [
				        {
				        	splitNumber : 0.3, //自定义刻度  
				        	show:false,
				            type : 'value',
				            precision: 0.0001,//小数精度
				            power: 1,
				            scale: true,
				        }
				    ],
				    series : [
				        {
				            name:'设定压力',
				            type:'line',
				            smooth:true,
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:[setUp, setUp, setUp, setUp, setUp, setUp, setUp,setUp,setUp,setUp],
				            //data:[0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3,0.3,0.3,0.3]
				        },
				        {
				            name:'水压监控',
				            type:'line',
				            smooth:true,
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:arrayHydraulicPressure,
				            //data:[0.1, 0.6, 1, 0.3, 0.6, 2, 0.2,0.6,0.8,1],
				        },
				    ],
				    legend: {
				        data : [ '指标值', '预测极限',]
				    },
					/* color: [
						'#DDA490', '#46D0AF'
					], */
				    //鼠标悬停
				    /* tooltip : {
				        trigger: 'axis'
				    },
				    grid: {
				    	top: '0%',
				        left: '0%',
				        right: '0%',
				        bottom: '0%',
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : arrayDate
				        }
				    ],
				    yAxis : [
				        {
				        	show:false,
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'水压监控',
				            type:'line',
				            smooth:true,
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:arrayHydraulicPressure
				        },
				        {
				            name:'设定压力',
				            type:'line',
				            smooth:true,
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:[setUp, setUp, setUp, setUp, setUp, setUp, setUp,setUp,setUp,setUp]
				        },
				    ] */
				};
				waterChart.hideLoading();
				waterChart.setOption(waterOption);
			}
			
			//能耗图表
			function energyConsumption(arrayDate,arrayConsumePower){
				//水压监控
				var energyConsumptionChart = echarts.init(document.getElementById("energyConsumption"));
				//指定图表的配置项和数据
				energyConsumptionOption = {
					color: [
							'#DDA490', '#46D0AF'
					],
				    //鼠标悬停
				    tooltip : {
				        trigger: 'axis'
				    },
				    grid: {
				    	top: '0%',
				        left: '0%',
				        right: '0%',
				        bottom: '0%',
				        /* containLabel: true */
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : arrayDate,
				            precision: 0.0001,//小数精度
				            power: 1,
				            scale: true,
				        }
				    ],
				    yAxis : [
				        {
				        	splitNumber : 1, //自定义刻度  
				        	show:false,
				            type : 'value',
				            precision: 0.0001,//小数精度
				            power: 1,
				            scale: true,
				        }
				    ],
				    series : [
				        {
				            name:'平均吨水耗电量',
				            type:'line',
				            stack: '总量',
				            areaStyle: {normal: {}},
				            data:arrayConsumePower
				        },
				    ]
				};
				energyConsumptionChart.hideLoading();
				energyConsumptionChart.setOption(energyConsumptionOption);
			}
		</script>
  </body>
</html>
