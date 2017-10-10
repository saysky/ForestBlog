# 关于项目

该项目是博主暑假粗略学完 SSM（Spring+SpringMVC+Mybatis）后，开始着手做的一个博客系统。
主要涉及的包括 JSP，JSTL，EL表达式，MySQL，Druid连接池，Spring，SpringMVC，MyBatis 等。
前端采用Layui框架和扒了一个[网站](http://liuyanzhao.com)的前台样式。通过Maven管理依赖。 <br/>
详细介绍：[https://liuyanzhao.com/6347.html](https://liuyanzhao.com/6347.html)

## 效果预览

预览地址：http://blog.liuyanzhao.com  <br/>
前台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2017/10/front-1024x608.jpg)

后台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2017/10/back-1024x611.jpg)

## 使用注意
任何问题都可以联系我 <br/>
Q Q：847064370 <br/>
微信：847064370 <br/>
在线留言：[https://liuyanzhao.com/message.html](https://liuyanzhao.com/message.html)

#### 1、使用IDE导入项目  <br/>
将项目即（ForestBlog文件夹）放到 某个位置，用IDE(如Eclipse，IntelliJ IDEA)导入，然后可以在IDE里运行 Tomcat，访问项目。
因为该项目使用了`Maven`，所有你需要新的IDE需要有Maven插件或者功能，这里就不多介绍了。


#### 2、 导入数据库   <br/>
新建数据库 `forest_blog`，导入数据库（即forest_blog.sql）。注意，数据库的编码和排序规则是utf-8和utf-8_general_ci


#### 3、修改项目中的数据库连接信息   <br/>
修改 `db.properties` 文件，该文件很容易找到，在 src/main/resources 中，注意修改数据库地址、表名、用户名和密码。<br/>
如果不修改，会出现无法启动项目
 
#### 4、修改上传文件路径   <br/>
该项目中，文件上传是传到本地，且和项目文件夹不在一起，就是说是分离的。比如你的项目是在D盘，你可以修改上传路径到E盘。当然我们访问上传的图片时，需要   给Tomcat添加静态资源映射，比如访问 localhost:8080/uplaods/1.jpg tomcat可以在你的E盘找到。这个在第5步会说。
现在是修改上传文件路径，该文件在 src/main/java/com/liuyanzhao/blog/controller/Common/UploadFileController.java
修改第33行的 `String rootPath ="/www/uploads/";` 为你的 uploads 的路径。<br/>
如果不修改，会出现无法上传图片
 
#### 5、给uploads文件夹添加静态资源映射 <br/>
同第4步，我们已经修改了uploads路径，该路径就要和下面的docBase一致。
在 tomcat/conf/server.xml 的 Host 标签内添加如下代码
<Context path="/uploads" docBase="/www/uploads" debug="0" reloadable="true" />
对啦，如果你使用的是IDE，需要在该IDE里修改。
比如 IntelliJ IDEA 是在Tomcat配置中添加<br/>


如果不修改，会出现uploads的图片无法加载，网页打开有延迟
 
#### 6、修改 Tomcat的首页为该项目   <br/>
跟第5步相似
该项目中的所有根路径都是 / ，没有使用相对路径的 `${pagecontext.request.getcontextpath}` ,因为如何相对路径不存在，该值就会为空字符串，以致   首页链接我必须改为 / 。
所以，你的项目首页应该是 localhost:8080，而不是loclahost:8080/ForestBlog
所以要在 server.xml 的Host标签内里添加
<Context path="" docBase="/www/server/panel/vhost/tomcat/ForestBlog" debug="0" reloadable="true" />
其中path=""表示为首页,即localhost:8080或者127.0.0.1：8080
docBase-"xxx"是文件路径


对啦，如果你使用的是IDE，需要在该IDE里修改。
比如 IntelliJ IDEA 是在Tomcat配置中添加<br/>


如果不修改，会出现内页很多链接不对
 
## 下载地址：
Github地址：https://github.com/saysky/ForestBlog
（如果可以帮忙点一次Star和Fork）
 
## 更新
上一次更新：2017年10月10日14:02:02
