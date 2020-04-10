## springboot启动时执行任务CommandLineRunner

在使用SpringBoot构建项目时，我们通常有一些预先数据的加载。那么SpringBoot提供了一个简单的方式来实现–CommandLineRunner。

我们在使用Springboot开发项目时，有时需要在项目启动时候就初始化数据，这时我们可以使用SpringBoot为我们提供的CommandLineRunner类来执行启动时执行任务。


SpringBoot提供的一种简单的实现方案就是添加一个model并实现CommandLineRunner接口，项目启动初始化数据的代码放在实现的run方法中即可。
这里的初始化你可以调用相关服务层的代码实现查询数据，存入redis，初始化相关数据的功能。


CommandLineRunner是一个接口，我们需要时，只需实现该接口就行。如果存在多个加载的数据，我们也可以使用@Order注解来排序。

SpringBoot在项目启动后会遍历所有实现CommandLineRunner的实体类并执行run方法，如果需要按照一定的顺序去执行，那么就需要在实体类上使用一个@Order注解【 @Order(value=1..)】（或者实现Order接口）来表明顺序.


CommandLineRunner和ApplicationRunner的区别：                
CommandLineRunner和ApplicationRunner接口的作用是完全一致的，唯一不同的则是接口中待实现的run方法，
其中CommandLineRunner的run方法参数类型与main一样是原生的String[] 类型，
而ApplicationRunner的run方法参数类型为ApplicationArguments类型。
