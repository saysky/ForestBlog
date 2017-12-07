package com.liuyanzhao.blog.controller.Admin;


import com.liuyanzhao.blog.entity.Category;
import com.liuyanzhao.blog.entity.custom.CategoryCustom;

import com.liuyanzhao.blog.service.ArticleService;
import com.liuyanzhao.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {
    @Autowired
    private ArticleService articleService;


    @Autowired
    private CategoryService categoryService;

    //后台分类列表显示
    @RequestMapping(value = "")
    public ModelAndView categoryList() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(null);
        modelandview.addObject("categoryCustomList",categoryCustomList);

        modelandview.setViewName("Admin/Category/index");
        return modelandview;

    }


    //后台添加分类提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category) throws Exception {
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    //删除分类
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) throws Exception {

        //禁止删除有文章的分类
        int count = articleService.countArticleWithCategory(null,id);
        if (count == 0) {
            categoryService.deleteCategory(id);
        }

        return "redirect:/admin/category";
    }

    //编辑分类页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        CategoryCustom categoryCustom =  categoryService.getCategoryById(null,id);
        modelAndView.addObject("categoryCustom",categoryCustom);

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(null);
        modelAndView.addObject("categoryCustomList",categoryCustomList);

        modelAndView.setViewName("Admin/Category/edit");
        return modelAndView;
    }

    //编辑分类提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category) throws Exception {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
