package com.welge.sys.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.service.BaseService;
import com.welge.framework.service.CommonService;
import com.welge.framework.utils.PoiUtils;
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
	/*public void exportExcel() throws Exception{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("username","用户名" );
		getResponse().setContentType("application/vnd.ms-excel");   
		getResponse().setHeader("Content-Disposition", "attachment; filename=a.xls" );  
		//PrintWriter writer = getResponse().getWriter();
		List<User> listAll = getBaseService().getListAll();
		File file = new File("F:/1.xls");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		PoiUtils.exportExcel2003(User.class, hashMap, listAll, getResponse().getOutputStream());
		fileOutputStream.close();
      //  writer.flush();
	}*/
}
