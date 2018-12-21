# 关于项目

该博客是基于SSM实现的一个个人博客系统，适合初学SSM和个人博客制作的同学学习。
主要涉及技术包括的包括 Maven、Spring、SpringMVC、MyBatis、Redis、JSP等。
前端采用Layui框架和扒了一个[网站](http://liuyanzhao.com)的前台样式。 <br/>
详细介绍：[https://liuyanzhao.com/6347.html](https://liuyanzhao.com/6347.html)

## 效果预览

预览地址：http://blog.liuyanzhao.com  <br/>
前台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2018/12/ssmblog.png)

后台效果图  <br/>
![](https://liuyanzhao.com/wp-content/uploads/2018/12/ssmblog2.png)
<br/>
后台地址：/admin 或者 /login <br/>
为了避免大家删除数据，暂时不给后台账号

## 使用注意
#### 1.开发工具的选择
请使用 IntelliJ IDEA, 尽量不要用 Eclipse/MyEclipse，使用前者项目起不来我可以帮忙解决，后者直接忽视(理论上可以起)

#### 2.确保你安装了 Maven
没有用过 `Maven` 的童鞋，感觉去学一下，安装一下，可以使用 3.3.9 或 3.5.0的版本

#### 3.请安装 Lombok 插件
代码中多次使用 `@Data` 注解，请确保你的 IDE 安装了 `Lombok` 插件，否则找不到 getter/setter 方法

#### 4.请安装 Redis
项目中简单集成了 `Redis`，请确保提前安装好 `Redis`，`Redis` 密码在 `db.properties` 配置文件中有，默认为空

#### 5.项目首页没有文件夹名称
确保 tomcat 配置中 `application context` 是 /，而不是 /ForestBlog。这是导致你们首页css样式全无的原因，因为引用css路径都是 /xxx/xxx.css
![](https://liuyanzhao.com/wp-content/uploads/2018/12/tomcat.png)

## 使用步骤
任何问题都可以联系我 <br/>
Q Q：847064370 <br/>
微信：847064370 <br/>
在线留言：[https://liuyanzhao.com/message.html](https://liuyanzhao.com/message.html)

#### 1、克隆项目  <br/>
克隆或者下载项目到本地，解压，主要分为三个部分：ForestBlog、uploads 和 forest_blog.sql <br/>
ForestBlog: 完整项目源码, 可以使用 IDEA 导入或者打开  <br/>
uploads: 上传图片的目录，与源码分离开来，可以放到物理磁盘某一目录，如D盘某目录，后面会讲 <br/>
forest_blog.sql: 数据库文件，请先创建数据库，然后以运行sql文件方式导入 <br/>

#### 2.使用 IDEA 导入项目
确保你安装了 Maven，导入项目时，选择已存在的项目，类型是 Maven 项目

#### 3、 导入数据库   <br/>
新建数据库 `forest_blog`，导入数据库（即forest_blog.sql）。注意，数据库的编码和排序规则是utf-8和utf-8_general_ci  <br/>
数据库默认用户名 root，密码 123456

#### 4、修改项目中的数据库连接信息   <br/>
修改 `db.properties` 文件，该文件很容易找到，在 src/main/resources 中<br/>
里面有 MySQL 和 Redis 的数据库连接信息，请确保已安装和启动 MySQL 和 Redis <br/>
注意修改数据库地址、表名、用户名和密码。<br/>

 
#### 5、配置 uploads 目录   <br/>
该项目中，文件上传是传到本地，且和项目文件夹不在一起，就是说是源码和上传目录是分离的。 <br/>
比如我们把 uploads 目录放到 E盘根目录，比如有一张图片路径是 `E:/uploads/2017/10/avatar.jpg`, 我们想在项目中以 `http://loclahost:8080/uploads/2017/10/avatar.jpg ` 方式访问，需要以下两步：<br/>

1. 修改 UploadFileController.java 中上传路径，需要修改 rootPath 为你指定的 uploads 目录，如 `String rootPath ="E:/uploads/";` <br/>
如果不修改，会出现无法上传失败；<br/>

2. 给 IDEA 设置静态资源映射，在 tomcat 配置中 Deployment 里面添加，如图
![](https://liuyanzhao.com/wp-content/uploads/2018/12/tomcat2.png)

如果你是在 Linux 服务器上部署，需要在 Tomcat 配置文件中添加 <br/>
在 tomcat/conf/server.xml 的 Host 标签内添加如下代码 <br/>
<Context path="/uploads" docBase="/www/uploads" debug="0" reloadable="true" />

如果不修改，会出现uploads的图片无法加载，无法显示上传目录的图片

 
## 下载地址：
Github地址：https://github.com/saysky/ForestBlog
（如果可以帮忙点一次Star和Fork）
 
## 更新记录
第二次更新：2018年11月26日 <br/>
大更，重新规范了代码，完善了注释，集成了 Redis

首次提交：2017年10月10日  <br/>
