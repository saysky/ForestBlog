package com.liuyanzhao.blog.mapper.custom;



import com.liuyanzhao.blog.entity.Category;
import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import com.liuyanzhao.blog.entity.custom.CategoryCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 言曌 on 2017/8/24.
 */

@Repository
public interface CategoryMapperCustom {
	//查询分类总数
	public Integer countCategory(@Param(value="status") Integer status) throws Exception;
	
	//获得分类列表
	public List<CategoryCustom> listCategory(@Param(value="status") Integer status) throws Exception;
	
	//根据分类id获得分类信息
	public CategoryCustom getCategoryById(@Param(value="status") Integer status,@Param(value="id")Integer id) throws Exception;

	//获得含有该分类的文章列表
	public List<ArticleCustom> listArticleWithCategoryByPage(
			@Param(value="status") Integer status,
			@Param(value = "cateId") Integer cateId
			,@Param(value="startPos") Integer startPos
			,@Param(value="pageSize") Integer pageSize
	) throws Exception;

	//删除分类
	public void deleteCategory(Integer id) throws Exception;

	//根据父分类找子分类
	public List<CategoryCustom> findChildCategory(@Param(value="status") Integer status,@Param(value="id")Integer id) throws Exception;

	//根据标签名获取标签
	public Category  getCategoryByName(String name) throws Exception;

}



