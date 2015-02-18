package com.welge.framework.web;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.welge.framework.service.BaseService;

public abstract class BaseStrutsActionPageable<T,ID extends Serializable> extends BaseStrutsAction<T, ID>{
	public static final Integer DEFAULT_PAGENUM=1;
	public static final Integer DEFAULT_PERPAGENUM=10;
	
	private static final long serialVersionUID = 1L;
	private Integer pageNum=DEFAULT_PAGENUM;
	private Integer numPerPage=DEFAULT_PERPAGENUM;
	private String orderField;
	public Pageable getPageable(){
		return new Pageable(){

			@Override
			public int getPageNumber() {
				return 1;
			}

			@Override
			public int getPageSize() {
				// TODO Auto-generated method stub
				return 10;
			}

			@Override
			public int getOffset() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Sort getSort() {
				return new Sort(Direction.ASC,"id");
			}};
	}
	public abstract BaseService<T, ID> getBaseService();
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
