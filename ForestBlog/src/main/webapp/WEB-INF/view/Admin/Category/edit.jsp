<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 分类列表
    </rapid:override>
<rapid:override name="header-style">
    <style>
        .layui-input-block {
            margin:0px 10px;
        }
    </style>
</rapid:override>

<rapid:override name="content">


    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/category">分类列表</a>
              <a><cite>编辑分类</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4" style="border: 1px solid #FF5722;">
            <form class="layui-form"  method="post" id="myForm" action="/admin/category/editSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>修改分类</strong>
                    </div>
                    <input type="hidden" name="categoryId" value="${categoryCustom.categoryId}">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="categoryName" value="${categoryCustom.categoryName}" placeholder="" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        父节点 <span style="color: #FF5722; ">*</span>
                        <select name="categoryPid" class="layui-input" required>
                            <c:forEach items="${categoryCustomList}" var="c">
                                <c:choose>
                                    <c:when test="${c.categoryId==categoryCustom.categoryPid}">
                                        <option value="${c.categoryId}" selected>${c.categoryName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${c.categoryPid==0}">
                                            <option value="${c.categoryId}">${c.categoryName}</option>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <option value="0" <c:if test="${categoryCustom.categoryPid==0}">selected</c:if> >无</option>
                        </select>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        分类描述
                        <input type="text" name="categoryDescription" value="${categoryCustom.categoryDescription}"  class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标样式
                        <input type="text" name="categoryIcon" value="${categoryCustom.categoryIcon}"class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        状态 <br>
                        <input type="radio" name="categoryStatus" value="1" title="显示" <c:if test="${categoryCustom.categoryStatus==1}">checked</c:if>>
                        <input type="radio" name="categoryStatus" value="0" title="隐藏" <c:if test="${categoryCustom.categoryStatus==0}">checked</c:if>>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
            <br><br>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>如果该分类为一级分类，父节点将不可修改</li>
                    <li>如果该分类为二级分类，父节点可选择其对应的一级目录</li>
                </ul>
            </blockquote>
        </div>
        <div class="layui-col-md8">
            <table class="layui-table" >
                <colgroup>
                    <col width="50">
                    <col width="50">
                    <col width="300">
                    <col width="100">
                    <col width="50">
                    <col width="100">
                </colgroup>
                <thead>
                <tr>
                    <th>id</th>
                    <th>pid</th>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryCustomList}" var="c">
                    <c:if test="${c.categoryPid==0}">
                        <tr>
                            <td class="layui-bg-cyan">${c.categoryId}</td>
                            <td>${c.categoryPid}</td>
                            <td>
                                <a href="/category/${c.categoryId}" target="_blank">${c.categoryName}</a>
                            </td>
                            <td>
                                <a href="/category/${c.categoryId}" target="_blank">${c.articleCount}</a>
                            </td>
                            <td>
                                    ${c.categoryStatus}
                            </td>
                            <td>
                                <a href="/admin/category/edit/${c.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                <c:if test="${c.articleCount==0}">
                                    <a href="/admin/category/delete/${c.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                </c:if>
                            </td>
                        </tr>
                        <c:forEach items="${categoryCustomList}" var="c2">
                            <c:if test="${c2.categoryPid==c.categoryId}">
                                <tr>
                                    <td class="cate-parent">${c2.categoryId}</td>
                                    <td>${c2.categoryPid}</td>
                                    <td>
                                        <a href="/category/${c2.categoryId}" target="_blank">${c2.categoryName}</a>
                                    </td>
                                    <td>
                                        <a href="/category/${c2.categoryId}" target="_blank">${c2.articleCount}</a>
                                    </td>
                                    <td>
                                            ${c2.categoryStatus}
                                    </td>
                                    <td>
                                        <a href="/admin/category/edit/${c2.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                        <c:if test="${c2.articleCount==0}">
                                            <a href="/admin/category/delete/${c2.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>


                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>分类最多只有两级，一级分类pid=0，二级分类pid=其父节点id</li>
                    <li>如果该分类包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>






</rapid:override>
<rapid:override name="footer-script">

</rapid:override>

<%@ include file="../Public/framework.jsp"%>
