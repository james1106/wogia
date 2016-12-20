package com.magic.wogia.controller.app;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.bean.SuggestBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ISuggestService;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 *  投诉建议 控制层
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/app/suggest")
public class SuggestController extends BaseController {

	@Resource
	private ISuggestService suggestServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**新增建议*/
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addSuggest(String content){
		if(null == content || content.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		UserBean user = LoginHelper.getCurrentUser();
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		SuggestBean suggest = new SuggestBean();
		suggest.setContent(content);
		suggest.setUserId(user.getId());
		try {
			suggestServiceImpl.add(suggest);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return buildFailureJson(StatusConstant.Fail_CODE, "提交失败");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "提交成功");
	}
	
}
