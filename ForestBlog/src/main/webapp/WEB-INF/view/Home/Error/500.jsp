<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>抱歉，无法找到该页面！</title>
    <link rel="stylesheet" href="/css/style.css">

    <link rel="stylesheet" type="text/css" href="/plugin/404/main.css">
    <style>

    </style>
</head>
<body>
<div id="page" class="site" style="transform: none;" >


    <div id="content" class="site-content" style="transform: none;" >

        <%--博客主体-左侧正文 start--%>
        <div id="primary" class="content-area">
            <main id="main" class="site-main" role="main">

                <header id="header_404">
                    <h1><span class="icon">!</span>500<span class="sub">page not found</span></h1>
                </header>
                <div id="content_404">
                    <h2>系统内容错误！</h2>
                    <div class="utilities">
                        <center>
                        <form name="formsearch" action="/search" id="formkeyword">
                            <div class="input-container">
                                <input type="text" class="left" name="query" size="24" value="在这里搜索..." onfocus="if(this.value=='在这里搜索...'){this.value='';}" onblur="if(this.value==''){this.value='在这里搜索...';}" id="inputString" onkeyup="lookup(this.value);" onblur="fill();" placeholder="搜索..." />
                                <button id="search"></button>
                            </div>
                        </form>
                        <div id="btn">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button" href="/">返回首页</a>
                            <a class="button" href="http://wpa.qq.com/msgrd?v=3&uin=847064370&site=qq&menu=yes">联系站长</a>
                            <div class="clear"></div>
                        </div>
                        </center>
                    </div>
                </div>
            </main><!-- .site-main -->

        </div>
      <%--  博客主体-左侧正文 end--%>


        <div class="clear"></div>
    </div st><!-- .site-content -->

</div>
</body>
</html>
