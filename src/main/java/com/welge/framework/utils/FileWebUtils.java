package com.welge.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.welge.framework.exception.AppcationException;

/** 
 * <p>Title: 处理文件上传下载的类</p> 
 * <p>Description:  
 *    通过设置long MAX_SIZE可以设置上传文件的大小限制 
 *    通过设置String[] allowedExt设置允许上传的文件类型 
 *    通过Map parameters获得表单域的信息 
 *    通过List fileInfoList获取上传的每个文件的详细信息 
 * </p> 
 * <p>Copyright: Copyright (c) 2006, 2008 Royzhou Corporation.All rights reserved. </p> 
 * @author royzhou 
 * 2009-02-20 
 */  
public class FileWebUtils {  
    /** 
     * 表单域的信息 
     */  
    private Map<String,String> parameters = null;  
      
    /** 
     * 文件域的详细信息 
     */  
    private List<Map<String,String>> fileInfoList = null;  
      
    /** 
     * 允许上传的文件大小 
     */  
    private long MAX_SIZE = 10*1024*1024;  
      
    /** 
     * 允许上传的文件类型 
     */  
    private String[] allowedExt = new String[] { "jpg", "jpeg", "gif", "txt","doc", "docx", "mp3", "wma", "m4a" };  
      
    public FileWebUtils() {  
        parameters = new HashMap<String,String>();  
        fileInfoList = new ArrayList<Map<String,String>>();  
    }  
      
    /** 
     * @param request 
     * @param response 
     * @param path 用户设置的保存路径 
     * 上传文件并获取表单域及文件域的详细信息 
     * @throws Exception 
     */  
    public void upload(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {  
        
    	/** 
         * 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload 
         */  
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();  
        diskFileItemFactory.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
        /** 
         * 采用系统临时文件目录作为上传的临时目录 
         */  
        File tempfile = new File(System.getProperty("java.io.tmpdir"));    
        diskFileItemFactory.setRepository(tempfile);  
          
        /** 
         * 用以上工厂实例化上传组件 
         * 设置最大上传尺寸 
         */  
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);  
        fileUpload.setSizeMax(MAX_SIZE);  
          
        /** 
         * 调用FileUpload.settingHeaderEncoding(”UTF-8″)，这项设置可以解决路径或者文件名为乱码的问题。 
         * 设置输出字符集 
         */  
        fileUpload.setHeaderEncoding("UTF-8");  
        response.setContentType("text/html;charset=utf-8");  
          
        PrintWriter out = response.getWriter();  
        /** 
         * 从request得到 所有 上传域的列表 
         */  
        List<?> fileList = null;  
        try {  
            fileList = fileUpload.parseRequest(request);  
        } catch (FileUploadException e) {  
            if (e instanceof SizeLimitExceededException) {  
                /** 
                 * 文件大小超出限制 
                 */  
                throw new AppcationException("文件大小超出限制");
            }  
        }  
        /** 
         * 没有上传文件 
         */  
        if (fileList == null || fileList.size() == 0) {  
        	throw new AppcationException("请选择文件");  
        }  
        /** 
         * 得到所有上传的文件 
         * 对文件域操作 
         * 并保存每个文件的详细信息 
         */  
        Iterator<?> fileItr = fileList.iterator();  
        Map<String,String> fileInfo = null;  
        while (fileItr.hasNext()) {  
            FileItem fileItem = null;  
            long size = 0;  
            String userPath = null;  
            String serverPath = null;  
            String fileName = null;  
            String fileExt = null;  
            fileItem = (FileItem) fileItr.next();  
            /** 
             * 忽略简单form字段而不是上传域的文件域(<input type="text" />等) 
             */  
            if (!fileItem.isFormField()) {  
              
                /** 
                 * 得到文件的详细信息 
                 * 客户端完整路径：userPath 
                 * 服务器端完整路径：serverPath 
                 * 大小：size 
                 * 文件名：fileName 
                 * 扩展名：fileExt 
                 *  
                 */  
                userPath = fileItem.getName();  
                size = fileItem.getSize();  
                if ("".equals(userPath) || size == 0) {  
                    out.println("请选择上传文件<p />");  
                    out.println("<a href=\"upload.html\" target=\"_top\">返回</a>");  
                    return;  
                }  
                fileName = userPath.substring(userPath.lastIndexOf("\\") + 1);  
                fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);  
                /** 
                 * 文件类型是否合法 
                 */  
                int allowFlag = 0;  
                int allowedExtCount = allowedExt.length;  
                for (; allowFlag < allowedExtCount; allowFlag++) {  
                    if (allowedExt[allowFlag].toLowerCase().equals(fileExt.toLowerCase()))  
                        break;  
                }  
                if (allowFlag == allowedExtCount) {  
                    out.println("请上传以下类型的文件<p />");  
                }  
                /** 
                 * 根据系统时间生成上传后保存的文件名 
                 */  
                serverPath = path + System.currentTimeMillis() + "." + fileExt;  
                  
                /** 
                 * 保存文件 
                 */  
                File diskPath = new File(path);  
                if(!diskPath.exists()) {  
                    diskPath.mkdirs();  
                }  
                File diskFile = new File(serverPath);  
                if(!diskFile.exists()) {  
                    diskFile.createNewFile();  
                }  
                fileItem.write(diskFile);  
                
                out.println("文件上传成功. 已保存为:");
                  
                fileInfo = new HashMap<String,String>();  
                fileInfo.put("size", String.valueOf(size));  
                fileInfo.put("userpath", userPath);  
                fileInfo.put("name",fileName);  
                fileInfo.put("ext", fileExt);  
                fileInfo.put("serverpath", serverPath);  
                fileInfoList.add(fileInfo);  
            } else {  
                String fieldName = fileItem.getFieldName();  
                /** 
                 * 在取字段值的时候，用FileItem.getString(”UTF-8″)，这项设置可以解决获取的表单字段为乱码的问题。 
                 */   
                String value = fileItem.getString("UTF-8");  
                parameters.put(fieldName, value);  
            }  
        }  
    }  
      
      
    /** 
     * 该方法支持支持国际化 
     * 但是文件名不能超过17个汉字 
     * 而且在IE6下存在bug 
     */  
    public void downloadI18N(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=utf-8");  
        java.io.BufferedInputStream bis = null;  
        java.io.BufferedOutputStream bos = null;  
  
        String filePath = request.getParameter("filePath");  
        String fileName = request.getParameter("fileName");  
        System.out.println(fileName);  
        try {  
            long fileLength = new File(filePath).length();  
  
            fileName = URLEncoder.encode(fileName, "UTF-8");  
            response.setContentType("application/x-msdownload;");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);  
            response.setHeader("Content-Length", String.valueOf(fileLength));  
  
            bis = new BufferedInputStream(new FileInputStream(filePath));  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                bis.close();  
            if (bos != null)  
                bos.close();  
        }  
    }  
    /** 
     * 支持中文,文件名长度无限制 
     * 不支持国际化 
     */  
    public void download(HttpServletRequest request, HttpServletResponse response,InputStream input,String fileName) throws Exception {  
       // response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");  
        java.io.BufferedInputStream bis = null;  
        java.io.BufferedOutputStream bos = null;  
  
       // String filePath = request.getParameter("filePath");  
        //String fileName = request.getParameter("fileName");  
        //System.out.println(fileName);
        //String fileName = filePath.substring(filePath.lastIndexOf("\\")+1); 
        try {  
            //long fileLength = new File(filePath).length();  new String(fileName.getBytes(),"ISO8859-1"))
        	response.addHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(),"ISO8859-1"));  
            response.setContentType("application/x-msdownload;");  
            response.setHeader("Content-Length", String.valueOf(input.available()));  
  
            bis = new BufferedInputStream(input);  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                bis.close();  
            if (bos != null)  
                bos.close();  
        }  
    }  
  
    public List<Map<String,String>> getFileInfoList() {  
        return fileInfoList;  
    }  
  
    public void setFileInfoList(List<Map<String,String>> fileInfoList) {  
        this.fileInfoList = fileInfoList;  
    }  
  
    public Map<String,String> getParameters() {  
        return parameters;  
    }  
  
    public void setParameters(Map<String,String> parameters) {  
        this.parameters = parameters;  
    }  
  
    public String[] getAllowedExt() {  
        return allowedExt;  
    }  
  
    public void setAllowedExt(String[] allowedExt) {  
        this.allowedExt = allowedExt;  
    }  
  
    public long getMAX_SIZE() {  
        return MAX_SIZE;  
    }  
  
    public void setMAX_SIZE(long max_size) {  
        MAX_SIZE = max_size;  
    }  
}  
