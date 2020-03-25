##SpringBoot整合生成验证码

通过修改前端代码，实现算术或字符串验证码，前端代码如下：
```java
<div style="float: left;">
   <i><img style="height:28px;" id="codeImg" alt="点击更换" title="点击更换" src="captcha/captchaImage?type=char" /></i>
</div>
```

其中type取值如下：
1. type=math : 验证码为算术类型

2. type=char  : 验证码为字符串类型
