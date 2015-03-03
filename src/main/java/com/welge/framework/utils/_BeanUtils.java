package com.welge.framework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class _BeanUtils {
	public static Object getFieldValue(Object object ,String fieldName) throws Exception{
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			throw new Exception("无法找到类<font color=\"red\">"+object.getClass().getSimpleName()+"</font>的字段："+fieldName);
		}
	}
	public static String getFieldNameByType(Class clazz,final Class type) throws Exception{
		Field[] fields = clazz.getDeclaredFields();
		List<String>  list= new ArrayList();
		for(Field field:fields){
			if(type==field.getType()){
				list.add(field.getName());
			}
		}
		if(list.size()>1){
			throw new Exception(clazz.getSimpleName()+"的"+type.getSimpleName()+"类型字段多于一个");
		}
		return list.iterator().next();
	}
}
