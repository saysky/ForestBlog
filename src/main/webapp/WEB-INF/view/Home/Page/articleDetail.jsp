<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/myTag.tld" prefix="lyz" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<%--<rapid:override name="description">--%>
<%--</rapid:override>--%>

<%--<rapid:override name="keywords">--%>
<%--</rapid:override>--%>

<rapid:override name="title">
    <title>${articleDetailVo.articleCustom.articleTitle}</title>
</rapid:override>

<rapid:override name="header-style">
    <rapid:override name="header-style">
        <link rel="stylesheet" href="/css/highlight.css">
        <style>
            .entry-title {
                background: #f8f8f8;
            }
        </style>
    </rapid:override>
</rapid:override>

<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页
        </a>
        <c:choose>
            <c:when test="${a.categoryCustomList.size()!=0}">
                <c:forEach items="${articleDetailVo.categoryCustomList}" var="c">
                    <i class="fa fa-angle-right"></i>
                    <a href="/category/${c.categoryId}">
                            ${c.categoryName}
                    </a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <a>未分类</a>
            </c:otherwise>
        </c:choose>
        <i class="fa fa-angle-right"></i>
        正文
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>


<rapid:override name="left">
    <%--博客主体-左侧文章正文 start--%>
    <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
            <article class="post">
                <header class="entry-header">
                    <h1 class="entry-title">
                            ${articleDetailVo.articleCustom.articleTitle}
                    </h1>
                </header><!-- .entry-header -->
                <div class="entry-content">
                    <div class="single-content">
                            ${articleDetailVo.articleCustom.articleContent}
                    </div>
                    <div class="s-weixin">
                        <ul class="weimg1">
                            <li><strong>微信</strong></li>
                            <li>赶快加我聊天吧</li>
                            <li><img src="/img/weixin.jpg"></li>
                        </ul>
                        <ul class="weimg2">
                            <li><strong>博客交流群</strong></li>
                            <li>海纳百川，大家来水</li>
                            <li><img src="/img/qqGroup.jpg" alt="weinxin"></li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <div class="clear"></div>
                    <div id="social">
                        <div class="social-main">
                                    <span class="like">
                                        <a href="javascript:;" data-action="ding" data-id="1" title="点赞"
                                           class="favorite" onclick="increaseLikeCount()">
                                            <i class="fa fa-thumbs-up"></i>赞
                                            <i class="count"
                                               id="count-${articleDetailVo.articleCustom.articleId}">${articleDetailVo.articleCustom.articleLikeCount}</i>
                                        </a>
                                    </span>
                            <div class="shang-p">
                                <div class="shang-empty">
                                    <span></span>
                                </div>
                                <span class="shang-s">
                                              <a onclick="PaymentUtils.show();" style="cursor:pointer">赏</a>
                                        </span>
                            </div>
                            <div class="share-sd">
                                        <span class="share-s" style="margin-top: 25px!important;">
                                            <a href="javascript:void(0)" id="share-s" title="分享">
                                                <i class="fa fa-share-alt"></i>分享
                                            </a>
                                        </span>
                                <div id="share">
                                    <ul class="bdsharebuttonbox bdshare-button-style1-16" data-bd-bind="1503997585792">
                                        <li><a title="更多" class="bds_more fa fa-plus-square" data-cmd="more"
                                               onclick="return false;" href="#"></a></li>
                                        <li><a title="分享到QQ空间" class="fa fa-qq" data-cmd="qzone" onclick="return false;"
                                               href="#"></a></li>
                                        <li><a title="分享到新浪微博" class="fa fa-weibo" data-cmd="tsina"
                                               onclick="return false;" href="#"></a></li>
                                        <li><a title="分享到腾讯微博" class="fa fa-pinterest-square" data-cmd="tqq"
                                               onclick="return false;" href="#"></a></li>
                                        <li><a title="分享到人人网" class="fa fa-renren" data-cmd="renren"
                                               onclick="return false;" href="#"></a></li>
                                        <li><a title="分享到微信" class="fa fa-weixin" data-cmd="weixin"
                                               onclick="return false;" href="#"></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>

                    <footer class="single-footer">
                        <ul class="single-meta">
                            <c:if test="${sessionScope.user!=null}">
                            <li class="edit-link">
                                <a target="_blank" class="post-edit-link"
                                   href="/admin/article/edit/${articleDetailVo.articleCustom.articleId}">编辑</a>
                            </li>
                            </c:if>
                            <li class="comment">
                                <a href="/article/${articleDetailVo.articleCustom.articleId}#comments"
                                   rel="external nofollow">
                                    <i class="fa fa-comment-o"></i>
                                    <i class="comment-count">${articleDetailVo.commentCustomList.size()}</i>
                                </a>
                            </li>
                            <li class="views">
                                <i class="fa fa-eye"></i> <span
                                    class="articleViewCount">${articleDetailVo.articleCustom.articleViewCount}</span>
                                views
                            </li>
                            <li class="r-hide">
                                <a href="javascript:pr()" title="侧边栏">
                                    <i class="fa fa-caret-left"></i>
                                    <i class="fa fa-caret-right"></i>
                                </a>
                            </li>
                        </ul>
                        <ul id="fontsize">
                            <li>A+</li>
                        </ul>
                        <div class="single-cat-tag">
                            <div class="single-cat">所属分类：
                                <c:forEach items="${articleDetailVo.categoryCustomList}" var="c">
                                    <a href="/category/${c.categoryId}">
                                            ${c.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </footer><!-- .entry-footer -->


                    <div class="clear"></div>
                </div><!-- .entry-content -->
            </article><!-- #post -->

                <%--所属标签 start--%>
            <div class="single-tag">
                <ul class="" data-wow-delay="0.3s">
                    <c:forEach items="${articleDetailVo.tagCustomList}" var="t">
                        <li>
                            <a href="/tag/${t.tagId}" rel="tag"
                               style="background:#666666">
                                    ${t.tagName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
                <%--所属标签 end--%>


                <%--版权声明 start--%>
            <div class="authorbio wow fadeInUp" >
                <img alt="${articleDetailVo.userCustom.userNickname}" src="${articleDetailVo.userCustom.userAvatar}"
                     class="avatar avatar-64 photo" height="64" width="64">
                <ul class="postinfo">
                    <li></li>
                    <li><strong>版权声明：</strong>本站原创文章，于<fmt:formatDate
                            value="${articleDetailVo.articleCustom.articlePostTime}"
                            pattern="yyyy-MM-dd"/>，由
                            <strong>
                                    ${articleDetailVo.userCustom.userNickname}
                            </strong>
                        发表。
                    </li>
                    <li class="reprinted"><strong>转载请注明：</strong>
                        <a href="/article/${articleDetailVo.articleCustom.articleId}"
                           rel="bookmark"
                           title="本文固定链接 /article/${articleDetailVo.articleCustom.articleId}">
                                ${articleDetailVo.articleCustom.articleTitle} | ${options.optionSiteTitle}</a>
                    </li>
                </ul>
                <div class="clear"></div>
            </div>
                <%--版权声明 end--%>


                <%--相关文章 start--%>
            <div id="single-widget">
                <div class="wow fadeInUp" data-wow-delay="0.3s">
                    <aside id="related_post-2" class="widget">
                        <h3 class="widget-title">
                            <span class="s-icon"></span>相关文章
                        </h3>
                        <div id="related_post_widget">
                            <ul>
                                <c:forEach items="${similarArticleList}" var="s">
                                    <li>
                                        <a href="/article/${s.articleId}">${s.articleTitle}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </aside>
                        <%--猜你喜欢 start--%>
                    <aside id="hot_post-8" class="widget hot_post">
                        <h3 class="widget-title"><span class="s-icon"></span>猜你喜欢</h3>
                        <div id="hot_post_widget">
                            <ul>
                                <c:forEach items="${mostViewArticleList}" var="m">
                                    <li>
                                        <a href="/article/${m.articleId}">
                                                ${m.articleTitle}
                                        </a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="clear"></div>
                    </aside>
                        <%--猜你喜欢 end--%>
                </div>
                <div class="clear"></div>
            </div>
                <%--相关文章 end--%>

                <%--上一篇下一篇 start--%>
            <nav class="nav-single">
                <c:choose>
                    <c:when test="${preArticle!=null}">
                        <a href="/article/${preArticle.articleId}" rel="next">
                            <span class="meta-nav">
                                <span class="post-nav">上一篇
                                 <i class="fa fa-angle-left"></i>
                                </span>
                                <br>${preArticle.articleTitle}
                            </span>
                        </a>
                    </c:when>
                    <c:otherwise>
                              <span class="meta-nav">
                                    <span class="post-nav">
                                        没有了<br>
                                    </span>已是第一篇文章
                                </span>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${afterArticle!=null}">
                        <a href="/article/${afterArticle.articleId}" rel="next">
                            <span class="meta-nav">
                                <span class="post-nav">下一篇
                                 <i class="fa fa-angle-right"></i>
                                </span>
                                <br>${afterArticle.articleTitle}
                            </span>
                        </a>
                    </c:when>
                    <c:otherwise>
                            <span class="meta-nav">
                                <span class="post-nav">
                                    没有了<br>
                                </span>已是最后文章
                             </span>
                    </c:otherwise>
                </c:choose>

                <div class="clear"></div>
            </nav>
                <%--上一篇下一篇 end--%>

                <%--评论区域 start--%>
            <div class="scroll-comments"></div>
            <div id="comments" class="comments-area">
                <div id="respond" class="comment-respond">
                    <h3 id="reply-title" class="comment-reply-title"><span id="reply-title-word">发表评论</span>
                        <a rel="nofollow" id="cancel-comment-reply-link"
                           href="/article/${articleDetailVo.articleCustom.articleId}#respond"
                           style="">取消回复</a>
                    </h3>
                    <form id="comment_form" method="post">
                        <c:if test="${sessionScope.user!=null}">
                            <div class="user_avatar">
                                <img alt="言曌"
                                     src="${sessionScope.user.userAvatar}"
                                     class="avatar avatar-64 photo" height="64" width="64">
                                登录者：${sessionScope.user.userNickname}
                                <br> <a href="javascript:void(0)" onclick="logout()">登出</a>
                                <input type="hidden" name="commentRole" value="1">
                                <input type="hidden" name="commentAuthorName"
                                       value="${sessionScope.user.getUserNickname()}">
                                <input type="hidden" name="commentAuthorEmail"
                                       value="${sessionScope.user.getUserEmail()}">
                                <input type="hidden" name="commentAuthorUrl" value="${sessionScope.user.getUserUrl()}">
                            </div>
                        </c:if>
                        <p class="comment-form-comment">
                            <textarea id="comment" name="commentContent" rows="4" tabindex="1" required></textarea>
                        </p>
                        <div id="comment-author-info">
                            <input type="hidden" name="commentPid" value="0">
                            <input type="hidden" name="commentPname" value="">
                            <c:if test="${sessionScope.user==null}">
                                <input type="hidden" name="commentRole" value="0">
                                <p class="comment-form-author">
                                    <label for="author_name">
                                        昵称<span class="required">*</span>
                                    </label>
                                    <input type="text" name="commentAuthorName" id="author_name" class="" value=""
                                           tabindex="2" required>
                                </p>
                                <p class="comment-form-email">
                                    <label for="author_email">
                                        邮箱<span class="required">*</span>
                                    </label>
                                    <input type="email" name="commentAuthorEmail" id="author_email" class="" value=""
                                           tabindex="3" required>
                                </p>
                                <p class="comment-form-url">
                                    <label for="author_url">网址</label>
                                    <input type="url" name="commentAuthorUrl" id="author_url" class="" value=""
                                           tabindex="4">
                                </p>
                            </c:if>
                        </div>
                        <div class="clear"></div>
                        <p class="form-submit">
                            <input id="submit" name="submit" type="submit" tabindex="5" value="提交评论">
                            <input type="hidden" name="commentArticleId"
                                   value="${articleDetailVo.articleCustom.articleId}" id="article_id">
                            <input type="hidden" name="commentPid" id="comment_pid" value="0">
                        </p>
                    </form>
                </div>

                <ol class="comment-list">
                    <c:set var="floor" value="0"/>
                    <c:forEach items="${articleDetailVo.commentCustomList}" var="c">
                        <c:if test="${c.commentPid==0}">
                            <c:set var="floor" value="${floor+1}"/>
                            <li class="comments-anchor">
                                <ul id="anchor-comment-${c.commentId}"></ul>
                            </li>
                            <li class="comment">
                                <div id="div-comment-${c.commentId}" class="comment-body">
                                    <div class="comment-author vcard">
                                        <img class="avatar" src="${c.commentAuthorAvatar}" alt="avatar"
                                             style="display: block;">
                                        <strong>${c.commentAuthorName} </strong>
                                        <c:if test="${c.commentRole==1}">
                                            <i class="fa fa-black-tie" style="color: #c40000;"></i>
                                            <span class=""
                                                  style="margin-top: 2px!important;color: #c40000;font-size: 13px;;"><b>博主</b></span>
                                        </c:if>
                                        <span class="comment-meta commentmetadata">
                                            <span class="ua-info" style="display: inline;">
                                                <br>
                                                <span class="comment-aux">
                                                    <span class="reply">
                                                        <a rel="nofollow" class="comment-reply-link" href="#respond"
                                                           onclick="replyComment()">回复
                                                        </a>
                                                    </span>
                                                    <fmt:formatDate value="${c.commentCreateTime}"
                                                                    pattern="yyyy年MM月dd日 HH:mm:ss"/>&nbsp;
                                                    <c:if test="${sessionScope.user!=null}">
                                                        <a href="javascript:void(0)"
                                                           onclick="deleteComment(${c.commentId})">删除</a>
                                                        <a class="comment-edit-link"
                                                           href="/admin/comment/edit/${c.commentId}"
                                                           target="_blank">编辑</a>
                                                    </c:if>
                                                    <span class="floor"> &nbsp;${floor}楼 </span>
                                                </span>
                                            </span>
                                        </span>
                                        <p>
                                            <c:if test="${c.commentPid!=0}">
                                                <span class="at">@ ${c.commentPname}</span>
                                            </c:if>
                                                ${c.commentContent}
                                        </p>
                                    </div>
                                </div>
                                <ul class="children">
                                    <c:set var="floor2" value="0"/>
                                    <c:forEach items="${articleDetailVo.commentCustomList}" var="c2">
                                        <c:if test="${c.commentId==c2.commentPid}">
                                            <c:set var="floor2" value="${floor2+1}"/>
                                            <li class="comments-anchor">
                                                <ul id="anchor-comment-${c2.commentId}"></ul>
                                            </li>
                                            <li class="comment">
                                                <div id="div-comment-${c.commentId}" class="comment-body">
                                                    <div class="comment-author vcard">
                                                        <img class="avatar" src="${c2.commentAuthorAvatar}" alt="avatar"
                                                             style="display: block;">
                                                        <strong>${c2.commentAuthorName} </strong>
                                                        <c:if test="${c2.commentRole==1}">
                                                            <i class="fa fa-black-tie" style="color: #c40000;"></i>
                                                            <span class=""
                                                                  style="margin-top: 2px!important;color: #c40000;font-size: 13px;;"><b>博主</b></span>
                                                        </c:if>
                                                        <span class="comment-meta">
                                                    <span class="ua-info" style="display: inline;">
                                                    <br>
                                                    <span class="comment-aux">
                                                        <span class="reply">
                                                            <a rel="nofollow" class="comment-reply-link" href="#respond"
                                                               onclick="replyComment()">回复
                                                            </a>
                                                        </span>
                                                        <fmt:formatDate value="${c2.commentCreateTime}"
                                                                        pattern="yyyy年MM月dd日 HH:mm:ss"/>&nbsp;
                                                        <c:if test="${sessionScope.user!=null}">
                                                            <a href="javascript:void(0)"
                                                               onclick="deleteComment(${c2.commentId})">删除</a>
                                                            <a class="comment-edit-link"
                                                               href="/admin/comment/edit/${c2.commentId}"
                                                               target="_blank">编辑</a>
                                                        </c:if>
                                                        <span class="floor"> &nbsp;${floor2}层 </span>
                                                    </span>
                                                </span>
                                                    </span>
                                                        <p>
                                                            <c:if test="${c2.commentPid!=0}">
                                                                <c:if test="${c2.commentPid!=0}">
                                                                    <span class="at">@ ${c2.commentPname}</span>
                                                                </c:if>
                                                                ${c2.commentContent}
                                                            </c:if>
                                                        </p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ol>
            </div>
                <%--评论框 end--%>

        </main>
        <!-- .site-main -->
    </div>
    <%--博客主体-左侧文章正文end--%>
</rapid:override>


<rapid:override name="footer-script">
    <script src="/js/jquery.cookie.js"></script>

    <script type="text/javascript">
        increaseViewCount();
        layui.code({
            elem: 'pre',//默认值为.layui-code
           // skin: 'notepad', //如果要默认风格，不用设定该key。
            about: false
        });
    </script>

</rapid:override>


<%@ include file="../Public/framework.jsp" %>