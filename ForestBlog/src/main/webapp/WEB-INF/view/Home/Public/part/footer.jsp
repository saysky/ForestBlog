<%--
    博客页脚部分
    包括：页脚部分
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %> --%>
<%--页脚 start--%>
<footer id="colophon" class="site-footer" role="contentinfo">
	<%-- <div id="footer-widget-box" class="site-footer wow fadeInUp" data-wow-delay="0.3s">
		<div class="footer-widget">
			<aside id="text-22" class="widget widget_text wow fadeInUp" data-wow-delay="0.3s">
				<h3 class="widget-title">
					<span class="s-icon"></span>勤有功，戏无益
				</h3>
				<div class="textwidget">
					<ul class="menu">
						<li><a title="" target="_blank" href="http://liuyanzhao.com/links.html"> <i class="fa-link fa"></i> <span class="font-text">友链链接</span>
						</a></li>
						<li><a title="" target="_blank" href="http://liuyanzhao.com/message.html"> <i class="fa-commenting fa"></i> <span class="font-text">留言板</span>
						</a></li>
						<li><a target="_blank" href="http://liuyanzhao.com/sitemap.html"> <i class="fa-sitemap fa"></i> <span class="font-text">站点地图</span>
						</a></li>
						<li><a target="_blank" rel="nofollow" href="http://liuyanzhao.com/article-file.html"> <i class="fa-list-alt fa"></i> <span class="font-text">文章归档</span>
						</a></li>
						<li><a title="" target="_blank" href="https://liuyanzhao.com/tags.html"> <i class="fa fa-tag"></i> <span class="font-text">标签库</span>
						</a></li>
						<li><a title="" target="_blank" href="https://liuyanzhao.com/recent-comments.html"> <i class="fa-comment fa"></i> <span class="font-text">最新评论</span>
						</a></li>
						<li><a title="" target="_blank" href="https://liuyanzhao.com/code-highlight.html"> <i class="fa-code fa"></i> <span class="font-text">代码高亮</span>
						</a></li>
						<li><a title="" target="_blank" href="https://liuyanzhao.com/category/other-cat/vedio"> <i class="fa-video-camera fa"></i> <span
								class="font-random">&nbsp;视频收藏</span>
						</a></li>
						<li><a title="" target="_blank" href="https://liuyanzhao.com/readers.html"> <i class="fa-newspaper-o fa"></i> <span class="font-text">读者排行</span>
						</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</aside>
			 <aside class="widget about">
			        <div id="feed_widget">
			            <div class="feed-about">
			                <ul>
			                    <li class="weixin">
			                        <a title="微信" id="weixin_btn" rel="external nofollow">
			                            <i class="fa fa-weixin"> </i>
			                            <div id="weixin_code" class="hide" >
			                                <img src="${options.optionAboutsiteWechat}" alt="">
			                            </div>
			                        </a>
			                    </li>
			                    <li class="tqq">
			                        <a target="blank" rel="external nofollow"
			                           href="http://wpa.qq.com/msgrd?V=3&amp;uin=${options.optionAboutsiteQq}&amp;Site=QQ&amp;Menu=yes"
			                           title="QQ在线">
			                            <i class="fa fa-qq"></i>
			                        </a>
			                    </li>
			                    <li class="tsina">
			                        <a title=""
			                           href="http://weibo.com/${options.optionAboutsiteWeibo}"
			                           target="_blank" rel="external nofollow">
			                            <i class="fa fa-weibo"></i>
			                        </a>
			                    </li>
			                    <li class="feed">
			                        <a title="" href="https://github.com/${options.optionAboutsiteGithub}" target="_blank"
			                           rel="external nofollow">
			                            <i class="fa fa-github"></i>
			                        </a>
			                    </li>
			                </ul>
			            </div>
			        </div>
		        <div class="clear"></div>
		    </aside>
			<aside id="text-23" class="widget widget_text wow fadeInUp" data-wow-delay="0.3s">
				<h3 class="widget-title">
					<span class="s-icon"></span>关于博主
				</h3>
				<div class="textwidget">
					<p style="text-indent: 2em;">博主目前是一名Java工程师，喜欢写代码，乐意追求新的技术。对前后端、运维都很有兴趣，需要做网站和系统的朋友可以找我哦。未来将会向大数据和人工智能靠拢，去实现自己的梦想，做自己喜欢的软件。</p>
					<p style="text-indent: 2em;">QQ交流群(交流或求助)：590480292</p>
				</div>
				<div class="clear"></div>
			</aside>
			<div class="clear"></div>
		</div>
	</div> --%>
	<%--底部工具栏  菜单 start--%>
	<nav id="top-header">
		<div class="top-nav">
			<div class="user-login">
				<a href="http://www.miibeian.gov.cn/" target="_Blank">豫ICP备18024944号</a>
			</div>
			<div class="menu-topmenu-container">
				<ul id="menu-topmenu" class="top-menu">
					<c:forEach items="${menuCustomList}" var="m">
						<li class="menu-item"><c:if test="${m.menuLevel==1}">
								<a href="${m.menuUrl}"> <i class="${m.menuIcon}"></i> <span class="font-text">${m.menuName}&nbsp;</span>&nbsp;
								</a>
							</c:if></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</nav>
	<!-- #top-header -->
	<%--底部工具栏 end--%>
	<div class="site-info">
		<p style="text-align: center;">
			Copyright © 2018 
			<a href="/" target="_blank" rel="noopener noreferrer">${options.optionSiteTitle}</a> 
			All rights reserved <br>
			<a target="_blank" href="/map">
				<span class="font-text">站点地图</span>
			</a>&nbsp;|&nbsp; <a target="_blank" href="#"> <span class="font-text">百度统计</span>
			</a>
		</p>
	</div>
	<!-- .site-info -->
</footer>
<!-- .site-footer -->
<%--页脚 end--%>

