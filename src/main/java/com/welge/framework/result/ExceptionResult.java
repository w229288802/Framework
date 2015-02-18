package com.welge.framework.result;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.welge.framework.interceptor.ExceptionHandlerInterceptor;
/**
 * 
 */
public class ExceptionResult  extends StrutsResultSupport{

	
	private static final long serialVersionUID = 1L;

	@Override
	public void doExecute(String arg0,ActionInvocation invocation) throws Exception {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.debug(getClass().getSimpleName()+"初调用");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		writer.print(request.getAttribute(ExceptionHandlerInterceptor.EXCEPTION));
	}

}
