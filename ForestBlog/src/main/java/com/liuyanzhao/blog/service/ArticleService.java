package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Article;
import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import com.liuyanzhao.blog.entity.custom.ArticleDetailVo;
import com.liuyanzhao.blog.entity.custom.ArticleListVo;
import com.liuyanzhao.blog.entity.custom.ArticleSearchVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 言曌 on 2017/8/24.
 */
public interface ArticleService {
	//获取文章总数
	public Integer countArticle(Integer status) throws Exception;
	
	//获取评论总数
	public Integer countArticleComment(Integer status) throws Exception;
	
	//获得浏览量总数
	public Integer countArticleView(Integer status) throws Exception;
	
	//获得所有文章不分页
	public List<ArticleListVo> listArticle(Integer status) throws Exception;
	
	//根据id获得文章
	public ArticleCustom getArticleById(Integer status,Integer id) throws Exception;

	//修改文章信息
	public void updateArticle(Integer id, Article article) throws Exception;

	//批量删除文章
	public void deleteArticleBatch(Integer[] ids) throws  Exception;

	//删除文章
	public void deleteArticle(Integer id) throws Exception;

	//分页显示()
	public List<ArticleListVo> listArticleByPage(Integer status,Integer pageNow,Integer pageSize) throws Exception;
	
	//文章详情页面显示
	public ArticleDetailVo getArticleDetailById(Integer id) throws Exception;
	
	//文章查询分页显示
	public List<ArticleSearchVo> listSearchResultByPage(Integer status,HttpServletRequest request, Model model, Integer pageNow, Integer pageSize, String s) throws Exception;
 
	//获得相关文章
	public List<ArticleCustom> listArticleWithSameCategory(Integer status,Integer parentCategoryId,Integer childCategoryId, Integer limit) throws Exception;
	
	//获取访问量较多的文章
	public List<ArticleCustom> listArticleByViewCount(Integer status,Integer limit) throws Exception;
	
	//获得上一篇文章
	public ArticleCustom getAfterArticle(Integer status,Integer id) throws Exception;
	
	//获得下一篇文章
	public ArticleCustom getPreArticle(Integer status,Integer id) throws Exception;
	
	//获得随机文章
	public List<ArticleCustom> listRandomArticle(Integer status,Integer limit) throws  Exception;
	
	//获得评论数较多的文章
	public List<ArticleCustom> listArticleByCommentCount(Integer status,Integer limit) throws Exception;

	//添加文章
	public void insertArticle(Article article) throws Exception;

	//获得某个分类的文章数
	public Integer countArticleWithCategory(Integer status,Integer id) throws Exception;

	//获得某个标签的文章数
	public Integer countArticleWithTag(Integer status,Integer id) throws Exception;

	//更新文章的评论数
	public void updateCommentCount(Integer articleId) throws Exception;

	//获得最后更新记录
	public ArticleCustom getLastUpdateArticle() throws Exception;
}
