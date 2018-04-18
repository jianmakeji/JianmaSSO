package com.jianma.sso.model;

import java.util.List;

public class PageModel {

	public List<? extends Object> list;
	public int count;
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
