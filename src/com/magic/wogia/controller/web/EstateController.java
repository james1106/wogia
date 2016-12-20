package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.EstateBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.EstateService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：物业控制器
 * 编写人员：lzh
 * 时间：2016年9月5日下午5:31:18
 */
@Controller
@RequestMapping("/web/estate")
public class EstateController extends BaseController{

	@Resource
	private EstateService estateService;
	

	/**
	 * 添加或者更新物业信息
	 * @param estate 物业对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdEstate",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdEstate(EstateBean estate){
		try {
			estateService.addOrUpdEstate(estate);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询物业
	 * @param estate 物业对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findEstate")
	@ResponseBody
	public ViewData findEstate(EstateBean estate,Integer pageNum,Integer pageSize,HttpServletRequest req){
		List<EstateBean> estateList = new ArrayList<EstateBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			estateList = estateService.findEstate(estate,pageNum,pageSize,user);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, estateList);
	}
}
