package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.UserBean;
import com.magic.wogia.bean.WaterWorkBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.WaterWorkService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：水厂控制器
 * 编写人员：lzh
 * 时间：2016年9月5日下午2:32:04
 */
@Controller
@RequestMapping("/web/water")
public class WaterWorkController extends BaseController{

	@Resource
	private WaterWorkService waterWorkService;
	
	/**
	 * 添加或者更新水厂信息
	 * @param waterWork 办事处对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdWaterWork",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdWaterWork(WaterWorkBean waterWork){
		try {
			waterWorkService.addOrUpdWaterWork(waterWork);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询水厂
	 * @param waterWork 水厂对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findWaterWork")
	@ResponseBody
	public ViewData findWaterWork(WaterWorkBean waterWork,Integer pageNum,Integer pageSize,HttpServletRequest req){
		List<WaterWorkBean> waterWorkList = new ArrayList<WaterWorkBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			waterWorkList = waterWorkService.findWaterWork(waterWork,pageNum,pageSize,user);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, waterWorkList);
	}
	
	
	/**
     * 根据项目id查询水厂
     * @param projectId
     * @return
     */
	@RequestMapping("/findWaterByProId")
	@ResponseBody
	public ViewData findWaterByProId(@RequestParam("projectId")Integer projectId,HttpServletRequest req){
		List<WaterWorkBean> waterWorkList = new ArrayList<WaterWorkBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			waterWorkList = waterWorkService.findWaterByProId(projectId);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, waterWorkList);
	}
	
}
