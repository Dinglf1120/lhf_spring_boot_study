package com.lhf.springboot.service;

import com.lhf.springboot.entity.DictumBaseInfo;
import com.lhf.springboot.repository.DictumBaseInfoRepository;
import com.lhf.springboot.utils.ExcelImportUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExcelServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/29 10:20
 */
@Service
public class ExcelServiceImpl implements ExcelService{

    @Autowired
    private DictumBaseInfoRepository dictumBaseInfoRepository;

    @Value("${file-path}")
    private String filePath;

    @Override
    public String batchImport(String fileName, MultipartFile mfile, String userName) {
        File uploadDir = new  File(filePath);
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        //新建一个文件
        File tempFile = new File(filePath + new Date().getTime() + ".xlsx");
        //初始化输入流
        InputStream is = null;
        try{
            //将上传的文件写入新建的文件中
            mfile.transferTo(tempFile);

            //根据新建的文件实例化输入流
            is = new FileInputStream(tempFile);

            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            //根据文件名判断文件是2003版本还是2007版本
            if(ExcelImportUtils.isExcel2007(fileName)){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            //根据excel里面的内容读取知识库信息
            return readExcelValue(wb,userName,tempFile);
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return "导入出错！请检查数据格式！";
    }

    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private String readExcelValue(Workbook wb,String userName,File tempFile){

        //错误信息接收器
        String errorMsg = "";
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(1) != null){
            totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
        }
        System.out.println("totalCells = " + totalCells);
        List<DictumBaseInfo> infoList= new ArrayList<>();
        DictumBaseInfo dictumBaseInfo;

        String br = "<br/>";

        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null){
                errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
                continue;
            }
            dictumBaseInfo = new DictumBaseInfo();

            Integer id = null;
            String content = "";
            Integer showed = null;
            Date addTime = null;
            String addMan = "";

            //循环Excel的列
            for(int c = 0; c <totalCells; c++){
                Cell cell = row.getCell(c);
                if (null != cell){
                    if(c==0){
                        id = cell.getRowIndex();
                        System.out.println("id = " + id);
                        if(id == null){
                            rowMessage += "id不能为空；";
                        }
                        dictumBaseInfo.setId(id);
                    }else if(c==1){
                        content = cell.getStringCellValue();
                        if(StringUtils.isEmpty(content)){
                            rowMessage += "内容不能为空；";
                        }
                        dictumBaseInfo.setContent(content);
                    }else if(c == 2){
                        showed = cell.toString().equals("0.0") ? 0 : 1;
                        System.out.println("cell =" + cell);

                        System.out.println("showed = " + showed);
                        dictumBaseInfo.setShowed(showed);
                    }else if(c == 3){
                        addTime = cell.getDateCellValue();
                        dictumBaseInfo.setCreateTime(addTime);
                    }else if(c == 4){
                        addMan = cell.getStringCellValue();
                        dictumBaseInfo.setCreateUser(addMan);
                    }
                }else{
                    rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
                }
            }
            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
            }else{
                infoList.add(dictumBaseInfo);
            }
        }

        //删除上传的临时文件
        if(tempFile.exists()){
            tempFile.delete();
        }

        //全部验证通过才导入到数据库
        if(StringUtils.isEmpty(errorMsg)){
            for(DictumBaseInfo baseInfo : infoList){
                this.saveDictumBaseInfo(baseInfo, userName);
            }
            errorMsg = "导入成功，共"+infoList.size()+"条数据！";
        }
        return errorMsg;
    }

    @Override
    public void saveDictumBaseInfo(DictumBaseInfo baseInfo, String userName) {

        baseInfo.setCreateUser(userName);
        dictumBaseInfoRepository.saveAndFlush(baseInfo);
    }

}
