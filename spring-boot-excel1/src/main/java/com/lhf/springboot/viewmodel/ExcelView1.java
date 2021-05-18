package com.lhf.springboot.viewmodel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName: ExcelView1
 * @Author: liuhefei
 * @Description: 定义成抽象类方便扩展
 * @Date: 2020/5/28 15:21
 */
public abstract class ExcelView1 extends AbstractXlsView {

    public CellStyle cellStyle;
    /**
     * 设置样式
     */
    protected abstract void setStyle(Workbook workbook);

    /**
     * 设置row由子类实现
     */
    protected abstract void setRowStyle(Sheet sheet, Map<String, Object> map);


    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //自定义文件名称
        String excelName = "数据报表" + System.currentTimeMillis() +".xls";
        String Agent = request.getHeader("User-Agent");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        if (null !=Agent){
            Agent = Agent.toLowerCase();
            //针对火狐乱码的处理
            if (Agent.indexOf("firebox") !=-1){
                response.setHeader("content-disposition", String.format("attachment;filename*=utf-8'zh_cn'%s", URLEncoder.encode(excelName, "utf-8")));
            } else {
                //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
                response.addHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode(excelName, "utf-8") + "\"");
            }
        }
        response.setContentType("application/ms-excel; charset=UTF-8");
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);
        this.setStyle(workbook);
        setRowStyle(sheet, model);
    }

}
