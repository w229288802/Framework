package com.welge.framework.web;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T,ID extends Serializable> extends ActionSupport implements ModelDriven<T> {
	//JSON格式返回类型
	protected static final String JSON="json";
	private Logger logger ;
	public Logger getLogger() {
		return logger;
	}


	private static final long serialVersionUID = 1L;
	protected T entity;
	private Class<T> entityClass;
	

	@SuppressWarnings("unchecked")
	public BaseAction(){
		
		ParameterizedType parameterizedType = ((ParameterizedType)this.getClass().getGenericSuperclass());
		entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(getClass());
		
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
