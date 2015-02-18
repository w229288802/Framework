package com.welge.framework.web;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public  class BaseStrutsAction<T,ID extends Serializable> extends ActionSupport implements ModelDriven<T> {
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
	public BaseStrutsAction(){
		
		Type superClass= (Type) this.getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {  
		  entityClass = (Class<T>) ((ParameterizedType) superClass)  
                    .getActualTypeArguments()[0];  
        } else if (((Class<?>) superClass).getGenericSuperclass() instanceof ParameterizedType) {  
        	//由CGLIB代理的代理类会继承基类，所以代理类的父类的父类才是BaseAction
        	entityClass = (Class<T>) ((ParameterizedType) ((Class<?>) superClass)  
                    .getGenericSuperclass()).getActualTypeArguments()[0];  
        } else {  
        	entityClass = (Class<T>) ((ParameterizedType) ((Class<?>) ((Class<?>) superClass)  
                    .getGenericSuperclass()).getGenericSuperclass())  
                    .getActualTypeArguments()[0];  
        }  
		logger = LoggerFactory.getLogger(getClass());
		try {
			entity = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public void pushStack(Object object){
		ActionContext.getContext().getValueStack().push(object);
	}
	public ValueStack getValueStack(){
		return ActionContext.getContext().getValueStack();
	}
	@Override
	public T getModel() {
		return entity;
	}
	public void set_(String _){
		
	}
}
