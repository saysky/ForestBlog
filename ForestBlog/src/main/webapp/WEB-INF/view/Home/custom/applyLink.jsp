<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }
        #applyLink-box {
            width: 400px;
            padding: 20px;
        }
        #applyLink-info {
            width: 100%;
        }

       #applyLink-info p {
            position: relative;
        }
        #applyLink-info label {
            background: #f1f1f1;
            position: absolute;
            color: #555;
            display: inline-block;
            top: 0;
            left: 0;
            width: 75px;
            padding: 6px 10px;
            border-radius: 2px 0 0 2px;
            border: 1px solid #ddd;
            z-index: 1;
            height: 24px;

        }

       #applyLink-info .required {
           position: absolute;
           color: #bd3500;
           font-size: 20px;
           font-weight: bold;
           right: 5px;
           z-index: 1;
       }
       #applyLink-info input {
           background: #fff;
           position: relative;
           width: 100%;
           height: 37px;
           line-height: 37px;
           margin: 0 10px 10px 0;
           border: 1px solid #ddd;
           display: block;
           text-indent: 105px;
           border-radius: 2px;
           -webkit-appearance: none;
           _vertical-align: middle;
           font-size: 16px;
       }

        #submit {
            background: #fff;
            width: 100%;
            cursor: pointer;
            border: 1px solid #ddd;
            border-radius: 2px;
            height: 37px;
            line-height: 37px;
            margin: 0 10px 10px 0;
            font-size: 16px;
        }

        #submit:hover {
            background-color: #2F889A;
            border: 1px solid #2F889A;
            color: #ffffff;
        }

        #tips {
            margin-top: 10px;
            border-left: 5px solid #0078AD;
            background-color: #f2f2f2;
            padding: 8px 20px;
        }

        a {
            text-decoration: none;
            color:#222;
        }
    </style>
</head>
<body>
<form id="apply_link_form">
    <div id="applyLink-box">
        <div id="applyLink-info">
            <p>
                <label for="link_name">站点名
                    <span class="required">*</span>
                </label>
                <input type="text" name="link_name" id="link_name" placeholder="如:言曌博客" required="required">
            </p>

            <p>
                <label for="link_url">网站地址
                    <span class="required">*</span>
                </label>
                <input type="url" name="link_url" id="link_url" placeholder="如:https://liuyanzhao.com" required="required">
            </p>

            <p>
                <label for="link_description">网站描述
                </label>
                <input type="text" name="link_description" id="link_description" placeholder="如:一个后端开发者的成长笔记" >
            </p>

            <p>
                <label for="link_owner_contact">联系方式
                    <span class="required">*</span>
                </label>
                <input type="text" name="link_owner_contact" id="link_owner_contact" placeholder="如:邮箱service@liuyanzhao.com" required="required">
            </p>
        </div>
        <p class="form-submit">
            <input id="submit" name="submit" type="submit" value="提交申请" >
        </p>

        <div id="tips" >
            <p>温馨提示：如果可以，申请前请先添加本站链接</p>
            <p>网站名称：言曌博客</p>
            <p>站点地址：<a href="http://liuyanzhao.com" target="_blank">http://liuyanzhao.com</a></p>
            <p>网站描述：一个Javaer的成长之路</p>
            <p>联系方式：QQ(微信)
                <a target="blank" rel="external nofollow" href="http://wpa.qq.com/msgrd?V=3&amp;uin=847064370&amp;Site=QQ&amp;Menu=yes" title="QQ在线">
                847064370
                </a>
            </p>

        </div>
    </div>

</form>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/layer/layer.js"></script>
<script>

     //ajax提交信息
     $("#apply_link_form").submit(function(){
         $.ajax({
             async: false,
             type: "POST",
             url:'${pageContext.request.contextPath}/applyLinkSubmit',
             contentType : "application/x-www-form-urlencoded; charset=utf-8",
             data:$("#apply_link_form").serialize(),
             dataType: "text",
             success: function () {
                     parent.layer.alert("感谢申请！");
                     var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                     parent.layer.close(index); //再执行关闭

               },
             error: function () {

             }
         })
     })


</script>

</body>
</html>
