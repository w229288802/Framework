package com.welge.sys.web;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
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
	
}
