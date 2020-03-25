package com.lhf.springboot.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: QRCodeUtil
 * @Author: liuhefei
 * @Description: 二维码解析
 * @Date: 2019/11/7 11:54
 */
public class QRCodeUtil {


    public static void createQRCode(String content, String path) {
        //二维码宽度
        int width = 200;
        //二维码高度
        int height = 200;
        //二维码格式
        String format = "jpg";

        //定义二维码内容参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错等级，在这里我们使用M级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置边框距
        hints.put(EncodeHintType.MARGIN, 2);

        //生成二维码
        try {
            //生成二维码的内容
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            //指定生成图片的保存路径
            Path file = new File(path).toPath();
            //生成二维码
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码
     *
     * @param content 二维码的内容
     * @param width   二维码图片的宽度
     * @param height  二维码图片的高度
     * @param format  二维码图片的格式，如：jpg
     * @return 二维码的字节流
     * @throws Exception 异常
     */
    public static byte[] createQRCode(String content, int width, int height, String format) throws Exception {
        //定义二维码内容参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错等级，在这里我们使用H级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置边框距
        hints.put(EncodeHintType.MARGIN, 0);

        //生成二维码
        //生成二维码的内容
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        return outputStream.toByteArray();
    }

}
