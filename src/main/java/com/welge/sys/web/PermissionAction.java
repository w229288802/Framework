package com.welge.sys.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.welge.framework.service.BaseService;
import com.welge.framework.vo.dwz.JsonTable;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Permission;
import com.welge.sys.service.PermissionService;

@Namespace("/sys/permission")
public class PermissionAction extends BaseStrutsActionPageable<Permission, String>{

	private static final long serialVersionUID = 1L;
	@Autowired
	private PermissionService permissionService;
	@Override
	public BaseService<Permission, String> getBaseService(){
		return permissionService;
	}
	public String menu(){
		return "menu";
	};
	public String listAjax(){
		List<Map<String, Object>> list = permissionService.getListByRoleId(getId());
		pushStack(list);
		return JSON;
	}
	public String listTable(){
		JsonTable jsonTable = new JsonTable();
		List<Map<String, Object>> list = permissionService.getListByRoleId(getId());
		jsonTable.setRows(list);
		pushStack(jsonTable);
		return JSON;
	}
	
	public String grant(){ 
		permissionService.grantPermission(getId(), getIds(),getIds_());
		success("授权成功");
		return JSON;
	}
	
}
