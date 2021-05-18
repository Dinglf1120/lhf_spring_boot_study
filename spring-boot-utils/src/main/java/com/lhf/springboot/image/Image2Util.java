package com.lhf.springboot.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.catalina.core.ApplicationContext;

/**
 * @ClassName: Image2Util
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/24 14:36
 */
public final class Image2Util {
    public Image2Util() {

    }

    /*
    public final static String getPressImgPath() {
        String realPath = ApplicationContext.getRealPath("/template/data/util/shuiyin.gif");
        return realPath;
    }
    */


    /**
     * 把图片印刷到图片上
     *
     * @param pressImg --水印文件
     * @param targetImg --目标文件
     * @param resultImg -- 结果图片
     * @param x --x坐标
     * @param y --y坐标
     */
    public final static void pressImage(String pressImg, String targetImg, String resultImg, int x, int y) {
        try {
            //目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            //水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao) / x, (height - height_biao) / y, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(resultImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片
     *
     * @param pressText--文字
     * @param targetImg --目标图片
     * @param degree -- 旋转角度
     * @param fontName --字体名
     * @param fontStyle --字体样式
     * @param newTargetImg --新文件名
     * @param fontSize --字体大小
     * @param x --偏移量
     * @param y
     */

    public static void pressText(String pressText, String targetImg, String newTargetImg, Integer degree, String fontName, int fontStyle, int fontSize, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            //BufferedImage buffImg = new BufferedImage(src.getWidth(null),src.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            //Graphics2D g = buffImg.createGraphics();

            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            // String s="www.qhd.com.cn";
            g.setColor(Color.RED);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            // 4、设置水印旋转
            if (null != degree) {
                ((Graphics2D) g).rotate(Math.toRadians(degree),  image.getWidth()/2, image.getHeight() /2);
            }

            //g.drawString(pressText, width - fontSize - x, height - fontSize / 2 - y);
            g.drawString(pressText,  image.getWidth()/4 , image.getHeight()/2);

            g.dispose();
            FileOutputStream out = new FileOutputStream(newTargetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        //pressText("黑排脚", "C:\\Users\\v_feiheliu\\Pictures\\Camera Roll\\20200509182148.jpg", "F:\\123456.png", 45, "隶书", 150, 120, -100, -250);
        pressImage("C:\\Users\\v_feiheliu\\Pictures\\Camera Roll\\001.jpg", "C:\\Users\\v_feiheliu\\Pictures\\Camera Roll\\005.jpg", "F:\\123456.png", 1, 4);
    }
}
