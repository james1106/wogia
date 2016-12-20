package com.magic.wogia.controller.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.SuggestBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ISuggestService;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;





@Controller
@RequestMapping("/web/suggest")
public class AdminSuggestController extends BaseController {
	
	@Resource
	private ISuggestService suggestServiceImpl;
	
	@RequestMapping("/getSuggest")
	@ResponseBody
	public ViewData getSuggest(Integer pageNum,Integer pageSize){
		List<SuggestBean> data = suggestServiceImpl.queryPage(pageNum, pageSize);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		
	}

}
