package com.lhf.springboot.springbootjapidocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJapidocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJapidocsApplication.class, args);
        DocsConfig config = new DocsConfig();
        config.setProjectPath("G:\\lhf_spring_boot_study\\spring-boot-japidocs"); // 项目根目录
        config.setProjectName("spring-boot-japidocs"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("spring-boot-japidocs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
