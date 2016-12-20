package com.magic.wogia.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类.
 * @author hep
 *
 */
public class Menu {

	private String name;

	private String text;

	private String url;

	private List<Menu> child;

	private boolean check;

	private String style;   //菜单图标样式

	/**
	 * 创建没有链接的父菜单.
	 * @param name
	 * @param text
	 */
	public Menu(String name, String text, String style) {
		this.name = name;
		this.text = text;
		this.style = style;

		child = new ArrayList<Menu>();
	}

	/**
	 * 创建指向页面的菜单.
	 * @param name
	 * @param text
	 * @param url
	 */
	public Menu(String name, String text, String url, String style) {
		this.name = name;
		this.text = text;
		this.url = url;
		this.style = style;

		child = new ArrayList<Menu>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}

