package com.welge.framework.web;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public  class BaseStrutsAction<T,ID extends Serializable> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	//JSON格式返回类型
	protected static final String JSON="json";
	//返回编辑页页
	protected static final String EDIT="edit";
	//当前Action的相对路径
	protected static final String REQUEST_ACTIONPATH = "actionPath";
	
	protected ID[] ids ;
	private Logger logger ;
	protected T entity;
	protected Class<T> entityClass;
	
	public BaseStrutsAction(){
		//设置请求的Action路径到Request中
		setActionPath();
		//初始化实体类型和实体对象
		initParameterizedType();
		//实例化一个日志对象
		logger = LoggerFactory.getLogger(getClass());
	}
	
	/**
	 * 设置请求的Action路径到Request中
	 */
	public void setActionPath(){
		String requestURI = getRequest().getRequestURI();
		getRequest().setAttribute(REQUEST_ACTIONPATH,requestURI.replaceAll("(!\\w*)?(.action|.do)", ""));
	}
	
	
	/**
	 * 初始化实体类型和实体对象
	 */
	@SuppressWarnings("unchecked")
	private void initParameterizedType(){
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
		try {
			entity = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	/**
	 * 放入Struts栈顶
	 * @param object
	 */
	protected void pushStack(Object object){
		ActionContext.getContext().getValueStack().push(object);
	}
	/**
	 * 得到Struts值栈
	 * @return
	 */
	protected ValueStack getValueStack(){
		return ActionContext.getContext().getValueStack();
	}
	@Override
	public T getModel() {
		return entity;
	}
	public Logger getLogger() {
		return logger;
	}
	public ID[] getIds() {
		return ids;
	}

	public void setIds(ID[] ids) {
		this.ids = ids;
	}
}
