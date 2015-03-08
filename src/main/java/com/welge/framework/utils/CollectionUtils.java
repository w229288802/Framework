package com.welge.framework.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtils {
	public static Collection<?> transform(Collection<?> list){
		return null;
	}
	public static Collection<?> getFieldFrom(Collection<?> set,String FieldName){
		ArrayList arrayList = new ArrayList();
		for(Object o:set){
			try {
				Object object = o.getClass().getField(FieldName).get(o);
				arrayList.add(o);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}	
