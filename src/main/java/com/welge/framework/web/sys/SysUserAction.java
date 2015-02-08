package com.welge.framework.web.sys;


import org.apache.struts2.convention.annotation.Namespace;

import com.welge.framework.model.SysUser;
import com.welge.framework.web.base.BaseAction;
@Namespace("/sys/user")
public class SysUserAction extends BaseAction<SysUser>{
	public String kk(){
		return INPUT;
	}
}
