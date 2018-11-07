### Swagger2

[Swagger2和Springmvc整合详细记录](https://blog.csdn.net/lee_sine/article/details/80727795)


### Spring AOP

[无法找到元素 'aop:aspectj-autoproxy'](https://blog.csdn.net/u010004317/article/details/47700447)

[无法找到元素 'context:component-scan' 的声明](https://blog.csdn.net/eacter/article/details/44624505)

```
 <!--spring aop编程支持-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.2.0.RELEASE</version>
    </dependency>
    
  pom.xml中使用的是 aop 4.2,该版本引入schem时需要注意，没有4.2.xsd的工作空间
spring.schemas
http\://www.springframework.org/schema/aop/spring-aop-2.0.xsd=org/springframework/aop/config/spring-aop-2.0.xsd
http\://www.springframework.org/schema/aop/spring-aop-2.5.xsd=org/springframework/aop/config/spring-aop-2.5.xsd
http\://www.springframework.org/schema/aop/spring-aop-3.0.xsd=org/springframework/aop/config/spring-aop-3.0.xsd
http\://www.springframework.org/schema/aop/spring-aop-3.1.xsd=org/springframework/aop/config/spring-aop-3.1.xsd
http\://www.springframework.org/schema/aop/spring-aop-3.2.xsd=org/springframework/aop/config/spring-aop-3.2.xsd
http\://www.springframework.org/schema/aop/spring-aop-4.0.xsd=org/springframework/aop/config/spring-aop-4.0.xsd
http\://www.springframework.org/schema/aop/spring-aop-4.1.xsd=org/springframework/aop/config/spring-aop-4.1.xsd
http\://www.springframework.org/schema/aop/spring-aop.xsd=org/springframework/aop/config/spring-aop-4.1.xsd

引用版本
 http://www.springframework.org/schema/aop
 http://www.springframework.org/schema/aop/spring-aop-4.1.xsd
```
