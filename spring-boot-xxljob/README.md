## 集成xxl-job实现调度任务相关API接口

项目整合，实现远程调度xxl-job-admin的相关API接口，实现添加任务执行器，添加任务，修改任务，删除任务等功能

xxl-job官方文档：https://www.xuxueli.com/xxl-job/    


xxl-job源码仓库：               
https://github.com/xuxueli/xxl-job	
            
http://gitee.com/xuxueli0323/xxl-job


部署：
1. 先部署运行xxl-job-admin
2. 在部署运行此项目
3. 记得修改两个项目的属性配置文件。
4. 如果修改了xxl-job-admin的登录账号密码，则要修改xxl.job.accessToken和LOGIN_IDENTITY_KEY的值
