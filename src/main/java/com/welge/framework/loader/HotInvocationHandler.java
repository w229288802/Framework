package com.welge.framework.loader;  

import java.lang.reflect.Method;

/** 
 * 拦截java方法，更新新的类 
 * @author xuwei 
 * Jul 9, 2008 12:02:26 PM 
 */  
public class HotInvocationHandler extends DefaultInvocationHandler {  

    private ClassManager manager;  

    public HotInvocationHandler(ClassManager manager) {  
        this.manager = manager;  
    }  

    /** 
     * 
     *  如何更新原来已创建的对象
     *  
	 * 	当class发生改变时可以更新生成新的Class类从而创建新的对象，但是原来已创建的对象怎么办呢，
	 * 	可以用委托实现，代码同上，此处拦截类其实是个委托类，他把方法转给target对象，
	 * 	并在类更新时重新定义target的引用，而从外部调用看是感觉不到这点的。
	 * 
     * 	在调用类时判断该类是否重新编译过，如编译过则调用新类的方法 
     */  
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object targetObject = null;  
        Class c = target.getClass();  
        Class cNew = manager.reloadClass(c);  
        if (cNew == null) {  
            targetObject = target;  
        } else {  
            targetObject = cNew.newInstance();  
            this.setTarget(targetObject);  
        }  
        Object returnValue = method.invoke(targetObject, args);  
        return returnValue;  
    }  

}  