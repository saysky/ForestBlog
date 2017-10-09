# ForestBlog
该项目是博主暑假粗略学完 SSM（Spring+SpringMVC+Mybatis）后，开始着手做的一个博客系统。
主要涉及的包括 JSP，JSTL，EL表达式，MySQL，Druid连接池，Spring，SpringMVC，MyBatis 等。
前端采用Layui框架和扒了一个[网站](http://liuyanzhao.com)的前台样式。
通过Maven管理依赖。


## 预览地址
* 前台地址：[http://119.29.54.53:8080/ForestBlog](http://119.29.54.53:8080/ForestBlog)
* 后台地址：[http://119.29.54.53:8080/ForestBlog/login](http://119.29.54.53:8080/ForestBlog/login)
  后台账号 admin  密码 123456
  
## 使用注意
1. 修改数据库连接信息
   数据库的信息在 `db.properties` 文件中
   
2. 添加 Tomcat 静态资源映射
   因为该项目中的文件上传的路径是一个物理路径，如 /Users/liuyanzhao/Documents/uploads
   现在需要在 Tomcat 配置文件里添加一条记录
 
 ```
<Context path="/uploads" docBase="/Users/liuyanzhao/Documents/uploads" reloadable="true" crossContext="true"></Context>
 ```

  这样，当我们访问 
  `http://localhost:8080/ForestBlog/uploads/2017/10/20171009140054148.jpg` 
  或者
  `/Uploads/2017/10/20171009140054148.jpg` 时，
  能够访问到 `/Users/liuyanzhao/Documents/uploads` 中的实际文件
  (如果是使用 Eclipse 或者 IntelliJ IDEA，IDE 里就可以设置)
  
  3. 修改文件上传路径
     该文件在 `com/liuyanzhao/blog/controller/common/UploadFileController.java`
     大概 35 行，修改
```
String rootPath ="/www/uploads/";
```
     注意该路径要和第 2 步的静态资源映射的路径一致。



