package com.lhf.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootTestApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringBootTestApplication.class, args);
        new SpringApplicationBuilder(SpringBootTestApplication.class).bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }

}
