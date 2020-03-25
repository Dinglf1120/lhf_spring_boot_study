#Springboot整合swagger文档

http://localhost:8090/swagger-ui.html

### SpringBoot线上禁用Swagger文档
方法一：使用@Profile注解            
@Profile("{dev}")  表示在开发环境开启，而在生产环境禁用                   

方法二：使用@ConditionalOnProperty注解                     
@ConditionalOnProperty(name = "enabled", prefix = "swagger", havingValue = "true", matchIfMissing = true)            
然后在属性配置文件中配置如下信息：
swagger.enable=true         
即可开启，生产环境下不配置则默认关闭。
