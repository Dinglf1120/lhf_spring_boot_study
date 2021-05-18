##Springboot整合Elastic-Job

Elastic-Job官网：http://elasticjob.io/index_zh.html

Elastic-Job默认采用平均分片策略

如果有3台服务器，分成9片，则每台服务器分到的分片是：1=[0,1,2], 2=[3,4,5], 3=[6,7,8]

如果有3台服务器，分成8片，则每台服务器分到的分片是：1=[0,1,6], 2=[2,3,7], 3=[4,5]

如果有3台服务器，分成10片，则每台服务器分到的分片是：1=[0,1,2,9], 2=[3,4,5], 3=[6,7,8]


项目的启动之前需要安装配置并启动zookeeper服务


来源：          
https://www.cnblogs.com/xmzJava/p/9838358.html   

https://www.cnblogs.com/gdufs/p/10925420.html

