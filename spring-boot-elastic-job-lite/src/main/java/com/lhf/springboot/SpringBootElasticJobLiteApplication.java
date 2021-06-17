package com.lhf.springboot;

import com.lhf.springboot.service.EmbedZookeeperServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootElasticJobLiteApplication {

    public static void main(String[] args) {
        EmbedZookeeperServer.start(6181);
        SpringApplication.run(SpringBootElasticJobLiteApplication.class, args);
    }


}
