package com.welge.framework.exception;

/**
 * 没有登录异常
 * @author haha
 *
 */
public class NoLoginException extends AppcationException{
	private static final long serialVersionUID = 1L;

	public NoLoginException(){
		super("没有登录，或者超时");
	}
}
