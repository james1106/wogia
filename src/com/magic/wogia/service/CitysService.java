package com.magic.wogia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.wogia.bean.CitysBean;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.exception.InterfaceCommonException;
import com.magic.wogia.mapper.ICitysMapper;
import com.magic.wogia.mapper.ICompanyMapper;
import com.magic.wogia.util.EntityToMap;
import com.magic.wogia.util.StatusConstant;

/**
 * 
 * 功能：地区业务实现
 * 编写人员：lzh
 * 时间：2016年9月6日上午9:36:09
 */
@Service
public class CitysService {

	@Resource
	private ICitysMapper citysMapper;
	@Resource
	private ICompanyMapper companyMapper;
	/**
	 * 查询地区区
	 * @param city
	 * @return
	 */
	public List<CitysBean> findCitys(CitysBean city,Integer pageNum,Integer pageSize,UserBean user){
		Map<String, Object> map = new HashMap<String, Object>();
		map = EntityToMap.setConditionMap(city);
		if (pageNum != null && pageSize != null) {
			map.put("pageStart", (pageNum-1)*pageSize);
			map.put("pageSize", pageSize);
		}
		List<CitysBean> cityList = citysMapper.findCitys(map);
		return cityList;
	}
	
	/**
	 * 查询存在项目的地区
	 * @param city
	 * @return
	 */
	public Map<String,Map<String, Set<String>>> findById(UserBean user){
		
		 List<CitysBean> city = new ArrayList<CitysBean>();
		 
		 Map<String, Object> projectCityListMap = new HashMap<String, Object>();
		 projectCityListMap.put("userType", user.getIdType());
		 projectCityListMap.put("objectId", user.getCompanyId());
		//查询出当前用户所有项目的地区  cityId
	 	city = citysMapper.findProjectCityId(projectCityListMap);
		if (projectCityListMap.size() < 0) {
			throw new InterfaceCommonException(StatusConstant.Fail_CODE, "没有你负责的项目地区");
		}
		 //存放一级地区
		 List<CitysBean> city1 =  new ArrayList<CitysBean>();
		 //存放二级地区
		 List<CitysBean> city2 = new ArrayList<CitysBean>();
		 //存放三级地区
		 List<CitysBean> city3 = new ArrayList<CitysBean>();
		 List<Integer> idList1 = new ArrayList<Integer>();
		 List<Integer> idList2 = new ArrayList<Integer>();
		 List<Integer> idList3 = new ArrayList<Integer>();
		 Map<String,Map<String, Set<String>>> cityMap = new HashMap<String, Map<String,Set<String>>>();
		 if (city.size() > 0) {
			for (CitysBean citysBean : city) {
				if (citysBean.getLevelType() == 3) {
					idList3.add(citysBean.getParentId());
					city3.add(citysBean);
				}
				if (citysBean.getLevelType() == 2) {
					idList2.add(citysBean.getParentId());
					city2.add(citysBean);
				}
				if (citysBean.getLevelType() == 1) {
					idList1.add(citysBean.getParentId());
					city1.add(citysBean);
				}
			}
			city2.addAll(citysMapper.findById(idList3));
			for (CitysBean citysBean : city2) {
				idList2.add(citysBean.getParentId());
			}
			city1.addAll(citysMapper.findById(idList2)) ;
			//构造返回格式
			for (CitysBean citysBean1 : city1) {
				Map<String, Set<String>> map = new HashMap<String, Set<String>>();
				for (CitysBean citysBean2 : city2) {
					Set<String> s = new HashSet<String>();
					for (CitysBean citysBean3 : city3) {
						if (citysBean3.getParentId().equals(citysBean2.getId())) {
							s.add(citysBean3.getName());
						}
					}
					if (citysBean2.getParentId().equals(citysBean1.getId())) {
						map.put(citysBean2.getName(), s);
					}
				}
				cityMap.put(citysBean1.getName(), map);
			}
		 }
		 return cityMap;
	}
	
}
