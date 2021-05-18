package com.lhf.springboot.springbootxxljob.model;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: XxlJobInfoVo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/04/14 11:49
 */
@ApiModel("任务调度")
public class XxlJobInfoVo {

    private Integer id;  //任务id，必填

    private Integer type; //操作类型：0:添加任务，1:修改任务

    @ApiModelProperty("执行器主键ID")
    private Integer jobGroup;		// 执行器主键ID

    @ApiModelProperty("任务时间表达式")
    private String jobCron;		// 任务执行CRON表达式

    @ApiModelProperty("任务描述")
    private String jobDesc;

    @ApiModelProperty("执行器路由策略")
    private String executorRouteStrategy;	// 执行器路由策略

    @ApiModelProperty("GLUE类型")
    private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum

    @ApiModelProperty("执行器，任务Handler名称")
    private String executorHandler;		    // 执行器，任务Handler名称

    @ApiModelProperty("阻塞处理策略")
    private String executorBlockStrategy;	// 阻塞处理策略

    @ApiModelProperty("执行器，任务参数")
    private String executorParam;		    // 执行器，任务参数

    @ApiModelProperty("负责人")
    private String author;		// 负责人

    @ApiModelProperty("报警邮件")
    private String alarmEmail;	// 报警邮件

    @ApiModelProperty("任务执行超时时间，单位秒")
    private int executorTimeout;     		// 任务执行超时时间，单位秒

    @ApiModelProperty("失败重试次数")
    private int executorFailRetryCount;		// 失败重试次数

    @ApiModelProperty("子任务ID，多个逗号分隔")
    private String childJobId;		// 子任务ID，多个逗号分隔

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getExecutorRouteStrategy() {
        return executorRouteStrategy;
    }

    public void setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy;
    }

    public String getGlueType() {
        return glueType;
    }

    public void setGlueType(String glueType) {
        this.glueType = glueType;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    public int getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(int executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public int getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    public void setExecutorFailRetryCount(int executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public String getChildJobId() {
        return childJobId;
    }

    public void setChildJobId(String childJobId) {
        this.childJobId = childJobId;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
