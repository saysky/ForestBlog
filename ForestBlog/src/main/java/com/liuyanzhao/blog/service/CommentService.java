package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Comment;
import com.liuyanzhao.blog.entity.custom.CommentCustom;
import com.liuyanzhao.blog.entity.custom.CommentListVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 言曌 on 2017/9/10.
 */
@Service
public interface CommentService {
	//添加评论
	public void insertComment(HttpServletRequest request, Comment comment) throws Exception;
	
	//根据文章id获取评论列表
	public List<CommentCustom> listCommentByArticleId(Integer status,Integer articleId);

	//根据id获取评论
	public CommentCustom getCommentById(Integer id) throws Exception;


	//获取所有评论列表
	public List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception;

	//获得评论列表
	public List<CommentListVo> listCommentVo(Integer status) throws Exception;

	//获得评论列表
	public List<CommentCustom> listComment(Integer status) throws Exception;

	//删除评论
	public void deleteComment(Integer id) throws Exception;

	//修改评论
	public void updateComment(Comment comment) throws Exception;

	//统计评论数
	public Integer countComment(Integer status) throws Exception;

	//获得最近评论
	public List<CommentListVo> listRecentComment(Integer limit) throws Exception;

	//获得评论的子评论
	public List<Comment> listChildComment(Integer id) throws Exception;


}
