package com.welge.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.welge.framework.utils.FileWebUtils;

public class FileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_PATH = "uploadPath";
	private static String uploadPath = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(null==uploadPath){
			uploadPath = getServletContext().getInitParameter(UPLOAD_PATH); 
		}
		FileWebUtils fileWebUtils = new FileWebUtils();
		try {
			fileWebUtils.upload(request, response, uploadPath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
	
}
