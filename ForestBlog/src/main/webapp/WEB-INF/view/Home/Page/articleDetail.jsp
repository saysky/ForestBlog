<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${article.articleTitle}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* 基础样式重置 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', 'Microsoft YaHei', sans-serif;
        }
        
        body {
            background-color: #f8f9fa;
            color: #333;
            line-height: 1.6;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }
        
        a {
            text-decoration: none;
            color: #3498db;
            transition: color 0.3s;
        }
        
        a:hover {
            color: #2980b9;
        }
        
        /* 面包屑导航 */
        .breadcrumb {
            background: white;
            padding: 15px 25px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            margin: 20px 0;
            font-size: 14px;
        }
        
        .breadcrumb a {
            color: #666;
        }
        
        .breadcrumb i {
            margin: 0 10px;
            color: #ccc;
        }
        
        /* 主要内容区域布局 */
        .content-wrapper {
            display: flex;
            gap: 30px;
            margin: 30px 0;
        }
        
        .main-content {
            flex: 1;
        }
        
        .sidebar {
            width: 300px;
        }
        
        /* 文章卡片 */
        .article-card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            overflow: hidden;
            margin-bottom: 30px;
        }
        
        .article-header {
            padding: 30px 30px 20px;
            border-bottom: 1px solid #f0f0f0;
        }
        
        .article-title {
            font-size: 28px;
            font-weight: 700;
            line-height: 1.3;
            margin-bottom: 15px;
            color: #2c3e50;
        }
        
        .article-meta {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            color: #7f8c8d;
            font-size: 14px;
        }
        
        .article-meta i {
            margin-right: 5px;
        }
        
        .article-content {
            padding: 30px;
            line-height: 1.8;
            font-size: 16px;
        }
        
        .article-content h1, 
        .article-content h2, 
        .article-content h3 {
            margin: 25px 0 15px;
            color: #2c3e50;
        }
        
        .article-content p {
            margin-bottom: 20px;
        }
        
        .article-content img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
            margin: 15px 0;
        }
        
        /* 文章底部操作栏 */
        .article-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 30px;
            border-top: 1px solid #f0f0f0;
            background: #fafafa;
        }
        
        .action-buttons {
            display: flex;
            gap: 15px;
        }
        
        .action-btn {
            display: flex;
            align-items: center;
            gap: 8px;
            padding: 8px 15px;
            background: white;
            border: 1px solid #e0e0e0;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.2s;
            font-size: 14px;
        }
        
        .action-btn:hover {
            background: #f5f5f5;
            transform: translateY(-2px);
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        
        .action-btn.like.active {
            background: #ffeaea;
            color: #e74c3c;
            border-color: #e74c3c;
        }
        
        .article-info {
            display: flex;
            gap: 20px;
            color: #7f8c8d;
            font-size: 14px;
        }
        
        /* 分类和标签 */
        .article-taxonomy {
            padding: 20px 30px;
            border-top: 1px solid #f0f0f0;
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }
        
        .taxonomy-item {
            display: inline-block;
            padding: 5px 12px;
            background: #f0f7ff;
            border-radius: 20px;
            font-size: 14px;
            color: #3498db;
        }
        
        .taxonomy-item.tag {
            background: #f0f8f0;
            color: #27ae60;
        }
        
        /* 版权声明 */
        .copyright-notice {
            background: #fff9e6;
            padding: 20px 30px;
            border-radius: 8px;
            margin: 30px 0;
            border-left: 4px solid #f1c40f;
        }
        
        /* 相关文章和猜你喜欢 */
        .related-section {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 25px;
            margin: 30px 0;
        }
        
        .widget {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            padding: 25px;
        }
        
        .widget-title {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px solid #f0f0f0;
            color: #2c3e50;
        }
        
        .widget-list {
            list-style: none;
        }
        
        .widget-list li {
            margin-bottom: 12px;
            padding-bottom: 12px;
            border-bottom: 1px dashed #f0f0f0;
        }
        
        .widget-list li:last-child {
            margin-bottom: 0;
            padding-bottom: 0;
            border-bottom: none;
        }
        
        /* 上一篇下一篇导航 */
        .article-navigation {
            display: flex;
            justify-content: space-between;
            margin: 30px 0;
        }
        
        .nav-item {
            flex: 1;
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            transition: all 0.3s;
        }
        
        .nav-item:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .nav-item.prev {
            margin-right: 15px;
            text-align: left;
        }
        
        .nav-item.next {
            margin-left: 15px;
            text-align: right;
        }
        
        .nav-label {
            font-size: 14px;
            color: #7f8c8d;
            margin-bottom: 8px;
        }
        
        .nav-title {
            font-weight: 600;
            color: #2c3e50;
        }
        
        /* 评论区域 */
        .comments-section {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            padding: 30px;
            margin: 30px 0;
        }
        
        .comments-title {
            font-size: 22px;
            font-weight: 600;
            margin-bottom: 25px;
            color: #2c3e50;
        }
        
        .comment-form {
            margin-bottom: 40px;
        }
        
        .comment-textarea {
            width: 100%;
            padding: 15px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            resize: vertical;
            min-height: 120px;
            font-size: 16px;
            margin-bottom: 15px;
            transition: border 0.3s;
        }
        
        .comment-textarea:focus {
            border-color: #3498db;
            outline: none;
        }
        
        .comment-submit {
            background: #3498db;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: background 0.3s;
        }
        
        .comment-submit:hover {
            background: #2980b9;
        }
        
        .comment-list {
            list-style: none;
        }
        
        .comment {
            padding: 20px 0;
            border-bottom: 1px solid #f0f0f0;
        }
        
        .comment:last-child {
            border-bottom: none;
        }
        
        .comment-header {
            display: flex;
            align-items: center;
            margin-bottom: 12px;
        }
        
        .comment-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 15px;
        }
        
        .comment-author {
            font-weight: 600;
            color: #2c3e50;
        }
        
        .comment-meta {
            font-size: 14px;
            color: #7f8c8d;
            margin-left: auto;
        }
        
        .comment-content {
            color: #444;
            line-height: 1.6;
        }
        
        .comment-reply {
            margin-top: 10px;
            font-size: 14px;
            color: #3498db;
            cursor: pointer;
        }
        
        .comment-reply:hover {
            text-decoration: underline;
        }
        
        /* 侧边栏 */
        .sidebar-widget {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            padding: 25px;
            margin-bottom: 30px;
        }
        
        .sidebar-title {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px solid #f0f0f0;
            color: #2c3e50;
        }
        
        /* 响应式设计 */
        @media (max-width: 992px) {
            .content-wrapper {
                flex-direction: column;
            }
            
            .sidebar {
                width: 100%;
            }
            
            .related-section {
                grid-template-columns: 1fr;
            }
        }
        
        @media (max-width: 768px) {
            .article-actions {
                flex-direction: column;
                gap: 15px;
                align-items: flex-start;
            }
            
            .article-navigation {
                flex-direction: column;
                gap: 15px;
            }
            
            .nav-item.prev, 
            .nav-item.next {
                margin: 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 面包屑导航 -->
        <nav class="breadcrumb">
            <a class="crumbs" href="/">
                <i class="fa fa-home"></i>首页
            </a>
            <c:choose>
                <c:when test="${article.categoryList != null && article.categoryList.size() > 0}">
                    <c:forEach items="${article.categoryList}" var="c">
                        <i class="fa fa-angle-right"></i>
                        <a href="/category/${c.categoryId}">
                            ${c.categoryName}
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <i class="fa fa-angle-right"></i>
                    <a>未分类</a>
                </c:otherwise>
            </c:choose>
            <i class="fa fa-angle-right"></i>
            正文
        </nav>
        
        <!-- 主要内容区域 -->
        <div class="content-wrapper">
            <div class="main-content">
                <!-- 文章内容 -->
                <article class="article-card">
                    <div class="article-header">
                        <h1 class="article-title">${article.articleTitle}</h1>
                        <div class="article-meta">
                            <span><i class="far fa-user"></i> ${article.user.userNickname}</span>
                            <span><i class="far fa-calendar"></i> <fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy-MM-dd"/></span>
                            <span><i class="far fa-eye"></i> ${article.articleViewCount} 次阅读</span>
                            <span><i class="far fa-comments"></i> ${commentList.size()} 条评论</span>
                        </div>
                    </div>
                    
                    <div class="article-content">
                        ${article.articleContent}
                    </div>
                    
                    <div class="article-actions">
                        <div class="action-buttons">
                            <button class="action-btn like" onclick="increaseLikeCount()">
                                <i class="far fa-thumbs-up"></i>
                                <span class="count" id="count-${article.articleId}">${article.articleLikeCount}</span> 赞
                            </button>
                            <button class="action-btn share" onclick="PaymentUtils.show()">
                                <i class="fas fa-share-alt"></i> 分享
                            </button>
                            <c:if test="${sessionScope.user!=null && (sessionScope.user.userId == article.articleUserId || sessionScope.user.userRole == 'admin')}">
                                <a class="action-btn" href="/admin/article/edit/${article.articleId}" target="_blank">
                                    <i class="far fa-edit"></i> 编辑
                                </a>
                            </c:if>
                        </div>
                        <div class="article-info">
                            <span>本文由 ${article.user.userNickname} 创作</span>
                        </div>
                    </div>
                    
                    <!-- 分类和标签 -->
                    <div class="article-taxonomy">
                        <div class="taxonomy-item">
                            <i class="fas fa-folder"></i> 
                            <c:forEach items="${article.categoryList}" var="c">
                                <a href="/category/${c.categoryId}">${c.categoryName}</a>
                            </c:forEach>
                        </div>
                        
                        <c:forEach items="${article.tagList}" var="t">
                            <div class="taxonomy-item tag">
                                <i class="fas fa-tag"></i> 
                                <a href="/tag/${t.tagId}">${t.tagName}</a>
                            </div>
                        </c:forEach>
                    </div>
                </article>
                
                <!-- 版权声明 -->
                <div class="copyright-notice">
                    <p><strong>版权声明：</strong>本站原创文章，于<fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy-MM-dd"/>，由<strong>${article.user.userNickname}</strong>发表。</p>
                    <p><strong>转载请注明：</strong><a href="/article/${article.articleId}" rel="bookmark" title="本文固定链接 /article/${article.articleId}">${article.articleTitle} | ${options.optionSiteTitle}</a></p>
                </div>
                
                <!-- 相关文章和猜你喜欢 -->
                <div class="related-section">
                    <div class="widget">
                        <h3 class="widget-title">相关文章</h3>
                        <ul class="widget-list">
                            <c:forEach items="${similarArticleList}" var="s">
                                <li><a href="/article/${s.articleId}">${s.articleTitle}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    
                    <div class="widget">
                        <h3 class="widget-title">猜你喜欢</h3>
                        <ul class="widget-list">
                            <c:forEach items="${mostViewArticleList}" var="m">
                                <li><a href="/article/${m.articleId}">${m.articleTitle}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                
                <!-- 上一篇下一篇导航 -->
                <div class="article-navigation">
                    <c:choose>
                        <c:when test="${preArticle!=null}">
                            <a href="/article/${preArticle.articleId}" class="nav-item prev">
                                <div class="nav-label">上一篇</div>
                                <div class="nav-title">${preArticle.articleTitle}</div>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <div class="nav-item prev">
                                <div class="nav-label">上一篇</div>
                                <div class="nav-title">已是第一篇文章</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                        <c:when test="${afterArticle!=null}">
                            <a href="/article/${afterArticle.articleId}" class="nav-item next">
                                <div class="nav-label">下一篇</div>
                                <div class="nav-title">${afterArticle.articleTitle}</div>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <div class="nav-item next">
                                <div class="nav-label">下一篇</div>
                                <div class="nav-title">已是最后文章</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                
                <!-- 评论区域 -->
                <div class="comments-section">
                    <h3 class="comments-title">评论 (${commentList.size()})</h3>
                    
                    <!-- 评论表单 -->
                    <div class="comment-form">
                        <c:if test="${sessionScope.user == null}">
                            <p style="color: #e74c3c; margin-bottom: 15px;">您未登录，登录后才能评论，<a href="/login" target="_blank">前往登录</a></p>
                        </c:if>
                        
                        <c:if test="${sessionScope.user!=null}">
                            <div style="display: flex; align-items: center; margin-bottom: 15px;">
                                <img src="${sessionScope.user.userAvatar}" class="comment-avatar" alt="用户头像">
                                <span>登录者：${sessionScope.user.userNickname}</span>
                                <a href="javascript:void(0)" onclick="logout()" style="margin-left: auto; font-size: 14px;">登出</a>
                            </div>
                        </c:if>
                        
                        <form id="comment_form" method="post">
                            <textarea id="comment" name="commentContent" class="comment-textarea" placeholder="写下您的评论..." required></textarea>
                            <input type="hidden" name="commentArticleId" value="${article.articleId}">
                            <input type="hidden" name="commentPid" id="comment_pid" value="0">
                            <button type="submit" class="comment-submit">提交评论</button>
                        </form>
                    </div>
                    
                    <!-- 评论列表 -->
                    <ul class="comment-list">
                        <c:set var="floor" value="0"/>
                        <c:forEach items="${commentList}" var="c">
                            <c:if test="${c.commentPid == 0}">
                                <c:set var="floor" value="${floor + 1}"/>
                                <li class="comment">
                                    <div class="comment-header">
                                        <img src="${c.commentAuthorAvatar}" class="comment-avatar" alt="评论者头像">
                                        <div class="comment-author">
                                            ${c.commentAuthorName}
                                            <c:if test="${c.commentRole == 1}">
                                                <span style="color: #e74c3c; font-size: 12px; background: #ffeaea; padding: 2px 6px; border-radius: 4px; margin-left: 5px;">博主</span>
                                            </c:if>
                                        </div>
                                        <div class="comment-meta">
                                            <fmt:formatDate value="${c.commentCreateTime}" pattern="yyyy-MM-dd HH:mm"/>
                                            <span style="margin-left: 10px;">${floor}楼</span>
                                        </div>
                                    </div>
                                    <div class="comment-content">
                                        ${c.commentContent}
                                    </div>
                                    <div class="comment-reply" onclick="replyComment(${c.commentId}, '${c.commentAuthorName}')">回复</div>
                                    
                                    <!-- 子评论 -->
                                    <c:set var="floor2" value="0"/>
                                    <c:forEach items="${commentList}" var="c2">
                                        <c:if test="${c.commentId == c2.commentPid}">
                                            <c:set var="floor2" value="${floor2+1}"/>
                                            <div class="comment" style="margin-left: 40px; border-left: 3px solid #f0f0f0; padding-left: 20px;">
                                                <div class="comment-header">
                                                    <img src="${c2.commentAuthorAvatar}" class="comment-avatar" alt="评论者头像">
                                                    <div class="comment-author">
                                                        ${c2.commentAuthorName}
                                                        <c:if test="${c2.commentRole==1}">
                                                            <span style="color: #e74c3c; font-size: 12px; background: #ffeaea; padding: 2px 6px; border-radius: 4px; margin-left: 5px;">博主</span>
                                                        </c:if>
                                                    </div>
                                                    <div class="comment-meta">
                                                        <fmt:formatDate value="${c2.commentCreateTime}" pattern="yyyy-MM-dd HH:mm"/>
                                                        <span style="margin-left: 10px;">${floor2}层</span>
                                                    </div>
                                                </div>
                                                <div class="comment-content">
                                                    <span style="color: #3498db;">@${c2.commentPname}</span> ${c2.commentContent}
                                                </div>
                                                <div class="comment-reply" onclick="replyComment(${c.commentId}, '${c2.commentAuthorName}')">回复</div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            
            <!-- 侧边栏 -->
            <div class="sidebar">
                <!-- 这里可以放置侧边栏内容 -->
                <div class="sidebar-widget">
                    <h3 class="sidebar-title">关于博主</h3>
                    <p>这里是关于博主的介绍信息...</p>
                </div>
                
                <div class="sidebar-widget">
                    <h3 class="sidebar-title">热门文章</h3>
                    <ul class="widget-list">
                        <li><a href="#">热门文章示例一</a></li>
                        <li><a href="#">热门文章示例二</a></li>
                        <li><a href="#">热门文章示例三</a></li>
                        <li><a href="#">热门文章示例四</a></li>
                    </ul>
                </div>
                
                <div class="sidebar-widget">
                    <h3 class="sidebar-title">标签云</h3>
                    <div style="display: flex; flex-wrap: wrap; gap: 8px;">
                        <a href="#" style="background: #f0f7ff; color: #3498db; padding: 5px 10px; border-radius: 15px; font-size: 14px;">Java</a>
                        <a href="#" style="background: #f0f8f0; color: #27ae60; padding: 5px 10px; border-radius: 15px; font-size: 14px;">Spring</a>
                        <a href="#" style="background: #fff0f0; color: #e74c3c; padding: 5px 10px; border-radius: 15px; font-size: 14px;">数据库</a>
                        <a href="#" style="background: #f5f0ff; color: #9b59b6; padding: 5px 10px; border-radius: 15px; font-size: 14px;">前端</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        // 点赞功能
        function increaseLikeCount() {
            const likeBtn = document.querySelector('.action-btn.like');
            const countElement = document.querySelector('.count');
            let count = parseInt(countElement.textContent);
            
            if (!likeBtn.classList.contains('active')) {
                count++;
                countElement.textContent = count;
                likeBtn.classList.add('active');
                likeBtn.innerHTML = '<i class="fas fa-thumbs-up"></i> <span class="count">' + count + '</span> 赞';
                
               
            }
        }
        
        // 回复评论功能
        function replyComment(commentId, authorName) {
            const commentTextarea = document.getElementById('comment');
            commentTextarea.focus();
            commentTextarea.value = '@' + authorName + ' ';
            document.getElementById('comment_pid').value = commentId;
            
            // 滚动到评论框
            commentTextarea.scrollIntoView({ behavior: 'smooth' });
        }
        
        // 登出功能
        function logout() {
            if (confirm('确定要退出登录吗？')) {
                window.location.href = '/logout';
            }
        }
        
        // 增加阅读量（页面加载时执行）
        window.addEventListener('load', function() {
           
        });
    </script>
</body>
</html>