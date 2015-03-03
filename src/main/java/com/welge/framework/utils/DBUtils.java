package com.welge.framework.utils;

import java.util.Random;

public class DBUtils {
	private static Random random = new Random();
	public static String generateBeanID(){
		
		long currentTimeMillis = System.currentTimeMillis();
		return String.valueOf(currentTimeMillis)+random.nextInt(100);
	}
}
