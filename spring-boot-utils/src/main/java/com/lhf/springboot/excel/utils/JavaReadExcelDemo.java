package com.lhf.springboot.excel.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @ClassName: JavaReadExcelDemo.java
 * @Description: 获取到excel文件的行数
 *
 * @version: v1.0.0
 * @author: liuhefei
 * @date: 2019年12月27日 下午6:17:37
 */
public class JavaReadExcelDemo {

	public static void main(String[] args) {
		try {
			String execlFilePath = "D:\\图书名著581570525905657.xlsx";
			File file = new File(execlFilePath);

			// 创建FileInputStream 对象用于读取excel 文件
			FileInputStream inputStream = new FileInputStream(file);


			// 声明Workbook 对象
			Workbook Workbook = null;

			// 获取文件名参数的后缀名，判断xlsx文件还是xls文件
			String fileExtensionName = execlFilePath.substring(execlFilePath.indexOf("."));

			// 判断文件类型如果是xlsx，则使用XSSFWorkbook 对象进行实例化
			// 判断文件类型如果是xls，则使用HSSFWorkbook 对象进行实例化
			if (fileExtensionName.equals(".xlsx")) {
				//如果是2007的，也就是.xlsx， 让Workbook = new XSSFWorkbook(inputStream);
				Workbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				//如果是2003的，也就是.xls， 让Workbook = new HSSFWorkbook(inputStream);
				Workbook = new HSSFWorkbook(inputStream);
			}
			Sheet Sheet = Workbook.getSheetAt(0);
			System.out.println("行数：" + Sheet.getLastRowNum());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
