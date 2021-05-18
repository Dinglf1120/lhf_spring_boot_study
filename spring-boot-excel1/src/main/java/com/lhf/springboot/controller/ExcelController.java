package com.lhf.springboot.controller;

import com.lhf.springboot.constant.ExcelSheetSettingEnum;
import com.lhf.springboot.entity.PageData;
import com.lhf.springboot.viewmodel.ExcelView;
import com.lhf.springboot.viewmodel.ExcelView1;
import com.lhf.springboot.viewmodel.OrdersExcleView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @ClassName: ExcelController
 * @Author: liuhefei
 * @Description: 当代码执行new ModelAndView(excelView, map)时会执行ExcelView#buildExcelDocument的方法
 * @Date: 2020/5/28 11:53
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    //http://localhost:8066/excel/export
    @RequestMapping("/export")
    public ModelAndView exportExcelDate(){
        List<List<String>> sheet1 = Arrays.asList(
                Arrays.asList("1", "兰兰", "28", "C罩杯"),
                Arrays.asList("2", "珊珊", "25", "B罩杯"),
                Arrays.asList("3", "婷婷", "24", "B罩杯"),
                Arrays.asList("4", "依依", "26", "D罩杯"),
                Arrays.asList("5", "雯雯", "20", "A罩杯")
        );
        List<List<String>> sheet2 = Arrays.asList(
                Arrays.asList("6", "圈圈", "28", "C罩杯"),
                Arrays.asList("7", "诶诶", "25", "B罩杯"),
                Arrays.asList("8", "蓉蓉", "24", "B罩杯"),
                Arrays.asList("9", "甜甜", "26", "D罩杯"),
                Arrays.asList("10", "豆豆", "20", "A罩杯")
        );

        List<List<List<String>>> sheets = Arrays.asList(sheet1, sheet2);
        Map<String, Object> map = new HashMap<>();
        map.put("ExcelSheetSetting", ExcelSheetSettingEnum.REPORT_TEST);
        map.put("data", sheets);
        ExcelView excelView = new ExcelView();
        //当代码执行new ModelAndView(excelView, map)时会执行ExcelView#buildExcelDocument的方法
        return new ModelAndView(excelView, map);

    }

    //http://localhost:8066/excel/download
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ModelAndView download() throws Exception {
        PageData pageData = new PageData();
        pageData.setOrdersn("10000001234");
        pageData.setTotalprice(358.00);
        pageData.setConsignee("刘先生");
        pageData.setMobile("18296666612");
        pageData.setProvincename("广东省");
        pageData.setAddress("深圳市宝安区");

        PageData pageData1 = new PageData();
        pageData1.setOrdersn("10000001235");
        pageData1.setTotalprice(458.00);
        pageData1.setConsignee("王先生");
        pageData1.setMobile("18296336612");
        pageData1.setProvincename("河南省");
        pageData1.setAddress("郑州市北京路24号");

        //List<PageData> list = ordersService.listAll(pageData);
        List<PageData> list = new ArrayList<>();
        list.add(pageData);
        list.add(pageData1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderList",list);
        ExcelView1 view = new OrdersExcleView();
        //当代码执行new ModelAndView(excelView, map)时会执行ExcelView#buildExcelDocument的方法
        return new ModelAndView(view, map);
    }

}
