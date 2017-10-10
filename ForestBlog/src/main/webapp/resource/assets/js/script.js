$(document).ready(function() {
	// 搜索
	$(".nav-search").click(function() {
		$("#search-main").fadeToggle(300);
	});

	// 菜单
	$(".nav-mobile").click(function() {
		$("#mobile-nav").slideToggle(500);
	});


	// 分享 √
	if(/iphone|ipod|ipad|ipad|mobile/i.test(navigator.userAgent.toLowerCase())){
		$('.share-sd').click(function() {
			$('#share').animate({
				opacity: 'toggle',
				top: '-80px'
			},
				500).animate({
				top: '-60px'
			},
			'fast');
			return false;
		});
	} else {
		$(".share-sd").mouseover(function() {
			$(this).children("#share").show();
		});
		$(".share-sd").mouseout(function() {
			$(this).children("#share").hide();
		});
	}


	// 去边线
	$(".message-widget li:last, .message-page li:last, .hot_commend li:last, .search-page li:last, .my-comment li:last, .message-tab li:last").css("border", "none");


	// 字号 √
	$("#fontsize").click(function() {
		var _this = $(this);
		var _t = $(".single-content");
		var _c = _this.attr("class");
		if (_c == "size_s") {
			_this.removeClass("size_s").addClass("size_l");
			_this.text("A+");
			_t.removeClass("fontsmall").addClass("fontlarge");
		} else {
			_this.removeClass("size_l").addClass("size_s");
			_this.text("A-");
			_t.removeClass("fontlarge").addClass("fontsmall");
		};
	});



// 结束
});




// 隐藏侧边
function pr() {
	var R = document.getElementById("sidebar");
	var L = document.getElementById("primary");
	if (R.className == "sidebar") {
		R.className = "sidebar-hide";
		L.className = "";
	} else {
		R.className = "sidebar";
		L.className = "primary";
	}
}

//微信二维码
$("#weixin_btn").click(function () {
    //页面层-微信二维码
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '516px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: $('#weixin_code'),
        end:function () {
            $("#weixin_code").attr("style","display:none;")
        }
    });
});

//获取相对路径
// function  getPath(){
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0,index+1);
//     return result;
// }


// 文字滚动
(function($) {
    $.fn.textSlider = function(settings) {
        settings = jQuery.extend({
                speed: "normal",
                line: 2,
                timer: 1000
            },
            settings);
        return this.each(function() {
            $.fn.textSlider.scllor($(this), settings)
        })
    };
    $.fn.textSlider.scllor = function($this, settings) {
        var ul = $("ul:eq(0)", $this);
        var timerID;
        var li = ul.children();
        var _btnUp = $(".up:eq(0)", $this);
        var _btnDown = $(".down:eq(0)", $this);
        var liHight = $(li[0]).height();
        var upHeight = 0 - settings.line * liHight;
        var scrollUp = function() {
            _btnUp.unbind("click", scrollUp);
            ul.animate({
                    marginTop: upHeight
                },
                settings.speed,
                function() {
                    for (i = 0; i < settings.line; i++) {
                        ul.find("li:first").appendTo(ul)
                    }
                    ul.css({
                        marginTop: 0
                    });
                    _btnUp.bind("click", scrollUp)
                })
        };
        var scrollDown = function() {
            _btnDown.unbind("click", scrollDown);
            ul.css({
                marginTop: upHeight
            });
            for (i = 0; i < settings.line; i++) {
                ul.find("li:last").prependTo(ul)
            }
            ul.animate({
                    marginTop: 0
                },
                settings.speed,
                function() {
                    _btnDown.bind("click", scrollDown)
                })
        };
        var autoPlay = function() {
            timerID = window.setInterval(scrollUp, settings.timer)
        };
        var autoStop = function() {
            window.clearInterval(timerID)
        };
        ul.hover(autoStop, autoPlay).mouseout();
        _btnUp.css("cursor", "pointer").click(scrollUp);
        _btnUp.hover(autoStop, autoPlay);
        _btnDown.css("cursor", "pointer").click(scrollDown);
        _btnDown.hover(autoStop, autoPlay)
    }
})(jQuery);

$("#scrolldiv").textSlider({line: 1, speed: 300, timer: 6000});


//打赏
(function($){
    var id = Date.now();
    if($("#STYLE_"+id).size()<1){
        document.writeln("<style id='STYLE_"+id+"'>@CHARSET \"UTF-8\";*{-webkit-tap-highlight-color:rgba(255,0,0,0)}.box-size{box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box}.ds-hide{display:none}.ds-reward-stl{font-family:\"microsoft yahei\";text-align:center;background:#f1f1f1;padding:10px 0;color:#666;margin:20px auto;width:90%}#dsRewardBtn {padding: 0;margin: 0;position: absolute;background: #7ab951;left: 110px;top: -7px;width: 50px;height: 50px;font-size: 16px;font-weight: 600;line-height: 43px;display: block;border: 4px solid #fff;border-radius: 40px;color: #FFF;}#dsRewardBtn span{display:inline-block;width:50px;height:50px;border-radius:100%;line-height:58px;color:#fff;font:400 25px/50px 'microsoft yahei';background:#FEC22C}#dsRewardBtn:hover{cursor:pointer}.ds-dialog{z-index:9999;width:100%;height:100%;position:fixed;top:0;left:0;border:1px solid #d9d9d9}.ds-dialog .ds-close-dialog{position:absolute;top:15px;right:20px;font:400 24px/24px Arial;width:20px;height:20px;text-align:center;padding:0;cursor:pointer;background:transparent;border:0;-webkit-appearance:none;font-weight:700;line-height:20px;opacity:.6;filter:alpha(opacity=20)}.ds-dialog .ds-close-dialog:hover{color:#000;text-decoration:none;cursor:pointer;opacity:.6;filter:alpha(opacity=40)}.ds-dialog-bg{position:absolute;opacity:.6;filter:alpha(opacity=30);background:#000;z-index:9999;left:0;top:0;width:100%;height:100%}.ds-dialog-content{font-family:'microsoft yahei';font-size:14px;background-color:#FFF;position:fixed;padding:0 20px;z-index:10000;overflow:hidden;border-radius:6px;-webkit-box-shadow:0 3px 7px rgba(0,0,0,.3);-moz-box-shadow:0 3px 7px rgba(0,0,0,.3);box-shadow:0 3px 7px rgba(0,0,0,.3);-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}.ds-dialog-pc{width:390px;height:380px;top:50%;left:50%;margin:-190px 0 0 -195px}.ds-dialog-wx{width:90%;height:280px;top:50%;margin-top:-140px;margin-left:5%}.ds-dialog-content h5{text-align:left;font-size:15px;font-weight:700;margin:15px 0;color:#555}.ds-payment-way{text-align:left}.ds-payment-way label{cursor:pointer;font-weight:400;display:inline-block;font-size:14px;margin:0 15px 0 0;padding:0}.ds-payment-way input[type=radio]{vertical-align:middle;margin:-2px 5px 0 0}.ds-payment-img{margin:15px 0;text-align:center}p.ds-pay-info{font-size:15px;margin:0 0 10px}.ds-pay-money{font-size:14px;margin-top:10px}.ds-pay-money p{margin:0}.ds-pay-money .ds-pay-money-sum{margin-bottom:4px}.ds-payment-img img{margin:0 auto;width:185px;}.ds-payment-img #qrCode_1{display:none}.ds-payment-img .qrcode-border{margin:0 auto}.ds-payment-img .qrcode-tip{width:48.13px;position:relative;margin:0 auto;font-size:12px;font-weight:700;background:#fff;height:15px;line-height:15px;margin-top:-12px}#qrCode_0 .qrcode-tip{color:#3caf36}#qrCode_3 .qrcode-tip{color:#e10602}.ds-payment-img #qrCode_3{display:none}.ds-payment-img #qrCode_2{display:none}#qrCode_2 .qrcode-tip{color:#eb5f01}#qrCode_1 .qrcode-tip{color:#6699cc}.wx_qrcode_container{text-align:center}.wx_qrcode_container h2{font-size:17px}.wx_qrcode_container p{font-size:14px}.ds-reward-stl{text-align:left;background:#fff;padding:0;color:#666;margin:0;width:0}#dsRewardBtn span{position:absolute;left:115px;top:-7px;background:#7ab951;width:50px;height:50px;font-size:16px;font-weight:600;line-height:43px;border:4px solid #fff;border-radius:40px}.share-s a{margin-top:-25px} .ds-payment-img .qrcode-border{border-radius: 29.97px; width: 236.89px; height: 236.89px; padding: 18.05px; margin-top: 25.53px; } </style>");
    }
    function write(){
        var content = "<div class=\"ds-dialog\" id='PAY_"+id+"'>"
            +"   <div class=\"ds-dialog-bg\" onclick=\"PaymentUtils.hide();\"></div>"
            +"   <div class=\"ds-dialog-content ds-dialog-pc \">"
            +"    <i class=\"ds-close-dialog\">&times;</i>"
            +"    <h5>选择打赏方式：</h5>"
            +"    <div class=\"ds-payment-way\">"
            +"     <label for=\"wechat\"><input type=\"radio\" id=\"wechat\" class=\"reward-radio\" value=\"0\" checked=\"checked\" name=\"reward-way\" />微信红包</label>"
            + "     <label for=\"qqqb\"><input type=\"radio\" id=\"qqqb\" class=\"reward-radio\" value=\"1\" name=\"reward-way\" />QQ钱包</label>"
            +"     <label for=\"alipay\"><input type=\"radio\" id=\"alipay\" class=\"reward-radio\" value=\"2\" name=\"reward-way\" />支付宝</label>"
            + "    </div>"
            + "    <div class=\"ds-payment-img\">"
            + "     <div class=\"qrcode-img qrCode_0\" id=\"qrCode_0\">"
            + "      <div class=\"qrcode-border box-size\" style=\"border: 9.02px solid rgb(60, 175, 54\">"
            + "       <img  class=\"qrcode-img qrCode_0\" id=\"qrCode_0\" src='/img/shang/weixinpay.jpg'  />"
            + "      </div>"
            + "      <p class=\"qrcode-tip\">打赏</p>"
            + "     </div>"
            + "     <div class=\"qrcode-img qrCode_1\" id=\"qrCode_1\">"
            + "      <div class=\"qrcode-border box-size\" style=\"border: 9.02px solid rgb(102, 153, 204\">"
            + "       <img  class=\"qrcode-img qrCode_1\" id=\"qrCode_1\"  src=\"/img/shang/qqpay.jpg\"  />"
            + "      </div>"
            + "      <p class=\"qrcode-tip\">打赏</p>"
            + "     </div>"
            + "     <div class=\"qrcode-img qrCode_2\" id=\"qrCode_2\">"
            + "      <div class=\"qrcode-border box-size\" style=\"border: 9.02px solid rgb(235, 95, 1\">"
            + "       <img  class=\"qrcode-img qrCode_2\" id=\"qrCode_2\"  src=\"/img/shang/alipay.jpg\"  />"
            + "      </div>"
            + "      <p class=\"qrcode-tip\">打赏</p>"
            + "     </div>"
            + "     </div>"
            + "    </div>"
            + "   </div>"
            + "  </div> ";
        $("body").append(content);
    }
    $(function(){
        write();
        var $pay = $("#PAY_"+id).hide();
        $pay.find(".ds-payment-way").bind("click",function(){
            $pay.find(".qrcode-img").hide();
            $pay.find(".qrCode_"+$pay.find("input[name=reward-way]:checked").val()).show();
        });
        $pay.find(".ds-close-dialog").bind("click",function(){
            $pay.hide();
        });
    });
    var PaymentUtils = window['PaymentUtils']={};
    PaymentUtils.show=function(){
        $("#PAY_"+id).show();
    }
    PaymentUtils.hide=function(){
        $("#PAY_"+id).hide();
    }
})(jQuery);



function confirmDelete() {
    var msg = "您确定要删除吗？";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}

//退出登录
function logout() {
    $.ajax({
        async: false,
        type: "POST",
        url:'/admin/logout',
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete:function () {
            window.location.reload();
        }
    })
}

//删除评论
function deleteComment(id) {
    if(confirmDelete()==true){
        $.ajax({
            async: false,
            type: "POST",
            url:'/admin/comment/delete/'+id,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete:function () {
                window.location.reload();
            }
        })
    }
}

//评论区域
$(".comment-reply-link").click(function () {
    var authorName = $(this).parents('.comment-author').find("strong").text();
    $("#cancel-comment-reply-link").show();
    $("#reply-title-word").html("回复 " + authorName);
    var commentId = $(this).parents('.comment-body').attr("id").match(/\d+/g);
    $("input[name=commentPid]").attr("value", commentId);
    $("input[name=commentPname]").attr("value", authorName);
    $("#comment").attr("placeholder", "@ " + authorName)
})

$("#cancel-comment-reply-link").click(function () {
    $("#cancel-comment-reply-link").hide();
    $("input[name=commentPid]").attr("value", 0);
    $("input[name=commentPname]").attr("value", "");
    $("#reply-title-word").html("发表评论");
})

var articleId = (window.location.pathname).match(/\d+/g);
//文章浏览量+1
function increaseViewCount() {
    if ($.cookie("viewId") != articleId || $.cookie("viewId") == null) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/article/addView/"+articleId,
            contentType : "application/x-www-form-urlencoded",
            success: function (data) {
                $(".articleViewCount").html(data);
                $.cookie(
                    "viewId",
                    articleId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                alert("获取数据出错!");
            },
        });
    }
}





//点赞+1
function increaseLikeCount() {
    if ($.cookie("likeId") != articleId || $.cookie("likeId") == null) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/article/addLike/"+articleId,
            contentType : "application/x-www-form-urlencoded",
            success: function (data) {
                $(".count").html(data);
                $.cookie(
                    "likeId",
                    articleId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                //alert("获取数据出错!");
            },
        });
    }
}


//ajax提交评论信息
$("#comment_form").submit(function () {
    $.ajax({
        async: false,
        type: "POST",
        url: '/comment/insert',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: $("#comment_form").serialize(),
        dataType: "text",
        success: function () {
        },
        error: function () {
        }
    })
})

//百度分享
window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "",
        "bdStyle": "0",
        "bdSize": "16"
    },
    "share": {},
   // "image": {"viewList": ["qzone", "tsina", "tqq", "renren", "weixin"], "viewText": "分享到：", "viewSize": "16"},
  //  "selectShare": {"bdContainerClass": null, "bdSelectMiniList": ["qzone", "tsina", "tqq", "renren", "weixin"]}
};
with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];




//申请友情链接
$("#applyLinkForm").submit(function () {
    $.ajax({
        async: false,
        type: "POST",
        url: '/applyLinkSubmit',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: $("#applyLinkForm").serialize(),
        success: function () {
            alert("申请成功，请耐心等待审核！");
        }
    })
})
