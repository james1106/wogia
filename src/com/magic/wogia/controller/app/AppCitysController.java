package com.magic.wogia.controller.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.controller.BaseController;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.service.CitysService;
import com.magic.wogia.util.ErrorMessage;
import com.magic.wogia.util.LoginHelper;
import com.magic.wogia.util.StatusConstant;
import com.magic.wogia.util.ViewData;

/**
 * 
 * 功能：地区控制器
 * 编写人员：lzh
 * 时间：2016年9月6日上午9:38:26
 */
@Controller
@RequestMapping("/app/city")
public class AppCitysController extends BaseController{

	@Resource
	private CitysService citysService;
	
	
	/**
	 * 查询项目存在的地区
	 * @param citys 地区对象
	 * @return
	 */
	@RequestMapping("/findCitys")
	@ResponseBody
	public ViewData findCitys(){
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG1);
		UserBean user = LoginHelper.getCurrentUser();
		Map<String,Map<String, Set<String>>> cityMap = new HashMap<String, Map<String,Set<String>>>();
		try {
			if (user == null) {
				return buildFailureJson(StatusConstant.NOTLOGIN, ErrorMessage.NO_LOGIN);
			}
			cityMap = citysService.findById(user);
		} catch(InterfaceCommonException e){
			return buildFailureJson(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return buildFailureJson(StatusConstant.Fail_CODE, ErrorMessage.Fail);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, ErrorMessage.SUCCESS, cityMap);
	}
}
