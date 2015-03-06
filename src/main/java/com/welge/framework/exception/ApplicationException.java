package com.welge.framework.exception;

import javax.servlet.ServletException;

public class ApplicationException extends ServletException{

	private static final long serialVersionUID = 1L;
	
	public ApplicationException(String msg){
		super(msg);
	}
}
