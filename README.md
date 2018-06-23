【新消息】基于 SpringBoot 项目(CoderGroup,开发者社区)已上线：[预览地址](http://codergroup.cn)，[开源地址](https://github.com/saysky/CoderGroup)
<br/>
<hr/>

重点强调，已经不少于100人问我为什么这个项目跑不起来，为什么没有样式(主要是这两个问题)。很多家伙也不看我后面的说明，我在这里统一回答一下 <br/>
1. 项目跑不起来，很可能是你的 Tomcat 版本不一致，请更换 Tomcat7 试试，也有可能是 Maven 版本不一致。 <br/>
2. 没有样式是因为，你需要在项目路径上去掉项目名，即项目首页是 http://localhost:8080 而没有 ForestBlog，关于去掉项目名，IDEA很容易，Eclipse需要配置 Tomcat。

# 关于项目

该项目是博主暑假粗略学完 SSM（Spring+SpringMVC+Mybatis）后，开始着手做的一个博客系统。
主要涉及的包括 JSP，JSTL，EL表达式，MySQL，Druid连接池，Spring，SpringMVC，MyBatis 等。
前端采用Layui框架和扒了一个[网站](http://liuyanzhao.com)的前台样式。通过Maven管理依赖。 <br/>
详细介绍：[https://liuyanzhao.com/6347.html](https://liuyanzhao.com/6347.html)

```` 
该项目尚不成熟，有很多不足，不规范
预计十二月份再开发一套 SpringBoot 的博客系统，代码将会更规范，开发会更用心，到时候还会加上一些新的内容，比如 Redis 
准备作为以后的博客使用，敬请期待(已上线：请百度搜索 CoderGroup,或者访问 http://codergroup.cn)。
````



## 效果预览

预览地址：http://blog.liuyanzhao.com  <br/>
前台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2017/10/front-1024x608.jpg)

后台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2017/10/back-1024x611.jpg)
<br/>
后台地址：/admin 或者 /login <br/>
用户名为admin，密码为123456  （备注：密码已改，大家可以在本地测试，实在需要的请联系我。因为这个是个人博客，没有做权限管理，所以不少家伙登录后台就开始测试删除功能，搞得我重新导入数据库几次）

## 项目部署
#### 版本
**tomcat** 7.0.37(建议版本)  <br/>
**maven** 3.5.0(建议版本)  <br/>
**jdk** 1.8.0   <br/>
**mysql** 5.7.19   <br/>
**spring** 4.2.0   <br/>
**mybatis** 3.4.0   <br/>
**os** OS X 10.12 / CentOs 6.8   <br/>

以上版本是博主在本机(Mac)和阿里云的服务器上的主要版本信息。

#### 编码
所有编码统一 UTF-8  <br/>
包括 数据库,Tomcat的server.xml配置,IDE的编码,xml文件编码


## 使用注意
任何问题都可以联系我 <br/>
Q Q：847064370 <br/>
微信：847064370 <br/>
在线留言：[https://liuyanzhao.com/message.html](https://liuyanzhao.com/message.html)

#### 1、使用IDE导入项目  <br/>
将项目即（ForestBlog文件夹）放到 某个位置，用IDE(如Eclipse，IntelliJ IDEA)导入，然后可以在IDE里运行 Tomcat，访问项目。
因为该项目使用了`Maven`，所有你需要新的IDE需要有Maven插件或者功能，这里就不多介绍了。

#### 2、给 resource 目录变成 Source Folder或者 Resource Root 之类的
如果web.xml里出现波浪线表示文件无法找到，请把 resource 文件夹设置为资源文件夹，加入到类路径。


#### 3、 导入数据库   <br/>
新建数据库 `forest_blog`，导入数据库（即forest_blog.sql）。注意，数据库的编码和排序规则是utf-8和utf-8_general_ci


#### 4、修改项目中的数据库连接信息   <br/>
修改 `db.properties` 文件，该文件很容易找到，在 src/main/resources 中，注意修改数据库地址、表名、用户名和密码。<br/>
如果不修改，会出现无法启动项目
 
#### 5、修改上传文件路径   <br/>
该项目中，文件上传是传到本地，且和项目文件夹不在一起，就是说是分离的。比如你的项目是在D盘，你可以修改上传路径到E盘。当然我们访问上传的图片时，需要   给Tomcat添加静态资源映射，比如访问 localhost:8080/uplaods/1.jpg tomcat可以在你的E盘找到。这个在第5步会说。
现在是修改上传文件路径，该文件在 src/main/java/com/liuyanzhao/blog/controller/Common/UploadFileController.java
修改第33行的 `String rootPath ="/www/uploads/";` 为你的 uploads 的路径。<br/>
如果不修改，会出现无法上传图片
 
#### 6、给uploads文件夹添加静态资源映射 <br/>
同第4步，我们已经修改了uploads路径，该路径就要和下面的docBase一致。
在 tomcat/conf/server.xml 的 Host 标签内添加如下代码
<Context path="/uploads" docBase="/www/uploads" debug="0" reloadable="true" />
对啦，如果你使用的是IDE，需要在该IDE里修改。
比如 IntelliJ IDEA 是在Tomcat配置中添加<br/>


如果不修改，会出现uploads的图片无法加载，网页打开有延迟
 
#### 7、修改 Tomcat的首页为该项目   <br/>
跟第5步相似
该项目中的所有根路径都是 / ，没有使用相对路径的 `${pagecontext.request.getcontextpath}` ,因为如何相对路径不存在，该值就会为空字符串，以致首页链接我必须改为 / 。
所以，你的项目首页应该是 localhost:8080，而不是loclahost:8080/ForestBlog
所以要在 server.xml 的Host标签内里添加
<Context path="" docBase="/www/server/panel/vhost/tomcat/ForestBlog" debug="0" reloadable="true" />
其中path=""表示为首页,即localhost:8080或者127.0.0.1：8080
docBase-"xxx"是文件路径


对啦，如果你使用的是IDE，需要在该IDE里修改。
比如 IntelliJ IDEA 是在Tomcat配置中添加<br/>


如果不修改，会出现内页很多链接不对


#### 部署可能出现的问题
1、`class path resource [spring/] cannot be resolved to URL because it does not exist`   <br/>
解决方案：**请查看你的Maven的target生成的文件是否完整**，可以和项目文件对比。你可以在IDE里依次展开几个文件夹，然后编译运行项目（maven会自动compile和install），然后再查看target是否完整。多次重新编译，一般可以解决问题。
如果要部署到tomcat或者远程服务器，可以使用maven的命令package。

2、`3 字节的 UTF-8 序列的字节 3 无效`   <br/>
解决方案：该错误的原因是 **Maven使用的的版本不一致**，请使用maven3.5版本，都在可能会出现上面的报错
 
3、`At least one JAR was scanned for TLDs yet contained no TLDs` <br/>
解决方案：该问题是tomcat8可能出现的问题，我在linux服务器上部署时遇到的，解决方案是skip掉那些要检查的jar
具体方法点此：[https://liuyanzhao.com/6341.html](https://liuyanzhao.com/6341.html)

4、Tomcat 版本不一致可能无法启动

5、待发现补充（希望大家帮我测试，提意见）




#### 其他
* 请关注数据表字段的类型，比如 article 表的article_content字段的类型是 mediumtext 类型。
如果你在后台添加内容时候，出现字数过多的情况，可能会出异常(异常500页面已经全部转成404,可以在 web.xml 里去掉)。

* 因为时间仓促和目前所学的东西有限，还有很多东西没有使用，比如 Redis,Shiro 等



## 下载地址：
Github地址：https://github.com/saysky/ForestBlog
（如果可以帮忙点一次Star和Fork）
 
## 更新
上一次更新：2017年10月10日14:02:02
