package com.welge.framework.loader;  

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/** 
 * 创建可以动态更新的java对象<br> 
 * 限制:构造函数不能有参数 
 * 必须实现一个接口 
 * 只能有实例方法(因为接口中不能有静态方法) 
 * @author xuwei 
 * Jul 9, 2008 12:01:00 PM 
 */  
public class ClassManager {  
    /** 
     * 保存类路径和时间 
     */  
    private  Map mapModify = new HashMap();  
    /** 
     * 该类被加载时的时间 
     */  
    private  Date firstDate = new Date();  

    /** 
     *  检测类变化的时机,创建类时检查
     * 	
     * 
     * 如果class文件重新生成过会自动加载,只有重新创建才会更新 
     *  
     * @param name 
     * @return 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */  
    public Object getInstance(String name) throws InstantiationException,  
            IllegalAccessException, ClassNotFoundException, IOException {  
        Class c = Class.forName(name);  
        Class cNew = reloadClass(c);  
        if (cNew == null) {  
            cNew = c;  
        }  
        Object o = cNew.newInstance();  
        return o;  
    }  

    /** 
     * 创建代理对象,如果class文件重新生成过会自动加载,调用原先以实例化的方法时也会更新类 
     *  
     * @param name 
     * @return 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */  
    public Object getInstanceProxy(String name) throws InstantiationException,  
            IllegalAccessException, ClassNotFoundException, IOException {  
        Object target = getInstance(name);  

        DynamicProxyFactory factory = new DynamicProxyFactory(  
                new HotInvocationHandler(this));  

        return factory.newProxyInstance(target);  
    }  

    /** 
     *  如何重新加载类,重新加载class示例代码
     * 重新加载类 
     *  
     * @param c 
     * @return 
     * @throws IOException 
     */  
    public synchronized Class reloadClass(Class c) throws IOException {  
        Class cNew = null;  
        if (hasChanged(c)) {  
            cNew = loadClass(c);  
        }  
        return cNew;  
    }  
    /**
     * 检测类变化的方法  判断类创建的时间如变化则重新加载
     * @param c
     * @return
     * @throws IOException
     */
    public  boolean hasChanged(Class c) throws IOException {  
        boolean isChanged = false;  
        String name = c.getSimpleName();
        Class enclosingClass = c.getEnclosingClass();
        if(enclosingClass!=null){
        	name = enclosingClass.getSimpleName()+"$"+c.getSimpleName();
        }
        //匿名内部类
        if(name.endsWith("$")){
        	return false;
        }
        String path = c.getResource(name + ".class").getPath();  
        File f = new File(path);  
        if (f.exists()) {   
            Date newDate = new Date(f.lastModified());  
            Date oldDate = null;  
            String key = f.getCanonicalPath();  
            if (mapModify.containsKey(key)) {  
                oldDate = (Date) mapModify.get(key);  
            } else {  
                oldDate = firstDate;  
            }  
            isChanged = oldDate.compareTo(newDate) < 0;  
            if (isChanged) {  
                mapModify.put(key, newDate);  
            }  
        }  
        return isChanged;  
    }  
    public  Set<Class> getChangedClasses(Set<Class> oldSet) throws IOException{
    	HashSet<Class> classSet = new HashSet<Class>();
    	for(Class clazz :oldSet){ 
    		if(hasChanged(clazz)){
    			Class loadClass = loadClass(clazz);
    			classSet.add(loadClass);
    		}
    	}
    	return classSet;
    }
    private Class loadClass(Class c) throws IOException {  
    	ClassLoaderAdvisor classLoader = new ClassLoaderAdvisor(ClassLoader.getSystemClassLoader());  
        Class cNew = classLoader.loadClass(c);  
        return cNew;  
    }  

}  