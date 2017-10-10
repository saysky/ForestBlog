package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.Comment;
import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import com.liuyanzhao.blog.entity.custom.CommentListVo;
import com.liuyanzhao.blog.mapper.ArticleMapper;
import com.liuyanzhao.blog.mapper.CommentMapper;
import com.liuyanzhao.blog.mapper.custom.ArticleMapperCustom;
import com.liuyanzhao.blog.mapper.custom.CommentMapperCustom;
import com.liuyanzhao.blog.entity.custom.CommentCustom;
import com.liuyanzhao.blog.service.CommentService;
import com.liuyanzhao.blog.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuyanzhao.blog.util.Functions;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 言曌 on 2017/9/10.
 */
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapperCustom commentMapperCustom;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private ArticleMapperCustom articleMapperCustom;

	@Override
	public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
	    String ip = Functions.getIpAddr(request);
	    comment.setCommentIp(ip);
		commentMapper.insertSelective(comment);
	}
	
	@Override
	public List<CommentCustom> listCommentByArticleId(Integer status,Integer articleId) {
		List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByArticleId(status,articleId);
		return commentCustomList;
	}

	@Override
	public CommentCustom getCommentById(Integer id) throws Exception {
		CommentCustom commentCustom = new CommentCustom();
		Comment comment = commentMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(comment,commentCustom);
		return commentCustom;
	}

	@Override
	public List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception {
		List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

		List<CommentCustom> commentCustomList = new ArrayList<CommentCustom>();


		Page page = null;
		int totalCount = commentMapperCustom.countComment(status);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			commentCustomList = commentMapperCustom.listCommentByPage(status,page.getStartPos(),pageSize);
		} else {
			page = new Page(totalCount, 1,pageSize);
			commentCustomList = commentMapperCustom.listCommentByPage(status,page.getStartPos(), pageSize);
		}


		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//获得文章信息
			Integer articleId = commentCustomList.get(i).getCommentArticleId();
			ArticleCustom articleCustom = articleMapperCustom.getArticleById(status,articleId);
			commentListVo.setArticleCustom(articleCustom);

			//评论信息
            CommentCustom commentCustom = commentCustomList.get(i);
			//评论者Gravatar头像
            String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
            commentCustom.setCommentAuthorAvatar(avatar);
            commentListVo.setCommentCustom(commentCustomList.get(i));

			commentListVoList.add(commentListVo);
		}

		if(commentListVoList.size()>0) {
			//4、将Page信息存储在第一个元素中
			commentListVoList.get(0).setPage(page);
		}
		return commentListVoList;
	}

	@Override
	public List<CommentListVo> listCommentVo(Integer status) throws Exception {
		List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

		List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);

		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//获得文章信息
			Integer articleId = commentCustomList.get(i).getCommentArticleId();
			ArticleCustom articleCustom = articleMapperCustom.getArticleById(status,articleId);
			commentListVo.setArticleCustom(articleCustom);

			//评论信息
			CommentCustom commentCustom = commentCustomList.get(i);
			//评论者Gravatar头像
			String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
			commentCustom.setCommentAuthorAvatar(avatar);
			commentListVo.setCommentCustom(commentCustomList.get(i));

			commentListVoList.add(commentListVo);
		}

		return commentListVoList;
	}

	@Override
	public List<CommentCustom> listComment(Integer status) throws Exception {
		List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);
		return commentCustomList;
	}

	@Override
	public void deleteComment(Integer id) throws Exception {
		commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		commentMapper.updateByPrimaryKeySelective(comment);
	}

	@Override
	public Integer countComment(Integer status) throws Exception {
		Integer commentCount = commentMapperCustom.countComment(status);
		return commentCount;
	}

	@Override
	public List<CommentListVo> listRecentComment(Integer limit) throws Exception {
		List<CommentListVo> recentCommentList = new ArrayList<CommentListVo>();
		List<CommentCustom> commentCustomList = commentMapperCustom.listRecentComment(limit);
		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//给每个评论用户添加头像
			String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
			CommentCustom commentCustom = commentCustomList.get(i);
			commentCustom.setCommentAuthorAvatar(avatar);
			commentListVo.setCommentCustom(commentCustom);
			//找到评论对应的文章信息
			ArticleCustom articleCustom = articleMapperCustom.getArticleById(1,commentCustom.getCommentArticleId());
			commentListVo.setArticleCustom(articleCustom);

			recentCommentList.add(commentListVo);
		}

		return recentCommentList;
	}

	@Override
	public List<Comment> listChildComment(Integer id) throws Exception {
		List<Comment> childCommentList = commentMapperCustom.listChildComment(id);
		return childCommentList;
	}

}
