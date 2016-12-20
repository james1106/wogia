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
		.fileinput-upload-button{
			/* display: none; */
		}
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
  		.preview-img{
  			display: none;
  		}
	</style>
  </head>
  <body>
  
  	<div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">水泵信息</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	水泵
	        <small>
	            WaterPump
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加水泵信息&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" name="waterPumpForm" id="waterPumpForm">
                    
                        <div class="form-group sel">
                            <label>选择项目负责人</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="projectLiablePerson" name="projectLiablePersonId" required></select>
                            </span>
                        </div>
                        
                        <div class="form-group sel">
                            <label>项目负责人电话</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="liablePersonMobile" id="liablePersonMobile" type="text" placeholder="项目负责人电话" required>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel">
                            <label>监管单位</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="superviseUnit" name="superviseUnitId" required></select>
                            </span>
                        </div>
                        
                        <div class="form-group sel">
                            <label>单位地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" name="superviseAddress" id="superviseAddress" type="text" placeholder="请输入单位地址" required>
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group sel">
                            <label>监管单位管理人</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="administrator" name="administratorId" required>
                            		<option value='0' selected>--请选择--</option>
                            	</select>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>监管单位管理人电话</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="administratorMobile" id="administratorMobile" type="text" placeholder="监管单位管理人">
                                </div>
                            </span>
                        </div>
                    
                    	<div class="form-group sel">
                            <label>建筑面积（㎡）</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="area" id="area" type="text" placeholder="请输入建筑面积 &nbsp;单位：m²"onkeyup="value=value.replace(/[^\d.]/g,'')">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>总栋数</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="totalBuild" id="totalBuild" type="text" placeholder="请输入总栋数" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>住户数量</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="householdNum" id="householdNum" type="text" placeholder="请输入住户数量" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>二次供水户数</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="twoWaterHouseholdNum" id="twoWaterHouseholdNum" type="text" placeholder="请输入二次供水户数" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>泵房位置</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="pumpHouseAddress" id="pumpHouseAddress" type="text" placeholder="请输入泵房位置">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>二次供水源</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="twoWaterSource" required name="twoWaterSourceId"></select>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>二次供水方式</label>
                            <span class="input-icon icon-right">
                            	<select class="form-control" id="twoWaterMode" required name="twoWaterModeId"></select>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>生产商</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="manufacturer" id="manufacturer" type="text" placeholder="请输入生产商">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>供应商</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="supplier" id="supplier" type="text" placeholder="请输入供应商">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>生产日期</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input type="text" class="form-control spinner-input" required readonly="readonly" name="manufactureDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="manufactureDate" placeholder="生产日期"/>
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>进水管网最低压力</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="inletPipeLowestPressure" id="inletPipeLowestPressure" type="text" placeholder="请输入进水管网最低压力">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>进水管网最高压力</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="inletPipePeakPressure" id="inletPipePeakPressure" type="text" placeholder="请输入进水管网最高压力">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>市政小区介入点管径</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="municipalDistrictCaliber" id="municipalDistrictCaliber" type="text" placeholder="请输入市政小区入点管径">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group preview-img" id="previewWaterSysImgsDiv">
                        	<label>预览给水系统图</label>
                        	<div>
                        		<input type="button" class="btn btn-info" value="预览图片" id="previewWaterSysImgs">
                        	</div>
                        </div>
                        <div class="form-group preview-img" id="isWaterSysImgsDiv">
                        	<label>是否修改给水系统图</label>
                        	<span class="input-icon icon-right" id="isWaterSysImgs">
                        		<input type="radio" name="isWaterSysImgs" value="0" checked="checked" id="noIsWaterSysImgs"/>否
                        		<input type="radio" name="isWaterSysImgs" value="1" id="yesIsWaterSysImgs"/>是
                        	</span>
                        </div>
                        <div class="form-group" id="waterSysImgsDiv">
                            <label>给水系统图</label>
                            <div>
							  	<input id="waterSysImgs" type="file" class="file-loading" required>
							</div>
                        </div>
                        
                        <div class="form-group sel">
                            <label>开始楼层</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="startFloor" id="startFloor" type="text" placeholder="请输入开始楼层" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>结束楼层</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="endFloor" id="endFloor" type="text" placeholder="请输入结束楼层" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>供水能力</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="waterAbility" id="waterAbility" type="text" placeholder="请输入供水能力">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>供水压力</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="waterPressure" id="waterPressure" type="text" placeholder="请输入供水压力">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>主设备型号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="mainDeviceModel" id="mainDeviceModel" type="text" placeholder="请输入主设备型号">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>辅设备型号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="assistDeviceModel" id="assistDeviceModel" type="text" placeholder="请输入辅设备型号">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>水泵品牌</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="pumpBrand" id="pumpBrand" type="text" placeholder="请输入水泵品牌">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>扬程（m）</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="lift" id="lift" type="text" placeholder="请输入扬程&nbsp;单位：m">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>流量（m³/h）</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="flow" id="flow" type="text" placeholder="请输入流量&nbsp;单位：m³/h">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>主泵电机</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="mainPumpMotor" id="mainPumpMotor" type="text" placeholder="请输入主泵电机">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>辅泵电机</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="assistPumpMotor" id="assistPumpMotor" type="text" placeholder="请输入辅泵电机">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>台数</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="platformNum" id="platformNum" type="text" placeholder="请输入台数" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>控制柜型号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="controlCabinetModel" id="controlCabinetModel" type="text" placeholder="请输入控制柜型号">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group preview-img" id="previewCabinetCircuitImgsDiv">
                        	<label>预览控制柜及电路图配置</label>
                        	<div>
                        		<input type="button" class="btn btn-info" value="预览图片" id="previewCabinetCircuitImgs">
                        	</div>
                        </div>
                        <div class="form-group preview-img" id="isCabinetCircuitImgsDiv">
                        	<label>是否修改控制柜及电路图配置</label>
                        	<span class="input-icon icon-right" id="isCabinetCircuitImgs">
                        		<input type="radio" name="isCabinetCircuitImgs" value="0" checked="checked" id="noIsCabinetCircuitImgs"/>否
                        		<input type="radio" name="isCabinetCircuitImgs" value="1" id="yesIsCabinetCircuitImgs"/>是
                        	</span>
                        </div>
                        <div class="form-group" id="cabinetCircuitImgsDiv">
                            <label>控制柜及电路图配置</label>
                            <div>
							  	<input id="cabinetCircuitImgs" type="file" class="file-loading" required>
							</div>
                        </div>
                        
                        <div class="form-group sel">
                            <label>稳压罐型号</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="surgePumpModel" id="surgePumpModel" type="text" placeholder="请输入稳压罐型号">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>稳压罐压力等级</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="surgePumpPressureLevel" id="surgePumpPressureLevel" type="text" placeholder="请输入稳压罐压力等级">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>进水主管口径尺寸</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="inletMainNozzleCaliber" id="inletMainNozzleCaliber" type="text" placeholder="请输入进水主管口径尺寸">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>出水主管口径尺寸</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="effluentMainNozzleCaliber" id="effluentMainNozzleCaliber" type="text" placeholder="请输入出水管口径尺寸">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>进水支管口径尺寸</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="inletAssistNozzleCaliber" id="inletAssistNozzleCaliber" type="text" placeholder="请输入进水支管口径尺寸">
                                </div>
                            </span>
                        </div>
                        <div class="form-group sel">
                            <label>出水支管口径尺寸</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="effluentAssistNozzleCaliber" id="effluentAssistNozzleCaliber" type="text" placeholder="请输入出水支管口径尺寸">
                                </div>
                            </span>
                        </div>
						<div class="form-group sel">
                            <label>进出口主管阀门类别</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control spinner-input" required name="importMainTubeValveType" id="importMainTubeValveType" type="text" placeholder="请输入进出口主管阀门类别">
                                </div>
                            </span>
                        </div>
                        
                        <div class="form-group preview-img" id="previewAssembleImgsDiv">
                        	<label>预览装配图</label>
                        	<div><input type="button" class="btn btn-info" value="预览图片" id="previewAssembleImgs"></div>
                        </div>
                        <div class="form-group preview-img" id="isAssembleImgsDiv">
                        	<label>是否修改装配图</label>
                        	<span class="input-icon icon-right" id="isAssembleImgs">
                        		<input type="radio" name="isAssembleImgs" value="0" checked="checked" id="noIsAssembleImgs"/>否
                        		<input type="radio" name="isAssembleImgs" value="1" id="yesIsAssembleImgs"/>是
                        	</span>
                        </div>
                        <div class="form-group" id="assembleImgsDiv">
                            <label>装配图</label>
                            <div>
							  	<input id="assembleImgs" type="file" class="file-loading" required>
							</div>
                        </div>
                        
                        <div class="form-group sel">
                            <label>设备阀门类别</label>
                            <div class="checkboxDiv" id="deviceValveTypeCheckbox">
                            	<label><input name="type1" type="checkbox" value="蝶阀">蝶阀</label>
                            	<label><input name="type1" type="checkbox" value="球阀">球阀</label>
                            	<label><input name="type1" type="checkbox" value="闸阀">闸阀</label>
                            </div>
                            
                        </div>
                        <div class="form-group sel">
                            <label>止回阀门材质</label>
                            <div class="checkboxDiv" id="nonReturnValveTypeCheckbox">
                            	<label><input name="type2" type="checkbox" value="不锈钢">不锈钢</label>
                            	<label><input name="type2" type="checkbox" value="球阀">碳钢</label>
                            </div>
                        </div>
                        <input type="hidden" id="deviceValveType" name="deviceValveType"/>
                        <input type="hidden" id="nonReturnValveType" name="nonReturnValveType">
                        <input type="hidden" name="waterSysImgs" id="waterSysImgsHidden">
                        <input type="hidden" name="cabinetCircuitImgs" id="cabinetCircuitImgsHidden">
                        <input type="hidden" name="assembleImgs" id="assembleImgsHidden">
                        
                        <!-- 存放关联分区表ID -->
                        <input type="hidden" name="deviceId" id="deviceId">
                        <!-- 后台作为判断 -->
                        <input type="hidden" name="type" id="state">
                        <a href="javascript:void(0);" target="iframe" class="btn btn-info" onclick="submit()">确认</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  	<%@include file="../resource_js.jsp" %>
  	<script>
	  	//获取参数
		var deviceId = getUrlParam("id");
		var state = getUrlParam("state");
		var projectId = getUrlParam("projectId");
		$("#deviceId").val(deviceId);
		$("#state").val(state);
	  	
	  	//临时存放地址
	  	var map1 = new Map();
	  	var map2 = new Map();
	  	var map3 = new Map();
	  	
	  //载入负责人
		$.ajax({
			type:"POST",
			data:{roleId:7,projectId:projectId},
			url:getRootPath()+"/web/user/findContacts",
			dataType:"json",
			async: false,
			success:function(data){
				var str = "<option value='' selected>--请选择--</option>";
				for (var i = 0; i < data.data.length; i++) {
					str += "<option mobile='"+data.data[i].mobile+"' value='"+data.data[i].id+"'>"+data.data[i].realName+"</option>";
				}
				//负责人
				$("#projectLiablePerson").html(str);
			}
		});
	  
		//载入监管单位
		$.ajax({
			type:"POST",
			data:{projectId:projectId},
			url:getRootPath()+"/web/water/findWaterByProId",
			dataType:"json",
			async: false,
			success:function(data){
				var str = "<option value='0' selected>--请选择--</option>";
				for (var i = 0; i < data.data.length; i++) {
					str += "<option address='"+data.data[i].waterworkAddress+"' value='"+data.data[i].id+"'>"+data.data[i].waterworkName+"</option>";
				}
				//填充监管单位
				$("#superviseUnit").html(str);
				//填充二次供水源
				$("#twoWaterSource").html(str);
				//填充二次供水源方式
				$("#twoWaterMode").html(str);
				
			}
		});
	  	
		$(function(){
			
			//选择负责人填充手机号码
			$("#projectLiablePerson").bind("change",function(){
				var mobile = $("#projectLiablePerson").find("option:selected").attr("mobile");
				$("#liablePersonMobile").val(mobile);
			});
			
			//选择监管单位填充单位地址
			$("#superviseUnit").bind("change",function(){
				var address = $("#superviseUnit").find("option:selected").attr("address");
				$("#superviseAddress").val(address);
				//查询当前监管单位人员
				$.ajax({
					type:"POST",
					data:{waterWorkId:$("#superviseUnit").val()},
					url:getRootPath()+"/web/user/findContacts",
					dataType:"json",
					async: false,
					success:function(data){
						var str = "<option value='0' selected>--请选择--</option>";
						for (var i = 0; i < data.data.length; i++) {
							str += "<option mobile='"+data.data[i].mobile+"' value='"+data.data[i].id+"'>"+data.data[i].realName+"</option>";
						}
						//监管管理人
						$("#administrator").html(str);
					}
				});
				
			});
			
			//选择监管管理人填充监管管理人手机号码
			$("#administrator").bind("change",function(){
				var mobile = $("#administrator").find("option:selected").attr("mobile");
				$("#administratorMobile").val(mobile);
			});
			
			uploadImg("waterSysImgs",1);//给水系统图
			uploadImg("cabinetCircuitImgs",2);//控制柜及电路图配置
			uploadImg("assembleImgs",3);//装配图
			
			//判断是不是修改进来
			if(state!=0){
				$(".preview-img").show();
				$("#waterSysImgsDiv").hide();
				$("#cabinetCircuitImgsDiv").hide();
				$("#assembleImgsDiv").hide();
				$.ajax({
	    			type:"POST",
					data:{deviceId:deviceId},
					url:getRootPath()+"/web/deviceInformation/findDeviceInformationByDeviceId",
					dataType:"json",
					async: false,
					success:function(data){
						console.log(data);
						if(data.code==200){
							//负责人
							$("#projectLiablePerson option").each(function(){
								console.log($("#projectLiablePerson option").text()+"----"+data.data.projectLiablePerson);
								if($(this).text()==data.data.projectLiablePerson){
									$(this).attr("selected","selected");
								}
							});
							$("#liablePersonMobile").val(data.data.liablePersonMobile);
							
							//监管单位
							$("#superviseUnit option").each(function(){
								if($(this).text()==data.data.superviseUnit){
									$(this).attr("selected","selected");
								}
							});
							$("#superviseAddress").val(data.data.superviseAddress);
							
							var administratorId = data.data.administratorId;
							//查询当前监管单位人员
							$.ajax({
								type:"POST",
								data:{waterWorkId:data.data.superviseUnitId},
								url:getRootPath()+"/web/user/findContacts",
								dataType:"json",
								async: false,
								success:function(data){
									
									var str = "<option value='0' selected>--请选择--</option>";
									for (var i = 0; i < data.data.length; i++) {
										if(data.data[i].id==administratorId){
											str += "<option selected='selected' mobile='"+data.data[i].mobile+"' value='"+data.data[i].id+"'>"+data.data[i].realName+"</option>";
										}else{
											str += "<option mobile='"+data.data[i].mobile+"' value='"+data.data[i].id+"'>"+data.data[i].realName+"</option>";
										}
									}
									//监管管理人
									$("#administrator").html(str);
								}
							});
							
							$("#administratorMobile").val(data.data.administratorMobile);
							
							$("#area").val(data.data.area);
							$("#totalBuild").val(data.data.totalBuild);
							$("#householdNum").val(data.data.householdNum);
							$("#twoWaterHouseholdNum").val(data.data.twoWaterHouseholdNum);
							$("#pumpHouseAddress").val(data.data.pumpHouseAddress);
							//二次供水源
							$("#twoWaterSource option").each(function(){
								if($(this).text()==data.data.twoWaterSource){
									$(this).attr("selected","selected");
								}
							});
							//二次供水方式
							$("#twoWaterMode option").each(function(){
								if($(this).text()==data.data.twoWaterMode){
									$(this).attr("selected","selected");
								}
							});
							$("#manufacturer").val(data.data.manufacturer);
							$("#supplier").val(data.data.supplier);
							$("#manufactureDate").val(data.data.manufactureDate);
							$("#inletPipeLowestPressure").val(data.data.inletPipeLowestPressure);
							$("#inletPipePeakPressure").val(data.data.inletPipePeakPressure);
							$("#municipalDistrictCaliber").val(data.data.municipalDistrictCaliber);
							
							//给水系统图
							/* var imgs1 = data.data.waterSysImgs.split(",");
							var arr1 = new  Array();
							for (var i = 0; i < imgs1.length-1; i++) {
								arr1[i] = "<img src='" +getRootPath()+"/"+ imgs1[i] + "' class='file-preview-image' alt='给水系统图' title='给水系统图'>";
							}
							//重要，需要更新控件的附加参数内容，以及图片初始化显示
				            $("#waterSysImgs").fileinput('refresh', {
				            	language:'zh',
				            	/* showUpload: false, 
							    showPreview : true,
							    showRemove: false,
							    maxFileSize : 850,  //上传图片的最大限制  50KB
							    allowedFileExtensions: ["jpg", "png"],
							    initialCaption: "请选择图片", 
				                initialPreview:arr1,
				            }); */
							//给存放给水系统图地址的隐藏input赋值
				            $("#waterSysImgsHidden").val(data.data.waterSysImgs);
							
				            $("#startFloor").val(data.data.startFloor);
				            $("#endFloor").val(data.data.endFloor);
							$("#waterAbility").val(data.data.waterAbility);
							$("#waterPressure").val(data.data.waterPressure);
							$("#mainDeviceModel").val(data.data.mainDeviceModel);
							$("#assistDeviceModel").val(data.data.assistDeviceModel);
							$("#pumpBrand").val(data.data.pumpBrand);
							$("#lift").val(data.data.lift);
							$("#flow").val(data.data.flow);
							$("#mainPumpMotor").val(data.data.mainPumpMotor);
							$("#assistPumpMotor").val(data.data.assistPumpMotor);
							$("#platformNum").val(data.data.platformNum);
							$("#controlCabinetModel").val(data.data.controlCabinetModel);
							
				          	//控制柜及电路图配置
							/* var imgs2 = data.data.cabinetCircuitImgs.split(",");
							var arr2 = new  Array();
							for (var i = 0; i < imgs2.length-1; i++) {
								arr2[i] = "<img src='" +getRootPath()+"/"+ imgs2[i] + "' class='file-preview-image' alt='控制柜及电路图配置' title='控制柜及电路图配置'>";
							}
							//重要，需要更新控件的附加参数内容，以及图片初始化显示
				            $("#cabinetCircuitImgs").fileinput('refresh', {
				            	language:'zh',
				            	/* showUpload: false,
							    showPreview : true,
							    showRemove: false,
							    maxFileSize : 850,  //上传图片的最大限制  50KB
							    allowedFileExtensions: ["jpg", "png"],
							    initialCaption: "请选择图片",
				                initialPreview:arr2,
				            }); */
							//给存放给水系统图地址的隐藏input赋值
				            $("#cabinetCircuitImgsHidden").val(data.data.cabinetCircuitImgs);
							
							$("#surgePumpModel").val(data.data.surgePumpModel);
							$("#surgePumpPressureLevel").val(data.data.surgePumpPressureLevel);
							$("#inletMainNozzleCaliber").val(data.data.inletMainNozzleCaliber);
							$("#effluentMainNozzleCaliber").val(data.data.effluentMainNozzleCaliber);
							$("#inletAssistNozzleCaliber").val(data.data.inletAssistNozzleCaliber);
							$("#effluentAssistNozzleCaliber").val(data.data.effluentAssistNozzleCaliber);
							$("#importMainTubeValveType").val(data.data.importMainTubeValveType);
							
							//装配图
							/* var imgs3 = data.data.assembleImgs.split(",");
							var arr3 = new  Array();
							for (var i = 0; i < imgs3.length-1; i++) {
								arr3[i] = "<img src='" +getRootPath()+"/"+ imgs3[i] + "' class='file-preview-image' alt='装配图' title='装配图'>";
							}
							//重要，需要更新控件的附加参数内容，以及图片初始化显示
				            $("#assembleImgs").fileinput('refresh', {
				            	language:'zh',
				            	/* showUpload: false,
							    showPreview : true,
							    showRemove: false,
							    maxFileSize : 850,  //上传图片的最大限制  50KB
							    allowedFileExtensions: ["jpg", "png"],
							    initialCaption: "请选择图片",
				                initialPreview:arr3,
				            }); */
							//给存放给水系统图地址的隐藏input赋值
				            $("#assembleImgsHidden").val(data.data.assembleImgs);
							
							//$("#deviceValveType").val(data.data.deviceValveType);
							//$("#nonReturnValveType").val(data.data.nonReturnValveType);
							var deviceValveTypeList = data.data.deviceValveType.split(",");
							$("input[name='type1']").each(function(){
								for (var i = 0; i < deviceValveTypeList.length; i++) {
									if($(this).val()==deviceValveTypeList[i]){
										$(this).attr("checked",true);
										break;
									}
								}
							});
							
							var nonReturnValveTypeList = data.data.nonReturnValveType.split(",");
							$("input[name='type2']").each(function(){
								for (var i = 0; i < nonReturnValveTypeList.length; i++) {
									if($(this).val()==nonReturnValveTypeList[i]){
										$(this).attr("checked",true);
										break;
									}
								}
							});
						}
					}
	    		});
			}
			/**
				$("#waterSysImgsDiv").hide();
				$("#cabinetCircuitImgsDiv").hide();
				$("#assembleImgsDiv").hide();
			*/
			//预览图片
			$("#previewWaterSysImgs").click(function(){
				previewImg("waterSysImgsHidden");
			});
			$("#previewCabinetCircuitImgs").click(function(){
				previewImg("cabinetCircuitImgsHidden");
			});
			$("#previewAssembleImgs").click(function(){
				previewImg("assembleImgsHidden");
			});
			
			$("#noIsWaterSysImgs").click(function(){
				$("#waterSysImgsDiv").hide("slow");
			});
			$("#yesIsWaterSysImgs").click(function(){
				$("#waterSysImgsDiv").show("slow");
			});
			$("#noIsCabinetCircuitImgs").click(function(){
				$("#cabinetCircuitImgsDiv").hide("slow");
			});
			$("#yesIsCabinetCircuitImgs").click(function(){
				$("#cabinetCircuitImgsDiv").show("slow");
			});
			$("#noIsAssembleImgs").click(function(){
				$("#assembleImgsDiv").hide("slow");
			});
			$("#yesIsAssembleImgs").click(function(){
				$("#assembleImgsDiv").show("slow");
			});
			
		});
		
		function previewImg(idName){
			//alert(idName);
			var msg="<div class='col-xs-12' style='padding:15px 0;'>";
			var img = $("#"+idName).val().split(",");
			for (var i = 0; i < img.length-1; i++) {
				msg += "<div class='previewDiv'><i class='glyphicon glyphicon-remove removeImg' onclick='removeImg(this,\"" +img[i]+ "\",\""+idName+"\");'></i>"+
					"<img src='" +getRootPath()+"/"+ img[i] + "' class='file-preview-image'>"+
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
		//本地移除已上传图片
		function removeImg(obj,img,idName){
    		var imgArray = $("#"+idName).val().split(",");
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
    		$("#"+idName).val(imgArray);
    	}
		
		//抽取上传图片方法
		function uploadImg(imgId,state){
			$("#"+imgId).fileinput({
	    		language:'zh',
			    uploadUrl: getRootPath()+"/res/upload", // 图片上传接口
			    showPreview : true,
			    showRemove: false,
			    maxFileSize : 850,  //上传图片的最大限制  50KB
			    allowedFileExtensions: ["jpg", "png"],
			    initialCaption: "请选择图片",
			});
			$("#"+imgId).on("fileuploaded", function (event, data, previewId, index) {
			    if (null != data) {
			    	if(state==1){
			    		map1.put(data.filenames[0],data.response.data.url);
						//点击删除按钮移出图片
				    	$(".kv-file-remove").click(function(){
				    		var filenames = $(this).closest(".file-thumbnail-footer").children(".file-footer-caption").text();
				    		map1.remove(filenames);
				    	});
					}
					else if(state==2){
						map2.put(data.filenames[0],data.response.data.url);
						//点击删除按钮移出图片
				    	$(".kv-file-remove").click(function(){
				    		var filenames = $(this).closest(".file-thumbnail-footer").children(".file-footer-caption").text();
				    		map2.remove(filenames);
				    	});
					}
					else if(state==3){
						map3.put(data.filenames[0],data.response.data.url);
						//点击删除按钮移出图片
				    	$(".kv-file-remove").click(function(){
				    		var filenames = $(this).closest(".file-thumbnail-footer").children(".file-footer-caption").text();
				    		map3.remove(filenames);
				    	});
					}
		        	
			    } else {
			    	 Notify("上传失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
			    }
			});
		}
		
		function submit(){
			
			//整数正则
			var reg=/^[-+]?\d*$/;
			//小数正则
			//var regTow=/^[-\+]?\d+(\.\d+)?$/; 
			var regTow=/^\d+(\.\d{2})?$/;
			//手机正则
			var phoneReg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
			
			var array1 = map1.keySet();
			var array2 = map2.keySet();
			var array3 = map3.keySet();
			
    		var temp1="",temp2="",temp3="";
    		for(i in array1){
    			temp1 += map1.get(array1[i])+",";
    		}
    		for(i in array2){
    			temp2 += map2.get(array2[i])+",";
    		}
    		for(i in array3){
    			temp3 += map3.get(array3[i])+",";
    		}
    		//alert("temp1:"+temp1+"--temp2:"+temp2+"---temp3:"+temp3);
			if(temp1!=""){
				temp1 += $("#waterSysImgsHidden").val();
				$("#waterSysImgsHidden").val(temp1);
			}
			if(temp2!=""){
				temp2 += $("#cabinetCircuitImgsHidden").val();
				$("#cabinetCircuitImgsHidden").val(temp2);
			}
			if(temp3!=""){
				temp3 += $("#assembleImgsHidden").val();
				$("#assembleImgsHidden").val(temp3);
			}
			
			//装载设备阀门类别
			var deviceValveTypeStr="";
			$("input[name='type1']:checked").each(function(){
				deviceValveTypeStr+=$(this).val()+",";
			});
			$("#deviceValveType").val(deviceValveTypeStr.substring(0, deviceValveTypeStr.length-1));
			
			//止回阀门类别
			var nonReturnValveTypeStr="";
			$("input[name='type2']:checked").each(function(){
				nonReturnValveTypeStr+=$(this).val()+",";
			});
			$("#nonReturnValveType").val(nonReturnValveTypeStr.substring(0, nonReturnValveTypeStr.length-1));
			
			console.log("startFloor:"+$("#startFloor").val());
			console.log("endFloor:"+$("#endFloor").val());
			var startFloorNum = parseInt($("#startFloor").val());
			var endFloorNum = parseInt($("#endFloor").val());
			
			if($("#projectLiablePerson").val()==0||$("#projectLiablePerson").val()==""){
				$("#projectLiablePerson").focus();
    			Notify("请选择项目负责人", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#liablePersonMobile").val()==null||$("#liablePersonMobile").val()==""){
				$("#liablePersonMobile").focus();
				Notify("请填写项目负责人电话", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!phoneReg.test($("#liablePersonMobile").val().trim())){
				$("#liablePersonMobile").focus();
				Notify("对不起，请输入正确的手机号码格式!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#superviseUnit").val()==0||$("#superviseUnit").val()==""){
				$("#superviseUnit").focus();
				Notify("请选择监管单位", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#superviseAddress").val()==null||$("#superviseAddress").val()==""){
				$("#superviseAddress").focus();
				Notify("请填写单位地址", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#administrator").val()==0||$("#administrator").val()==""){
				$("#administrator").focus();
				Notify("请选择监管单位管理人", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#administratorMobile").val()==null||$("#administratorMobile").val()==""){
				$("#administratorMobile").focus();
				Notify("请填写监管单位管理人电话", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!phoneReg.test($("#administratorMobile").val().trim())){
				$("#administratorMobile").focus();
				Notify("对不起，请输入正确的手机号码格式!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#area").val()==null||$("#area").val()==""){
				$("#area").focus();
				Notify("请填写建筑面积", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!regTow.test($("#area").val().trim())){
				$("#area").focus();
				Notify("对不起，请输入正确的面积!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#area").val().trim().length>10){
				$("#area").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#totalBuild").val()==null||$("#totalBuild").val()==""){
				$("#totalBuild").focus();
				Notify("请填写总栋数", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!reg.test($("#totalBuild").val().trim())){
				$("#totalBuild").focus();
				Notify("对不起，总栋数必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#totalBuild").val().trim().length>10){
				$("#totalBuild").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#householdNum").val()==null||$("#householdNum").val()==""){
				$("#householdNum").focus();
				Notify("请填写住户数量", 'top-right', '5000', 'danger', 'fa-desktop', true);  
    			return;
    		}
			else if(!reg.test($("#householdNum").val().trim())){
				$("#householdNum").focus();
				Notify("对不起，住户数量必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#householdNum").val().trim().length>10){
				$("#householdNum").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#twoWaterHouseholdNum").val()==null||$("#twoWaterHouseholdNum").val()==""){
				$("#twoWaterHouseholdNum").focus();
				Notify("请填写二次供水户数", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!reg.test($("#twoWaterHouseholdNum").val().trim())){
				$("#twoWaterHouseholdNum").focus();
				Notify("对不起，二次供水户数必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#twoWaterHouseholdNum").val().trim().length>10){
				$("#twoWaterHouseholdNum").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#pumpHouseAddress").val()==null||$("#pumpHouseAddress").val()==""){
				$("#pumpHouseAddress").focus();
				Notify("请填写泵房位置", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#twoWaterSource").val()==0||$("#twoWaterSource").val()==""){
				$("#twoWaterSource").focus();
				Notify("请填写二次供水源", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#twoWaterMode").val()==0||$("#twoWaterMode").val()==""){
				$("#twoWaterMode").focus();
				Notify("请填写二次供水方式", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#manufacturer").val()==null||$("#manufacturer").val()==""){
				$("#manufacturer").focus();
				Notify("请填写生产商", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#supplier").val()==null||$("#supplier").val()==""){
				$("#supplier").focus();
				Notify("请填写供应商", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#manufactureDate").val()==null||$("#manufactureDate").val()==""){
				$("#manufactureDate").focus();
				Notify("请选择生产日期", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#inletPipeLowestPressure").val()==null||$("#inletPipeLowestPressure").val()==""){
				$("#inletPipeLowestPressure").focus();
				Notify("请填写进水管网最低压力", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#inletPipePeakPressure").val()==null||$("#inletPipePeakPressure").val()==""){
				$("#inletPipePeakPressure").focus();
				Notify("请填写进水管网最高压力", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#municipalDistrictCaliber").val()==null||$("#municipalDistrictCaliber").val()==""){
				$("#municipalDistrictCaliber").focus();
				Notify("请填写市政小区介入点管径", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#waterSysImgsHidden").val()==""||$("#waterSysImgsHidden").val()==null||$("#waterSysImgsHidden").val()=="undefined"){
				$("#waterSysImgs").focus();
				Notify("请上传给水系统图", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#startFloor").val()==null||$("#startFloor").val()==""){
				$("#startFloor").focus();
				Notify("请填写开始楼层", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!reg.test($("#startFloor").val().trim())){
				$("#startFloor").focus();
				Notify("对不起，开始楼层必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#startFloor").val().trim().length>10){
				$("#startFloor").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#endFloor").val()==null||$("#endFloor").val()==""){
				$("#endFloor").focus();
				Notify("请填写结束楼层", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!reg.test($("#endFloor").val().trim())){
				$("#endFloor").focus();
				Notify("对不起，结束楼层必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#endFloor").val().trim().length>10){
				$("#endFloor").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if(startFloorNum > endFloorNum){
				$("#startFloor").focus();
				Notify("开始楼层不能大于结束楼层", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return;
			}
			else if($("#waterAbility").val()==null||$("#waterAbility").val()==""){
				$("#waterAbility").focus();
				Notify("请填写供水能力", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#waterPressure").val()==null||$("#waterPressure").val()==""){
				$("#waterPressure").focus();
				Notify("请填写供水压力", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#mainDeviceModel").val()==null||$("#mainDeviceModel").val()==""){
				$("#mainDeviceModel").focus();
				Notify("请填写主设备型号", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#assistDeviceModel").val()==null||$("#assistDeviceModel").val()==""){
				$("#assistDeviceModel").focus();
				Notify("请填写辅设备型号", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#pumpBrand").val()==null||$("#pumpBrand").val()==""){
				$("#pumpBrand").focus();
				Notify("请填写水泵品牌", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#lift").val()==null||$("#lift").val()==""){
				$("#lift").focus();
				Notify("请填写扬程", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#flow").val()==null||$("#flow").val()==""){
				$("#flow").focus();
				Notify("请填写流量", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#mainPumpMotor").val()==null||$("#mainPumpMotor").val()==""){
				$("#mainPumpMotor").focus();
				Notify("请填写主泵电机", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#assistPumpMotor").val()==null||$("#assistPumpMotor").val()==""){
				$("#assistPumpMotor").focus();
				Notify("请填写辅泵电机", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#platformNum").val()==null||$("#platformNum").val()==""){
				$("#platformNum").focus();
				Notify("请填写台数", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if(!reg.test($("#platformNum").val().trim())){
				$("#platformNum").focus();
				Notify("对不起，台数必须为整数!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#platformNum").val().trim().length>10){
				$("#platformNum").focus();
				Notify("对不起，该字段长度在10以内!", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
			}
			else if($("#controlCabinetModel").val()==null||$("#controlCabinetModel").val()==""){
				$("#controlCabinetModel").focus();
				Notify("请填写控制柜型号", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#cabinetCircuitImgsHidden").val()==""||$("#cabinetCircuitImgsHidden").val()==null||$("#cabinetCircuitImgsHidden").val()=="undefined"){
				$("#cabinetCircuitImgs").focus();
				Notify("请上传控制柜及电路图配置", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#surgePumpModel").val()==null||$("#surgePumpModel").val()==""){
				$("#surgePumpModel").focus();
				Notify("请填写稳压罐型号", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#surgePumpPressureLevel").val()==null||$("#surgePumpPressureLevel").val()==""){
				$("#surgePumpPressureLevel").focus();
				Notify("请填写稳压罐压力等级", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#inletMainNozzleCaliber").val()==null||$("#inletMainNozzleCaliber").val()==""){
				$("#inletMainNozzleCaliber").focus();
				Notify("请填写进水主管口径尺寸", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#effluentMainNozzleCaliber").val()==null||$("#effluentMainNozzleCaliber").val()==""){
				$("#effluentMainNozzleCaliber").focus();
				Notify("请填写出水主管口径尺寸", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#inletAssistNozzleCaliber").val()==null||$("#inletAssistNozzleCaliber").val()==""){
				$("#inletAssistNozzleCaliber").focus();
				Notify("请填写进水支管口径尺寸", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#effluentAssistNozzleCaliber").val()==null||$("#effluentAssistNozzleCaliber").val()==""){
				$("#effluentAssistNozzleCaliber").focus();
				Notify("请填写出水支管口径尺寸", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#importMainTubeValveType").val()==null||$("#importMainTubeValveType").val()==""){
				$("#importMainTubeValveType").focus();
				Notify("请填写进出口主管阀门类别", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else if($("#assembleImgsHidden").val()==""||$("#assembleImgsHidden").val()==null||$("#assembleImgsHidden").val()=="undefined"){
				$("#assembleImgs").focus();
				Notify("请上传装配图", 'top-right', '5000', 'danger', 'fa-desktop', true);
    			return;
    		}
			else{
				$.ajax({
					type:"POST",
					data:$("#waterPumpForm").serialize(),
					url:getRootPath()+"/web/deviceInformation/addOrUpdDeviceInformation",
					dataType:"json",
					success:function(data){
						if(data.code==200){
							window.location.href=getRootPath()+"/admin/device/list.do";
						}else{
							Notify("操作失败！", 'top-right', '5000', 'danger', 'fa-desktop', true);
						}
					}
				});
			}
		}
		
		/* $.validator.setDefaults({
		    submitHandler: function() {
		    	//提交
		    	alert("正确提交");
		    }
		});
		
		$().ready(function() {
			// 提交时验证表单
			var validator = $("#waterPumpForm").validate({
				errorPlacement: function(error, element) {
					$( element )
						.closest( "form" )
							.find( "label[for='" + element.attr( "id" ) + "']" )
								.append( error );
				},
				errorElement: "span",
				rules : {
					liablePersonMobile : {
						required : true,
						minlength : 11,
					}
				},
				messages: {
					projectLiablePersonId:{
						required:"请选择项目负责人"
					},
					liablePersonMobile:{
						required : "请输入手机号",
						minlength : "确认手机不能小于11个字符",
						isMobile : "请正确填写您的手机号码"
					}
					
				}
			});
		}); */
  	</script>
  </body>
</html>
