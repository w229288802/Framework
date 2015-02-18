package com.welge.framework.utils;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class LogUtils{
	private static Logger loger = LoggerFactory.getLogger(Logger.class);
	public static void debug(String msg){
		loger.debug(msg);
	}
	public static void info(String msg){
		loger.info(msg);
	}
	public static void warn(String msg){
		loger.warn(msg);
	}
	public static void error(String msg){
		loger.error(msg);
	}
}
