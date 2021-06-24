package com.lhf.springboot.sms;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 创蓝发送短信工具类
 * @author BRUSS
 *
 */
public class ChuangLanClient {

	public static void main(String[] args) {

		/*国内URL：http://intapi.253.com/send/json*/
		/*国外URL：http://intapi.sgap.253.com/send/json*/
//		String url = "http://intapi.sgap.253.com/send/json" ;//http://intapi.sgap.253.com/send/json
		JSONObject paramMap = new JSONObject();
		String account = "xxxxx"  ;   //账号
		String password = "xxxxxxx"  ;   //密码
		String content = "你好！" ;
		String mobile = "xxxxxxx" ;   //手机号
		paramMap.put("account", account  );
		paramMap.put("password", password  );
		paramMap.put("msg", content  );
		paramMap.put("mobile", mobile   );
		paramMap.put("senderId", ""   );
//		System.out.println(post(paramMap,url));
		System.out.println(JSONObject.toJSONString(paramMap));
	}

	/**
	 * 单发短信
	 * @param account   创蓝账号
	 * @param password  创蓝密码
	 * @param mobile    收短信手机号
	 * @param content   发送短信内容
	 * @return
	 * {"code": "0","error": "","msgid": "11871xxxxxxxx"}
	 */
	public String singleSend(String url, String account, String password, String mobile, String content){
		JSONObject paramMap = new JSONObject();
		paramMap.put("account", account );
		paramMap.put("password", password );
		paramMap.put("mobile", mobile );
		paramMap.put("msg", content );
		paramMap.put("senderId", "" );
		return post(paramMap, url);
	}

	public String post(JSONObject json, String url){
		String result = "";
		HttpPost post = new HttpPost(url);
		try{
			CloseableHttpClient httpClient = HttpClients.createDefault();

			post.setHeader("Content-Type","application/json;charset=utf-8");
			post.addHeader("Authorization", "Basic YWRtaW46");
			StringEntity postingString = new StringEntity(json.toString(),"utf-8");
			post.setEntity(postingString);
			post.setConfig(RequestConfig.custom()
					.setConnectionRequestTimeout(3000)
					.setConnectTimeout(3000)
					.setSocketTimeout(3000).build());
			HttpResponse response = httpClient.execute(post);

			InputStream in = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			StringBuilder strber= new StringBuilder();
			String line = null;
			while((line = br.readLine())!=null){
				strber.append(line+'\n');
			}
			br.close();
			in.close();
			result = strber.toString();
			if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
				result = "服务器异常";
			}
		} catch (Exception e){

		} finally{
			post.abort();
		}
		return result;
	}

}
