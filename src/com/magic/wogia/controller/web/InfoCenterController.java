package com.magic.wogia.controller.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.service.ISystemInfoService;
import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;
import com.magic.wogia.controller.BaseController;

/**
 * 
 * 功能：系统消息控制器
 * 编写人员：lzh
 * 时间：2016年9月18日上午10:23:50
 */
@Controller
@RequestMapping("/web/info")
public class InfoCenterController extends BaseController{
	
	@Resource
	private ISystemInfoService systemInfoServiceImpl;
	
	
	/**
	 * 推送系统通知   添加系统消息
	 * @param title
	 * @param content
	 * @param brief
	 * @param req
	 * @return
	 */
	@RequestMapping("/sendNotice")
	@ResponseBody
	public ViewData addNotice(String title,String content,String brief,Integer type,Integer id,HttpServletRequest req){
		if(title == null || brief == null || content == null){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if(null ==  user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		boolean isOk = false;
		SystemInfoBean info = new SystemInfoBean();
		info.setContent(content);
		info.setTitle(title);
		info.setBrief(brief);
		info.setPublisherId(user.getId());
		info.setType(type);
		info.setId(id);
		try {
			id = systemInfoServiceImpl.addSystemInfo(info);
			isOk= systemInfoServiceImpl.sendNotice(info,id);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "发送失败");
		}
		if(isOk){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "发送成功");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "发送失败");
	}
	
	
	/**
	 *  获取 系统消息通知
	 * @return
	 */
	@RequestMapping("/adminGetInfo")
	@ResponseBody
	public ViewData getSystemInfoList(Integer pageNum,Integer pageSize,Integer type){
		List<SystemInfoBean> list = systemInfoServiceImpl.quertPage(pageNum, pageSize,type,null);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", list);
	}
	
	/**
	 * 获取单个系统通知的详情
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/getMsgById")
	@ResponseBody
	public ViewData getMsgById(Integer systemId){
		if(null == systemId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		SystemInfoBean info = systemInfoServiceImpl.findById(systemId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", info);
	}
	
	/**
	 * 删除系统公告
	 * @param systemId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delSystemInfo")
	@ResponseBody
	public ViewData delSystemInfo(Integer systemId) throws Exception{
		if(null == systemId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		systemInfoServiceImpl.delSystemInfo(systemId);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "删除成功");
	}
}

