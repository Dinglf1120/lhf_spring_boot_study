package com.lhf.springboot.springbootxxljob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 19:26
 */
@Configuration
@EnableSwagger2
@Profile("{dev}")  //在开发环境开启，在生产环境禁用
//@ConditionalOnProperty(name = "enabled", prefix = "swagger", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot.springbootxxljob"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Api接口接口")
                .description("api接口描述信息")
                .contact(new Contact("liuhefei", "https://www.imooc.com/u/1323320", "2510736432@qq.com"))
                .termsOfServiceUrl("https://swagger.io/swagger-ui/")
                .version("1.0")
                .build();
    }
}
