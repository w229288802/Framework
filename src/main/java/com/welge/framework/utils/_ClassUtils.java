package com.welge.framework.utils;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.welge.framework.loader.ClassManager;


public class _ClassUtils {
//	private static String CLASS_PATH = ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/");
	private static String EXPERSSION_CLASS_FILE= "*.class";
	private static String PATTERN_CLASS_PACKAGE = "^([\\w|*]+.)+[\\w|*]$";
	/**
	 * 把包名转换成路径
	 * @param packageName
	 */
	public static String packageName2URLPath(String packageName){
		return packageName.replaceAll("\\.", "/");
	}
	public static String packageName2WindowPath(String packageName){ 
		return packageName.replaceAll("\\.", "\\\\");
	}
	/**
	 * 把路径转换成包名
	 * @param path
	 */
	public static String path2PackageName(String path){
		return path.replaceAll("\\\\", ".").replaceAll("/", ".");
	}
	/**
	 * 通过包名得到类的集合
	 * @param packageName
	 */
	public static Set<Class<?>> getClassByPackageName(String packageName) throws Exception{
		return getClassByPackageName(packageName,null);
	}
	public static Set<Class<?>> getClassByPackageName(final String packageName,final ClassChangedClassCallBack callback) throws Exception{
		//return getClassByPackageName(packageName, callback ,ConfigManager.classManager, false);
		return null;
	}
	public static Set<Class<?>> getClassByPackageName(final String packageName,final ClassChangedClassCallBack callback,ClassManager classManager) throws Exception{
		return getClassByPackageName(packageName, callback,classManager,false);
	}
	/**
	 * 通过包名得到实际目录
	 * @param packageName
	 * @return
	 * @throws IllegalAccessException 
	 */
	public static Collection<File> getDirByPackageName(final String packageName) throws IllegalAccessException{
		//包路径的正则表达式
		String fuzzyRegExp = GeneralParser.getFuzzyRegExp(packageName2URLPath(packageName));
		final Pattern packagePah = Pattern.compile(fuzzyRegExp);
		//如果包名不合法
		if(!packageName.matches(PATTERN_CLASS_PACKAGE)){
			throw new IllegalAccessException(packageName+"包名不合法");
		} 
		//符合packagePath的dirs
		final Collection<File> listFilesAndDirs = new HashSet<File>();
		//如果package是表达式
		if(!packageName.contains("*")){ 
			listFilesAndDirs.add(new File(null+"/"+packageName2URLPath(packageName)));
			return listFilesAndDirs;
		}
		//得到包的最大路径，即没有*号
		String path =null;//=ConfigManager.WEB_CLASSPATH +"/"+packageName2URLPath(packageName.substring(0,packageName.indexOf('*')));
		/*FileUtils.listFilesAndDirs(new File(path), FalseFileFilter.FALSE, new AbstractFileFilter() {
			@Override
			public boolean accept(File file) {
				String convertURLPath = _FileUtils.convertURLPath(file.getAbsolutePath());
				if(packagePah.matcher(convertURLPath).find()){
					File dir = new File(convertURLPath);
					if(dir.isDirectory()){
						listFilesAndDirs.add(dir);
					}
				}
				return true;
			}
		});*/
		return listFilesAndDirs;
	}
	
	/**
	 * 
	 * @param packageName
	 * @param callback 要迭代的回调函数数
	 * @throws Exception
	 */
	public abstract class CallBack {
	}

	public abstract class ClassChangedClassCallBack extends CallBack{
		public abstract void execute(Class<?> clazz);
	}

	public static Set<Class<?>> getClassByPackageName(final String packageName,final ClassChangedClassCallBack callback,final ClassManager classManager ,final boolean force) throws Exception{
		//类文件表达式
		final Parser classFileParser = GeneralParser.getExactParser(EXPERSSION_CLASS_FILE);
		//给出包名的所有类
		final Set<Class<?>> classSet = new HashSet<Class<?>>();
		IOFileFilter filter = new AbstractFileFilter()  {
				@SuppressWarnings("unused")
				private String realPath;
				@Override
				public boolean accept(File file) {
					try {
						//搜索类文件
						if(!classFileParser.find(file.getAbsolutePath())){
							return false;
						}
						//得到文件路径(去除.class)
						String target = classFileParser.group(1);
						//得到修补的包名
						String headerPackageName = GeneralParser.getFuzzyParser(packageName2WindowPath(packageName)).fixPlaceHolder(target);
						//提取类名
						String className = path2PackageName(target.substring(target.indexOf(headerPackageName)));
						//载入类
						Class<?> loadClass = Thread.currentThread().getContextClassLoader().loadClass(className);
						//添加该类
						classSet.add(loadClass);
						
						//如果类有变化，则调动callBack
						if(classManager.hasChanged(loadClass)||force){
							if(callback!=null){
								callback.execute(loadClass);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
			};
		Collection<File> dirs = getDirByPackageName(packageName);
		for(File dir:dirs){
			if(dir.isDirectory()){
				//传递值给匿名类
				FieldUtils.writeDeclaredField(filter, "realPath", dir.getAbsolutePath(),true);
				FileUtils.listFiles(dir, filter,TrueFileFilter.TRUE);
			}
		}
		return classSet;
	}
}
