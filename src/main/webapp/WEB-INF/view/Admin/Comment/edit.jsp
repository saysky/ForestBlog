<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="com.liuyanzhao.blog.util.Functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 评论列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-btn {
            margin: 2px 0 !important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/comment">评论列表</a>
              <a><cite>编辑评论</cite></a>
        </span>
    </blockquote>

    <form class="layui-form"  method="post" id="myForm" action="/admin/comment/editSubmit">
        <input type="hidden" name="commentId" value="${commentCustom.commentId}">
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorName"  value="${commentCustom.commentAuthorName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorEmail"  value="${commentCustom.commentAuthorEmail}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">网址 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorUrl"  value="${commentCustom.commentAuthorUrl}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea name="commentContent"  class="layui-textarea">${commentCustom.commentContent}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="commentStatus" value="1" title="正常" <c:if test="${commentCustom.commentStatus==1}">checked</c:if>>
                <input type="radio" name="commentStatus" value="0" title="隐藏"<c:if test="${commentCustom.commentStatus==0}">checked</c:if>>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>

    </form>


</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp" %>
