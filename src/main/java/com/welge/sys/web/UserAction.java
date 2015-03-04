package com.welge.sys.web;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
import com.welge.framework.service.CommonService;
import com.welge.framework.web.BaseStrutsActionPageable;
import com.welge.sys.entity.User;
import com.welge.sys.service.UserService;
@Namespace("/sys/user")
public class UserAction extends BaseStrutsActionPageable<User, String>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;
	@Override
	public BaseService<User, String> getBaseService() {
		return userService;
	}
	
}
