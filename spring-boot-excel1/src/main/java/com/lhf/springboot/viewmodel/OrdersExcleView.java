package com.lhf.springboot.viewmodel;

import com.lhf.springboot.entity.PageData;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrdersExcleView
 * @Author: liuhefei
 * @Description: excel表格设置
 * @Date: 2020/5/28 15:24
 */
public class OrdersExcleView extends ExcelView1 {
    //样式设置
    @Override
    protected void setStyle(Workbook workbook) {

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());//背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        Font font = workbook.createFont();
        font.setBold(true);//加粗
        font.setFontHeight((short)240);//字体大小
        font.setFontName("黑体");   //字体
        font.setColor((short)1);  //字体颜色
        font.setFontHeightInPoints((short) 15);

        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        cellStyle.setFillForegroundColor((short)5);
        cellStyle.setFillPattern(FillPatternType.forInt((short) 1));

    }

    @Override
    protected void setRowStyle(Sheet sheet, Map<String, Object> map) {
        //创建行的头部
        Row header = sheet.createRow(0);
        sheet.setColumnWidth(3,3500);//给第4列设置宽度(tel栏)

        header.createCell(0).setCellValue("订单ID");
        header.getCell(0).setCellStyle(super.cellStyle);
        header.createCell(1).setCellValue("订单总价");
        header.getCell(1).setCellStyle(super.cellStyle);
        header.createCell(2).setCellValue("收货人");
        header.getCell(2).setCellStyle(super.cellStyle);
        header.createCell(3).setCellValue("手机号码");
        header.getCell(3).setCellStyle(super.cellStyle);
        header.createCell(4).setCellValue("省份");
        header.getCell(4).setCellStyle(super.cellStyle);
        header.createCell(5).setCellValue("地址");
        header.getCell(5).setCellStyle(super.cellStyle);

        List<PageData> orderList= (List<PageData>) map.get("orderList");
        int rowCount = 1;
        for (PageData pageData : orderList) {

            String totalprice = String.valueOf(pageData.getTotalprice());
            System.out.println(totalprice);
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(pageData.getOrdersn());
            userRow.createCell(1).setCellValue(totalprice);
            userRow.createCell(2).setCellValue(pageData.getConsignee());
            userRow.createCell(3).setCellValue(pageData.getMobile());
            userRow.createCell(4).setCellValue(pageData.getProvincename());
            userRow.createCell(5).setCellValue(pageData.getAddress());
        }

    }
}
