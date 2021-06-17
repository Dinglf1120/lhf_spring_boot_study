package com.lhf.springboot.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExportExcel {
    /**
     * 导出Excel
     * @param mapList   数据
     * @param response
     * @param stringList  列名
     * @param name   表头名
     * @throws IOException
     */
    public static void exportExcel(List<Map<String, String>> mapList, HttpServletResponse response,List<String> stringList, String name) throws IOException {
        // List<Map<String, String>> mapList = null;//通过Map<String,String> map传参
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(""+name+"统计表");//新建表
        sheet.setDefaultColumnWidth(15);//设置表宽
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);

        HSSFCellStyle headerStyle = wb.createCellStyle();
        HSSFFont headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 14);

        headerStyle.setFont(headerFont);

        CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 9);
        sheet.addMergedRegion(cra0);
        sheet.setDefaultColumnWidth((short) 15);
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);

        cell1.setCellValue(""+name+"统计表");
        cell1.setCellStyle(headerStyle);
        //设置字体样式
        HSSFFont titleFont = wb.createFont();

        Row row1 = sheet.createRow(2);
        for (int i = 0; i <stringList.size() ; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(stringList.get(i));
            cell.setCellStyle(style);
        }

//        Cell cell = row1.createCell(0);
//        cell.setCellValue("ktp_apply_register_times_4h_b");
//        cell.setCellStyle(style);
//
//        cell = row1.createCell(1);
//        cell.setCellValue("username");
//        cell.setCellStyle(style);
//
//        cell = row1.createCell(2);
//        cell.setCellValue("password");
//        cell.setCellStyle(style);
        int createRow = 0;
        for (int i = 0; i <mapList.size() ; i++) {
            Map<String, String> m= mapList.get(i);
            row1 = sheet.createRow(3 + createRow);
            for (String key : m.keySet()) {
                for (int j = 0; j <stringList.size() ; j++) {
                    if (key.equals(stringList.get(j))) {
//                    //createCell 0 表示第一列
                        row1.createCell(j).setCellValue(getStringValeByKey(m, key));
                    }
                }
            }
            createRow++;

        }
//        stringList
//        for (Map<String, String> m : mapList) {
//            row1 = sheet.createRow(3 + createRow);
//            for (String key : m.keySet()) {
//                if (key.equals("ktp_apply_register_times_4h_b")) {
//                    //createCell 0 表示第一列
//                    row1.createCell(0).setCellValue(getStringValeByKey(m, key));
//                }
//                if (key.equals("username")) {
//                    //createCell 1 表示第二列
//                    row1.createCell(1).setCellValue(getStringValeByKey(m, key));
//                }
//                if (key.equals("password")) {
//                    //createCell 2 表示第三列
//                    row1.createCell(2).setCellValue(getStringValeByKey(m, key));
//                }
//
//
//            }
//            createRow++;
//        }


        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = newsdf.format(new Date());
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String((""+name+"统计表" + date + ".xls").getBytes("GBK"),
                    "ISO8859_1") + "\"");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }

}

    public static String getStringValeByKey(Map<?,?> map,String key){
        return map.get(key).toString().trim();
    }
}


