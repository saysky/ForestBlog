package com.liuyanzhao.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 言曌
 * @date 2017/9/10
 */
@Service
public interface CommentService {

    /**
     * 添加评论
     *
     * @param comment 评论
     */
    void insertComment(Comment comment);

    /**
     * 根据文章id获取评论列表
     *
     * @param articleId 文章ID
     * @return 列表
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 根据id获取评论
     *
     * @param id
     * @return
     */
    Comment getCommentById(Integer id);


    /**
     * 获取所有评论列表
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示数量
     * @return 列表
     */
    PageInfo<Comment> listCommentByPage(
            Integer pageIndex,
            Integer pageSize);

    /**
     * 获得评论列表
     *
     * @return 列表
     */
    List<Comment> listComment();


    /**
     * 删除评论
     *
     * @param id ID
     */
    void deleteComment(Integer id);

    /**
     * 修改评论
     *
     * @param comment 评论
     */
    void updateComment(Comment comment);

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
    List<Comment> listRecentComment(Integer limit);

    /**
     * 获得评论的子评论
     *
     * @param id 评论ID
     * @return 列表
     */
    List<Comment> listChildComment(Integer id);


}
