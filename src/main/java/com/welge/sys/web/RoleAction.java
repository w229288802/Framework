package com.welge.sys.web;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.Role;
import com.welge.sys.service.PermissionService;
import com.welge.sys.service.RoleService;

@Namespace("/sys/role")
public class RoleAction extends BaseStrutsActionPageable<Role, String>{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	@Override
	public BaseService<Role, String> getBaseService() {
		return roleService;
	}
	
}
