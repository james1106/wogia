package com.magic.wogia.controller.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.WaterWorkBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.WaterWorkService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：水厂控制器
 * 编写人员：lzh
 * 时间：2016年9月5日下午2:32:04
 */
@Controller
@RequestMapping("/app/water")
public class AppWaterWorkController extends BaseController{

	@Resource
	private WaterWorkService waterWorkService;
	
	
	
	
	/**
	 * 查询水厂
	 * @param waterWork 水厂对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findWaterWork")
	@ResponseBody
	public ViewData findWaterWork(WaterWorkBean waterWork,Integer pageNum,Integer pageSize){
		List<WaterWorkBean> waterWorkList = new ArrayList<WaterWorkBean>();
		try {
			waterWorkList = waterWorkService.findWaterWork(waterWork,pageNum,pageSize);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, waterWorkList);
	}
	
	
}
