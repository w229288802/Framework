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

import org.codehaus.jackson.map.ObjectMapper;

import com.welge.framework.exception.dwz.DWZException;
import com.welge.framework.exception.dwz.InvalidOperationException;
import com.welge.framework.exception.dwz.NoLoginException;
import com.welge.framework.exception.dwz.NotImplementException;
import com.welge.framework.utils.LogUtils;
import com.welge.framework.vo.dwz.JsonResponse;
/**
 * 异常处理
 * 1、设置HTTP状态为500，服务器内部错误
 * 2、打印异常栈
 * @author welge
 *
 */
public class ExceptionHandlerFilter implements Filter{
	public final Integer USER_OPERATION_EXCEPTION_HTTP_STATUS=210;
	
	public static final String  EXCEPTION = "ExceptionHandlerFilterxception";

	private static final Integer DWZ_STATE_INVALID_OPERATION = 400;

	private static final Integer DWZ_STATE_NOLOGIN = 401;

	private static final Integer DWZ_STATE_NOT_IMPLEMENT = 402;

	private static final Integer DWZ_STATE_ERROR = 300;
	
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
			//Struts的异常
			Object error_object = request.getAttribute(ExceptionHandlerFilter.EXCEPTION);
			if(error_object instanceof Exception){
				err = (Exception) error_object;
			}
			if(err!=null){
				JsonResponse jsonResponse = new JsonResponse();
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("text/html");
				String msg =((Exception)err).getMessage()+"";
				jsonResponse.setMessage(msg);
				
				//当前没有登录
				if(err instanceof NoLoginException){
					jsonResponse.setStatusCode(DWZ_STATE_NOLOGIN);
				}
				//当前用户操作异常
				else if(err instanceof InvalidOperationException){
					jsonResponse.setStatusCode(DWZ_STATE_INVALID_OPERATION);
				}
				//其它异常
				else if (err instanceof NotImplementException){
					jsonResponse.setStatusCode(DWZ_STATE_NOT_IMPLEMENT);
				}
				else if(err instanceof DWZException){
					jsonResponse.setStatusCode(DWZ_STATE_ERROR);
				}
				if(!(err instanceof DWZException )){
					((HttpServletResponse)response).setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
					err.printStackTrace();
				}else{
					msg = mapper.writeValueAsString(jsonResponse);
				}
				
				//输出异常信息
				PrintWriter writer = response.getWriter();
				writer.print(new String(msg.getBytes("utf8"),"ISO8859-1"));
			}
		}
	}

	@Override
	public void destroy() {
		LogUtils.debug("exception handler filter destory");
	}
	
}
