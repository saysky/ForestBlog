package com.liuyanzhao.blog.mapper.custom;

import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 言曌 on 2017/8/24.
 */

@Repository
public interface ArticleMapperCustom {
	//获取文章总数
	public Integer countArticle(@Param(value="status") Integer status) throws Exception;
	
	//获得留言总数
	public Integer countArticleComment(@Param(value="status")Integer status) throws Exception;
	
	//获得浏览量总数
	public Integer countArticleView(@Param(value="status")Integer status) throws Exception;
	
	//获得所有文章(文章归档)
	public List<ArticleCustom> listArticle(@Param(value="status")Integer status) throws Exception;
	
	//根据id查询用户信息
	public ArticleCustom getArticleById(@Param(value="status")Integer status,@Param(value="id")Integer id) throws Exception;
	
	//分页操作
	public List<ArticleCustom> listArticleByPage(@Param(value="status") Integer status,@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize) throws Exception;
	
	//文章结果查询结果的数量
	public Integer getSearchResultCount(@Param(value="status") Integer status,@Param(value="query")String query) throws Exception;
	
	//查询文章分页操作
	public List<ArticleCustom> listSearchResultByPage(@Param(value="status") Integer status,@Param(value="query") String query,@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize) throws Exception;
	
	//获得同分类的文章(相关文章)
	public List<ArticleCustom> listArticleWithSameCategory(
			@Param(value="status") Integer status,
			 @Param(value = "parentCategory") Integer parentCategory
			,@Param(value = "childCategory") Integer childCategory
			,@Param(value = "limit") Integer limit
	) throws Exception;
	
	//获得访问最多的文章(猜你喜欢)
	public List<ArticleCustom> listArticleByViewCount(@Param(value="status") Integer status,@Param(value = "limit") Integer limit) throws Exception;
	
	//获得上一篇文章
	public ArticleCustom getAfterArticle(@Param(value="status") Integer status,@Param(value="id") Integer id) throws Exception;
	
	//获得下一篇文章
	public ArticleCustom getPreArticle(@Param(value="status") Integer status,@Param(value="id") Integer id) throws Exception;
	
	//获得随机文章
	public List<ArticleCustom> listRandomArticle(@Param(value="status") Integer status,@Param(value = "limit") Integer limit) throws  Exception;

	//热评文章
	public List<ArticleCustom> listArticleByCommentCount(@Param(value="status") Integer status,@Param(value = "limit") Integer limit) throws Exception;

	//获得该分类的文章数
	public Integer countArticleByCategory(@Param(value="status") Integer status,@Param(value = "id") Integer id) throws Exception;

	//获得该分类的文章数
	public Integer countArticleByTag(@Param(value="status") Integer status,@Param(value = "id") Integer id) throws Exception;

	//更新文章的评论数
	public void updateCommentCount(@Param(value = "articleId") Integer articleId) throws Exception;

	//获得最后更新的记录
	public ArticleCustom getLastUpdateArticle() throws Exception;
}



