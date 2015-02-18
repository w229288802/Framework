package com.welge.sys.web;

import org.apache.struts2.convention.annotation.Namespace;

import com.welge.framework.vo.dwz.JsonResponse;
import com.welge.framework.web.BaseStrutsAction;

@Namespace("/sys/menu")
public class MenuAction extends BaseStrutsAction<Object, Long>{
	private static final long serialVersionUID = 1L;

	public String input(){
		return INPUT;
	}
	public String save(){
		JsonResponse ajaxResponse = new JsonResponse();
		ajaxResponse.setNavTabId("navTab");
		ajaxResponse.setStatusCode(200);
		ajaxResponse.setCallbackType("closeCurrent");
		ajaxResponse.setMessage("操作成功");
		pushStack(ajaxResponse);
		return JSON;
	}
}
