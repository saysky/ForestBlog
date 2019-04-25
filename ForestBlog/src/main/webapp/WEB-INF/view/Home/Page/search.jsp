<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        搜索 ${param.keywords} 找到 ${pageInfo.total} 个与之相关的文章
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>

<rapid:override name="left">
    <%--博客主体 start--%>
    <section id="content" class="site-content shadow">
        <%--博客主体-左侧正文 start--%>
        <section id="primary" class="content-area">
            <main id="main" class="site-main">
                <c:choose>
                    <c:when test="${pageInfo.list.size() != 0}">
                        <%--文章列表-start--%>
                        <c:forEach items="${pageInfo.list}" var="a">

                            <article class="post">

                                <figure class="thumbnail">
                                    <a href="/article/${a.articleId}">
                                        <img width="280" height="210"
                                             src="/img/thumbnail/random/img_${a.articleId%15}.jpg"
                                             class="attachment-content size-content wp-post-image"
                                             alt="${a.articleTitle}">
                                    </a>
                                    <span class="cat">
                                              <a href="/category/${a.categoryList[0].categoryId}">${a.categoryList[0].categoryName}</a>
                                        </span>
                                </figure>

                                <header class="entry-header">
                                    <h2 class="entry-title">
                                        <a href="/article/${a.articleId}" rel="bookmark">
                                                ${a.articleTitle}
                                        </a>
                                    </h2>
                                </header><!-- .entry-header -->

                                <div class="entry-content">
                                    <div class="archive-content">
                                            ${a.articleSummary}...
                                    </div>
                                    <span class="title-l"></span>
                                    <span class="new-icon">
                                                    <c:choose>
                                                        <c:when test="${a.articleStatus == 2}">
                                                            <i class="fa fa-bookmark-o"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <jsp:useBean id="nowDate"
                                                                         class="java.util.Date"/> <%--当前时间--%>
                                                            <c:set var="interval"
                                                                   value="${nowDate.time - a.articleCreateTime.time}"/><%--时间差毫秒数--%>
                                                            <fmt:formatNumber value="${interval/1000/60/60/24}"
                                                                              pattern="#0"
                                                                              var="days"/><%--取天数整数--%>
                                                            <c:if test="${days <= 7}">NEW</c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                    <span class="entry-meta">
                                                    <span class="date">
                                                         <fmt:formatDate value="${a.articleCreateTime}"
                                                                         pattern="yyyy年MM月dd日"/>
                                                        &nbsp;&nbsp;
                                                    </span>
                                                    <span class="views">
                                                        <i class="fa fa-eye"></i>
                                                            ${a.articleViewCount} views
                                                    </span>
                                                    <span class="comment">&nbsp;&nbsp;
                                                        <a href="/article/${a.articleId}#comments"
                                                           rel="external nofollow">
                                                          <i class="fa fa-comment-o"></i>
                                                            <c:choose>
                                                                <c:when test="${a.articleCommentCount==0}">
                                                                    发表评论
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${a.articleCommentCount}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                    </span>
                                                </span>
                                    <div class="clear"></div>
                                </div><!-- .entry-content -->
                                <span class="entry-more">
                                            <a href="/article/${a.articleId}"
                                               rel="bookmark">阅读全文
                                            </a>
                                        </span>
                            </article>
                        </c:forEach>
                        <%--文章列表-end--%>
                    </c:when>
                </c:choose>
            </main>
            <%@ include file="../Public/part/paging.jsp" %>

        </section>
    </section>
</rapid:override>



<%@ include file="../Public/framework.jsp" %>