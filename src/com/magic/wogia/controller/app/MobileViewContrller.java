package com.magic.wogia.controller.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.wogia.bean.ApplyContextBean;
import com.magic.wogia.bean.NewsBean;
import com.magic.wogia.bean.SystemInfoBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.ApplyContextService;
import com.magic.wogia.service.ISystemInfoService;
import com.magic.wogia.service.NewsService;

/**
 *  web app 请求路径
 * @author QimouXie
 *
 */
@RequestMapping("/requestWeb")
@Controller
public class MobileViewContrller extends BaseController {
	
	@Resource
	private NewsService newsService;
	@Resource
	private ISystemInfoService systemInfoServiceImpl;
	@Resource
	private ApplyContextService applyContextService;
	
	
	/**自动跳转*/
	@RequestMapping("/autoSkip")
	public String autoSkip(){
		return "pages/autoSkip/autoskip";
	}
	
	@RequestMapping("/applyPage")
	public String requestApplyProjectPage(Model model,String token,Integer projectId){
		model.addAttribute("token", token);
		model.addAttribute("projectId",projectId);
		ApplyContextBean context = applyContextService.findApplyContext();
		if(null != context){
			model.addAttribute("context", context.getContext());
		}
		return "pages/admin/mobile/projectApply";
	}
	
	@RequestMapping("/monitorPage")
	public String requestMonitorPage(Model model,String deviceName){
		model.addAttribute("deviceName", deviceName);
		return "pages/admin/mobile/monitor";
	}
	
	/**
	 *  资讯详细页 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/newsDetail")
	public String requestNewsPage(Model model,Integer newsId){
		NewsBean news = newsService.findById(newsId);
		model.addAttribute("news", news);
		return "pages/admin/mobile/news";
	}
	
	/**
	 *  系统公告页面
	 * @param model
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/systemInfo")
	public String requestSystemInfoPage(Model model,Integer systemId){
		SystemInfoBean info = systemInfoServiceImpl.findById(systemId);
		model.addAttribute("info", info);
		return "pages/admin/mobile/systemInfo";
	}

	/**
	 * 服务承诺书
	 * @return
	 */
	@RequestMapping("/servicePromise")
	public String servicePromise(){
		return "pages/admin/mobile/serviceCommitment";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@RequestMapping("/aboutUs")
	public String aboutUs(){
		return "pages/admin/mobile/aboutUs";
	}
	
	
}
