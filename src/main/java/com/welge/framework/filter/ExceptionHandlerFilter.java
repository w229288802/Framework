package com.welge.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.welge.framework.utils.LogUtils;
/**
 * 异常处理
 * 1、设置HTTP状态为500，服务器内部错误
 * 2、打印异常栈
 * @author welge
 *
 */
public class ExceptionHandlerFilter implements Filter{
	public static final String  EXCEPTION = "ExceptionHandlerFilterxception";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LogUtils.debug("exception handler filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Exception err = null;
		try{
			chain.doFilter(request, response);
		}catch (Exception e) {
			err=e;
		}finally{
			Object error_object = request.getAttribute(ExceptionHandlerFilter.EXCEPTION);
			if(error_object instanceof Exception){
				err = (Exception) error_object;
			}
			if(err!=null&&err.getMessage().endsWith("anonymousUser")){
				((HttpServletResponse)response).setStatus(HttpServletResponse.SC_BAD_GATEWAY);
				return;
			}
			if(err!=null){
				PrintWriter writer = response.getWriter();
				response.setContentType("text/html");
				((HttpServletResponse)response).setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
				writer.print(((Exception)err).getMessage());
				err.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		LogUtils.debug("exception handler filter destory");
	}
	
}
