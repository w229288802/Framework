package com.welge.sys.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Permission;
import com.welge.sys.service.PermissionService;

@Namespace("/sys/menu")
public class MenuAction extends BaseStrutsActionPageable<Permission, String>{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public BaseService<Permission, String> getBaseService() {
		return permissionService;
	}
	
	public String listAjax(){
		List<Permission> list = getBaseService().getListAll();
		pushStack(list);
		return JSON;
	}
	
}
