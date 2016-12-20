package com.magic.wogia.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页Bean
 * @author QimouXie
 *
 */
public class CutPageBean<T> {

	/** 当前页需要显示的数据 */
	private List<T> dataList = new ArrayList<T>();
	/** 总的记录数 */
	private int count;
	/** 总页数 */
	private int totalPage;

	public CutPageBean() {
		super();
	}

	public CutPageBean(List<T> dataList, int count, int totalPage) {
		super();
		this.dataList = dataList;
		this.count = count;
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
