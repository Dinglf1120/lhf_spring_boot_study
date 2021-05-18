package com.lhf.springboot.controller;

import com.lhf.springboot.service.ExcelService;
import com.lhf.springboot.utils.ExcelImportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ExcelController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/29 11:16
 */
@Controller
@RequestMapping("/api")
public class ExcelController {

    private final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/uploadFile")
    public String charList(Model model){

        return "uploadFile";
    }

    //导入
    @PostMapping(value = "/batchImport")
    public String batchImportUserKnowledge(@RequestParam(value="filename") MultipartFile file,
                                           HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
                                           @RequestParam(value = "username")String username) throws IOException {

        logger.info("入参 filename = " + file + " , username = " + username);
        //判断文件是否为空
        if(file==null){
            modelMap.addAttribute("message","文件不能为空！");
            return "error";
        }

        //获取文件名
        String fileName=file.getOriginalFilename();

        //验证文件名是否合格
        if(!ExcelImportUtils.validateExcel(fileName)){
            modelMap.addAttribute("message","文件必须是excel格式！");
            return "error";
        }

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            modelMap.addAttribute("message","文件不能为空！");
            return "error";
        }

        //批量导入
        String message = excelService.batchImport(fileName,file, username);
        modelMap.addAttribute("message",message);
        return "error";
    }


    @RequestMapping("/error")
    public String error(ModelMap modelMap){

        modelMap.addAttribute("message", "没有了你，万杯觥筹只不过是提醒寂寞");
        return "error";
    }



}
