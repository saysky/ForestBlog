package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 根据ID删除
     *
     * @param commentId 评论ID
     * @return 影响行数
     */
    int deleteById(Integer commentId);

    /**
     * 添加
     *
     * @param comment 评论
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 根据ID查询
     *
     * @param commentId 评论ID
     * @return 评论
     */
    Comment getCommentById(Integer commentId);

    /**
     * 更新
     *
     * @param comment 评论
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 根据文章id获取评论列表
     *
     * @param id ID
     * @return 列表
     */
    List<Comment> listCommentByArticleId(@Param(value = "id") Integer id);


    /**
     * 获得评论列表
     *
     * @return 列表
     */
    List<Comment> listComment(HashMap<String, Object> criteria);


    /**
     * 获得某个用户收到的评论
     *
     * @return 列表
     */
    List<Comment> getReceiveComment(List<Integer> articleIds);


    /**
     * 统计评论数
     *
     * @return 数量
     */
    Integer countComment();

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(@Param(value = "userId") Integer userId,
                                    @Param(value = "limit") Integer limit);

    /**
     * 获得评论的子评论
     *
     * @param id 评论ID
     * @return 列表
     */
    List<Comment> listChildComment(@Param(value = "id") Integer id);


    /**
     * 根据用户ID删除
     *
     * @param userId 用户ID
     * @return 影响函数
     */
    Integer deleteByUserId(Integer userId);


    /**
     * 根据文章ID删除
     *
     * @param articleId 文章ID
     * @return 影响函数
     */
    Integer deleteByArticleId(Integer articleId);
}