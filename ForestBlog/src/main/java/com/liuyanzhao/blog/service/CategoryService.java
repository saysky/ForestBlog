package com.liuyanzhao.blog.service;


import com.liuyanzhao.blog.entity.Category;


import java.util.List;

/**
 * @author 言曌
 * @date 2017/8/24
 */
public interface CategoryService {
    /**
     * 获得分类总数
     *
     * @return
     */
    Integer countCategory();

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);

    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategory();

    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategoryWithCount();

    /**
     * 删除分类
     *
     * @param id ID
     */
    void deleteCategory(Integer id);

    /**
     * 根据id查询分类信息
     *
     * @param id     ID
     * @return 分类
     */
    Category getCategoryById(Integer id);

    /**
     * 添加分类
     *
     * @param category 分类
     */
    void insertCategory(Category category);

    /**
     * 更新分类
     *
     * @param category 分类
     */
    void updateCategory(Category category);

    /**
     * 根据分类名获取分类
     *
     * @param name 名称
     * @return 分类
     */
    Category getCategoryByName(String name);


}
