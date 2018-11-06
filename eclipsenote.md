### Eclipse

[Eclipse Validating比较慢](https://blog.csdn.net/happyboywlg/article/details/79514910)

[@Eclipse导入项目后所有@Override处代码出错](https://blog.csdn.net/u011351725/article/details/42550593)

[Java compiler level does not match](https://blog.csdn.net/u012506661/article/details/52931628)


### Spring

[sping mvc访问.js,.css,.img等静态文件](http://www.cnblogs.com/wenhulu/p/5548867.html)


### Eclipse 部署环境配置

#### Eclispe Deployment Assembly

```
<?xml version="1.0" encoding="UTF-8"?><project-modules id="moduleCoreId" project-version="1.5.0">
    <wb-module deploy-name="forestblog">
        <wb-resource deploy-path="/" source-path="/target/m2e-wtp/web-resources"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/java"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/resources"/>
        <wb-resource deploy-path="/" source-path="/src/main/webapp"/>
        <property name="context-root" value="forestblog"/>
        <property name="java-output-path" value="/forestblog/target/classes"/>
    </wb-module>
</project-modules>
```

#### Tomcat 启动时部署路径

```
十一月 06, 2018 4:32:34 下午 org.apache.catalina.startup.VersionLoggerListener log
信息: Command line argument: -Dwtp.deploy=D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps

D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps\forestblog>tree
卷 DataDisk 的文件夹 PATH 列表
卷序列号为 D8B3-0120
D:.
├─META-INF
│  └─maven
│      └─com.scd
│          └─forestblog
├─resource
│  └─assets
│      ├─css
│      ├─img
│      │  ├─shang
│      │  └─thumbnail
│      │      └─random
│      ├─js
│      └─plugin
│          ├─404
│          ├─font-awesome
│          │  ├─css
│          │  ├─fonts
│          │  ├─less
│          │  └─scss
│          ├─layui
│          │  ├─css
│          │  │  └─modules
│          │  │      ├─laydate
│          │  │      │  └─default
│          │  │      └─layer
│          │  │          └─default
│          │  ├─font
│          │  ├─images
│          │  │  └─face
│          │  ├─lay
│          │  │  └─modules
│          │  ├─mobile
│          │  │  └─need
│          │  └─skin
│          │      └─default
│          └─login
└─WEB-INF
    ├─classes
    │  ├─com
    │  │  └─liuyanzhao
    │  │      └─blog
    │  │          ├─controller
    │  │          │  ├─Admin
    │  │          │  ├─Common
    │  │          │  └─Home
    │  │          ├─entity
    │  │          │  └─custom
    │  │          ├─Interceptor
    │  │          ├─mapper
    │  │          │  └─custom
    │  │          ├─service
    │  │          │  └─impl
    │  │          └─util
    │  │              └─others
    │  ├─mybatis
    │  └─spring
    ├─lib
    └─view
        ├─Admin
        │  ├─Article
        │  ├─Category
        │  ├─Comment
        │  ├─Link
        │  ├─Menu
        │  ├─Notice
        │  ├─Options
        │  ├─Page
        │  ├─Public
        │  ├─Tag
        │  └─User
        └─Home
            ├─Error
            ├─Page
            └─Public
                └─part

```

#### 修改tomcat的server.xml

```
<Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" pattern="%h %l %u %t &quot;%r&quot; %s %b" prefix="localhost_access_log." suffix=".txt"/>
		 <Context docBase="D:\SoftWare\apache-tomcat-7.0.69\resource" path="/resource" reloadable="true"/>
		 <Context docBase="D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps\forestblog\resource\assets\css" path="/css" reloadable="true"/>
		 <Context docBase="D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps\forestblog\resource\assets\js" path="/js" reloadable="true"/>
		 <Context docBase="D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps\forestblog\resource\assets\img" path="/img" reloadable="true"/>
		 <Context docBase="D:\SoftWare\apache-tomcat-7.0.69\wtpwebapps\forestblog\resource\assets\plugin" path="/plugin" reloadable="true"/>
      <Context docBase="forestblog" path="/" reloadable="true" source="org.eclipse.jst.jee.server:forestblog"/></Host>
```

>部署方式有点麻烦，但是可以正常跑动项目了，hahaha
