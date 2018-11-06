package com.liuyanzhao.blog.mapper.custom;

import com.liuyanzhao.blog.entity.Comment;
import com.liuyanzhao.blog.entity.custom.CommentCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/10.
 */
public interface CommentMapperCustom {

	//根据文章id获取评论列表
	public List<CommentCustom> listCommentByArticleId(@Param(value="status")  Integer status,@Param(value="id") Integer id);
	
	//获得评论列表分页
	public List<CommentCustom> listCommentByPage(@Param(value="status") Integer status,@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize) throws Exception;

	//获得评论列表
	public List<CommentCustom> listComment(@Param(value="status") Integer status) throws Exception;

	//统计评论数
	public Integer countComment(@Param(value="status") Integer status) throws Exception;

	//获得最近评论
	public List<CommentCustom> listRecentComment(@Param(value = "limit") Integer limit) throws Exception;

	//获得评论的子评论
	public List<Comment> listChildComment(@Param(value = "id") Integer id) throws Exception;

}
