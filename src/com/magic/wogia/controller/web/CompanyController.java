package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.magic.wogia.bean.CompanyBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.CompanyService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：片区控制器
 * 编写人员：lzh
 * 时间：2016年9月2日下午1:56:48
 */
@Controller
@RequestMapping("/web/company")
public class CompanyController extends BaseController{

	@Resource
	private CompanyService companyService;
	
	/**
	 * 添加或者更新片区信息
	 * @param company 片区对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdCompany",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdCompany(CompanyBean company){
		try {
			companyService.addOrUpdCompany(company);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询片区
	 * @param company 片区对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findCompany")
	@ResponseBody
	public ViewData findCompany(CompanyBean company,Integer pageNum,Integer pageSize,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			companyList = companyService.findCompany(company,pageNum,pageSize,user);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, companyList);
	}
	
	
	@RequestMapping("/findCityId")
	@ResponseBody
	public ViewData findCityId(Integer roleId ,Integer objectId){
		CompanyBean companyList = new CompanyBean();
		try {
			companyList = companyService.findCityId(roleId,objectId);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, companyList);
	}
}
