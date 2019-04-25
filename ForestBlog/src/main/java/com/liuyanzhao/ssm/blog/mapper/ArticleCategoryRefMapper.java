package com.liuyanzhao.ssm.blog.mapper;


import com.liuyanzhao.ssm.blog.entity.ArticleCategoryRef;
import com.liuyanzhao.ssm.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章分类关联表Mapper
 * @author liuyanzhao
 */
@Mapper
public interface ArticleCategoryRefMapper {
    
    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    int insert(ArticleCategoryRef record);

    /**
     * 根据分类ID删除记录
     * @param categoryId 分类ID
     * @return 影响行数
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * 根据文章ID删除记录
     * @param articleId 文章ID
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据分类ID统计文章数
     * @param categoryId 分类ID
     * @return 文章数量
     */
    int countArticleByCategoryId(Integer categoryId);


    /**
     * 根据文章ID查询分类ID
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

    /**
     * 根据分类ID查询文章ID
     *
     * @param categoryId 分类ID
     * @return 文章ID列表
     */
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);

}