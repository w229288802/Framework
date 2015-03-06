package com.welge.framework.exception.dwz;

/**
 * 没有登录异常
 * @author @c welge
 *
 */
public class NoLoginException extends DWZException{
	private static final long serialVersionUID = 1L;

	public NoLoginException(){
		super("没有登录，或者超时");
	}
}
