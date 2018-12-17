package com.hfanss.blog.controller.Home;

import com.hfanss.blog.entity.custom.*;
import com.hfanss.blog.service.*;
import com.hfanss.blog.util.Functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/7.
 */
@Controller
public class PageController {
	@Autowired
	private PageService pageService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private TagService tagService;


	@ModelAttribute
	public void init(Model model) throws Exception {

	}

	//页面显示
	@RequestMapping(value = "/{key}")
	public ModelAndView ArticleDetailView(@PathVariable("key") String key) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		PageCustom pageCustom = pageService.getPageByKey(1,key);
		if(pageCustom!=null) {
			modelAndView.addObject("pageCustom",pageCustom);
			modelAndView.setViewName("Home/Page/page");
		} else {
			modelAndView.setViewName("Home/Error/404");
		}
		return modelAndView;

	}


	//文章归档页面显示
	@RequestMapping(value = "/articleFile")
	public ModelAndView articleFile() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home/Page/articleFile");
		List<ArticleListVo> articleList = articleService.listArticle(1);
		modelAndView.addObject("articleList",articleList);
		return modelAndView;
	}

	//站点地图显示
	@RequestMapping(value = "/map")
	public ModelAndView siteMap() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home/Page/siteMap");
		//文章显示
		List<ArticleListVo> articleList = articleService.listArticle(1);
		modelAndView.addObject("articleList",articleList);
        //分类显示
        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        modelAndView.addObject("categoryCustomList",categoryCustomList);
        //标签显示
        List<TagCustom> tagCustomList = tagService.listTag(1);
        modelAndView.addObject("tagCustomList",tagCustomList);

		return modelAndView;
	}

	//留言板
	@RequestMapping(value = "/message")
	public ModelAndView message() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//查询所有留言，commentArticleId=0
//		List<CommentCustom> listMessage = commentService.listCommentByArticleId(1, 0);
		//文章信息，分类，标签，作者，评论
		//5、获取评论信息列表
		List<CommentCustom> commentCustomList = commentService.listCommentByArticleId(1,0);
		//给每个评论用户添加头像
		for(int i=0;i<commentCustomList.size();i++) {
			String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
			commentCustomList.get(i).setCommentAuthorAvatar(avatar);
		}
		modelAndView.addObject("listMessage",commentCustomList);
		modelAndView.setViewName("Home/Page/message");
		return modelAndView;
	}
}
