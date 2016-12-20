package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ComponentBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.ComponentService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：零件库控制器
 * 编写人员：lzh
 * 时间：2016年9月13日上午9:54:38
 */
@Controller
@RequestMapping("/web/component")
public class ComponentController extends BaseController{

	@Resource
	private ComponentService componentService;
	/**
	 * 添加或者更新零件信息
	 * @param record 零件对象
	 * @return
	 */
	@RequestMapping(value="/addOrUpdComponent",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdComponent(ComponentBean record){
		try {
			componentService.addOrUpdComponent(record);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 查询零件
	 * @param record 零件对象
	 * @param pageNum 页码
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@RequestMapping("/findComponent")
	@ResponseBody
	public ViewData findComponent(ComponentBean record,Integer pageNum,Integer pageSize,HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		List<ComponentBean> componentList = new ArrayList<ComponentBean>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			componentList = componentService.findComponent(record, pageNum, pageSize);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, componentList);
	}
}
