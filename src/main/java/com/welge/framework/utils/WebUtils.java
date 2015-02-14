package com.welge.framework.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class WebUtils {
	private static ObjectMapper om = new ObjectMapper();
	public static void writeObjectToReponse(Object object){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try {
			String json = om.writeValueAsString(object);
			PrintWriter writer = response.getWriter();
			writer.write(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
