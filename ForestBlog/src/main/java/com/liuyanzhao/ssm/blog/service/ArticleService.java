package com.liuyanzhao.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.entity.Article;

import java.util.HashMap;
import java.util.List;

/**
 * 文章Service
 *
 * @author 言曌
 * @date 2017/8/24
 */
public interface ArticleService {

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    /**
     * 获取评论总数
     *
     * @return 数量
     */
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    Integer countArticleView();

    /**
     * 统计有这个分类的文章数
     *
     * @param categoryId 分类ID
     * @return 数量
     */
    Integer countArticleByCategoryId(Integer categoryId);

    /**
     * 统计有这个表情的文章数
     *
     * @param tagId 标签ID
     * @return 数量
     */
    Integer countArticleByTagId(Integer tagId);


    /**
     * 获得所有文章不分页
     *
     * @param criteria 查询条件
     * @return 列表
     */
    List<Article> listArticle(HashMap<String, Object> criteria);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRecentArticle(Integer limit);


    /**
     * 修改文章详细信息
     *
     * @param article 文章
     */
    void updateArticleDetail(Article article);

    /**
     * 修改文章简单信息
     *
     * @param article 文章
     */
    void updateArticle(Article article);

    /**
     * 批量删除文章
     *
     * @param ids 文章ID
     */
    void deleteArticleBatch(List<Integer> ids);

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    void deleteArticle(Integer id);

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);

    /**
     * 文章详情页面显示
     *
     * @param status 状态
     * @param id     文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(Integer status, Integer id);

    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得评论数较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertArticle(Article article);


    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(Integer articleId);

    /**
     * 获得最后更新记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 获得相关文章
     *
     * @param cateId 分类ID
     * @param limit  查询数量
     * @return 列表
     */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);

    /**
     * 获得相关文章
     *
     * @param cateIds 分类ID集合
     * @param limit   数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);


    /**
     * 根据文章ID获得分类ID列表
     *
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 获得所有的文章
     *
     * @return 列表
     */
    List<Article> listAllNotWithContent();
}
