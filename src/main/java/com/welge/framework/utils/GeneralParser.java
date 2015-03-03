package com.welge.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralParser extends Parser{
	public  GeneralParser(){}
	/**
	 * 解释表达式
	 * @param exp <b>*</b>代表一个或多个字符
	 * @return 正则表达式
	 */
	private static String resolverExp(String exp){
		//除单词字符、*、\为匹配模式
		Pattern compile = Pattern.compile("[^(\\w|*|\\)]");
		Matcher matcher = compile.matcher(exp);
		//匹配模式的 char 替换成 \char
		exp = matcher.replaceAll("\\\\$0"); 
		//替换星号
		int currsor = 0;
		StringBuffer sb = new StringBuffer();
		while(currsor<exp.length()){
			if(exp.charAt(currsor)=='\\'){
				/*currsor++;
				sb.append("\\");
				sb.append(exp.charAt(currsor));*/
				if(exp.substring(currsor+1, currsor+2).matches("[^\\w]")){
					currsor++;
					sb.append("\\");
					sb.append(exp.charAt(currsor));
				}else{
					sb.append("\\\\");
				}
			}else if(exp.charAt(currsor)=='*'){
				sb.append("(.*)");
			}else{
				sb.append(exp.charAt(currsor));
			}
			currsor++;
		}
		return sb.toString();
	}
	public static String getFuzzyRegExp(String exp) {
		return "("+resolverExp(exp)+")";
	}
	public static String getExactRegExp(String exp){
		return "^"+resolverExp(exp)+"$";
	}
	public static Parser getExactParser(String exp) {
		Parser parser = new GeneralParser();
		parser.pattern=Pattern.compile(getExactRegExp(exp));
		return parser;
	}
	public static Parser getFuzzyParser(String exp){
		Parser parser = new GeneralParser();
		parser.pattern=Pattern.compile(getFuzzyRegExp(exp));
		return parser;
	}
	public Parser debug(){
		System.out.println(this.pattern.pattern());
		return this;
	}
	@Override
	public Boolean find(String target) {
		this.matcher = this.pattern.matcher(target); 
		this.matcher.reset();
		return matcher.find();
	}
	@Override
	public String group(int index) throws IllegalAccessException{
		if(this.matcher==null){
			throw new IllegalAccessException("macher为空，可能没有调用find");
		}
		return this.matcher.group(index);
	}
	public String fixPlaceHolder(String target){
		//System.out.println(pattern.pattern());
		//System.out.println(target);
		if(find(target)){
			return ""+matcher.group(0);
		}return null;
		
	}
}
