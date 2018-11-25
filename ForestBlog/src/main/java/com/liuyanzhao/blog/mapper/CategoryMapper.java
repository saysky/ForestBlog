package com.liuyanzhao.blog.mapper;

import com.liuyanzhao.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface CategoryMapper {

    /**
     * 根据ID删除
     * 
     * @param categoryId 分类ID
     * @return 影响行数
     */
    int deleteById(Integer categoryId);

    /**
     * 添加
     * 
     * @param category 分类
     * @return 影响行数
     */
    int insert(Category category);


    /**
     * 根据ID查询
     * 
     * @param categoryId 分类
     * @return 分类
     */
    Category selectByPrimaryKey(Integer categoryId);


    /**
     * 更新
     * 
     * @param category 分类
     * @return 影响行数
     */
    int update(Category category);


    /**
     * 查询分类总数
     * 
     * @return 数量
     */
    Integer countCategory();

    /**
     * 获得分类列表
     * 
     * @return 列表
     */
    List<Category> listCategory();

    /**
     * 根据分类id获得分类信息
     * 
     * @param id ID
     * @return 分类
     */
    Category getCategoryById(@Param(value = "id") Integer id);


    /**
     * 删除分类
     * 
     * @param id 文章ID
     */
    void deleteCategory(Integer id);

    /**
     * 根据父分类找子分类
     * 
     * @param id 分类ID
     * @return 列表
     */
    List<Category> findChildCategory(@Param(value = "id") Integer id);

    /**
     * 根据标签名获取标签
     * 
     * @param name 名称
     * @return 分类
     */
    Category getCategoryByName(String name);
}