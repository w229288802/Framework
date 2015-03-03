package com.welge.framework.utils;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 用于JPA的工具类
 * @author haha
 *
 */

public class JPAUtils {
	/**
	 * 设置 {@link Entity}的主键
	 * @param object
	 * @param value
	 */
	public static void setPrimaryKey(Object object,Object value){
		Class<? extends Object> clazz = object.getClass();
		for(Field field:clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(Id.class)){
				try {
					field.set(object, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}return;
			}
		}
	}
	public static String getPrimaryFieldName(Class<?> clazz){
		for(Field field:clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(Id.class)){
				try {
					return field.getName();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} 
			}
		}
		return null;
	}
	public static Serializable getPrimaryKey(Object object){
		Class<? extends Object> clazz = object.getClass();
		for(Field field:clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(Id.class)){
				try {
					return (Serializable) field.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
