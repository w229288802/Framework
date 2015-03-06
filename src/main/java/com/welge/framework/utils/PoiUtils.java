package com.welge.framework.utils;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.welge.framework.exception.ApplicationException;

public class PoiUtils  {
	public enum ExcelType{
		XLS,XLSX
	}
	public  static <T> void exportExcel2003(Class<?> cla,Map<String,String> headMap, Collection<T> list,OutputStream out) throws Exception{
		exportExcel(cla, headMap, list, out, ExcelType.XLS);
	}
	public  static <T> void exportExcel2007(Class<?> cla,Map<String,String> headMap, Collection<T> list,OutputStream out) throws Exception{
		exportExcel(cla, headMap, list, out, ExcelType.XLSX);
	}
 	private  static <T> void exportExcel(Class<?> cla,Map<String,String> headMap, Collection<T> list,OutputStream out,ExcelType type) throws Exception{
		Workbook wb = null;
		if(type==ExcelType.XLS){
			wb = new HSSFWorkbook();
		}else if(type==ExcelType.XLSX){
			wb = new XSSFWorkbook();
		}
		//设置样式
		setStyle(wb);
		CreationHelper helper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet();
		Row headRow = sheet.createRow(0);
		//Field[] fields2 = cla.getDeclaredFields();
		int colNum = headMap.size();
		int rowNum = list.size();
		
		//设置EXCEL头部
		Iterator<Entry<String, String>> headIterator = headMap.entrySet().iterator();
		for(int i = 0;i<colNum;i++){
			Entry<String, String> entry = headIterator.next();
			String field = entry.getKey();
			//检查类是否有这个字段
			try{
				cla.getDeclaredField(field);
			}catch (NoSuchFieldException e) {
				throw new ApplicationException(cla.getName()+"没有[ "+field+" ]这个字段");
			}
			String name = entry.getValue();
			Cell headCell = headRow.createCell(i);
			headCell.setCellValue(name);
			entry.setValue(String.valueOf(i));
		}
		
		
		//设置内容
		Iterator<T> iterator = list.iterator();
		for(int i =1;i<=rowNum;i++){
			T entity = iterator.next();
			Row row = sheet.createRow(i);
			headIterator = headMap.entrySet().iterator();
			for(int j=0;j<colNum;j++){
				Entry<String, String> next = headIterator.next();
				String value = next.getValue();
				String field = next.getKey();
				Cell cell = row.createCell(Integer.parseInt(value));
				Object fieldValue = _BeanUtils.getFieldValue(entity, field);
				cell.setCellValue(helper.createRichTextString((String)fieldValue));
			}
		}
		wb.write(out);
	}
	
	private static void setStyle(Workbook wb){
		// create 2 cell styles
		   CellStyle cs = wb.createCellStyle();
		   CellStyle cs2 = wb.createCellStyle();
		   DataFormat df = wb.createDataFormat();
	
		   // create 2 fonts objects
		   Font f = wb.createFont();
		   Font f2 = wb.createFont();
	
		   // Set font 1 to 12 point type, blue and bold
		   f.setFontHeightInPoints((short) 12);
		   f.setColor( IndexedColors.RED.getIndex() );
		   f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	
		   // Set font 2 to 10 point type, red and bold
		   f2.setFontHeightInPoints((short) 10);
		   f2.setColor( IndexedColors.RED.getIndex() );
		   f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
	
		   // Set cell style and formatting
		   cs.setFont(f);
		   cs.setDataFormat(df.getFormat("#,##0.0"));
	
		   // Set the other cell style and formatting
		   cs2.setBorderBottom(CellStyle.BORDER_THIN);
		   cs2.setDataFormat(df.getFormat("text"));
		   cs2.setFont(f2);
	}
}
