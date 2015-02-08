package com.welge.mis.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.welge.framework.action.BaseAction;
import com.welge.framework.model.SysUser;
@Namespace("/sysuser")
public class SysUserAction extends BaseAction<SysUser>{
	public String s(){
		return SUCCESS;
	}
}
