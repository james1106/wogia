package com.magic.wogia.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.wogia.bean.DeviceBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.service.ComponentDeviceService;
import com.magic.wogia.service.DeviceService;
import com.magic.wogia.service.TestService;
import com.magic.wogia.util.DateUtil;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

@Controller
@RequestMapping("/admin")
public class PageController extends BaseController {

	@Autowired
	private TestService testService;
	@Resource
	private ComponentDeviceService componentDeviceService;
	@Resource
	private DeviceService deviceService;
	
	@RequestMapping("/test")
	public ViewData test(){
		try {
			componentDeviceService.getAllComponentWillDue();
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "成功啦！！！！");
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, "异常");
		}
		
		
	}

	
	@RequestMapping("/index.do")
	public String findAll(HttpServletRequest req){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		req.setAttribute("user", user);
		return "/pages/admin/index";
	}
	

	/**默认页面*/
	@RequestMapping("/default.do")
	public String defaultPage(){
		return "pages/admin/default";
	}
	
	/**用户协议页面*/
	@RequestMapping("/mobile/aboutUs.do")
	public String aa(){
		return "pages/admin/mobile/aboutUs";
	}
	
	/*@RequestMapping("/mobile/projectApply.do")
	public String aa(){
		return "pages/admin/mobile/projectApply";
	}*/
	
	/*@RequestMapping("/mobile/monitor.do")
	public String aa(){
		return "pages/admin/mobile/monitor";
	}*/
	/**
	 * 分享页面
	 * @return
	 */
	@RequestMapping("/mobile/share.do")
	public String share(){
		return "pages/admin/mobile/share";
	}
	
	/**用户列表*/
	@RequestMapping("/user/list.do")
	public String findAllUser(){
		return "pages/admin/user/list";
	}
	
	/**添加用户*/
	@RequestMapping("/user/add.do")
	public String addUser(){
		return "pages/admin/user/add";
	}
	
	/**修改用户*/
	@RequestMapping("/user/userEdit.do")
	public String updateUser(){
		return "pages/admin/user/userEdit";
	}
	
	/**用户详情*/
	@RequestMapping("/user/detail.do")
	public String detailUser(){
		return "pages/admin/user/detail";
	}
	
	
	/**公司管理，公司列表*/
	@RequestMapping("/company/list.do")
	public String findAllCompany(){
		return "pages/admin/company/list";
	}
	
	/**添加公司*/
	@RequestMapping("/company/add.do")
	public String addCompany(){
		return "pages/admin/company/add";
	}
	
	/**办事处，办事处列表*/
	@RequestMapping("/office/list.do")
	public String findAllOffice(){
		return "pages/admin/office/list";
	}
	
	/**办事处，添加办事处*/
	@RequestMapping("/office/add.do")
	public String addOffice(){
		return "pages/admin/office/add";
	}
	
	/**水厂，水厂列表*/
	@RequestMapping("/water/list.do")
	public String findAllWater(){
		return "pages/admin/water/list";
	}
	
	/**水厂，添加水厂*/
	@RequestMapping("/water/add.do")
	public String addWater(){
		return "pages/admin/water/add";
	}
	
	/**物业，物业列表*/
	@RequestMapping("/estate/list.do")
	public String findAllEstate(){
		return "pages/admin/estate/list";
	}
	
	/**物业，添加物业*/
	@RequestMapping("/estate/add.do")
	public String addEstate(){
		return "pages/admin/estate/add";
	}
	
	/**项目，项目列表*/
	@RequestMapping("/project/list.do")
	public String findAllProject(){
		return "pages/admin/project/list";
	}
	
	@RequestMapping("/project/fenqu")
	public String queryFenquByProject(Model model,Integer projectId){
		List<DeviceBean> data = new ArrayList<DeviceBean>();
		if(null != projectId){
			data = deviceService.queryByProject(projectId);
		}
		for (DeviceBean deviceBean : data) {
			deviceBean.setDeviceCreatetimes(DateUtil.DateTime(deviceBean.getDeviceCreatetime(), "yyyy-MM-dd HH:mm"));
		}
		model.addAttribute("data", data);
		return "pages/admin/project/fenquList";
	}
	
	/**项目，添加项目*/
	@RequestMapping("/project/add.do")
	public String addProject(){
		return "pages/admin/project/add";
	}
	
	
	/**分区管理 ，同步分区*/
	@RequestMapping("/device/synchro.do")
	public String synchro(){
		return "pages/admin/device/synchro";
	}
	
	/**分区管理 ，分区列表*/
	@RequestMapping("/device/list.do")
	public String findAllDevice(){
		return "pages/admin/device/list";
	}
	
	/**分区管理 ，添加分区*/
	@RequestMapping("/device/add.do")
	public String addDevice(){
		return "pages/admin/device/add";
	}
	
	/**分区零件 ，查看分区零件*/
	@RequestMapping("/device/deviceSparePartsList.do")
	public String findDeviceSparePartsList(){
		return "pages/admin/device/deviceSparePartsList";
	}
	
	/**分区零件 ，添加分区零件*/
	@RequestMapping("/device/deviceSparePartsAdd.do")
	public String deviceSparePartsAdd(){
		return "pages/admin/device/deviceSparePartsAdd";
	}
	
	/**水质报告管理*/
	@RequestMapping("/presentation/uploadPresentation.do")
	public String uploadPresentation(){
		return "pages/admin/presentation/uploadPresentation";
	}
	
	/**水泵管理--水泵列表*/
	@RequestMapping("/waterPump/list.do")
	public String waterPumpfindAll(){
		return "pages/admin/waterPump/list";
	}
	
	/**水泵管理--添加水泵信息*/
	@RequestMapping("/waterPump/add.do")
	public String waterPumpAdd(){
		return "pages/admin/waterPump/add";
	}
	
	/**零件库--零件列表*/
	@RequestMapping("/spareParts/list.do")
	public String sparePartsfindAll(){
		return "pages/admin/spareParts/list";
	}
	
	/**订单--订单列表*/
	@RequestMapping("/order/list.do")
	public String findAllOrder(){
		return "pages/admin/order/list";
	}
	
	/**订单--订单列表*/
	@RequestMapping("/order/detail.do")
	public String orderDetail(){
		return "pages/admin/order/detail";
	}
	
	/**系统公告--发布公告*/
	@RequestMapping("/notice/add.do")
	public String noticeAdd(){
		return "pages/admin/notice/add";
	}
	
	/**系统公告--公告列表*/
	@RequestMapping("/notice/list.do")
	public String findAllNotice(){
		return "pages/admin/notice/list";
	}
	
	/**资讯管理--发布资讯*/
	@RequestMapping("/information/add.do")
	public String informationAdd(){
		return "pages/admin/information/add";
	}
	
	/**资讯管理--资讯列表*/
	@RequestMapping("/information/list.do")
	public String findAllInformation(){
		return "pages/admin/information/list";
	}
	
	/**banner--banner列表*/
	@RequestMapping("/banner/list.do")
	public String findAllBanner(){
		return "pages/admin/banner/list";
	}
	
	/**banner--banner添加*/
	@RequestMapping("/banner/add.do")
	public String addBanner(){
		return "pages/admin/banner/add";
	}
	
	/**专享服务--申请列表*/
	@RequestMapping("/exclusiveService/serviceList.do")
	public String findAllServiceList(){
		return "pages/admin/exclusiveService/serviceList";
	}
	
	/**专享服务--列表*/
	@RequestMapping("/exclusiveService/list.do")
	public String findAllExclusiveService(){
		return "pages/admin/exclusiveService/list";
	}
	
	/**专享服务--添加*/
	@RequestMapping("/exclusiveService/add.do")
	public String addExclusiveService(){
		return "pages/admin/exclusiveService/add";
	}
	
	/**申请内容*/
	@RequestMapping("/applyContext/add.do")
	public String addApplyContext(){
		return "pages/admin/applyContext/add";
	}
	
	/**意见反馈*/
	@RequestMapping("/suggest/list.do")
	public String findAllSuggest(){
		return "pages/admin/suggest/list";
	}
	
	/**菜单权限管理*/
	@RequestMapping("/powermanage/list.do")
	public String findPower(){
		return "pages/admin/powermanage/list";
	}
	
	/**菜单权限管理--添加权限*/
	@RequestMapping("/powermanage/add.do")
	public String addPower(){
		return "pages/admin/powermanage/add";
	}
		
	/**配置联系电话*/
	@RequestMapping("/contactUs/up.do")
	public String upContactUs(){
		return "pages/admin/contactUs/up";
	}
}
