<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 基本信息列表
    </rapid:override>
<rapid:override name="header-style">
    <style>
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/admin">首页</a>
          <a><cite>基本信息</cite></a>
        </span>
    </blockquote>
    <form class="layui-form" action="/admin/options/editSubmit" method="post">

    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">基本信息</li>
            <li>小工具</li>
        </ul>
        <div class="layui-tab-content">
            <br><br>
            <c:choose>
                <c:when test="${optionCustom.optionId>0}">
                    <input type="hidden" name="optionId" value="${optionCustom.optionId}">
                </c:when>
                <c:otherwise>
                    <%--给一个默认值，这个地方很奇怪，本来不需要加的--%>
                    <input type="hidden" name="optionId" value="1">
                </c:otherwise>
            </c:choose>

            <div class="layui-tab-item layui-show">
                <div class="layui-form-item">
                    <label class="layui-form-label">站点名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionSiteTitle"  value="${optionCustom.optionSiteTitle}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">站点描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="optionSiteDescrption"   value="${optionCustom.optionSiteDescrption}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">首页描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="optionMetaDescrption"  value="${optionCustom.optionMetaDescrption}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">首页关键词</label>
                    <div class="layui-input-block">
                        <input type="text" name="optionMetaKeyword"  value="${optionCustom.optionMetaKeyword}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-tab-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">头像</label>
                    <div class="layui-input-inline">
                        <div class="layui-upload">
                            <div class="layui-upload-list" style="">
                                <img class="layui-upload-img" src="${optionCustom.optionAboutsiteAvatar}" id="demo1" width="100"
                                     height="100">
                                <p id="demoText"></p>
                            </div>
                            <button type="button" class="layui-btn" id="test1">上传图片</button>
                            <input type="hidden" id="optionAboutsiteAvatar" name="optionAboutsiteAvatar" value="${optionCustom.optionAboutsiteAvatar}">
                        </div>
                    </div>
                    <div class="layui-form-mid layui-word-aux">建议 150px*150px</div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionAboutsiteTitle"   value="${optionCustom.optionAboutsiteTitle}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">说明</label>
                    <div class="layui-input-block">
                        <input type="text" name="optionAboutsiteContent"   value="${optionCustom.optionAboutsiteContent}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">微信二维码</label>
                    <div class="layui-input-inline">
                        <div class="layui-upload">
                            <div class="layui-upload-list" style="">
                                <img class="layui-upload-img" src="${optionCustom.optionAboutsiteWechat}" id="demo2" width="100"
                                     height="100">
                                <p id="demoText2"></p>
                            </div>
                            <button type="button" class="layui-btn" id="test2">上传图片</button>
                            <input type="hidden" id="optionAboutsiteWechat" name="optionAboutsiteWechat" value="${optionCustom.optionAboutsiteWechat}">
                        </div>
                    </div>
                    <div class="layui-form-mid layui-word-aux">建议 430px*430px</div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">QQ</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionAboutsiteQq"   value="${optionCustom.optionAboutsiteQq}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">微博</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionAboutsiteWeibo"  value="${optionCustom.optionAboutsiteWeibo}"    autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">Github</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionAboutsiteGithub"  value="${optionCustom.optionAboutsiteGithub}"   autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
        </div>

    </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存设置</button>
            </div>
        </div>
    </form>




</rapid:override>
<rapid:override name="footer-script">
    <script>
        //上传头像
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '/uploadFile',
                before: function (obj) {
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#optionAboutsiteAvatar").attr("value", res.data.src);
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                },
                error: function () {
                    var demoText = $('#demoText');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });
        });

        //上传微信号
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test2',
                url: '/uploadFile',
                before: function (obj) {
                    obj.preview(function (index, file, result) {
                        $('#demo2').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#optionAboutsiteWechat").attr("value", res.data.src);
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                },
                error: function () {
                    var demoText = $('#demoText2');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });

        });
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
