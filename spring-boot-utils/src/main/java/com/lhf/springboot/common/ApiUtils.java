package com.lhf.springboot.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回工具类
 * @author Bruss
 * @version 1.0
 */
public class ApiUtils {
	
	/**
	 * 参数为空时返回
	 * @param param   参数名
	 * @param code    返回码
	 * @return
	 */
	public static Map<String, Object> paramsFaild(String param,Integer code){
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		result.put("message", param + "参数不能为空" );
		return result;
	}
	
	/**
	 * 操作成功返回
	 * @param obj       返回值中附带对象
	 * @param massege   返回提示
	 * @return
	 */
	public static Map<String, Object> successed(String objName,Object obj,String massege){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("message", massege );
		result.put(objName, obj);
		return result;
	}
	
	/**
	 * 操作成功
	 * @return
	 */
	public static Map<String, Object> successed(){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("message", "SUCCESSED" );
		return result;
	}
	
	/**
	 * 操作成功
	 * @return
	 */
	public static Map<String, Object> successed(String message){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("message", message);
		return result;
	}
	
	/**
	 * 操作失败
	 * @return
	 */
	public static Map<String, Object> faild(){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("message", "FAILD" );
		return result;
	}
	
	/**
	 * 操作失败
	 * @return
	 */
	public static Map<String, Object> faild(String message){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("message", message );
		return result;
	}
	
	/**
	 * 错误的操作
	 * @param message   错误原因
	 * @param code      错误返回码
	 * @return
	 */
	public static Map<String, Object> wrong(String message,Integer code){
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		result.put("message", message );
		return result;
	}
	
	/**
	 * 校验返回
	 * @param obj       返回值中附带对象
	 * @param massege   返回提示
	 * @return
	 */
	public static Map<String, Object> detect(String objName,Object obj,String massege){
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("message", massege );
		result.put(objName, obj);
		return result;
	}
	
	public static Map<String, Object> getMap(){
		Map<String, Object> result = new HashMap<>();
		return result;
	}

}
