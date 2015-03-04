package com.welge.framework.exception;

import javax.servlet.ServletException;

public class AppcationException extends ServletException{

	private static final long serialVersionUID = 1L;
	
	public AppcationException(String msg){
		super(msg);
	}
}
