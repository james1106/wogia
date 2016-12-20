package com.magic.wogia.controller.app;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.ProjectApplyDetailsBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.ProjectApplyDetailsService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.Timestamp;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：专项服务详细控制器
 * 编写人员：lzh
 * 时间：2016年9月28日上午11:03:43
 */
@Controller
@RequestMapping("/app/pad")
public class AppProjectApplyDetailsController extends BaseController{

	@Resource
	private ProjectApplyDetailsService applyDetailsService;
	
	
	
	
	/**
	 * 查询最新一条 查询单个详情
	 * @param applyId
	 * @return
	 */
	@RequestMapping("/findNewestByApplyId")
	@ResponseBody
	public ViewData findNewestByApplyId(Integer applyId){
		ProjectApplyDetailsBean apd = new ProjectApplyDetailsBean();
		UserBean user = LoginHelper.getCurrentUser();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			apd = applyDetailsService.findNewestByApplyId(applyId);
			apd.setNextPayTime(Timestamp.TimeStamp2Date(String.valueOf(Timestamp.timesStr(apd.getNextPayTime(), "yyyy-MM-dd")), "yyyy年MM月dd日"));
		} catch (InterfaceCommonException e) {
			return buildFailureJson(e.getErrorCode(),e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, apd);
	}
}
