package com.lhf.springboot.image;

import cn.hutool.core.convert.Convert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: Imagebase64Util
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/23 17:49
 */
public class Imagebase64Util {

    /**
     * Image转base64
     * @param imgPath
     * @return
     */
    public static String ImageToBase64ByPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * URL转base64
     * @param imgURL
     * @return
     */
    public static String ImageToBase64ByUrl(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }		BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    /**
     * base64转image
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    public static boolean Base64ToImage(String imgStr, String imgFilePath) {
        if ("".equals(imgStr) || imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Image Base64ToImage(String base64String) {
        if("".equals(base64String) || base64String == null){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        InputStream inputStream = null;
        //FileOutputStream outputStream = null;
        try{
            byte[] b = decoder.decodeBuffer(base64String);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //outputStream.write(b);
            inputStream.read(b);

            Image image = ImageIO.read(inputStream);
            return image;
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) throws IOException {

        String base64 = "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";
        ImageUtil.generateImage(base64, "F:\\echarts\\test.png");

        Image image = Base64ToImage(base64);
        //Image image = ImageIO.read(new File("F:\\echarts\\test.png"));

        System.out.println(image);
        System.out.println(image.getWidth(null));
        System.out.println(image.getHeight(null));
    }

}
