package com.welge.framework.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	
	private static final long serialVersionUID = 1L;
	protected T entity;
	private Class<T> entityClass;
	

	@SuppressWarnings("unchecked")
	public BaseAction(){
		ParameterizedType parameterizedType = ((ParameterizedType)this.getClass().getGenericSuperclass());
		entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		try {
			entity = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return entity;
	}

}
