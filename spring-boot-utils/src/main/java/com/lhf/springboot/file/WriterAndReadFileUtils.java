package com.lhf.springboot.file;

import java.io.*;

/**
 * @ClassName: WriterAndReadFileUtils
 * @Author: liuhefei
 * @Description: Java写入文件，读取文件内容工具类
 * @Date: 2020/3/23 10:24
 */
public class WriterAndReadFileUtils {

    /**
     * FileWritter写入文件
     * FileWritter, 字符流写入字符到文件。默认情况下，它会使用新的内容取代所有现有的内容，
     * 然而，当指定一个true （布尔）值作为FileWritter构造函数的第二个参数，它会保留现有的内容，并追加新内容在文件的末尾。
     * @param fileName  文件路径+文件名   D:\\test.txt
     * @param content   要写入文件的内容
     */
    public static void writerFile(String fileName, String content){
        try{
            //创建文件
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();   //文件如果不存在，就创建文件
            }

            FileWriter writer = new FileWriter(fileName);  //替换所有现有的内容与新的内容。
            writer.write(content);
            writer.close();
            System.out.println("文件写入成功");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * FileWritter写入文件
     * FileWritter, 字符流写入字符到文件。默认情况下，它会使用新的内容取代所有现有的内容，
     * 然而，当指定一个true （布尔）值作为FileWritter构造函数的第二个参数，它会保留现有的内容，并追加新内容在文件的末尾。
     * @param fileName  文件路径+文件名   D:\\test.txt
     * @param content  要写入文件的内容
     */
    public static void writerFileAdd(String fileName, String content){
        try{
            //创建文件
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();   //文件如果不存在，就创建文件
            }

            FileWriter writer = new FileWriter(fileName, true);  //保留现有的内容和附加在该文件的末尾的新内容。
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write(content);
            writer.close();
            System.out.println("文件写入成功");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用BufferedWriter写入文件
     * 缓冲字符（BufferedWriter ）是一个字符流类来处理字符数据。不同于字节流（数据转换成字节），你可以直接写字符串，数组或字符数据保存到文件。
     * @param fileName  文件路径+文件名   D:\\test.txt
     * @param content 要写入文件的内容
     */
    public static void bufferedWriterToFile(String fileName, String content){

        try{
            //创建文件
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();   //文件如果不存在，就创建文件
            }

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            System.out.println("文件写入成功！");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * FileOutputStream写入文件
     * 文件输出流是一种用于处理原始二进制数据的字节流类。为了将数据写入到文件中，必须将数据转换为字节，并保存到文件。
     * @param fileName   文件路径+文件名   D:\\test.txt
     * @param content 要写入文件的内容
     */
    public static void fileOutputStreamToFile(String fileName, String content){
        FileOutputStream outputStream = null;
        File file;

        try{
            file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();  //如果文件不存在就创建文件
            }

            outputStream = new FileOutputStream(file);
            byte[] contentBytes = content.getBytes();
            outputStream.write(contentBytes);
            outputStream.flush();
            outputStream.close();
            System.out.println("文件写入成功！");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(outputStream != null){
                    outputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用FileReader类读取文件内容
     * @param fileName  文件路径+文件名   D:\\test.txt
     */
    public static void fileReaderToFile(String fileName){

        int num = 0;
        try{
            FileReader reader = new FileReader(fileName);
            num = reader.read();
            while (num != -1){
                System.out.print((char)num);
                num = reader.read();
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 使用BufferedReader类读取文件内容
     * @param fileName 文件路径+文件名   D:\\test.txt
     */
    public static void bufferedReaderToFile(String fileName){
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while (line != null){
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "D:\\test.txt";
        String content = "相思欲寄无从寄，画个圈儿替。话在圈儿外，心在圈儿里。单圈儿是我，双圈儿是你。你心中有我，我心中有你。月缺了会圆，月圆了会缺。整圆儿是团圆，半圈儿是别离。我密密加圈，你须密密知我意。还有数不尽的相思情，我一路圈儿圈到底。";
        //writerFile(fileName, content);

        //writerFileAdd( fileName,  content);

        //bufferedWriterToFile( fileName,  content);

        fileOutputStreamToFile( fileName,  content);

        fileReaderToFile(fileName);

        System.out.println();
        bufferedReaderToFile(fileName);
    }
}
