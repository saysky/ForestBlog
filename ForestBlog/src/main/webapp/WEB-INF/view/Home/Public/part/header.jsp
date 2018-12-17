<%--
    博客顶部部分
    包括：顶部菜单，主要菜单(包括搜索按钮)，面包屑
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%--导航 start--%>
<header id="masthead" class="site-header" >

    <%--主要菜单 satrt--%>
    <div id="menu-box"  style="position: fixed;background: rgba(255, 255, 255, 0.7);">
        <div id="top-menu">
                <span class="nav-search">
                    <i class="fa fa-search"></i>
                </span>
            <div class="logo-site"><h1 class="site-title">
                <a href="/admin" title="${options.optionSiteTitle}">
               		<img src="https://huanfan-1252958858.cos.ap-shanghai.myqcloud.com/2018/11/hfanss-logo001.png" title="${options.optionSiteTitle}" rel="home">
            	</a>
            </h1>
<%--                 <p class="site-description">${options.optionSiteDescrption}</p> --%>
            </div><!-- .logo-site -->
            <div id="site-nav-wrap">
                <div id="sidr-close">
                    <a href="#sidr-close" class="toggle-sidr-close">×</a>
                </div>
                <nav id="site-nav" class="main-nav" style="font-size:14px;font-weight: bold;">
                    <a href="#sidr-main" id="navigation-toggle" class="bars">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="menu-pcmenu-container">
                        <ul id="menu-pcmenu" class="down-menu nav-menu sf-js-enabled sf-arrows">

                            <li>
                                <a href="/">
<!--                                     <i class="fa-home fa"></i> -->
                                    <span class="font-text">首页</span>
                                </a>
                            </li>

                            <c:forEach items="${categoryList}" var="category">
                                <c:if test="${category.categoryPid==0}">
                                    <li>
                                        <a href="/category/${category.categoryId}">
                                            <i class="${category.categoryIcon}"></i>
                                            <span class="font-text">${category.categoryName}&nbsp;</span>
                                        </a>
                                        <ul class="sub-menu">
                                            <c:forEach items="${categoryList}" var="cate">
                                                <c:if test="${cate.categoryPid==category.categoryId}">
                                                    <li class="zidingyi003">
                                                        <a href="/category/${cate.categoryId}" target="_blank">${cate.categoryName}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <%--主要菜单其余部分--%>
                            <c:forEach items="${menuCustomList}" var="m">
                                <c:if test="${m.menuLevel==2}">
                                    <li>
                                        <a href="${m.menuUrl}">
                                            <i class="${m.menuIcon}"></i>
                                            <span class="font-text">${m.menuName}&nbsp;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="clear"></div>
        </div><!-- #top-menu -->
    </div><!-- #menu-box -->
    <%--主要菜单 satrt--%>

</header><!-- #masthead -->
<%--导航 end start--%>

<%--搜索框 start--%>
<div id="search-main">
    <div class="searchbar">
        <form method="get" id="searchform" action="/search">
                <span>
                    <input type="text" value="" name="query" id="s" placeholder="输入搜索内容"required="">
                    <button type="submit" id="searchsubmit">搜索</button>
                </span>
        </form>
    </div>
    <div class="clear"></div>
</div>
<%--搜索框 end--%>

<rapid:block name="breadcrumb"></rapid:block>