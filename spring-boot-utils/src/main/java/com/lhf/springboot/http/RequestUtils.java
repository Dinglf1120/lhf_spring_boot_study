package com.lhf.springboot.http;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
 /**
  * 获得iP地址
  * @return
  */
 public static String getRemortIP(HttpServletRequest request) {
  String ip = request.getHeader("x-forwarded-for");
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
   ip = request.getHeader("X-Forwarded-For");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
   ip = request.getHeader("Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
   ip = request.getHeader("WL-Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
   ip = request.getRemoteAddr();
  }
  if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
   String sp[] = ip.split(",");
   for (int i = 0; i < sp.length; i++) {
    if (sp[i] != null && !sp[i].equals("null")) {
     ip = sp[i].trim();
     break;
    }
   }
  }
  return ip;
 }
}