package com.welge.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parser {
	protected Pattern pattern;
	protected Matcher matcher;
	/**
	 * 返回第index个匹配的子串
	 * @param index
	 * @return匹配的子串
	 */
	abstract public String group(int index) throws Exception;
	/**
	 * 搜索子串
	 * @param target
	 * @return
	 */
	abstract public Boolean find(String target);
	abstract public String fixPlaceHolder(String target);
}
