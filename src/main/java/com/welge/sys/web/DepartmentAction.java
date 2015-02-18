package com.welge.sys.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.welge.framework.vo.dwz.JsonTable;
import com.welge.framework.web.BaseStrutsAction;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Department;
import com.welge.sys.repository.SysDepartmentRepository;
@Namespace("/sys/department")
public class DepartmentAction extends BaseStrutsActionPageable<Department,String>{

	private static final long serialVersionUID = 1L;
	@Autowired
	private  SysDepartmentRepository sysDepartmentRepository;
	public JsonTable datagrid ;
	public JsonTable getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(JsonTable datagrid) {
		this.datagrid = datagrid;
	}
	public class cc{
		public String iiii;
	}
	
	public String list(){
		//System.out.println(this);
		//SysDepartment one = sysDepartmentRepository.findOne("1");
		////SysDepartment one = new SysDepartment();
		List<Department> list = new ArrayList<Department>();
		if(getPageNum()==null||getPageNum()==1){
			 list= sysDepartmentRepository.findAll();
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
		return JSON;
	}
	public SysDepartmentRepository getSysDepartmentRepository() {
		return sysDepartmentRepository;
	}
	public void setSysDepartmentRepository(
			SysDepartmentRepository sysDepartmentRepository) {
		this.sysDepartmentRepository = sysDepartmentRepository;
	}
}
