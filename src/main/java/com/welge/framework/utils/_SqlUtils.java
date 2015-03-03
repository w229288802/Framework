package com.welge.framework.utils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class _SqlUtils {
	public static String BuildOrderSql(java.util.Map<String, String> orderBy) {
		StringBuffer obSql = new StringBuffer(" ");
			if(orderBy!=null){
			Set<Entry<String, String>> entrySet = orderBy.entrySet();
			for(Entry<String,String> one:entrySet){
				obSql.append(one.getKey()+" ");
				obSql.append(one.getValue()+" ");
			}
		}
		return obSql.toString();
	}

	public static void CheckWhereSql(String whereSql) {
	}

	public static String BuildLikeSql(Map<String, String> like) {
		StringBuffer likeSql = new StringBuffer("WHERE ");
		Set<Entry<String, String>> entrySet = like.entrySet();
		for(Entry<String,String> one : entrySet){
			likeSql.append(one.getKey()+" like "+"'%"+one.getValue()+"%'");
			likeSql.append(" AND ");
		}
		return  likeSql.delete(likeSql.length()-5, likeSql.length()-1).toString();
	}

	public static String BuildConditions(Object like,Class<?> source) {
		Field[] fields = like.getClass().getDeclaredFields();
		StringBuffer conditionSql = new StringBuffer();
		HashMap<String,String> conditions = new HashMap<String, String>();
		try {
			for(Field field:fields){
				field.setAccessible(true);
				if(field.getType()==String.class){
					String conditon = (String) field.get(like);
					if(conditon!=null){
						conditions.put(field.getName(),conditon);
					}
				}
			}
			fields = source.getDeclaredFields();
			for(Field field:fields){
				String fieldName = (String) field.getName();
				if(conditions.containsKey(fieldName)){
					conditionSql.append(fieldName + " like '%" +conditions.get(fieldName)+"%'");
					conditionSql.append(" AND ");
				}
			}
			return conditionSql.substring(0, conditionSql.length()-5);
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}
}
