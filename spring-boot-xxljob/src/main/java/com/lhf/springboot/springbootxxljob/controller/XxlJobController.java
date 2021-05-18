package com.lhf.springboot.springbootxxljob.controller;


import com.alibaba.fastjson.JSONObject;

import com.lhf.springboot.springbootxxljob.common.JsonResult;
import com.lhf.springboot.springbootxxljob.model.XxlJobGroupVo;
import com.lhf.springboot.springbootxxljob.model.XxlJobInfoVo;
import com.lhf.springboot.springbootxxljob.util.HttpClientResult;
import com.lhf.springboot.springbootxxljob.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: XxlJobController
 * @Author: liuhefei
 * @Description: xxl-job控制层接口 调用xxl-job-admin ApI接口
 */
@Controller
@Api(value = "xxl-job控制层接口", description = "远程调用xxl-job-admin的Api接口")
@RequestMapping("/xxlJob")
public class XxlJobController{

    private final Logger logger = LoggerFactory.getLogger(XxlJobController.class);

    @Value("${xxl.job.request-url}")
    private String requestUrl;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${LOGIN_IDENTITY_KEY}")
    private String LOGIN_IDENTITY_KEY;


    /**
     * 添加动态xxljob任务接口
     * 参数格式：{"jobCron": "0 0/1 * * * ? ","jobDesc": "打招呼","executorParam": "http://127.0.0.1:8086/user?name=lisi"}
     * @param xxlJob
     * @return
     */
    @RequestMapping(value = "/addXxlJobInfo" , method = RequestMethod.POST)
    @ApiOperation(value = "添加动态xxljob任务")
    public JsonResult addXxlJobInfo(@RequestBody String xxlJob){
        logger.info("添加定时任务入参：{}", xxlJob);
        JSONObject jsonObject = JSONObject.parseObject(xxlJob);
        String jobCron = jsonObject.getString("jobCron");  //任务表达式
        String jobDesc = jsonObject.getString("jobDesc");  //任务描述
        String executorParam = jsonObject.getString("executorParam");  //任务参数
        if(jobCron == null || "".equals(jobCron)){
            return new JsonResult(-1, "任务表达式不能为空");
        }
        if (!CronExpression.isValidExpression(jobCron)) {
            return new JsonResult(-1, "任务表达式格式非法");
        }
        if(jobDesc == null || "".equals(jobDesc)){
            return new JsonResult(-1, "任务描述不能为空");
        }
        if(executorParam == null || "".equals(executorParam)){
            return new JsonResult(-1, "任务参数不能为空");
        }

        //组装参数
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("jobCron", jobCron);
        mapParam.put("jobDesc", jobDesc);
        mapParam.put("executorParam", executorParam);
        mapParam.put("jobGroup", "1");
        mapParam.put("executorRouteStrategy", "FIRST");
        mapParam.put("glueType", "BEAN");
        mapParam.put("executorHandler", "httpJobHandler");
        mapParam.put("executorBlockStrategy", "SERIAL_EXECUTION");  //阻塞任务策略：单机串行
        mapParam.put("author", "lhf");
        mapParam.put("alarmEmail", "xxx@qq.com");
        mapParam.put("executorTimeout", "60000");
        mapParam.put("executorFailRetryCount", "3");
        mapParam.put("childJobId", "");
        mapParam.put("triggerStatus", "1");  //调度状态：0-停止，1-运行

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        String jobId = null;
        try{
            httpClientResult = HttpUtils.doPost(requestUrl+"/jobinfo/add", headerMap, mapParam);

            //logger.info("httpClientResult = {}", httpClientResult);
            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            jobId = jsonResult.getString("content");

            //logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, msg);
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(1, jobId, "SUCCESS");
    }


    /**
     * 修改动态支持GET请求方式的http定时任务
     * @param xxlJob
     * @return
     */
    @RequestMapping(value = "/updateXxlJobInfo" , method = RequestMethod.POST)
    @ApiOperation(value = "修改xxljob任务")
    public JsonResult updateXxlJobInfo(@RequestBody String xxlJob){
        JSONObject jsonObject = JSONObject.parseObject(xxlJob);
        String jobId = jsonObject.getString("jobId");
        String jobCron = jsonObject.getString("jobCron");  //任务表达式
        String jobDesc = jsonObject.getString("jobDesc");  //任务描述
        String executorParam = jsonObject.getString("executorParam");  //任务参数
        if(jobId == null || "".equals(jobId)){
            return new JsonResult(-1, "任务id不能为空");
        }
        if(jobCron == null || "".equals(jobCron)){
            return new JsonResult(-1, "任务表达式不能为空");
        }
        if (!CronExpression.isValidExpression(jobCron)) {
            return new JsonResult(-1, "任务表达式格式非法");
        }
        if(jobDesc == null || "".equals(jobDesc)){
            return new JsonResult(-1, "任务描述不能为空");
        }
        if(executorParam == null || "".equals(executorParam)){
            return new JsonResult(-1, "任务参数不能为空");
        }

        //组装参数
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("id", jobId);
        mapParam.put("jobCron", jobCron);
        mapParam.put("jobDesc", jobDesc);
        mapParam.put("executorParam", executorParam);
        mapParam.put("jobGroup", "1");
        mapParam.put("executorRouteStrategy", "FIRST");
        mapParam.put("glueType", "BEAN");
        mapParam.put("executorHandler", "httpJobHandler");
        mapParam.put("executorBlockStrategy", "SERIAL_EXECUTION");  //阻塞任务策略：单机串行
        mapParam.put("author", "lhf");
        mapParam.put("alarmEmail", "xxxx@qq.com");
        mapParam.put("executorTimeout", "2000");
        mapParam.put("executorFailRetryCount", "3");
        mapParam.put("childJobId", "");
        mapParam.put("triggerStatus", "1");  //调度状态：0-停止，1-运行

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        try{
            httpClientResult = HttpUtils.doPost(requestUrl+"/jobinfo/update", headerMap, mapParam);

            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");

            //logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, msg);
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(1, "SUCCESS");
    }


    /**
     * 根据参数添加或更新调度任务
     * //{"id":0,"jobGroup":6,"jobCron":"0 0/1 * * * ? ","jobDesc":"打招呼","addTime":null,"updateTime":null,"author":"lhf","alarmEmail":"2510736432@qq.com","executorRouteStrategy":"FIRST","executorHandler":"helloJobHandler","executorParam":"","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":2000,"executorFailRetryCount":2,"glueType":"BEAN","glueSource":"","glueRemark":"GLUE代码初始化","glueUpdatetime":null,"childJobId":"","triggerStatus":0,"triggerLastTime":0,"triggerNextTime":0}
     *
     * @param xxlJobInfoVo
     * @return
     */
    @RequestMapping(value = "/addXxlJobTask" , method = RequestMethod.POST)
    @ApiOperation(value = "根据参数添加或更新调度任务")
    public JsonResult addXxlJobTask(@RequestBody XxlJobInfoVo xxlJobInfoVo){
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("jobGroup", xxlJobInfoVo.getJobGroup()+"");
        mapParam.put("jobCron", xxlJobInfoVo.getJobCron());
        mapParam.put("jobDesc", xxlJobInfoVo.getJobDesc());
        mapParam.put("executorRouteStrategy", xxlJobInfoVo.getExecutorRouteStrategy());
        mapParam.put("glueType", xxlJobInfoVo.getGlueType());
        mapParam.put("executorHandler", xxlJobInfoVo.getExecutorHandler());
        mapParam.put("executorBlockStrategy", xxlJobInfoVo.getExecutorBlockStrategy());
        mapParam.put("executorParam", xxlJobInfoVo.getExecutorParam());
        mapParam.put("author", xxlJobInfoVo.getAuthor());
        mapParam.put("alarmEmail", xxlJobInfoVo.getAlarmEmail());
        mapParam.put("executorTimeout", xxlJobInfoVo.getExecutorTimeout()+"");
        mapParam.put("executorFailRetryCount", xxlJobInfoVo.getExecutorFailRetryCount()+"");
        mapParam.put("childJobId", xxlJobInfoVo.getChildJobId());
        mapParam.put("triggerStatus", "1");  //调度状态：0-停止，1-运行

        if(xxlJobInfoVo.getType() == 1){
            if(xxlJobInfoVo.getId() == null){
                return new JsonResult(-1, "修改调度任务时，调度任务id不能为空");
            }
            mapParam.put("id", xxlJobInfoVo.getId()+"");
        }

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        String jobId = null;
        try{
            if(xxlJobInfoVo.getType() == 0){
                httpClientResult = HttpUtils.doPost(requestUrl+"/jobinfo/add", headerMap, mapParam);
            }else if(xxlJobInfoVo.getType() == 1){
                httpClientResult = HttpUtils.doPost(requestUrl+"/jobinfo/update", headerMap, mapParam);
            }else {
                return new JsonResult(-1, "参数有误");
            }

            logger.info("httpClientResult = {}", httpClientResult);
            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());

            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            jobId = jsonResult.getString("content");

            logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, msg);
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(0, jobId, "SUCCESS");
    }

    /**
     * 根据id查询任务执行器
     * @param id
     * @return
     */
    @RequestMapping(value = "/findJobGroup" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询任务执行器")
    public JsonResult findJobGroup(@RequestParam(name = "id")Integer id){

        try{
            //设置header cookie请求头
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            HttpClientResult  httpClientResult = HttpUtils.doGet(requestUrl+"/jobgroup/loadById", headerMap, param);
            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            String code = jsonResult.getString("code");
            String msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, msg);
            }
            String content = jsonResult.getString("content");
            logger.info("content = {}", content);
            return new JsonResult(1, content, "SUCCESS");
        }catch (Exception e){
            logger.error("发生了异常，{}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }
    }


    /**
     * 根据id查找任务执行器
     * @param id
     * @return
     */
    @RequestMapping(value = "/findJobGroupInfo" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id查找任务执行器")
    private String findJobGroupInfo(Integer id){

        try{
            //设置header cookie请求头
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            HttpClientResult  httpClientResult = HttpUtils.doGet(requestUrl+"/jobgroup/loadById", headerMap, param);
            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());

            String content = jsonResult.getString("content");

            return content;
        }catch (Exception e){
            logger.error("发生了异常，{}", e.getMessage());
            return "";
        }
    }


    /**
     * 添加或更新任务执行器
     * @param xxlJobGroupVo
     * @return
     */
    @RequestMapping(value = "/addXxlJobGroup" , method = RequestMethod.POST)
    @ApiOperation(value = "添加或更新任务执行器")
    public JsonResult addXxlJobGroup(@RequestBody XxlJobGroupVo xxlJobGroupVo){
        logger.info("添加/修改执行器入参：{}", xxlJobGroupVo);

        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("appName", xxlJobGroupVo.getAppName());
        mapParam.put("title", xxlJobGroupVo.getTitle());
        mapParam.put("order", xxlJobGroupVo.getOrder()+"");
        mapParam.put("addressType", xxlJobGroupVo.getAddressType()+"");
        mapParam.put("addressList", xxlJobGroupVo.getAddressList());
        if(xxlJobGroupVo.getType() == 1){
            if(xxlJobGroupVo.getId() == null){
                return new JsonResult(-1, "修改调度器时，调度执行器id不能为空");
            }
            mapParam.put("id", xxlJobGroupVo.getId()+"");
        }

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        // {"id":2,"appName":"xxl-job-hi","title":"定时打招呼1","order":4,"addressType":1,"addressList":"127.0.0.1:9999  ","registryList":["127.0.0.1:9999  "]}
        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String requestResult = null;
        String code = null;
        String msg = null;
        JSONObject jsonResult = null;
        try{
            if(xxlJobGroupVo.getType() == 0){   //添加
                httpClientResult = HttpUtils.doPost(requestUrl+"/jobgroup/save", headerMap, mapParam);
            }else if(xxlJobGroupVo.getType() == 1){  //修改
                //修改之前，先判断这个执行器是否存在
                String jobGroup = findJobGroupInfo(xxlJobGroupVo.getId());
                if(jobGroup == null || "".equals(jobGroup)){
                    return new JsonResult(-1, "更新的任务执行器不存在");
                }

                httpClientResult = HttpUtils.doPost(requestUrl+"/jobgroup/update", headerMap, mapParam);
            }else {
                return new JsonResult(-1, "操作类型参数错误");

            }

            logger.info("httpClientResult = {}", httpClientResult);
            jsonResult = JSONObject.parseObject(httpClientResult.getContent());

            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, msg);
            }

        }catch (Exception e){
            logger.error("发生了异常，{}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }
        return new JsonResult(0, "SUCCESS");
    }


    /**
     * 根据id删除已有的调度任务
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteJobInfoById" , method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除已有的调度任务")
    public JsonResult deleteJobInfoById(@RequestParam(value = "id", required = true) Integer id, HttpServletResponse response){

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("id", id);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        try{
            //http://127.0.0.1:8888/xxl-job-admin/jobinfo/remove?id=8
            httpClientResult = HttpUtils.doGet(requestUrl+"/jobinfo/remove", headerMap, mapParam);
            //httpClientResult = HttpUtils.doGet(requestUrl+"/jobinfo/remove", headersMap, mapParam);
            logger.info("httpClientResult = {}", httpClientResult);

            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, "删除失败");
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(0, "SUCCESS");
    }

    /**
     * 根据id删除已有的调度执行器
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteJobGroupById" , method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除已有的调度执行器")
    public JsonResult deleteJobGroupById(@RequestParam(value = "id", required = true) Integer id){

        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("id", id);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        try{
            httpClientResult = HttpUtils.doGet(requestUrl+"/jobgroup/remove", headerMap, mapParam);
            logger.info("httpClientResult = {}", httpClientResult);

            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(!code.equals("200")){
                return new JsonResult(-1, "删除失败");
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(0, "SUCCESS");
    }


    /**
     * 启动任务
     * @param id
     * @return
     */
    @RequestMapping(value = "/startXxlJobTask" , method = RequestMethod.GET)
    @ApiOperation(value = "启动任务")
    public JsonResult startXxlJobTask(@RequestParam(value = "id", required = true) Integer id){
        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        Map<String, Object> startJobIdParam = new HashMap<>();
        startJobIdParam.put("id", id);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        try{
            httpClientResult = HttpUtils.doGet(requestUrl+"/jobinfo/start", headerMap, startJobIdParam);
            logger.info("httpClientResult = {}", httpClientResult);

            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(code.equals("500")){
                return new JsonResult(-1, "任务id不存在");
            }else if(!code.equals("200")) {
                return new JsonResult(-1, "任务启动失败");
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(0, "SUCCESS");
    }

    /**
     * 停止任务
     * @param id
     * @return
     */
    @RequestMapping(value = "/stopXxlJobTask" , method = RequestMethod.GET)
    @ApiOperation(value = "停止任务")
    public JsonResult stopXxlJobTask(@RequestParam(value = "id", required = true) Integer id){
        //设置header cookie请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", LOGIN_IDENTITY_KEY);

        Map<String, Object> stopJobIdParam = new HashMap<>();
        stopJobIdParam.put("id", id);

        //调用远程服务端
        HttpClientResult httpClientResult = null;
        String code = null;
        String msg = null;
        try{
            httpClientResult = HttpUtils.doGet(requestUrl+"/jobinfo/stop", headerMap, stopJobIdParam);
            logger.info("httpClientResult = {}", httpClientResult);

            JSONObject jsonResult = JSONObject.parseObject(httpClientResult.getContent());
            code = jsonResult.getString("code");
            msg = jsonResult.getString("msg");
            logger.info("code = {}, msg = {}", code, msg);
            if(code.equals("500")){
                return new JsonResult(-1, "任务id不存在");
            }else if(!code.equals("200")) {
                return new JsonResult(-1, "任务启动失败");
            }
        }catch (Exception e){
            logger.error("发生了异常， {}", e.getMessage());
            return new JsonResult(-1, "FAIL");
        }

        return new JsonResult(0, "SUCCESS");
    }


}
