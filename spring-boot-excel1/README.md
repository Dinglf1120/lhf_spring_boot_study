#Springboot导出Excel数据

引入依赖：
```java
<dependency>
 <groupId>org.apache.poi</groupId>
 <artifactId>poi-ooxml</artifactId>
 <version>3.17</version>
</dependency>
```

定义Excel视图，需要继承自AbstractXlsxView或者AbstractXlsView, 需要实现一个abstract方法buildExcelDocument用于创建Sheet，构造Excel数据。

在控制层方法中，定义的方法返回值是ModelAndView, 当代码执行new ModelAndView(excelView, map)时会执行ExcelView#buildExcelDocument的方法生成excel表格导出。

如果还需要导出其他excel，同样继承下ExcelView1并实现setRow和setStyle就可以了。仿照OrdersExcleView.java类实现导出其他excel.


https://www.cnblogs.com/fqfanqi/p/6172223.html                 
https://blog.csdn.net/wang124454731/article/details/73850645
