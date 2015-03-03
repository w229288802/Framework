package com.welge.framework.utils;
/*package com.welge.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.util.BeanUtil;


public class PoiUtils <T>{
	Class entityClass;
	private static final int START_LINE = 2;
	private static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	protected PoiUtils(){
		entityClass  =(Class) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public Map<String, Short> getHeadRow(String path) {
		Map<String, Short> map = new LinkedHashMap<String, Short>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			HSSFWorkbook book = null;
			book = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = book.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			int columnSize = row.getPhysicalNumberOfCells();
			for (short index = 0; index < columnSize; index++) {
				HSSFCell cell = row.getCell(index);
				map.put(cell.getStringCellValue().toLowerCase(), index);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	 }
	public Map<Short, String> swapMap(Map<String, Short> map){
		Map<Short,String> newMap = new LinkedHashMap<Short,String>();
		Set<Entry<String, Short>> entrySet = map.entrySet();
		for(Entry<String, Short> entry:entrySet){
			newMap.put(entry.getValue(), entry.getKey());
		}
		return newMap;
	}
	 public List<T> importExcel(String path){
		 	List<T> list = new ArrayList<T>();
			File inputFile = null;
			FileInputStream input = null;
			Map<Short,String>  header = swapMap(getHeadRow(path));
			try {
				inputFile = new File(path);
				input = new FileInputStream(inputFile);
				// 声明一个工作薄
				HSSFWorkbook workbook = new HSSFWorkbook(input);
				// 生成一个表格
				HSSFSheet sheet = workbook.getSheetAt(0);
				HSSFRow row = null;
				HSSFCell cell = null;
				int rowSize = sheet.getPhysicalNumberOfRows();
				String textValue = null;
				for(int y=START_LINE;y<rowSize;y++){
					row = sheet.getRow(y);
					if(row==null){
						continue;
					}
					int cellSize = header.size();
					T value = (T) entityClass.newInstance();
					Boolean haveDataFlag = false;
					for(Short x = 0;x<cellSize;x++){
						String fieldName = header.get(x);
						try{
							Field field = entityClass.getDeclaredField(fieldName);
							field.setAccessible(true);
							cell = row.getCell(x);
							if(cell==null){
								continue;
							}
							int cellType = cell.getCellType();
							if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
								if (HSSFDateUtil.isCellDateFormatted(cell)) {  
									SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");  
									textValue=formater.format(cell.getDateCellValue()) ; 
								}else{
									double numericCellValue = cell.getNumericCellValue();
									//DecimalFormat df = new DecimalFormat(); 
									//textValue = df.format(numericCellValue);
									textValue = String.valueOf(numericCellValue);  
								}
							}
							else{
								textValue=cell.getStringCellValue();
								if(StringUtils.isBlank(textValue)){
									continue;
								}
							}
							if(Number.class.isAssignableFrom(field.getType())){
								field.set(value, NumberFormat.getInstance().parse(textValue));}
							else{
								field.set(value, textValue);
							}
							haveDataFlag=true;
							
						}catch(NoSuchFieldException e){
							System.err.println(entityClass+"没有这样的字段:"+fieldName);
						}
					}
					if(haveDataFlag){
						list.add(value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally{
				try {
					if(input!=null){
						input.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return list;
		 
	 }
	 public FileInputStream exportExcel(List<T> list, String fileName) {
		String absolutePath =ServletActionContext.getServletContext().getRealPath("template");
		FileOutputStream output=null;
		File outFile =null;
		File inputFile = null;
		FileInputStream input = null;
		Map<String, Short> header = getHeadRow(absolutePath+"\\"+fileName);
		try {
			inputFile = new File(absolutePath+"\\"+fileName);
			input = new FileInputStream(inputFile);
			outFile = new File(absolutePath+"\\export.xls");
			output=new FileOutputStream(outFile);
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			// 生成一个表格
			HSSFSheet sheet = workbook.getSheetAt(0);

	        // 设置表格默认列宽度为15个字节
	        sheet.setDefaultColumnWidth((short) 15);
	        // 生成一个样式
	        HSSFCellStyle style = workbook.createCellStyle();
	        // 设置这些样式
	        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        // 生成一个字体
	        HSSFFont font = workbook.createFont();
	        font.setColor(HSSFColor.VIOLET.index);
	        font.setFontHeightInPoints((short) 12);
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        // 把字体应用到当前的样式
	        style.setFont(font);
	        // 生成并设置另一个样式
	        HSSFCellStyle style2 = workbook.createCellStyle();
	        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        // 生成另一个字体
	        HSSFFont font2 = workbook.createFont();
	        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	        // 把字体应用到当前的样式
	        style2.setFont(font2);

	        // 声明一个画图的顶级管理器
	        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	        // 定义注释的大小和位置,详见文档
	        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
	                0, 0, 0, (short) 4, 2, (short) 6, 5));
	        // 设置注释内容
	        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
	        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
	        comment.setAuthor("leno");
	        
	        // 产生表格标题行
	       // HSSFRow row = sheet.createRow(3);
			int y = START_LINE;
			HSSFRow row = null;
			HSSFCell cell = null;
			for (T object : list) {
				row = sheet.createRow(y++);
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName().toLowerCase();
					if (header.containsKey(fieldName)) {
						cell = row.createCell(header.get(fieldName));
						field.setAccessible(true);
						Object value = field.get(object);
						String textValue = null;
						if(value!=null){
							if (value instanceof Integer) {
								int intValue = (Integer) value;
								cell.setCellValue(intValue);
		                    }else if (value instanceof Float) {
		                    	float fValue = (Float) value;
		                    	cell.setCellValue(fValue);
		                    }else if (value instanceof Double) {
		                    	double dValue = (Double) value;
		                    	cell.setCellValue(dValue);
		                    } else if (value instanceof Long) {
		                    	long longValue = (Long) value;
		                    	cell.setCellValue(longValue);
		                    }
		                    if (value instanceof Boolean) {
		                        boolean bValue = (Boolean) value;
		                        textValue = "男";
		                        if (!bValue) {
		                        	textValue = "女";
		                        }
		                        cell.setCellValue(textValue);
		                    } else if (value instanceof Date) {
		                        Date date = (Date) value;
		                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		                        textValue = sdf.format(date);
		                        cell.setCellValue(textValue);
		                    }else{
		                        // 其它数据类型都当作字符串简单处理
		                    	cell.setCellValue(value.toString());
		                    	
		                    }
						}
					}

				}
			}
			workbook.write(output);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(input!=null){
					input.close();
					input = new FileInputStream(outFile);
				}if(output!=null){
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return input;
	 }
}
*/