package com.magic.wogia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.wogia.DataSource.DataSourceInstances;
import com.magic.wogia.DataSource.DataSourceSwitch;
import com.magic.wogia.bean.Test;
import com.magic.wogia.bean.UserBean;
import com.magic.wogia.mapper.ICompanyMapper;
import com.magic.wogia.mapper.IOfficeMapper;
import com.magic.wogia.mapper.IUserMapper;
import com.magic.wogia.mapper.TestMapper;
import com.magic.wogia.util.RoleConstant;


@Service
public class TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private IUserMapper userMapper;
	@Autowired
	private IOfficeMapper officeMapper;
	@Autowired
	private ICompanyMapper companyMapper;
	public List<Test> findAll1(){
		return testMapper.findAll1();
	}
	
	public List<Object> findAll2(){
		
		DataSourceSwitch.setDataSourceType(DataSourceInstances.WG2); 
		
		return testMapper.findAll2();
	}
	
	public void find(){
		
		UserBean user = new UserBean();
		if (user.getRoleId() == RoleConstant.OFFICE_WORKER) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getCompanyId());
		/*	List<CompanyBean> comList= companyMapper.findCompany(map);
			List<OfficeBean> offList = officeMapper.findOffice(map);*/
			
			
		}
		
		
		
	}
	
	
	
	
	
	
}
