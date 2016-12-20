package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.wogia.bean.Menu;


@Controller
public class WebMainController {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(WebMainController.class);

	
	/**
	 * 主页面.
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String main() {
		return "main";
	}

	/**
	 * 生成菜单接口.
	 * @param request
	 * @param model
	 * @param name 菜单逻辑名称
	 * @return
	 */
	@RequestMapping(value = "/menu/{name}")
	public String menu(
			HttpServletRequest request,
			Model model,
			@PathVariable("name") String name) {

		List<Menu> menuList = new ArrayList<Menu>();

		/*Menu menuMain = new Menu("main", "数据快报", "/main", "icon-layers");
		menuList.add(menuMain);*/
		Integer type = (Integer) request.getSession().getAttribute("adminType");
		//1:最高管理员
		if (type != null) {
			if (type == 1) {
				Menu adminUserManage = new Menu("adminUserManage", "管理员管理", "icon-layers");
				adminUserManage.getChild().add(new Menu("adminUserList", "管理员列表", "/web/adminUser/adminUserList", "icon-docs"));
				menuList.add(adminUserManage);
			}
		}

		Menu hotelManage = new Menu("hotelManage", "用户列表", "/WoGia/admin/user/list.do", "menu-icon glyphicon glyphicon-home");
		menuList.add(hotelManage);
		
		Menu menuServiceManage = new Menu("orderManage", "个人中心", "menu-icon fa fa-envelope");
		menuServiceManage.getChild().add(new Menu("wechatUserList", "订单管理", "/web/user/wechatUserList", "icon-user"));
		menuServiceManage.getChild().add(new Menu("orderList", "修改密码", "/web/order/orderList", "icon-home"));
		menuList.add(menuServiceManage);
		
		/*Menu menuServiceManage = new Menu("orderManage", "订单管理", "icon-home");
		menuServiceManage.getChild().add(new Menu("wechatUserList", "客户管理", "/web/user/wechatUserList", "icon-user"));
		menuServiceManage.getChild().add(new Menu("orderList", "订单管理", "/web/order/orderList", "icon-home"));
		menuList.add(menuServiceManage);

		Menu menuStatistics = new Menu("statistics", "统计中心", "icon-puzzle");
		menuStatistics.getChild().add(new Menu("orderStatistics", "订单统计", "/web/user/orderStatistics", "icon-bar-chart"));
		menuStatistics.getChild().add(new Menu("financeStatistics", "财务统计", "/web/user/financeStatistics", "icon-bar-chart"));
		menuList.add(menuStatistics);

		Menu menuNotificationManage = new Menu("notificationManage", "通知管理", "icon-home");
		menuNotificationManage.getChild().add(new Menu("notificationList", "通知列表", "/web/user/notificationList", "icon-home"));
		menuList.add(menuNotificationManage);*/

		for (Menu menuParent : menuList) {

			if (menuParent.getName().equals(name)) {
				menuParent.setCheck(true);
				break;
			}

			for (Menu menu : menuParent.getChild()) {
				if (menu.getName().equals(name)) {

					//设置选中菜单
					menu.setCheck(true);
					menuParent.setCheck(true);
					break;
				}
			}

			if (menuParent.isCheck()) {
				break;
			}
		}

		System.out.println("......");
		model.addAttribute("menuList", menuList);

		return "menu";
	}

	/**
	 * 页面头部.
	 * @param session
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/header")
	public String header(
			HttpSession session,
			Model model) {
		AdminUser user = (AdminUser)session.getAttribute(Constant.SESSION_USER);

		model.addAttribute("user", user);

		return "header";
	}*/
}


