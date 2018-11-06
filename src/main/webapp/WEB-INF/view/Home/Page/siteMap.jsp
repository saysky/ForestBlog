<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="description">
    <meta name="description" content="站点地图"/>
</rapid:override>

<rapid:override name="keywords">
    <meta name="keywords" content="站点地图"/>
</rapid:override>

<rapid:override name="title">
    <title>站点地图</title>
</rapid:override>

<rapid:override name="header-style">
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
</rapid:override>

<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页
        </a>
        <i class="fa fa-angle-right"></i>
        站点地图
        <i class="fa fa-angle-right"></i>
        正文
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>

<rapid:override name="left">
    <%--博客主体-左侧正文 start--%>
    <section id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
            <div class="layui-collapse">
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">文章列表</h2>
                    <div class="layui-colla-content layui-show">
                        <ul>
                            <c:forEach items="${articleList}" var="a">
                                <li style="padding: 5px">
                                    <a href="/article/${a.articleCustom.articleId}" title="ajax实现form表单提交" target="_blank">${a.articleCustom.articleTitle}</a>
                                </li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">分类目录</h2>
                    <div class="layui-colla-content layui-show">
                        <ul>
                            <c:forEach items="${categoryList}" var="c">
                                <c:if test="${c.categoryPid==0}">
                                    <li class="cat-item" style="padding: 5px">
                                        <a href="/category/${c.categoryId}">丨- ${c.categoryName}</a>
                                    </li>
                                    <ul class="children">
                                        <c:forEach items="${categoryList}" var="c2">
                                            <c:if test="${c2.categoryPid==c.categoryId}">
                                                <li class="cat-item" style="padding: 5px 30px;">
                                                    <a href="/category/${c2.categoryId}" target="_blank">${c2.categoryName}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">标签库</h2>
                    <div class="layui-colla-content layui-show">
                        <c:forEach items="${tagCustomList}" var="t">
                            <a href="/tag/${t.tagId}" style="font-size: ${t.articleCount/4+14}px" title="${t.articleCount}个话题" target="_blank">${t.tagName}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </main>
    </section>
</rapid:override>

<rapid:override name="footer-script">
    <script>
        //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
        layui.use('element', function(){
            var element = layui.element;

            //…
        });
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp" %>



