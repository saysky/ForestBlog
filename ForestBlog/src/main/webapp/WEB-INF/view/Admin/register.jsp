<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<!--[if IE 8]>
<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
    <!--<![endif]-->
    <html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>${options.optionSiteTitle} &lsaquo; 注册</title>
            <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
            <link rel="shortcut icon" href="/img/logo.png">
            <link rel='stylesheet' id='dashicons-css' href='/plugin/login/dashicons.min.css' type='text/css'
                  media='all'/>
            <link rel='stylesheet' id='buttons-css' href='/plugin/login/buttons.min.css' type='text/css' media='all'/>
            <link rel='stylesheet' id='forms-css' href='/plugin/login/forms.min.css' type='text/css' media='all'/>
            <link rel='stylesheet' id='l10n-css' href='/plugin/login/l10n.min.css' type='text/css' media='all'/>
            <link rel='stylesheet' id='login-css' href='/plugin/login/login.min.css' type='text/css' media='all'/>
            <style type="text/css">
                body {
                    font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
                    background: url(/img/loginBg.jpg);
                    width: 100%;
                    height: 100%;
                }

                .login h1 a {
                    background-size: 220px 50px;
                    width: 220px;
                    height: 50px;
                    padding: 0;
                    margin: 0 auto 1em;
                }

                .login form {
                    background: #fff;
                    background: rgba(255, 255, 255, 0.6);
                    border-radius: 2px;
                    border: 1px solid #fff;
                }

                .login label {
                    color: #000;
                    font-weight: bold;
                }

                #backtoblog a, #nav a {
                    color: #fff !important;
                }

            </style>
            <meta name='robots' content='noindex,follow'/>
            <meta name="viewport" content="width=device-width"/>
            <style>
                body {
                    background-repeat: no-repeat;
                    background-size: 100% 100%;
                    background-attachment: fixed;
                }
            </style>
        </head>
        <body class="login login-action-login wp-core-ui  locale-zh-cn">
            <div id="login">
                <h1><a href="/" title="欢迎您光临本站！" tabindex="-1">${options.optionSiteTitle}</a></h1>
                <form name="registerForm" id="registerForm" method="post">
                    <p>
                        <label for="username">用户名<br/>
                            <input type="text" name="username" id="username" class="input"
                                   size="20" required/></label>
                    </p>
                    <p>
                        <label for="nickname">昵称<br/>
                            <input type="text" name="nickname" id="nickname" class="input"
                                   size="20" required/></label>
                    </p>
                    <p>
                        <label for="email">电子邮箱<br/>
                            <input type="text" name="email" class="input" id="email"
                                   size="20" required/></label>
                    </p>
                    <p>
                        <label for="password">登录密码<br/>
                            <input type="password" name="password" id="password" class="input"
                                   size="20" required/>
                        </label>
                    </p>
                    <p>
                        <label for="confirmPassword">确认密码<br/>
                            <input type="password" name="confirmPassword" id="confirmPassword" class="input"
                                   size="20" required/>
                        </label>
                    </p>
                    <p class="submit">
                        <input type="button" name="wp-submit" id="submit-btn" class="button button-primary button-large"
                               value="注册"/>
                    </p>
                </form>

                <p id="backtoblog"><a href="/">&larr; 返回到风吟博客</a> | <a href="/login">登录</a></p>

            </div>


            <div class="clear"></div>

            <script src="/js/jquery.min.js"></script>
            <script type="text/javascript">


                <%--注册验证--%>
                $("#submit-btn").click(function () {
                    var user = $("#username").val();
                    var email = $("#email").val();
                    var nickname = $("#nickname").val();
                    var password = $("#password").val();
                    var confirmPass = $("#confirmPassword").val();
                    if (user == "" || nickname == "" || email == "" || password == "" || confirmPass == "") {
                        alert("请输入完整信息！")
                    } else if (password != confirmPass) {
                        alert("两次密码不一致!");
                    } else if(user.length < 4 || user.length > 20) {
                        alert('用户名长度不合法');
                    } else {
                        $.ajax({
                            async: false,//同步，待请求完毕后再执行后面的代码
                            type: "POST",
                            url: '/registerSubmit',
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            data: $("#registerForm").serialize(),
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 0) {
                                    alert('注册成功');
                                    window.location.href = "/login";
                                } else {
                                    alert(data.msg);
                                }
                            },
                            error: function () {
                                alert("数据获取失败")
                            }
                        })
                    }
                })

            </script>
        </body>
    </html>

