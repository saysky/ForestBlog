<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

<rapid:override name="header-style">
	<style>
.entry-title {
	background: #f8f8f8;
}
</style>
	<link rel="stylesheet" href="/plugin/layui/css/layui.css">
</rapid:override>


<rapid:override name="breadcrumb">
	<%--面包屑导航 start--%>
	<nav class="breadcrumb">
		<a class="crumbs" href="/"> <i class="fa fa-home"></i>首页
		</a> <i class="fa fa-angle-right"></i> 留言板 <i class="fa fa-angle-right"></i> 正文
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
                           留言板
                    </h1>
                </header><!-- .entry-header -->
                <div class="entry-content">
                    <div class="single-content" style="text-align: center;">
                        <div>
                           <div>未来不迎 过往不恋</div>
                           <div>不忘初心 方得始终</div>
                        </div>
                    </div>
                </div>
            </article><!-- #post -->
            <%--评论区域 start--%>
            <div class="scroll-comments"></div>
            <div id="comments" class="comments-area">
                <div id="respond" class="comment-respond">
                    <h3 id="reply-title" class="comment-reply-title"><span id="reply-title-word">发表评论</span>
                        <a rel="nofollow" id="cancel-comment-reply-link"
                           href="/message#"
                           style="">取消回复</a>
                    </h3>
                    <form id="comment_form" method="post">
                        <c:if test="${sessionScope.user!=null}">
                            <div class="user_avatar">
                                <img alt="幻凡ss"
                                     src="${sessionScope.user.userAvatar}"
                                     class="avatar avatar-64 photo" height="64" width="64">
                                登录者：${sessionScope.user.userNickname}
                                <br> <a href="javascript:void(0)" onclick="logout()">登出</a>
                                <input type="hidden" name="commentRole" value="1" id="commentRole">
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
                                   value="0" id="article_id">
                            <input type="hidden" name="commentPid" id="comment_pid" value="0">
                        </p>
                    </form>
                </div>

                <ol class="comment-list">
                    <c:set var="floor" value="0"/>
                    <c:forEach items="${listMessage}" var="c">
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
                                        <a href="${c.commentAuthorUrl}" target="_black"><strong>${c.commentAuthorName} </strong></a>
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
                                                        <a rel="nofollow" class="comment-reply-link" href="#"
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
                                    <c:forEach items="${listMessage}" var="c2">
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
                                                        <a href="${c2.commentAuthorUrl}"  target="_black"><strong>${c2.commentAuthorName} </strong></a>
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
	<script>
        //添加用户验证和编辑用户验证
        layui.use(['form', 'layedit', 'laydate'], function () {
            var form = layui.form, layer = layui.layer;
            form.verify({

                userName: function (value) {
                    if (value.length > 12 || value.length < 4) {
                        return "用户名必须4到12位";
                    }
                    if(checkUserName()==1) {
                        return "用户名已存在";
                    }
                },
                userEmail: function () {
                    if(checkUserEmail()==1) {
                        return "电子邮箱已存在";
                    }
                }

            });

            form.on('submit(demo1)', function (data) {
                return true;
            });
        });
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>