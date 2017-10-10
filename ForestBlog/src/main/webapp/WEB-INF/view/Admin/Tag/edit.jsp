<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 编辑标签
    </rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input-block {
            margin:0px 10px;
        }
        .layui-table {
            margin-top: 0;
        }
        .layui-col-md4 {
            padding:10px;
        }
        .layui-col-md8 {
            padding:10px;
        }
        .layui-btn {
            margin: 2px 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/tag">标签列表</a>
              <a><cite>编辑标签</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4" style="border: 1px solid #FF5722;">
            <form class="layui-form" method="post" id="myForm" action="/admin/tag/editSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>编辑标签</strong>
                    </div>
                    <input type="hidden" name="tagId" value="${tagCustom.tagId}">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="tagName" value="${tagCustom.tagName}" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" value="${tagCustom.tagDescription}" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        状态 <br>
                        <input type="radio" name="tagStatus" value="1" title="显示" <c:if test="${tagCustom.tagStatus==1}">checked</c:if>>
                        <input type="radio" name="tagStatus" value="0" title="隐藏" <c:if test="${tagCustom.tagStatus==0}">checked</c:if>>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md8">
            <table class="layui-table" >
                <colgroup>
                    <col width="50">
                    <col width="300">
                    <col width="100">
                    <col width="50">
                    <col width="100">
                </colgroup>
                <thead>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tagCustomList}" var="c">

                    <tr>
                        <td>${c.tagId}</td>
                        <td>
                            <a href="/tag/${c.tagId}" target="_blank">${c.tagName}</a>
                        </td>
                        <td>
                            <a href="/tag/${c.tagId}" target="_blank">${c.articleCount}</a>
                        </td>
                        <td>
                                ${c.tagStatus}
                        </td>
                        <td>
                            <a href="/admin/tag/edit/${c.tagId}" class="layui-btn layui-btn-mini">编辑</a>
                            <c:if test="${c.articleCount==0}">
                                <a href="/admin/tag/delete/${c.tagId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                            </c:if>

                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>如果该分类包含文章，将不可删除</li>
                    <li>分类状态为1表示将显示在侧边栏，为0表示不显示在侧边栏(依然显示在正文)</li>
                </ul>
            </blockquote>
        </div>
    </div>






</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
