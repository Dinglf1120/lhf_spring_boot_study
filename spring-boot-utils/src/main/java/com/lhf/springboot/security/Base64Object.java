package com.lhf.springboot.security;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName Base64Object
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/5/14 12:28
 **/
public class Base64Object {
    /**
     * 把String的转换成base64码
     */
    public static String stringToBase64(String ss) {
        byte[] bytes = ss.getBytes();
        String encode = Base64Utils.encode(bytes);
        return encode;
    }

    /**
     * 把base64的String码转换成正常显示的字符串
     */
    public static String base64ToString(String base64) throws Exception {
        String result;
        try {
            byte[] decode = Base64Utils.decode(base64);
            result = new String(decode);
        }catch (Exception e){
            throw new Exception(base64+"：格式错误！");
        }
        return result;
    }
    /**
     * 将流转base64字符串
     * @param in
     * @return
     */
    public static String streamToBase64(InputStream in) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }
    /**
     * 将文件转base64字符串
     * @param path
     * @return
     */
    public static String fileToBase64(String path) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes=new byte[(int)file.length()];
            in.read(bytes);
            base64 = java.util.Base64.getEncoder().encodeToString(bytes);
            //  base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    /**
     * base64转文件
     * @param base64
     * @param filePath
     */
    public static void base64ToFile(String base64, String filePath) {
        try {
            Files.write(Paths.get(filePath), java.util.Base64.getDecoder().decode(base64), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String baseStr = stringToBase64("84e51b9e9837c3053e427641cd1eeceed4b1d161");
        System.out.println(baseStr);
    }
}
