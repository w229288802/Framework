package com.welge.framework.utils;

import org.springframework.dao.DataIntegrityViolationException;

import com.welge.framework.exception.ApplicationException;

public class ExceptionUtils {
	public static Throwable handle(Throwable e){
		if(e instanceof DataIntegrityViolationException){
			//String message2 = e.getCause().getCause().getMessage();
			//System.out.println(e.getCause().getMessage());
			if(e.getCause()!=null&&e.getCause().getCause()!=null){
				String message = e.getCause().getCause().getMessage();
				Integer start = message.indexOf("'");
				int end = message.lastIndexOf("'");
				if(start!=null&&message.startsWith("Data truncation: Data too long for column ")){
					return new ApplicationException(message.substring(start+1,end)+"数据太长");
				}
			}
		}
		return e;
	}
}
