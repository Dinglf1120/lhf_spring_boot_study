package com.lhf.springboot.springbootxxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * @ClassName: HttpJobHandler
 * @Author: liuhefei
 * @Description: 跨平台Http任务  支持Get请求方式的http
 */
@JobHandler(value = "httpJobHandler")
@Component
public class HttpJobHandler extends IJobHandler {

    private final Logger logger = LoggerFactory.getLogger(HttpJobHandler.class);

    @Override
    public ReturnT<String> execute(String urlParam) throws Exception {
        logger.info("httpJobHandler 执行了...." + LocalDateTime.now() +" 参数："+ urlParam );

        // request
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            // connection
            URL realUrl = new URL(urlParam);
            connection = (HttpURLConnection) realUrl.openConnection();

            // connection setting
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(60 * 1000);
            connection.setConnectTimeout(60 * 1000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

            // do connection
            connection.connect();

            //Map<String, List<String>> map = connection.getHeaderFields();

            // valid StatusCode
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
            }

            // result
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            String responseMsg = result.toString();

            XxlJobLogger.log(responseMsg);
            logger.info("responseMsg = {}", responseMsg);
            return SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e);
            return FAIL;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e2) {
                XxlJobLogger.log(e2);
            }
        }
    }
}
