package com.welge.framework.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

public class _PrintUtils {
	
	private static String HOLDERPLACE=" | ";
	
	static long startTime ;
	static long endTime;
	private static Logger logger = Logger.getLogger(_PrintUtils.class);
	
	public static void start(){
		startTime = System.currentTimeMillis();
	}
	public static void end(){
		endTime = System.currentTimeMillis();
		System.out.println("时间为"+(endTime-startTime)+"毫秒");
	}
	
	public static void println(Object[] objs, int child,String... fieldNames){
		List<Object> asList = Arrays.asList(objs);
		_Print p = new _Print(child);
		try {
			p.println(asList,null, fieldNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void println(Object obj ,int child,String... fieldNames){
		
		try {
			_Print p = new _Print(child);
			if(obj instanceof Collection){
				p.println((Collection<?>)obj,null, fieldNames);
			} else if(obj instanceof Map){
				p.println((Map<?,?>)obj);
			}
			else{
				p.println(obj,null,fieldNames);
			}
			System.out.println(p.sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 该类用于格式化打印
	 * 该类每次都需要创建
	 *
	 */
	private static class _Print{
		private static final String COLLECTION_START_FLAG = "[";
		private static final String OBJECT_START_FLAG = "{";
		private static final String MAP_START_FLAG = "<";
		private String preIndex="" ;
		private StringBuilder sb = new StringBuilder();
		private int index=0;
		private int child;
		private String startDescription="";
		private LinkedList<String> stack = new LinkedList<String>();
		
		public  _Print(int child){
			this.child = child;
		}
		public void println(Map<?,?> map,String... fieldNames) throws Exception{
			Set<?> entrySet = map.entrySet();
			startFlag(MAP_START_FLAG); 
			for(Object obj :entrySet){
				Entry<?,?> entry=(Entry<?,?>) obj;
				Object value = entry.getValue();
				if(value instanceof Collection){
					println((Collection<?>)value,entry.getKey()+"=", fieldNames);
				}
				else if(value instanceof Serializable){
					middleContent(entry.getKey()+"=\""+value+"\"");
				}
				else if(value instanceof Map){
					println((Map<?,?>)value);
				}
				else{
					println(value,null,fieldNames);
				}
			}
			endFlag();
			
			
		}
		public  void println(Collection<?> collection,String desc,String... fieldNames) throws Exception{
			if(collection==null||collection.size()==0){
				logger.debug("集合为空");
			}
			int count =0;
			startDescription(desc==null?"":desc);
			startFlag(COLLECTION_START_FLAG);
			for(Object obj:collection){

				if(obj instanceof Collection){
					println(((Collection<?>)obj),null, fieldNames);
				}
				else if(obj instanceof Serializable){
					middleContent(obj);
				}
				else if(obj instanceof Map){
					middleContent(obj);
				}else if(obj instanceof Class){
					sb.append(((Class<?>)obj).getSimpleName());
				}else{
					println(obj,++count+"");
				}
			}
			endFlag();
		}
		private  void middleContent(Object content ){
			
			for(int i=0;i<index;i++){
				sb.append(HOLDERPLACE);
			}
			sb.append(content);
			sb.append("\n");
		}
		private  void startFlag(String startFlag) throws Exception{
			stack.push(startFlag);
			if(index<0){
				throw new Exception("开始位置出错");
			}
			for(int i=0;i<index;i++){
				sb.append(HOLDERPLACE);
			}
			sb.append(startDescription);
			startDescription="";
			index++;
			sb.append(startFlag);
			sb.append("\n");
		}
		private void startDescription(String desc){
			startDescription = desc;
		}
		private String getEndFlag(String startFlag) throws Exception{
			if(startFlag=="{")return "}";
			else if(startFlag=="[")return "]";
			else if(startFlag=="<")return ">";
			else throw new Exception("没有找到"+startFlag+"的结束标记");
		}
		private  void endFlag() throws Exception{
			String endFlag=getEndFlag(stack.pop());
			index--;
			for(int i=0;i<index;i++){
				sb.append(HOLDERPLACE);
			}
			sb.append(endFlag);
			sb.append("\n");
		}
		public void println(Object obj,String index,String... fieldNames) throws Exception{
			child--;
			Field[] fields = null;  
			if(obj instanceof Class){
				Class<?> forName = null;
				try { 
					forName = Class.forName(((Class<?>) obj).getName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				fields = forName.getDeclaredFields();
			}else{
				fields= obj.getClass().getDeclaredFields();     
			}
			if(index!=null){
				startDescription("第"+preIndex+index+"个"+obj.getClass().getSimpleName()+"=");
			}else{
				startDescription(obj.getClass().getSimpleName()+"=");
			}
			startFlag(OBJECT_START_FLAG);
			for(Field field :fields){
				field.setAccessible(true);
				if(Collection.class.isAssignableFrom(field.getType())&&child>0){
					preIndex+=index+".";
					println((Collection<?>)field.get(obj),null);
					preIndex=preIndex.substring(0,preIndex.lastIndexOf(".")-1);
				}else{
					if(fieldNames.length==0||(Arrays.asList(fieldNames).contains(field.getName()))) {
						Object value ="";
						if(!(obj instanceof Class)){
							value ="=" +field.get(obj);
						}
						middleContent( field.getType().getSimpleName()+" " + field.getName()+value);
					}
				}
			}
			endFlag();
			
		}
	}
	
	
}
