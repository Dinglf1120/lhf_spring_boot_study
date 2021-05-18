package com.lhf.springboot.controller;

import com.lhf.springboot.common.Pager;
import com.lhf.springboot.entity.RemarkInfo;
import com.lhf.springboot.service.RemarkInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ClassName: RemarkInfoController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/3 17:12
 */
@RestController
@RequestMapping("")
public class RemarkInfoController {

    private final Logger logger = LoggerFactory.getLogger(RemarkInfoController.class);

    @Autowired
    RemarkInfoService remarkInfoService;

    @RequestMapping("")
    @ResponseBody
    public ModelAndView index(@RequestParam Map<String, String> map) throws Exception {

        ModelAndView mv = new ModelAndView("index");
        Pager<RemarkInfo> pager = remarkInfoService.findAll(map, 2);
        logger.info("pager = " + pager);
        mv.addObject("listAll", pager.getPageContent());
        mv.addObject("pageTotal", pager.getPageTotal());
        mv.addObject("page", pager.getCurrentPage());
        mv.addObject("Result","查询成功！");
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam Map<String, String> map) throws Exception{
        System.out.println("map = " + map);
        ModelAndView mv = new ModelAndView("index");
        try{
            remarkInfoService.saveRemarkInfo(map);
            mv.addObject("Result", map.get("msg") + "成功 ");
        }catch (Exception e){
            logger.info("异常信息：" + e.getMessage());
            mv.addObject("Result", map.get("msg") + "失败！");
        }
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id")Integer id) throws Exception {
        remarkInfoService.deteleRemarkInfoById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("Result", "删除成功！");
        return mv;
    }
}
