package com.welge.framework.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.welge.framework.filter.ExceptionHandlerFilter;
/**
 * 把Struts的所有异常捕获，交给{@link ExceptionHandlerFilter} 处理,包括<br/>
 * 1、没有结果页面异常
 * 2、业务异常
 *
 * @author welge
 *
 */
public class ExceptionHandlerInterceptor implements Interceptor{
	public static final String EXCEPTION="StrutsException";
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		try{
			result = invocation.invoke();
		}catch (Exception e) {
			HttpServletRequest request = ServletActionContext.getRequest();
			//把异常对
			request.setAttribute(ExceptionHandlerFilter.EXCEPTION, e);
			//Struts默认结果，表示执行成果，无视图
			result=Action.NONE;
		}
		return result;
	}

}
