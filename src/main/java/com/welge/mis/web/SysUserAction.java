package com.welge.mis.web;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.welge.framework.model.SysUser;
import com.welge.framework.web.BaseAction;
@Namespace("/sys/user")
public class SysUserAction extends BaseAction<SysUser>{
	public String s(){
		return INPUT;
	}
}
