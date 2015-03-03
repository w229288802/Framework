package com.welge.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	private static final String DATE_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	private static SimpleDateFormat sdf ;
	private static SimpleDateFormat getDataFormat(){
		synchronized (DateTimeUtils.class) {
			if(sdf == null){
				sdf =  new SimpleDateFormat(DATE_FORMAT);
			}
		}
		return sdf;
	}
	
	public static String getCurrentDateTime(){
		return getDataFormat().format(new Date());
	}
}
