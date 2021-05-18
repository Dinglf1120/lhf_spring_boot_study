package com.lhf.springboot.service;

import com.lhf.springboot.entity.UserInfo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: UserInfoServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/28 17:22
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
    //创建临时文件存放的路径
    private String temp="d:\\temp\\excel\\";

    @Override
    public String exportUserInfo() {
        //查询数据库获取UserInfo信息列表
        //List<UserInfo> list = getAllUserInfo();
        List<UserInfo> list = getAllUserInfo();

        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet();
        xssfWorkbook.setSheetName(0,"美女信息表");

        //设置样式
        //创建styleHead
        CellStyle styleHead = xssfWorkbook.createCellStyle();
        styleHead.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());//背景色
        styleHead.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHead.setAlignment(HorizontalAlignment.CENTER);//水平居中
        XSSFFont font = xssfWorkbook.createFont();
        font.setBold(true);//加粗
        font.setFontHeight((short)240);//字体大小
        styleHead.setFont(font);
        //创建style1
        CellStyle style1 = xssfWorkbook.createCellStyle();
        style1.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());//背景色
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style1.setAlignment(HorizontalAlignment.CENTER);//水平居中
        //创建style2
        CellStyle style2 = xssfWorkbook.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());//背景色
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setAlignment(HorizontalAlignment.CENTER);//水平居中　　　　
        sheet.setColumnWidth(6,3500);//给第5列设置宽度(tel栏)

        //创建表头
        XSSFRow head = sheet.createRow(0);
        String[] heads = {"编号","姓名","年龄","性别","身高","罩杯", "手机号"};
        for(int i = 0;i < 7;i++){
            XSSFCell cell = head.createCell(i);
            cell.setCellValue(heads[i]);
        }
        for (int i = 1;i <= 4;i++) {
            UserInfo userInfo = list.get(i - 1);
            //创建行,从第二行开始，所以for循环的i从1开始取
            XSSFRow row = sheet.createRow(i);
            //创建单元格，并填充数据
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(userInfo.getId());

            cell = row.createCell(1);
            cell.setCellValue(userInfo.getUsername());

            cell = row.createCell(2);
            cell.setCellValue(userInfo.getAge());

            cell = row.createCell(3);
            cell.setCellValue(userInfo.getSex());

            cell = row.createCell(4);
            cell.setCellValue(userInfo.getHeight());

            cell = row.createCell(5);
            cell.setCellValue(userInfo.getCupSize());

            cell = row.createCell(6);
            cell.setCellValue(userInfo.getModile());

        }
        //创建临时文件的目录
        File file = new File(temp);
        if(!file.exists()){
            file.mkdirs();
        }
        //临时文件路径/文件名
        String downloadPath = file + "\\"  +System.currentTimeMillis() + UUID.randomUUID();
        OutputStream outputStream = null;
        try {
            //使用FileOutputStream将内存中的数据写到本地，生成临时文件
            outputStream = new FileOutputStream(downloadPath);
            xssfWorkbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadPath;
    }

    private List<UserInfo> getAllUserInfo() {
        List<UserInfo> list = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername("珊珊");
        userInfo.setAge(24);
        userInfo.setSex("女");
        userInfo.setCupSize("B罩杯");
        userInfo.setHeight(172);
        userInfo.setModile("18296615502");


        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(2);
        userInfo1.setUsername("萍萍");
        userInfo1.setAge(25);
        userInfo1.setSex("女");
        userInfo1.setCupSize("C罩杯");
        userInfo1.setHeight(175);
        userInfo1.setModile("18277715502");


        UserInfo userInfo2 = new UserInfo();
        userInfo2.setId(3);
        userInfo2.setUsername("梦梦");
        userInfo2.setAge(24);
        userInfo2.setSex("女");
        userInfo2.setCupSize("B罩杯");
        userInfo2.setHeight(168);
        userInfo2.setModile("13292215502");


        UserInfo userInfo3 = new UserInfo();
        userInfo3.setId(4);
        userInfo3.setUsername("婷婷");
        userInfo3.setAge(22);
        userInfo3.setSex("女");
        userInfo3.setCupSize("B罩杯");
        userInfo3.setHeight(171);
        userInfo3.setModile("18295515502");

        list.add(userInfo);
        list.add(userInfo1);
        list.add(userInfo2);
        list.add(userInfo3);
        return list;
    }
}
