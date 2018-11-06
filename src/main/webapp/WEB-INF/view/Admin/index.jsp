<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="header-style">
    <style>
        .layui-input-block {
             margin-left: 0!important;;
        }

        .layui-col-md6 {
            padding: 10px;
        }

        .postbox {
            min-width: 255px;
            border: 1px solid #e5e5e5;
            /* -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.04); */
            box-shadow: 0 1px 1px rgba(0,0,0,.04);
            background: #fff;
        }


        .postbox, .stuffbox {
            margin-bottom: 20px;
            padding: 0;
            line-height: 1;
        }

        .js .postbox .handlediv {
            display: block;
        }
        .wp-core-ui .button-link {
            margin: 0;
            padding: 0;
            /* -webkit-box-shadow: none; */
            box-shadow: none;
            border: 0;
            /* -webkit-border-radius: 0; */
            border-radius: 0;
            background: 0 0;
            outline: 0;
            cursor: pointer;
        }

        .postbox .handlediv {
            display: none;
            float: right;
            width: 36px;
            height: 36px;
            padding: 0;
        }

        .screen-reader-text span {
            position: absolute;
            margin: -1px;
            padding: 0;
            height: 1px;
            width: 1px;
            overflow: hidden;
            clip: rect(0 0 0 0);
            border: 0;
            word-wrap: normal!important;
        }
        .screen-reader-text span {
            position: absolute;
            margin: -1px;
            padding: 0;
            height: 1px;
            width: 1px;
            overflow: hidden;
            clip: rect(0 0 0 0);
            border: 0;
            word-wrap: normal!important;
        }

        .metabox-holder .postbox>h3, .metabox-holder .stuffbox>h3, .metabox-holder h2.hndle, .metabox-holder h3.hndle {
            font-size: 14px;
            padding: 8px 12px;
            margin: 0;
            line-height: 1.4;
        }
        #dashboard_quick_press form {
            margin: 12px;
        }
        form {
            display: block;
            margin-top: 0em;
        }
        .postbox .inside, .stuffbox .inside {
            padding: 0 12px 12px;
            line-height: 1.4em;
            font-size: 13px;
        }
        .postbox, .stuffbox {
            margin-bottom: 20px;
            padding: 0;
            line-height: 1;
        }
        Inherited from div#wpwrap

        a, div {
            outline: 0;
        }
        user agent stylesheet
        div {
            display: block;
        }
        Inherited from div.inside
        .postbox .inside, .stuffbox .inside {
            padding: 0 12px 12px;
            line-height: 1.4em;
            font-size: 13px;
        }

        #dashboard_quick_press .drafts li time {
            color: #72777c;
        }
        #description-wrap label, #title-wrap label {
            cursor: text;
        }


        #dashboard-widgets form .input-text-wrap input, #dashboard-widgets form .textarea-wrap textarea {
            width: 100%;
        }

        #dashboard_quick_press input, #dashboard_quick_press textarea {
            /* -webkit-box-sizing: border-box; */
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin: 0;
        }

        a, div {
            outline: 0;
        }

        #description-wrap label, #title-wrap label {
            cursor: text;
        }

        #dashboard-widgets form .input-text-wrap input, #dashboard-widgets form .textarea-wrap textarea {
            width: 100%;
        }


        .meta-box-sortables select {
            max-width: 100%;
        }
        .js #dashboard_quick_press .drafts {
            border-top: 1px solid #eee;
        }
        #dashboard_quick_press .drafts {
            padding: 10px 0 0;
        }
        #dashboard_quick_press .drafts .view-all {
            float: right;
            margin: 0 12px 0 0;
        }
        #dashboard_quick_press .drafts p {
            margin: 0;
            word-wrap: break-word;
        }
        #dashboard_quick_press .drafts h2 {
            line-height: inherit;
        }

        #dashboard-widgets h3, #dashboard-widgets h4, #dashboard_quick_press .drafts h2 {
            margin: 0 12px 8px;
            padding: 0;
            font-size: 14px;
            font-weight: 400;
            color: #23282d;
        }
        #dashboard_quick_press .drafts ul {
            margin: 0 12px;
        }
        ul {
            list-style: none;
        }
        ol, ul {
            padding: 0;
        }
        #dashboard_quick_press .drafts li {
            margin-bottom: 1em;
        }
        dd, li {
            /* margin-bottom: 6px; */
        }

        user agent stylesheet
        li {
            display: list-item;
            text-align: -webkit-match-parent;
        }
        #dashboard_quick_press .draft-title,.dashboard-comment-wrap {
            word-wrap: break-word;
        }
        a, div {
            outline: 0;
        }
        user agent stylesheet
        div {
            display: block;
        }
        #dashboard_quick_press .drafts p {
            /* margin: 0; */
            word-wrap: break-word;
        }
        p {
            font-size: 13px;
            line-height: 1.5;
        }
        h2, h3, p {
            margin: 1em 0;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <div class="layui-container">

    <div class="layui-row">
        <div class="layui-col-md6">
            <div id="dashboard_activity" class="postbox ">
                <div class="inside">
                    <div id="activity-widget">
                        <div id="published-posts" class="activity-block"><h3>最近发布</h3> <br>
                            <ul>
                                <c:forEach items="${articleCustomList}" begin="0" end="4" step="1" var="a">
                                    <li><span><fmt:formatDate value="${a.articleCustom.articlePostTime}"
                                                              pattern="HH:mm MM月dd日"/> </span>
                                        <a href="/article/${a.articleCustom.articleId}"
                                           target="_blank">${a.articleCustom.articleTitle}</a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                        <br>
                        <div id="latest-comments" class="activity-block"><h3>近期评论</h3>
                            <ul id="the-comment-list" data-wp-lists="list:comment">
                                <c:forEach items="${commentListVoList}" begin="0" end="4" step="1" var="c">
                                    <li class="comment   thread-even comment-item approved">

                                        <img alt="" src="${c.commentCustom.commentAuthorAvatar}"
                                             class="avatar avatar-50 photo" height="50" width="50">
                                        <div class="dashboard-comment-wrap has-row-actions">
                                            <p class="comment-meta">
                                                由<cite class="comment-author">
                                                <a target="_blank" href="${c.commentCustom.commentAuthorUrl}"
                                                   rel="external nofollow"
                                                   class="url">${c.commentCustom.commentAuthorName}</a>
                                            </cite>发表在《<a
                                                    href="/article/${c.commentCustom.commentArticleId}">${c.articleCustom.articleTitle}</a>》
                                                <c:if test="${c.commentCustom.commentStatus==0}">
                                                    <span class="approve">[待审]</span>
                                                </c:if>
                                            </p>

                                            <blockquote><p>${c.commentCustom.commentContent}</p></blockquote>
                                            <p class="row-actions">
                                            <span class="">
                                            <c:choose>
                                                <c:when test="${c.commentCustom.commentStatus==1}">
                                                    <a href="javascript:void(0)" style="color: #FF5722;"
                                                       onclick="hideComment(${c.commentCustom.commentId})">屏蔽</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="" style="color: #009688;"
                                                       onclick="approveComment(${c.commentCustom.commentId})">批准</a>
                                                </c:otherwise>
                                            </c:choose>
                                            </span> |
                                                <span class="">
                                            <a data-comment-id="1268"
                                               href="/admin/comment/reply/${c.commentCustom.commentId}">
                                                回复
                                            </a>
                                            </span>
                                                <span class=""> |
                                                <a href="/admin/comment/edit/${c.commentCustom.commentId}">编辑</a>
                                            </span>
                                                <span class=""> |
                                                <a href="javascript:void(0)"
                                                   onclick="deleteComment(${c.commentCustom.commentId})">删除</a>
                                            </span>
                                            </p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <ul class="subsubsub">
                                <li class="all"><a
                                        href="">全部<span
                                        class="count">（<span class="all-count">${allCommentCount}</span>）</span></a> |
                                </li>
                                <li class="moderated"><a
                                        href="">待审<span
                                        class="count">（<span class="pending-count">${hiddenCommentCount}</span>）</span></a>
                                    |
                                </li>
                                <li class="approved"><a
                                        href="">已批准<span
                                        class="count">（<span
                                        class="approved-count">${approvedCommentCount}</span>）</span></a> |
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div id="dashboard_quick_press" class="postbox ">
                <div class="inside">
                    <form name="post" method="post" id="insertDraftForm"
                          class="initial-form hide-if-no-js" action="/admin/article/insertDraftSubmit">

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <input type="text" name="articleTitle" id="articleTitle" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <div class="layui-input-block">
                                <textarea name="articleContent" placeholder="请输入内容" id="articleContent" class="layui-textarea" required></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="articleStatus" value="0">
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-small" lay-submit lay-filter="formDemo" onclick="insertDraft()">保存草稿</button>
                                <button type="reset" class="layui-btn layui-btn-small layui-btn-primary">重置</button>
                            </div>
                        </div>

                    </form>
                    <div class="drafts"><p class="view-all"><a
                            href="/admin/article"
                            aria-label="查看所有草稿">查看所有</a></p>
                        <h2 class="hide-if-no-js">草稿</h2>
                        <ul>
                            <c:forEach items="${articleCustomList}" var="a">
                                <c:if test="${a.articleCustom.articleStatus==0}">
                                    <li>
                                        <div class="draft-title"><a
                                                href="/admin/article/edit/${a.articleCustom.articleId}">${a.articleCustom.articleTitle}</a>
                                            <time ><fmt:formatDate value="${a.articleCustom.articlePostTime}" pattern="yyyy年MM月dd日"/></time>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>
<%@ include file="Public/framework.jsp" %>
