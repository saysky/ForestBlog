package com.liuyanzhao.blog.controller.Home;

import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户的controller
 * Created by 言曌 on 2017/8/24.
 */
@Controller
public class IndexController {
	
	@Autowired
	private ArticleService articleService;

	@Autowired
	private LinkService linkService;

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute
	public void init(Model model)  throws Exception {
		//友情链接
		List<LinkCustom> linkCustomList = linkService.listLink(1);
		model.addAttribute("linkCustomList",linkCustomList);

		//公告
		List<NoticeCustom> noticeCustomList = noticeService.listNotice(1);
		model.addAttribute("noticeCustomList",noticeCustomList);
	}
	
	//首页显示
	@RequestMapping("/")
	public ModelAndView IndexView() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//文章列表
		int pageSize = 10;
		List<ArticleListVo> articleListVoList = articleService.listArticleByPage(1,null,pageSize);
		modelAndView.addObject("articleListVoList",articleListVoList);

		modelAndView.setViewName("/Home/index");
		return modelAndView;
	}
	
	//文章分页显示
	@RequestMapping("p/{pageNow}")
	//适合RESTful
	public @ResponseBody  ModelAndView ArticleListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		List<ArticleListVo> articleListVoList = articleService.listArticleByPage(1,pageNow,pageSize);
		modelAndView.addObject("articleListVoList",articleListVoList);
		modelAndView.setViewName("Home/index");
		return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
	}

}




