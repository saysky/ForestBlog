<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/myTag.tld" prefix="lyz" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<rapid:override name="title">
    <title>搜索结果</title>
</rapid:override>

<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页</a>
            <i class="fa fa-angle-right"></i>
        搜索 ${articleSearchVoList.get(0).query} 找到 ${articleSearchVoList.get(0).page.totalCount} 个与之相关的文章
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>

<rapid:override name="left">
    <%--博客主体 start--%>
    <section id="content" class="site-content shadow">
        <%--博客主体-左侧正文 start--%>
        <section id="primary" class="content-area">

            <main id="main" class="site-main" role="main">
                <ul class="search-page">
                    <c:choose>
                        <c:when test="${articleSearchVoList.get(0).page.totalCount!=0}">
                            <c:forEach items="${articleSearchVoList}" var="a">
                                    <li class="search-inf">
                                        <fmt:formatDate value="${a.articleCustom.articlePostTime}" pattern="yyyy年MM月dd日"/>
                                    </li>
                                    <li class="entry-title">
                                        <a href="/article/${a.articleCustom.articleId}" rel="bookmark">
                                                ${a.articleCustom.articleTitle}
                                        </a>
                                    </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <br>
                            很遗憾，没有查询到带有 <font style="color: red;"> ${articleSearchVoList[0].query} </font> 的内容，换一个关键词再试试吧。
                            <br> <br>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </main>
            <c:if test="${articleSearchVoList[0].page.totalCount!=0}">
                <%--分页 start--%>
                <nav class="navigation pagination" role="navigation">
                    <div class="nav-links">
                        <c:choose>
                            <c:when test="${articleSearchVoList[0].page.totalPageCount <= 3 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="${articleSearchVoList[0].page.totalPageCount }"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="begin" value="${articleSearchVoList[0].page.pageNow-1 }"/>
                                <c:set var="end" value="${articleSearchVoList[0].page.pageNow + 2}"/>
                                <c:if test="${begin < 2 }">
                                    <c:set var="begin" value="1"/>
                                    <c:set var="end" value="3"/>
                                </c:if>
                                <c:if test="${end > articleSearchVoList[0].page.totalPageCount }">
                                    <c:set var="begin" value="${articleSearchVoList[0].page.totalPageCount-2 }"/>
                                    <c:set var="end" value="${articleSearchVoList[0].page.totalPageCount }"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <%--上一页 --%>
                        <c:choose>
                            <c:when test="${articleSearchVoList[0].page.pageNow eq 1 }">
                                <%--当前页为第一页，隐藏上一页按钮--%>
                            </c:when>
                            <c:otherwise>
                                <a class="page-numbers"
                                   href="/p/${articleSearchVoList[0].page.pageNow-1}/search?query=${articleSearchVoList[0].query}">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <%--显示第一页的页码--%>
                        <c:if test="${begin >= 2 }">
                            <a class="page-numbers"
                               href="/p/1/search?query=${articleSearchVoList[0].query}">1</a>
                        </c:if>
                        <%--显示点点点--%>
                        <c:if test="${begin  > 2 }">
                            <span class="page-numbers dots">…</span>
                        </c:if>
                        <%--打印 页码--%>
                        <c:forEach begin="${begin }" end="${end }" var="i">
                            <c:choose>
                                <c:when test="${i eq articleSearchVoList[0].page.pageNow }">
                                    <a class="page-numbers current">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-numbers"
                                       href="/p/${i}/search?query=${articleSearchVoList[0].query}">${i }</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <%-- 显示点点点 --%>
                        <c:if test="${end < articleSearchVoList[0].page.totalPageCount-1 }">
                            <span class="page-numbers dots">…</span>
                        </c:if>
                        <%-- 显示最后一页的数字 --%>
                        <c:if test="${end < articleSearchVoList[0].page.totalPageCount }">
                            <a href="/p/${articleSearchVoList[0].page.totalPageCount}/search?query=${articleSearchVoList[0].query}">
                                    ${articleSearchVoList[0].page.totalPageCount}
                            </a>
                        </c:if>
                        <%--下一页 --%>
                        <c:choose>
                            <c:when test="${articleSearchVoList[0].page.pageNow eq articleSearchVoList[0].page.totalPageCount }">
                                <%--到了尾页隐藏，下一页按钮--%>
                            </c:when>
                            <c:otherwise>
                                <%--如果没查询到结果，隐藏最后一个>--%>
                                <c:if test="${articleSearchVoList[0].page.totalPageCount>0}">
                                    <a class="page-numbers"
                                       href="/p/${articleSearchVoList[0].page.pageNow+1}/search?query=${articleSearchVoList[0].query}">
                                        <span class="fa fa-angle-right"></span>
                                    </a>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </nav>
                <%--分页 end--%>
            </c:if>

        </section>
    </section>
</rapid:override>



<%@ include file="../Public/framework.jsp" %>