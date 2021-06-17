package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExcel1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExcel1Application.class, args);
        System.out.println("访问地址：http://localhost:8066/excel/export");
    }

}
