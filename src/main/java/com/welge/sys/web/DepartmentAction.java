package com.welge.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.sun.tools.javac.comp.Todo;
import com.welge.framework.service.BaseService;
import com.welge.framework.vo.dwz.JsonTable;
import com.welge.framework.web.BaseStrutsAction;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Department;
import com.welge.sys.repository.DepartmentRepository;
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
	
	public String list(){
		//System.out.println(this);
		//SysDepartment one = sysDepartmentRepository.findOne("1");
		////SysDepartment one = new SysDepartment();
		List<Department> list = new ArrayList<Department>();
		if(getPageNum()==null||getPageNum()==1){
			list= departmentService.getListAll(getPageable());
		}
		//ArrayList arrayList = new ArrayList();
		//arrayList.add(one);
		JsonTable  datagrid= new JsonTable();
		datagrid.setTotals(1L);
		datagrid.setRows(list);
		//entity.setName("张三");
		ActionContext.getContext().getValueStack().push(datagrid);
		//WebUtils.writeObjectToReponse(d);
		getLogger().debug("成功");
		int i =1/0;
		return JSON;
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
