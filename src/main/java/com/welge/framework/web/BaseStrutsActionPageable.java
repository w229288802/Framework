package com.welge.framework.web;

import java.io.Serializable;

public class BaseStrutsActionPageable<T,ID extends Serializable> extends BaseStrutsAction<T, ID>{

	private static final long serialVersionUID = 1L;
	private Integer pageNum=1;
	private Integer numPerPage;
	private String orderField;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String orderDirection;
	public Integer getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
}
