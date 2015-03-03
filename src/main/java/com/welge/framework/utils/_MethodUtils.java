package com.welge.framework.utils;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class _MethodUtils {
	/**
	 * 查找类类型是否有指定方法
	 * @param clazz 类类型
	 * @param methodName 指定方法
	 * @param recursive 是否查找父类型
	 * @return 是否有该方法
	 */
	public static  boolean haveMethod(Class clazz,String methodName,boolean recursive){
		while(clazz!=Object.class&&recursive){
			try {
				clazz.getDeclaredMethod(methodName);
				return true;
			} catch (Exception e) {} 
			clazz=clazz.getSuperclass();
		}
		return false;
	}
}
