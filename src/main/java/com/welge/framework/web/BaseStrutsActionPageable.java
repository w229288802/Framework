package com.welge.framework.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.welge.framework.exception.ApplicationException;
import com.welge.framework.exception.dwz.InvalidOperationException;
import com.welge.framework.exception.dwz.NotImplementException;
import com.welge.framework.service.BaseService;
import com.welge.framework.utils.DBUtils;
import com.welge.framework.utils.JPAUtils;
import com.welge.framework.utils._PrintUtils;
import com.welge.framework.vo.dwz.JsonResponse;
import com.welge.framework.vo.dwz.JsonTable;
import com.welge.framework.vo.ztree.JsonZtree;

public abstract class BaseStrutsActionPageable<T,ID extends Serializable> extends BaseStrutsAction<T, ID>{
	private static final long serialVersionUID = 1L;
	//分页默认属性
	public static final Integer DEFAULT_PAGENUM=1;
	public static final Integer DEFAULT_NUMPERPAGE=10;
	public static final String DEFAULT_ORDERFIELD="id";
	public static final String DEFAULT_ORDERDIRECTION="asc";
	//分页关键字段
	private String orderDirection = DEFAULT_ORDERDIRECTION;
	private Integer pageNum = DEFAULT_PAGENUM;
	private Integer numPerPage = DEFAULT_NUMPERPAGE;
	private String orderField = DEFAULT_ORDERFIELD;
	/**
	 * 返回JSON格式的table
	 */
	public String listTable(){
		JsonTable jsonTable = new JsonTable();
		Page<T> page = getBaseService().getListPage(getPageable());
		jsonTable.setTotals(page.getTotalElements());
		jsonTable.setRows(page.getContent());
		pushStack(jsonTable);
		return JSON;
	}
	public String listAll(){
		Page<T> page = getBaseService().getListPage(getPageable());
		pushStack(page.getContent());
		return JSON;
	}
	/**
	 * 返回编辑页面
	 */
	public String edit(){
		ID id = JPAUtils.getPrimaryKey(getModel());
		if(id!=null){
			T one = getBaseService().getOne(id);
			pushStack(one);
		}
		return EDIT;
	}
	/**
	 * 保存实体并返回响应
	 * @return
	 */
	public String save(){
		//System.out.println(getModel());
		T model = getModel();
		String id = (String) JPAUtils.getPrimaryKey(getModel());
		if(id==null||id instanceof String&&id.length()==0){
			JPAUtils.setPrimaryKey(model, DBUtils.generateBeanID());
		}
		getBaseService().save(model);
		_PrintUtils.println(model, 0);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCallbackType("closeCurrent");
		jsonResponse.setMessage("保存成功");
		jsonResponse.setStatusCode(200);
		pushStack(jsonResponse);
		return JSON;
	}
	public void exportExcel() throws Exception{
		throw new NotImplementException("暂无实现该功能");
	}
	public void delete() throws Exception{
		ID[] ids = getIds();
		if(ids==null){
			throw new InvalidOperationException("请先选择要删除的数据");
		}
		ArrayList<T> arrayList = new ArrayList<T>();
		T newInstance = null;
		for(ID id:ids){
			newInstance = entityClass.newInstance();
			JPAUtils.setPrimaryKey(newInstance, id);
			arrayList.add(newInstance);
		}
		getBaseService().delete(arrayList);
	}
	public Pageable getPageable(){
		return new Pageable(){

			@Override
			public int getPageNumber() {
				return pageNum;
			}

			@Override
			public int getPageSize() {
				return numPerPage;
			}

			@Override
			public int getOffset() {
				return numPerPage*(pageNum-1);
			}

			@Override
			public Sort getSort() {
				if(StringUtils.isBlank(orderField)){
					orderField = JPAUtils.getPrimaryFieldName(entityClass);
				}
				return new Sort(Direction.ASC,orderField);
			}};
	}
	public abstract BaseService<T, ID> getBaseService();
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
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
