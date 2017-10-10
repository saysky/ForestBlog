package com.liuyanzhao.blog.service;


import com.liuyanzhao.blog.entity.Category;
import com.liuyanzhao.blog.entity.custom.ArticleListVo;
import com.liuyanzhao.blog.entity.custom.CategoryCustom;


import java.util.List;

/**
 * Created by 言曌 on 2017/8/24.
 */
public interface CategoryService {
	//获得分类总数
	public Integer countCategory(Integer status) throws Exception;
	
	//获得分类列表
	public List<CategoryCustom> listCategory(Integer status) throws Exception;

	//获得带有该分类的文章列表
	public  List<ArticleListVo> listArticleWithCategoryByPage(Integer status,Integer pageNow,Integer pageSize,Integer cateId) throws Exception;

	//获得某个分类信息
	public CategoryCustom getCategory(Integer status,Integer id) throws Exception;

	//删除分类
	public void deleteCategory(Integer id) throws Exception;

	//根据id查询分类信息
	public CategoryCustom getCategoryById(Integer status,Integer id) throws Exception;

	//添加分类
	public void insertCategory(Category category) throws Exception;

	//更新分类
	public void updateCategory(Category category) throws Exception;

	//根据分类名获取分类
	public Category getCategoryByName(String name) throws Exception;


}
