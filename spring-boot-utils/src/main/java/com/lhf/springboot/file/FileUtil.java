package com.lhf.springboot.file;

import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * @ClassName: FileUtil
 * @Author: liuhefei
 * @Description: 文件操作工具栏
 * @Date: 2019/8/15 18:52
 */
public class FileUtil {

    /**
     * 将base64编码的文件转换为图片
     * @param base64
     * @param path
     * @return
     * @throws IOException
     */
    public static File generateImage(String base64, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        File file = new File(path);
        String fileName = file.getName();
        System.out.println("file = " + file);
        //创建临时文件
        File tempFile = File.createTempFile(fileName, ".png");
        FileOutputStream fos = new FileOutputStream(tempFile);

        try (OutputStream out = new FileOutputStream(path)){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            return file;
        } finally {
            //关闭临时文件
            fos.flush();
            fos.close();
            try {
                Thread.sleep(10000);
                tempFile.deleteOnExit();//程序退出时删除临时文件
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static String readFile(String filePathAndName) {
        String fileContent = "";
        try {
            File f = new File(filePathAndName);
            if(f.isFile()&&f.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
                BufferedReader reader=new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //将读取到的字符拼接
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        System.out.println("fileContent:"+fileContent);
        return fileContent;
    }

    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(File file) {
        //File file = new File();
        String fileName = file.getName();
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
        }
    }


    /**
     * FileWritter写入文件
     * FileWritter, 字符流写入字符到文件。默认情况下，它会使用新的内容取代所有现有的内容，
     * 然而，当指定一个true （布尔）值作为FileWritter构造函数的第二个参数，它会保留现有的内容，并追加新内容在文件的末尾。
     * @param finalFilePath  文件路径+文件名   D:\\test.txt
     * @param jsonStr   要写入文件的内容
     * @param bool true 追加  false 覆盖旧数据
     */
    public static void writeToFile(String finalFilePath, String jsonStr, boolean bool) {
        try{
            //创建文件
            File file = new File(finalFilePath);
            if(!file.exists()){
                file.createNewFile();   //文件如果不存在，就创建文件
            }

            FileWriter writer = new FileWriter(finalFilePath, bool);
            writer.write(jsonStr);
            writer.close();
            System.out.println("文件写入成功");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
