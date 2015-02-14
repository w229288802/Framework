package com.welge.sys.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.welge.framework.vo.easyui.DataGrid;
import com.welge.framework.web.BaseAction;
import com.welge.sys.entity.SysDepartment;
import com.welge.sys.repository.SysDepartmentRepository;
@Namespace("/sys/department")
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SysDepartmentAction extends BaseAction<SysDepartment,String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private  SysDepartmentRepository sysDepartmentRepository;
	public DataGrid datagrid ;
	public DataGrid getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(DataGrid datagrid) {
		this.datagrid = datagrid;
	}
	public String list(){
		//System.out.println(this);
		//SysDepartment one = sysDepartmentRepository.findOne("1");
		////SysDepartment one = new SysDepartment();
		List<SysDepartment> list = sysDepartmentRepository.findAll();
		//ArrayList arrayList = new ArrayList();
		//arrayList.add(one);
		DataGrid  datagrid= new DataGrid();
		datagrid.setTotals(1L);
		datagrid.setRows(list);
		//entity.setName("张三");
		ActionContext.getContext().getValueStack().push(datagrid);
		//WebUtils.writeObjectToReponse(d);
		getLogger().debug("成功");
		return "json";
	}
	public SysDepartmentRepository getSysDepartmentRepository() {
		return sysDepartmentRepository;
	}
	public void setSysDepartmentRepository(
			SysDepartmentRepository sysDepartmentRepository) {
		this.sysDepartmentRepository = sysDepartmentRepository;
	}
}
