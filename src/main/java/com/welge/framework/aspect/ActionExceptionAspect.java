package com.welge.framework.aspect;

import java.io.IOException;

import javax.servlet.ServletException;

import com.welge.framework.utils.LogUtils;

public class ActionExceptionAspect extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void tho(Throwable ex) throws IOException, ServletException{
		LogUtils.info(" an internal exception occured in StrutsAction");
	}
}
