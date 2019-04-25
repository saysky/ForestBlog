package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 文章Mapper
 *
 * @author liuyanzhao
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据ID删除
     *
     * @param articleId 文章ID
     * @return 影响函数
     */
    Integer deleteById(Integer articleId);

    /**
     * 添加文章
     *
     * @param article 文章
     * @return 文章
     */
    Integer insert(Article article);

    /**
     * 更新文章
     *
     * @param article 文章
     * @return 影响行数
     */
    Integer update(Article article);

    /**
     * 获得所有的文章
     *
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Article> findAll(HashMap<String, Object> criteria);

    /**
     * 文章归档
     * @return
     */
    List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(@Param(value = "status") Integer status);

    /**
     * 获得留言总数
     *
     * @return 数量
     */
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 文章数量
     */
    Integer countArticleView();

    /**
     * 获得所有文章(文章归档)
     *
     * @return 文章列表
     */
    List<Article> listArticle();

    /**
     * 根据id查询用户信息
     *
     * @param status 状态
     * @param id 文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * 分页操作
     *
     * @param status    状态
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "status") Integer status,
                                    @Param(value = "pageIndex") Integer pageIndex,
                                    @Param(value = "pageSize") Integer pageSize);


    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(@Param(value = "id") Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(@Param(value = "id") Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     *
     * @param limit  查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);



    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 用户的文章数
     *
     * @param id 用户ID
     * @return 数量
     */
    Integer countArticleByUser(@Param(value = "id") Integer id);

    /**
     * 根据分类ID
     *
     * @param categoryId 分类ID
     * @param limit      查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                          @Param("limit") Integer limit);

    /**
     * 根据分类ID
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByLimit(Integer limit);

    /**
     * 批量删除文章
     *
     * @param ids 文章Id列表
     * @return 影响行数
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);
}