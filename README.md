最新消息，SpringBoot博客已经开源，[SENS](https://github.com/saysky/SENS) <br/>
最新消息 SpringBoot/SSM/Duubo多个版本 [初云博客-SpringBoot版本](https://github.com/saysky/ChuyunBlog)<br/>
[博主付费商品](https://liuyanzhao.com/shop.html)

# 关于项目
该博客是基于SSM实现的个人博客系统，适合初学SSM和个人博客制作的同学学习。<br/>
不支持用户注册，需要多用户的可以去看 [SENS](https://github.com/saysky/SENS) 和 [ChuyunBlog](https://github.com/saysky/ChuyunBlog) <br/>
主要涉及技术包括的包括 Maven、Spring、SpringMVC、MyBatis、JSP、MySQL等。 <br/>
详细介绍：[https://liuyanzhao.com/6347.html](https://liuyanzhao.com/6347.html) <br/>
预览地址 [https://forestblog.liuyanzhao.com](https://forestblog.liuyanzhao.com)

**博主提供有偿服务**
- 远程调试环境和运行项目 50
- 项目论文 200
- 项目详细讲解2-3小时 200-300
- 部署到云服务器 200
- 项目和部分功能定制 200-500
- 毕业设计定制 1000-1500

## 效果预览
因博主服务器数量有限，目前该SSM博客已下线，不提供实时预览，大家可以本地部署。<br/>
目前 SENS 部署了，效果和该项目前端相似，预览地址 https://blog.liuyanzhao.com 。<br/>
以及博主个人博客主站 https://liuyanzhao.com 也是基于 springboot+vuejs 开发。<br/>
<br/>

前台效果图  <br/>
![image](uploads/home.png)
<br/>
后台效果图  <br/>
![image](uploads/admin.png)
<br/>
后台地址：/admin 或者 /login <br/>


## 使用注意
#### 1.开发工具的选择
请使用 IntelliJ IDEA, 尽量不要用 Eclipse/MyEclipse，使用前者项目起不来我可以帮忙解决，后者直接忽视(理论上可以起)

#### 2.确保你安装了 Maven
从官网下载 Maven，并配置阿里云镜像，IDEA 或 Eclipse 里需要设置 Maven 的 settings.xml 。

#### 3.请安装 Lombok 插件
代码中多次使用 `@Data` 注解，请确保你的 IDE 安装了 `Lombok` 插件，否则找不到 getter/setter 方法。如果你的 lombok 无效，可能是 pom.xml 里的 lombok 版本和你安装的lombok 版本相差较大。<br/>
无论是 Eclipse 还是 IDEA 都需要安装 lombok。

#### 4.项目首页没有文件夹名称
确保 tomcat 配置中 `application context` 是 /，而不是 /ForestBlog。这是导致你们首页css样式全无的原因，因为引用css路径都是 /xxx/xxx.css
![image](https://github.com/saysky/ForestBlog/blob/master/uploads/tomcat.png)


## 使用步骤
任何问题都可以联系我 <br/>
简单的我可以直接口头回复你，复杂的可以有偿远程帮你。<br/>
Q Q：847064370 <br/>
微信：847064370 <br/>
在线留言：[https://liuyanzhao.com/message.html](https://liuyanzhao.com/message.html)

#### 1、克隆项目  <br/>
克隆或者下载项目到本地，解压，主要分为三个部分：ForestBlog、uploads 和 forest_blog.sql <br/>
ForestBlog: 完整项目源码, 可以使用 IDEA 导入或者打开，记得要导入里面那个 ForestBlog项目，即 pom.xml所在的父目录。  <br/>
uploads: 上传图片的目录，与源码分离开来，可以放到物理磁盘某一目录，如D盘某目录，后面会讲 <br/>
forest_blog.sql: 数据库文件，请先创建数据库，然后以运行sql文件方式导入 <br/>

#### 2.使用 IDEA 导入Maven项目
确保你安装了 Maven，导入项目时，选择已存在的项目，类型是 Maven 项目。<br/>
等待Maven下载完依赖，或者手动点击刷新，如果无法下载请检查 Maven 配置和切换网络，多次尝试。 <br/>
导入完成后，如果出现Java类里红色报错，多半是 Lombok 插件没有安装。


#### 3、 导入数据库   <br/>
新建数据库 `forest_blog`，导入数据库（即forest_blog.sql）。注意，数据库的编码和排序规则是utf-8和utf-8_general_ci  <br/>


#### 4、修改项目中的数据库连接信息   <br/>
修改 `db.properties` 文件，该文件很容易找到，在 src/main/resources 中<br/>
里面有 MySQL, 请确保已安装和启动 MySQL <br/>
注意修改数据库地址、表名、用户名和密码。<br/>
否则项目无法启动

 
#### 5、配置 tomcat 和 uploads 目录   <br/>
该项目是 SSM 项目，没有启动类，需要通过 tomcat 来运行。无论是 Eclipse 还是 IDEA 都很容易。<br/>
这里主要说一下上传目录映射问题：<br/>
该项目中，文件上传是传到本地，且和项目文件夹不在一起，就是说是源码和上传目录是分离的。 <br/>
比如我们把 uploads 目录放到 E盘根目录(建议直接把我的那个uploads文件夹拷贝到D盘根目录)，比如有一张图片路径是 `D:/uploads/2017/10/avatar.jpg`, 我们想在项目中以 `http://loclahost:8080/uploads/2017/10/avatar.jpg ` 方式访问，需要以下两步：<br/>

1、 修改 UploadFileController.java 中上传路径，需要修改 rootPath 为你指定的 uploads 目录，如 `String rootPath ="D:/uploads/";` <br/>
如果不修改，会出现无法上传失败；<br/>

2、为 tomcat 设置 uploads 映射。<br/>
-  IDEA 操作如下：<br/>
在 tomcat 配置中 Deployment 里面添加，如图
![image](uploads/tomcat.png)
![image](uploads/tomcat2.png)

- Eclispe 操作如下：<br/>
在 Server/server.xml 的 </Host> 前面添加
```
<Context path="/" docBase="项目路径，不需要改" debug="0" reloadable="true" />
<Context path="/uploads" docBase="D:/uploads/" debug="0" reloadable="true" />
```
- Linux 服务器环境部署

在 tomcat/conf/server.xml 的 Host 标签内添加如下代码 <br/>
```
<Context path="/" docBase="项目路径如 /www/server/tomcat/webapps/ForestBlog" debug="0" reloadable="true" />
<Context path="/uploads" docBase="/www/uploads" debug="0" reloadable="true" />
```

如果不修改，会出现uploads的图片无法加载，无法显示上传目录的图片

 
## 下载地址：
Github地址：https://github.com/saysky/ForestBlog
（如果可以帮忙点一次Star和Fork）
 
## 更新记录
第二次更新：2018年11月26日 <br/>
大更，重新规范了代码，完善了注释

首次完全提交：2017年10月10日  <br/>

## 请喝一杯奶茶
请我喝一杯奶茶吧！
插条广告：博主最近有点时间，长期接毕设定制，软件定制，Java问题解决 <br/>
具体查看[有偿服务](https://liuyanzhao.com/bulletin/my-service)  <br/>
博主付费商品：[https://liuyanzhao.com/shop.html](https://liuyanzhao.com/shop.html)

![image](uploads/donate.png)


## 关于开源和闭源
1. 项目来源
答：在开发这个项目的时候，我还是一介学生，也是初学者，做这个项目就是为了巩固一下知识。<br/>
我记得是2017年暑假边学SSM边做的，在这之前我已经用PHP(ThinkPHP)做过几个网站和博客。 <br/>
当时想2个月就完全做完，但是效率还是比较低，经常本末倒置，在一些细节地方卡很久。最终9月底10月初才完全做好，然后上传到GitHub。 <br/>
开源的初心很简单，就是分享给大家，不求回报的(因为当初年少的我也不知道这里面有make money的途径)。 <br/>
但是，后来某种原因，我放弃了这个项目，这个项目没有得到继续更新和维护。如果我坚持去做这件事，我相信会做的很不错，后来我也发现很多博主做了类似的事，我很欣赏他们。<br/>

2. 对开源的看法？为什么不想继续开源了？
答：我很欣赏 GitHub 上每一个愿意开源的作者，我曾经也是其中的一员，我也衷心希望你们能坚持下去，你们是正道的光。 <br/>
开源让路更宽，闭源让路越走越窄。<br/>
而我就是从开源走向闭源的一个人，当初(大致18年初)有很多朋友陆续告诉我闲鱼和淘宝上有很多出售我的开源项目，我也去找过那些卖家，有些卖家是下架了，有些把我拉黑了。 <br/>
我意识到有一大批人从 GitHub 上下载代码然后拿到闲鱼或淘宝去卖，通过信息差来赚钱，其获利不菲。我挺不屑的，我不会去做这些肮脏的事，但是我也不允许这样的事继续发生。<br/>
所以，我不想再开源新的项目了，即便是开源，也是部分开源，需要完整的请联系我，既然他们能拿我的项目赚钱，为什么我不能拿自己的项目赚钱呢？<br/>
也许我是一个心胸狭窄的人，我是否应该忽视那些人的行为，只做自己喜欢的事，只要你把开源这件事做好，持之以恒。会有越来越多的人加入你，一起帮你去做。来日方长，必将有所作为。 <br/>
不，我没有做到，我确实狭隘了。我放弃了开源，转而开始思考知识变现的问题，毕竟我很穷，我需要先考虑生存，先make money，如果等开源给我带来的红利或许需要3年5年，我等不及，我确实low了，我现在发现。<br/>

3. 未来
答：在未来3年内，我首先考虑的还是 make money。<br>
后面的话，我觉得我应该不缺钱了，至少基本够用了，我会去做一些东西，大家都需要的东西，完全开源，我觉得只有一个人无欲无求的时候，做的事情才是最高尚的。 <br/>
让我先堕落三年吧！三年后，2023年，我会重新回到开源的世界。<br/>

-- 2020年10月10日 更新
