###总结几种重定向（redirect）方法

1. 通过ModelAndView跳转

2. 通过HttpServletResponse的response.sendRedirect(url)跳转

3. 通过redirect返回String类型跳转，此时需要@Controller注解
