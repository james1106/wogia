package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ProjectApplyDetailsBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.ProjectApplyDetailsService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：专项服务详细控制器
 * 编写人员：lzh
 * 时间：2016年9月28日上午11:03:43
 */
@Controller
@RequestMapping("/web/pad")
public class ProjectApplyDetailsController extends BaseController{

	@Resource
	private ProjectApplyDetailsService applyDetailsService;
	
	

	/**
	 * 添加/更新详细
	 * @param beans
	 */
	@RequestMapping(value="/addOrUpdProjectApplyDetails",method=RequestMethod.POST)
	@ResponseBody
	public ViewData addOrUpdProjectApplyDetails(ProjectApplyDetailsBean beans){
		try {
			applyDetailsService.addOrUpdProjectApplyDetails(beans);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS);
	}
	
	
	/**
	 * 根据applyId 专项服务id 查询
	 * @param applyId
	 * @return
	 */
	@RequestMapping("/findProjectApplyDetailsByApplyId")
	@ResponseBody
	public ViewData findProjectApplyDetailsByApplyId(Integer applyId,HttpServletRequest req,Integer pageNum,Integer pageSize){
		List<ProjectApplyDetailsBean> apdList = new ArrayList<ProjectApplyDetailsBean>();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			apdList = applyDetailsService.findProjectApplyDetailsByApplyId(applyId, pageNum, pageSize);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, apdList);
	}
	
	
	/**
	 * 查询单个详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/findProjectApplyDetailsById")
	@ResponseBody
	public ViewData findProjectApplyDetailsById(Integer id,HttpServletRequest req){
		ProjectApplyDetailsBean apd = new ProjectApplyDetailsBean();
		UserBean user = LoginHelper.getCurrentAdmin(req);
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			apd = applyDetailsService.findProjectApplyDetailsById(id);
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, apd);
	}
}
