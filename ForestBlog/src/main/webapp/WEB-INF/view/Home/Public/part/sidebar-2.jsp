<%--
    一般用于首页侧边栏：
    包括 关于本站，网站概况，热评文章，所有标签，随机文章 等小工具

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--博客主体-右侧侧边栏 start--%>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

    <%--网站概况 start--%>
    <aside id="php_text-22" class="widget php_text">
        <h3 class="widget-title">
            <i class="fa fa-align-right"></i>网站概况
        </h3>
        <div class="textwidget widget-text">
            <ul class="site-profile">
                <li><i class="fa fa-file-o"></i> 文章总数：${siteBasicStatistics[0]} 篇</li>
                <li><i class="fa fa-commenting-o"></i> 留言数量：${siteBasicStatistics[1]} 条</li>
                <li><i class="fa fa-folder-o"></i> 分类数量：${siteBasicStatistics[2]} 个</li>
                <li><i class="fa fa-tags"></i> 标签总数：${siteBasicStatistics[3]} 个</li>
                <li><i class="fa fa-link"></i> 链接数量：${siteBasicStatistics[4]} 个</li>
                <li><i class="fa fa-eye"></i> 总浏览量：<span id="busuanzi_value_site_pv"><i class="fa fa-spinner fa-spin"></i></span></li>
<%--                 <li><i class="fa fa-eye"></i> 文章点击总量：${siteBasicStatistics[5]} 次</li> --%>
                <li><i class="fa fa-pencil-square-o"></i> 最后更新：
                    <span style="color:#5CACEE">
                                        <fmt:formatDate value="${lastUpdateArticle.articleUpdateTime}" pattern="yyyy年MM月dd日"/>

                                   </span>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--网站概况 end--%>

    <%--热评文章 start--%>
    <aside class="widget hot_comment">
        <h3 class="widget-title">
            <i class="fa fa-align-right"></i>热评文章
        </h3>
        <div id="hot_comment_widget">
            <ul>
                <c:forEach items="${mostCommentArticleList}" var="m" varStatus="size">
                    <li>
                       <c:if test="${size.count == 1}">
                        		<span class="li-icon li-icon-1">1</span>
                        	</c:if>
                        	<c:if test="${size.count == 2}">
                        		<span class="li-icon li-icon-2">2</span>
                        	</c:if>
                        	<c:if test="${size.count == 3}">
                        		<span class="li-icon li-icon-3">3</span>
                        	</c:if>
                        	<c:if test="${size.count != 3 and size.count != 2 and size.count != 1}">
                        		<span class="li-icon li-icon-${size.count}">${size.count}</span>
                        	</c:if>
                            <a href="/article/${m.articleId}" rel="bookmark" title=" (${m.articleCommentCount}条评论)">
                                    ${m.articleTitle}
                            </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--热评文章 end--%>

    <%--所有标签 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-align-right"></i>所有标签
        </h3>
        <div class="tagcloud">
            <c:forEach items="${tagList}" var="tag">
                <c:if test="${tag.tagId%5==0}">
	                <a href="/tag/${tag.tagId}"
	                   class="tag-link-129 tag-link-position-1" title="${tag.articleCount}个话题"
	                   style="font-size: 14px;color:#BF3EFF">
	                        ${tag.tagName}
	                </a>
                </c:if>
                <c:if test="${tag.tagId%5==1}">
	                <a href="/tag/${tag.tagId}"
	                   class="tag-link-129 tag-link-position-1" title="${tag.articleCount}个话题"
	                   style="font-size: 14px;color:#B0E2FF">
	                        ${tag.tagName}
	                </a>
                </c:if>
                <c:if test="${tag.tagId%5==2}">
	                <a href="/tag/${tag.tagId}"
	                   class="tag-link-129 tag-link-position-1" title="${tag.articleCount}个话题"
	                   style="font-size: 14px;color:#F08080">
	                        ${tag.tagName}
	                </a>
                </c:if>
                <c:if test="${tag.tagId%5==3}">
	                <a href="/tag/${tag.tagId}"
	                   class="tag-link-129 tag-link-position-1" title="${tag.articleCount}个话题"
	                   style="font-size: 14px;color:#CD9B1D">
	                        ${tag.tagName}
	                </a>
                </c:if>
                <c:if test="${tag.tagId%5==4}">
	                <a href="/tag/${tag.tagId}"
	                   class="tag-link-129 tag-link-position-1" title="${tag.articleCount}个话题"
	                   style="font-size: 14px;color:#00C5CD">
	                        ${tag.tagName}
	                </a>
                </c:if>
                
            </c:forEach>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--所有标签 end--%>


    <%--随机文章 start--%>
    <aside id="random_post-7" class="widget random_post wow fadeInUp" data-wow-delay="0.3s">
        <h3 class="widget-title">
            <i class="fa fa-align-right"></i>随机文章
        </h3>
        <div id="random_post_widget">
            <ul>
                <c:forEach items="${randomArticleList}" var="r">
                    <li>
                        <a href="/article/${r.articleId}" rel="bookmark">
                                ${r.articleTitle}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--近期文章 end--%>

    <%--最新评论 start--%>
    <aside id="recent_comments-2" class="widget recent_comments wow fadeInUp" data-wow-delay="0.3s"><h3
            class="widget-title"><i class="fa fa-align-right"></i>近期评论</h3>
        <div id="message" class="message-widget">
            <ul>
                <c:forEach items="${recentCommentList}" var="r">
                <li style="border: none;">
                    <a href="/article/${r.articleCustom.articleId}/#anchor-comment-${r.commentCustom.commentId}" title="${r.articleCustom.articleTitle}" rel="external nofollow">
                        <img alt=""src="${r.commentCustom.commentAuthorAvatar}" class="avatar avatar-64 photo" height="64" width="64">
                        <span class="comment_author">
                            <strong>${r.commentCustom.commentAuthorName}</strong>
                        </span>
                            ${r.commentCustom.commentContent}
                    </a>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--最新评论 end--%>

</div>



<%--博客主体-右侧侧边栏 end--%>
