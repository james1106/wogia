package com.magic.wogia.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.magic.wogia.bean.OrderBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.service.OrderService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：web端 订单控制器
 * 编写人员：lzh
 * 时间：2016年9月14日上午9:49:39
 */
@Controller
@RequestMapping("/web/order")
public class WebOrderController extends BaseController{

	@Resource
	private OrderService orderService;
	
	
	/**
	 * 订单列表
	 * @param req
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping("/findOrderList")
	@ResponseBody
	public ViewData findOrderList(HttpServletRequest req,@RequestParam("pageNum")Integer pageNum,
			String realName,String orderNumber,
			@RequestParam("pageSize")Integer pageSize,Integer status){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		try {
			orderList = orderService.queryPageOrder(pageNum, pageSize, user, status,null,null,realName,orderNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, orderList);
	}
	
	/**
	 * 订单详情
	 * @param req
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOrderById")
	@ResponseBody
	public ViewData findOrderById(HttpServletRequest req,@RequestParam("id")Integer id){
		UserBean user = LoginHelper.getCurrentAdmin(req);
		if (user == null) {
			return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
		}
		OrderBean order = new OrderBean();
		try {
			order = orderService.queryById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, order);
	};
	
}
