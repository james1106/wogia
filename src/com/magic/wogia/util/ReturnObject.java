package com.magic.wogia.util;

import java.util.ArrayList;
import java.util.List;

import com.magic.wogia.bean.ListData;



public class ReturnObject {
	private int status;
	private String detail;
	private List<? extends ListData> listData;

	private Object data;
	
	
	public ReturnObject() {
		status = 0;
		detail = "";
		listData = new ArrayList<ListData>();// 默认是空列表
	}

	public ReturnObject(Checker checker) {
		setChecker(checker);
		listData = new ArrayList<ListData>();// 默认是空列表
	}

	public ReturnObject(int status,String detail){
		this.status = status;
		this.detail = detail;
	}
	public ReturnObject(List<? extends ListData> listData) {
		status = 0;
		detail = "";
		setListData(listData);
	}

	public ReturnObject(Checker checker, List<? extends ListData> listData) {
		setChecker(checker);
		setListData(listData);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setStatusError() {
		this.status = 1;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		if (detail != null) {
			this.detail = detail;
		}
	}

	public List<? extends ListData> getListData() {
		return listData;
	}

	public void setListData(List<? extends ListData> listData) {
		if (listData != null) {
			this.listData = listData;
		}
	}

	public void setChecker(Checker checker) {
		if (!checker.isOk()) {
			setStatusError();
		} else {
			status = 0;
		}
		detail = checker.getErrorDetail();
		if (detail == null) {
			detail = "";
		}
	}
	
	//出错消息
	public static ReturnObject isError(ReturnObject ro,int type,String errorDetail){
		if(null != errorDetail && !"".equals(errorDetail)){
			ro.setDetail(errorDetail);
			ro.setStatus(500);
			return ro;
		}
		if(null != ro){
			if(type == 1){
				ro.setDetail("错误码:500,如重复出现,请与管理员联系!");
				ro.setStatus(500);
			}else if(type == 2){
				ro.setDetail("错误码:501,如重复出现,请与管理员联系!");	
				ro.setStatus(501);
			}else{
				//保留
			}
			return ro;
		}else{
			ro = new ReturnObject();
			ro.setDetail("错误码:502,如重复出现,请与管理员联系!");		
			ro.setStatus(502);
			return ro;
		}
	}
	//出错消息(分页)
	public static void isError(PageView pageView,ReturnObject ro,int type,String errorDetail){
		if(null != ro){
			if(type == 1){
				ro.setDetail("错误码:500,如重复出现,请与管理员联系!");
				ro.setStatus(500);
			}else if(type == 2){
				ro.setDetail("错误码:501,如重复出现,请与管理员联系!");		
				ro.setStatus(501);
			}else{
				//保留
			}
		}else{
			ro = new ReturnObject();
			ro.setDetail("系统错误,错误码:502,如重复出现,请与管理员联系!");		
			ro.setStatus(502); 
		}
		pageView.setReturnObject(ro);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
