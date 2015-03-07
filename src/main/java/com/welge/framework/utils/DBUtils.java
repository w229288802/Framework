package com.welge.framework.utils;

import java.util.Formatter;
import java.util.Random;

public class DBUtils {
	private static Random random = new Random();
	public static String generateBeanID(){ 
		long currentTimeMillis = System.currentTimeMillis();
		return String.valueOf(currentTimeMillis)+String.format("%1$02d",random.nextInt(100));
	}
}
