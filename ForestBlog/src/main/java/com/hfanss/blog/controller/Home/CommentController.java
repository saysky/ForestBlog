package com.hfanss.blog.controller.Home;

import com.hfanss.blog.entity.Article;
import com.hfanss.blog.entity.Comment;
import com.hfanss.blog.entity.custom.CommentCustom;
import com.hfanss.blog.service.ArticleService;
import com.hfanss.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 言曌 on 2017/9/10.
 */

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private ArticleService articleService;
	
	//添加评论
	@RequestMapping(value = "/comment/insert",method = {RequestMethod.POST})
	@ResponseBody
	public void insertComment(HttpServletRequest request,Comment comment) throws Exception {
		//添加评论
		comment.setCommentCreateTime(new Date());
		if (comment.getCommentAuthorEmail().equals("hfans@foxmail.com")&& comment.getCommentAuthorName().equals("幻凡ss")) {
			comment.setCommentRole(1);
		}
		commentService.insertComment(request,comment);
		//更新文章的评论数
		if (0!=comment.getCommentArticleId()) {
			Article article = articleService.getArticleById(null,comment.getCommentArticleId());
			articleService.updateCommentCount(article.getArticleId());
		}
	}

}
