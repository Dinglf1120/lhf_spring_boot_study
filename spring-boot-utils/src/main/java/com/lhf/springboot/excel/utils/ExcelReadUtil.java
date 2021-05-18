package com.lhf.springboot.excel.utils;

import com.lhf.springboot.file.FileUtil;
import com.lhf.springboot.json.JsonUtil;
import com.lhf.springboot.random.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ExcelReadUtil
 * @Author: liuhefei
 * @Description: Java读取excel文件，并逐行转换为json数据写入文件中
 *
 * 要使用poi-ooxml 3.14版本
 *
 * @Date: 2020/4/21 12:45
 */
public class ExcelReadUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelReadUtil.class);

    private static Workbook workbook;
    private static Sheet sheet;

    /**
     * 初始化表格，判断文件格式是xls还是xlsx
     * @param excelPath
     */
    private static void initExcel(String excelPath){
        if (StringUtils.isEmpty(excelPath)){
            logger.info("文件路径不能为空");
            workbook = null;
            return;
        }
        try {
            InputStream is = new FileInputStream(excelPath);
            if (excelPath.endsWith(".xls")){//.xls格式
                workbook = new HSSFWorkbook(is);
            }else if(excelPath.endsWith(".xlsx")){//.xlsx格式
                workbook = new XSSFWorkbook(is);
            }else{
                workbook = null;
                logger.info("无法转换的Excel文件格式(后缀名应为:.xls或.xlsx)");
            }
        } catch (FileNotFoundException e) {
            logger.info("找不到指定文件");
        } catch (IOException e) {
            logger.info("IO异常");
        }
    }

    /**
     * 读取Excel文件并解析成json字符串后写入新的文件
     * @param excelPath 要解析的Excel文件路径(具体文件)
     * @param fromRow 指定开始读取的行数，如从第2行开始则为2
     * @param filePath 写入json后的文件保存位置(文件路径)
     */
    private static void readExcelToJsonFile(String excelPath, int headerNum, int fromRow, String filePath){
        initExcel(excelPath);
        if(workbook == null){
            logger.info("初始化工作簿失败");
            return;
        }
        for(int s=0; s<workbook.getNumberOfSheets(); s++){//遍历sheet
            sheet = workbook.getSheetAt(s);
            Row headerRow = getRow(sheet, headerNum);//首行，即json-key
            int rowNum = sheet.getLastRowNum() + 1;//总行数
            for(int i=(fromRow-1); i<rowNum; i++){//遍历行,从fromRow行开始遍历
                Map<String, String > map = new HashMap<String, String>();
                Row row = sheet.getRow(i);
                if(row == null){
                    continue;
                }
                int cellNum = row.getLastCellNum();//总列数
                for(int j=0; j<cellNum; j++){//遍历单元格
                    String key = getCellValue(headerRow.getCell(j));
                    String value = getCellValue(row.getCell(j));
                    if(!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)){
                        map.put(key, value);
                    }
                }
                String jsonStr = JsonUtil.toJson(map);//将map解析成json字符串
                //String fileName = RandomUtil.generateStr(10);//生成随机文件名，
                String fileName = "data" + i;
                String finalFilePath = filePath + "\\" + fileName + ".txt";//最终保存的文件路径
                FileUtil.writeToFile(finalFilePath, jsonStr, false);//将json字符串写入文件，false:不追加，每一行数据即生成一个文件
                String fileNamePath = filePath + "\\fileNames.txt";//保存所有随机文件名
                FileUtil.writeToFile(fileNamePath, (fileName + "\r\n"), true);//将文件名写入fileNames.txt文本中
            }
            logger.info("成功读取Excel并生成文件");
        }
    }

    /**
     * 获取指定行
     * @param sheet
     * @param rowNum
     * @return
     */
    private static Row getRow(Sheet sheet, int rowNum){
        if(sheet == null){
            return null;
        }
        return sheet.getRow(rowNum - 1);
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return 单元格值
     */
    private static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell != null){
            switch (cell.getCellType()){//单元格类型
                case Cell.CELL_TYPE_BOOLEAN://布尔类型
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_NUMERIC://数字类型
                    if (DateUtil.isCellDateFormatted(cell)) {//格式化后的日期数值类型
                        cellValue = new DataFormatter().formatCellValue(cell);
                    } else {
                        //数值
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_FORMULA:{//公式
                    try{
                        cellValue = cell.getNumericCellValue() + "";
                    }catch(IllegalStateException e){
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }
                }
                break;
                case Cell.CELL_TYPE_STRING://字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK://空值
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR://故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue.trim();
    }

    public static void main(String[] args) {
        String test = "D:\\图书名著581570525905657.xlsx";
        String filePath = "D:\\test";
        readExcelToJsonFile(test, 1, 2, filePath);
    }

}
