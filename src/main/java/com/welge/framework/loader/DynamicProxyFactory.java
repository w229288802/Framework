package com.welge.framework.loader;  

import java.lang.reflect.Proxy;  

/** 
 * java代理工厂实现 
 * @author xuwei 
 * Jul 9, 2008 12:02:48 PM 
 */  
public class DynamicProxyFactory {  
    /* 
     * 方法处理者 
     */  
    private DefaultInvocationHandler invocationHandler;  

    public DynamicProxyFactory() {  
        this(null);  
    }  

    /** 
     *  
     * @param invocationHandler 
     */  
    public DynamicProxyFactory(DefaultInvocationHandler invocationHandler) {  
        if (invocationHandler == null) {  
            this.invocationHandler = new DefaultInvocationHandler();  
        } else {  
            this.invocationHandler = invocationHandler;  
        }  
    }  

    /** 
     * 创建代理对象 
     * @param target 
     * @return 
     */  
    public Object newProxyInstance(final Object target) {  
        invocationHandler.setTarget(target);  
        Object proxy = Proxy.newProxyInstance(target.getClass()  
                .getClassLoader(), target.getClass().getInterfaces(),  
                invocationHandler);  
        return proxy;  
    }  
}  