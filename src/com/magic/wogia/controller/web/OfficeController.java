package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.OfficeBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.OfficeService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：办事处控制器
 * 编写人员：lzh
 * 时间：2016年9月5日上午9:08:03
 */
@Controller
@RequestMapping("/web/office")
public class OfficeController extends BaseController{

	@Resource
	private OfficeService officeService;
	
	/**
	 * 添加或者更新办事处信息
	 * @param office 办事处对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdOffice",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdOffice(OfficeBean office){
		try {
			officeService.addOrUpdOffice(office);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询办事处
	 * @param office 办事处对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findOffice")
	@ResponseBody
	public ViewData findOffice(OfficeBean office,Integer pageNum,Integer pageSize,HttpServletRequest req){
		List<OfficeBean> officeList = new ArrayList<OfficeBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			officeList = officeService.findOffice(office,pageNum,pageSize,user);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, officeList);
	}
}
