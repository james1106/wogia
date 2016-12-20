package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.CitysBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.CitysService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：地区控制器
 * 编写人员：lzh
 * 时间：2016年9月6日上午9:38:26
 */
@Controller
@RequestMapping("/web/city")
public class CitysController extends BaseController{

	@Resource
	private CitysService citysService;
	
	
	/**
	 * 查询地区
	 * @param citys 地区对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findCitys")
	@ResponseBody
	public ViewData findCitys(CitysBean citys,Integer pageNum,Integer pageSize,HttpServletRequest req){
		
		UserBean user = LoginHelper.getCurrentAdmin(req);
		
		List<CitysBean> cityList = new ArrayList<CitysBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			cityList = citysService.findCitys(citys,pageNum,pageSize,user);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, cityList);
	}
}
