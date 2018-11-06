<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
       - 菜单列表
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
          <a><cite>菜单内容列表</cite></a>
        </span>
    </blockquote>

    <div class="layui-row">
        <div class="layui-col-md4">
            <form class="layui-form"  method="post" id="myForm" action="/admin/menu/insertSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>添加菜单项目</strong>
                    </div>
                    <div class="layui-input-block">
                        名称
                        <span style="color: #FF5722; ">*</span>
                        <input type="text" name="menuName" placeholder="如：如留言板" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        URL
                        <span style="color: #FF5722; ">*</span>
                        <input type="text" name="menuUrl" placeholder="如：http://liuyanzhao.com/message.html" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标
                        <input type="text" name="menuIcon" placeholder="如：fa fa-comment" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        菜单位置
                        <select name="menuLevel" id="">
                            <option value="1" selected>顶部菜单</option>
                            <option value="2" >主要菜单</option>
                        </select>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
                    </div>
                </div>
            </form>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>1、图标为名称前面的字体图标，可选。采用的是<a href="http://fontawesome.io/icons/" target="_blank">fontawesome</a>图标</li>
                    <li>2、目前只有两种菜单：顶部菜单和主要菜单</li>
                </ul>
            </blockquote>
        </div>
        <div class="layui-col-md8" style="border: 1px solid #FF5722;">

            <div class="layui-tab layui-tab-card">
                <ul class="layui-tab-title">
                    <li class="layui-this">顶部菜单</li>
                    <li>主要菜单</li>
                </ul>
                <div class="layui-tab-content" style="height: auto;">
                    <div class="layui-tab-item layui-show">

                        <table class="layui-table" >
                            <colgroup>
                                <col width="100">
                                <col width="200">
                                <col width="50">
                                <col width="50">
                                <col width="100">
                                <col width="50">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>URL</th>
                                <th>Order</th>
                                <td>状态</td>
                                <th>操作</th>
                                <th>ID</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${menuCustomList}" var="m">
                                <c:if test="${m.menuLevel==1}">
                                <tr>
                                    <td>
                                        <i class="${m.menuIcon}"></i>
                                            ${m.menuName}
                                    </td>
                                    <td>
                                        <a href="${m.menuUrl}" target="_blank">${m.menuUrl}</a>
                                    </td>
                                    <td>${m.menuOrder}</td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${m.menuStatus==0}">
                                            <span style="color:#FF5722;">隐藏</span>
                                        </c:when>
                                        <c:otherwise>
                                            显示
                                        </c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                        <a href="/admin/menu/edit/${m.menuId}" class="layui-btn layui-btn-mini" title="点击编辑">编辑</a>
                                        <a href="/admin/menu/delete/${m.menuId}" class="layui-btn layui-btn-danger layui-btn-mini" title="点击删除" onclick="return confirmDelete()">删除</a>
                                    </td>
                                    <td >${m.menuId}</td>
                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <blockquote class="layui-elem-quote layui-quote-nm">
                            温馨提示：
                            <ul>
                                <li>1、Order的大小为菜单中各项目的顺序</li>
                            </ul>
                        </blockquote>
                    </div>
                    <div class="layui-tab-item">
                        <table class="layui-table" >
                            <colgroup>
                                <col width="100">
                                <col width="200">
                                <col width="50">
                                <col width="50">
                                <col width="100">
                                <col width="50">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>URL</th>
                                <th>Order</th>
                                <th>状态</th>
                                <th>操作</th>
                                <th>ID</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${menuCustomList}" var="m">
                                <c:if test="${m.menuLevel==2}">
                                    <tr>
                                        <td>
                                            <i class="${m.menuIcon}"></i>
                                                ${m.menuName}
                                        </td>
                                        <td>
                                            <a href="${m.menuUrl}" target="_blank">${m.menuUrl}</a>
                                        </td>
                                        <td>${m.menuOrder}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${m.menuStatus==0}">
                                                    <span style="color:#FF5722;">隐藏</span>
                                                </c:when>
                                                <c:otherwise>
                                                    显示
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="/admin/menu/edit/${m.menuId}" class="layui-btn layui-btn-mini">编辑</a>
                                            <a href="/admin/menu/delete/${m.menuId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                        </td>
                                        <td >${m.menuId}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <blockquote class="layui-elem-quote layui-quote-nm">
                            温馨提示：
                            <ul>
                                <li>1、Order的大小为菜单中各项目的顺序</li>
                                <li>2、主要菜单的分类目录是默认显示的</li>
                            </ul>
                        </blockquote>
                    </div>
                    <br><br><br>
                </div>
            </div>
        </div>
    </div>



</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
