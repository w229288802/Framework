package com.welge.sys.web;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
import com.welge.framework.vo.dwz.JsonTable;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Department;
import com.welge.sys.service.DepartmentService;
@Namespace("/sys/department")
public class DepartmentAction extends BaseStrutsActionPageable<Department,String>{

	private static final long serialVersionUID = 1L;
	private DepartmentService departmentService;
	public JsonTable datagrid ;
	public JsonTable getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(JsonTable datagrid) {
		this.datagrid = datagrid;
	}
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@Override
	public BaseService<Department, String> getBaseService() {
		return departmentService;
	}
}
