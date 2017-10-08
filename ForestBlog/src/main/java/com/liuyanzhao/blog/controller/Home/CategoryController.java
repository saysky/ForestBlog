package com.liuyanzhao.blog.controller.Home;


import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.*;
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
 * 文章分类目录的controller
 * Created by 言曌 on 2017/8/24.
 */
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@ModelAttribute
	public void init(Model model) throws Exception {

		
	}
	
	//根据分类查询文章
	@RequestMapping("/category/{cateId}")
	@ResponseBody
	public ModelAndView ArticleListByCategoryView(@PathVariable("cateId") Integer cateId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示条数
		int pageSize = 10;
		List<ArticleListVo> articleListVoList = categoryService.listArticleWithCategoryByPage(1,null,pageSize,cateId);

		//如果articleListVoList=null表示该分类不存在，如果=0表示该分类暂时没有文章
        modelAndView.addObject("articleListVoList",articleListVoList);

		//该分类信息
		CategoryCustom categoryCustom = categoryService.getCategoryById(1,cateId);
		modelAndView.addObject("categoryCustom",categoryCustom);

		modelAndView.setViewName("Home/Page/articleListByCategory");
		return modelAndView;
	}
	
	//根据分类查询文章分页
	@RequestMapping("/category/{cateId}/p/{pageNow}")
	@ResponseBody
	public  ModelAndView ArticleListByCategoryAndPageView(@PathVariable("pageNow") Integer pageNow,@PathVariable("cateId") Integer cateId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示条数
		int pageSize = 10;
		List<ArticleListVo> articleListVoList = categoryService.listArticleWithCategoryByPage(1,pageNow,pageSize,cateId);
		modelAndView.addObject("articleListVoList",articleListVoList);
		modelAndView.setViewName("Home/Page/articleListByCategory");

        //该分类信息
        CategoryCustom categoryCustom = categoryService.getCategoryById(1,cateId);
        modelAndView.addObject("categoryCustom",categoryCustom);
		return modelAndView;
	}

}
