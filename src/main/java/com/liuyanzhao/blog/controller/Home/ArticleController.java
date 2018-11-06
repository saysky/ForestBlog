package com.liuyanzhao.blog.controller.Home;


import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 文章的controller
 * Created by 言曌 on 2017/8/24.
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@ModelAttribute
	public void init(Model model) throws Exception {
		
	}
	
	//文章详情页显示
	@RequestMapping(value = "/article/{articleId}")
	@ResponseBody //适合RESTful
	public ModelAndView ArticleDetailView(@PathVariable("articleId") Integer articleId) throws Exception{
		ModelAndView modelAndView = new ModelAndView();

		//文章信息，分类，标签，作者，评论
		ArticleDetailVo articleDetailVo  = articleService.getArticleDetailById(articleId);
		if(articleDetailVo!=null) {

			modelAndView.addObject("articleDetailVo", articleDetailVo);
			//相关文章
			Integer parentCategoryId = articleService.getArticleById(1, articleId).getArticleParentCategoryId();
			Integer childCategoryId = articleService.getArticleById(1, articleId).getArticleChildCategoryId();
			List<ArticleCustom> similarArticleList = articleService.listArticleWithSameCategory(1, parentCategoryId, childCategoryId, 5);
			modelAndView.addObject("similarArticleList", similarArticleList);

			//猜你喜欢
			List<ArticleCustom> mostViewArticleList = articleService.listArticleByViewCount(1, 5);
			modelAndView.addObject("mostViewArticleList", mostViewArticleList);
			//获取下一篇文章
			ArticleCustom afterArticle = articleService.getAfterArticle(1, articleId);
			modelAndView.addObject("afterArticle", afterArticle);
			//获取上一篇文章
			ArticleCustom preArticle = articleService.getPreArticle(1, articleId);
			modelAndView.addObject("preArticle", preArticle);
			modelAndView.setViewName("Home/Page/articleDetail");
		} else {
			modelAndView.setViewName("Home/Error/404");
		}
		return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
		
	}
	
	//文章点赞数增加
	@RequestMapping(value = "/article/addLike/{id}",method = {RequestMethod.POST})
	@ResponseBody
	public Integer increaseLikeCount(@PathVariable("id") Integer id)
		throws Exception {
		ArticleCustom articleCustom = articleService.getArticleById(1,id);
		int articleCount = articleCustom.getArticleLikeCount();
		articleCustom.setArticleLikeCount(articleCount + 1);
		articleService.updateArticle(id, articleCustom);
		return articleCount+1;
	}
	
	//文章访问量数增加
	@RequestMapping(value = "/article/addView/{id}",method = {RequestMethod.POST})
	@ResponseBody
	public Integer increaseViewCount(@PathVariable("id") Integer id)
		throws Exception {
		ArticleCustom articleCustom = articleService.getArticleById(1,id);
		int articleCount = articleCustom.getArticleViewCount();
		articleCustom.setArticleViewCount(articleCount + 1);
		articleService.updateArticle(id, articleCustom);
		return articleCount+1;
	}

	


	//文章信息修改提交
	@RequestMapping(value = "/editArticleSubmit",method = RequestMethod.POST)
	public String editArticleSubmit(Integer id ,
								 ArticleCustom articleCustom
	) throws Exception {
		
		
		articleService.updateArticle(id,articleCustom);
		
		return "redirect:articleList.action";
	}

	//文章搜索实现
	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView SearchPageView(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		String query = request.getParameter("query");
		List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1,request,model,null,pageSize,query);
		if(articleSearchVoList!=null) {
			modelAndView.addObject("articleSearchVoList", articleSearchVoList);
		} else {
			modelAndView.addObject("articleSearchVoList", null);
		}
		modelAndView.setViewName("Home/Page/search");
		return modelAndView;
	}

	//文章搜索分页实现
	@RequestMapping("/p/{pageNow}/search")
	@ResponseBody
	public  ModelAndView SearchPageByPageView(HttpServletRequest request, Model model,@PathVariable("pageNow") Integer pageNow) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		String query = request.getParameter("query");
		List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1,request,model,pageNow,pageSize,query);
		modelAndView.addObject("articleSearchVoList", articleSearchVoList);
		modelAndView.setViewName("Home/Page/search");
		return modelAndView;
	}
	






	
	
}
