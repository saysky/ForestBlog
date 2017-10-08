package com.liuyanzhao.blog.controller.Admin;

import com.liuyanzhao.blog.entity.Article;
import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.ArticleService;
import com.liuyanzhao.blog.service.CategoryService;
import com.liuyanzhao.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;


    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    //后台文章列表显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //分页显示已发布文章
        Integer pageSize = 20;
        List<ArticleListVo> publishedArticleListVoList = articleService.listArticleByPage(1,null,pageSize);
        modelAndView.addObject("publishedArticleListVoList",publishedArticleListVoList);

        //不分页显示 草稿文章
        List<ArticleListVo> draftArticleList = articleService.listArticle(0);
        modelAndView.addObject("draftArticleList",draftArticleList);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }

    //文章分页显示
    @RequestMapping("/p/{pageNow}")
    public @ResponseBody  ModelAndView ArticleListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //分页显示已发布文章
        Integer pageSize = 20;
        List<ArticleListVo> publishedArticleListVoList = articleService.listArticleByPage(1,pageNow,pageSize);
        modelAndView.addObject("publishedArticleListVoList",publishedArticleListVoList);

        //不分页显示 草稿文章
        List<ArticleListVo> draftArticleList = articleService.listArticle(0);
        modelAndView.addObject("draftArticleList",draftArticleList);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }

    //后台添加文章页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertArticleView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        List<TagCustom> tagCustomList = tagService.listTag(1);

        modelAndView.addObject("categoryCustomList",categoryCustomList);
        modelAndView.addObject("tagCustomList",tagCustomList);

        modelAndView.setViewName("Admin/Article/insert");
        return modelAndView;
    }

    //后台添加文章提交操作
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertArticleSubmit(Article article) throws Exception {

        article.setArticlePostTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleStatus(1);
        article.setArticleOrder(1);

        articleService.insertArticle(article);

        return "redirect:/admin/article";
    }

    //后台添加文章提交操作
    @RequestMapping(value = "/insertDraftSubmit",method = RequestMethod.POST)
    public String insertArticleDraftSubmit(Article article) throws Exception {

        article.setArticlePostTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleStatus(0);
        article.setArticleOrder(1);

        articleService.insertArticle(article);

        return "redirect:/admin/article";
    }


    //搜索实现
    @RequestMapping("/search")
    @ResponseBody
    public ModelAndView SearchPageView(HttpServletRequest request,Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 20;
        String query = request.getParameter("query");
        List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1,request,model,null,pageSize,query);
        modelAndView.addObject("articleSearchVoList", articleSearchVoList);
        modelAndView.setViewName("Admin/Article/search");
        return modelAndView;
    }

    //搜索分页实现
    @RequestMapping("/p/{pageNow}/search")
    @ResponseBody
    public  ModelAndView SearchPageByPageView(HttpServletRequest request, Model model,@PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 20;
        String query = request.getParameter("query");
        List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1,request,model,pageNow,pageSize,query);
        modelAndView.addObject("articleSearchVoList", articleSearchVoList);
        modelAndView.setViewName("/Admin/Article/search");
        return modelAndView;
    }



    //删除文章
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) throws Exception {
        //调用service批量删除
        articleService.deleteArticle(id);
    }

    //批量删除文章
    @RequestMapping(value = "/deleteBatch")
    public void deleteArticles(HttpServletRequest request) throws Exception {
        String str = request.getParameter("ids");
        String[] arr = str.split(",");
        Integer[] ids = new Integer[arr.length];
        for(int i=0;i<arr.length;i++) {
            ids[i] = Integer.valueOf(arr[i]);
        }
        //调用service批量删除
        articleService.deleteArticleBatch(ids);

    }

    //编辑文章页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        ArticleCustom articleCustom =  articleService.getArticleById(null,id);
        modelAndView.addObject("articleCustom",articleCustom);

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        modelAndView.addObject("categoryCustomList",categoryCustomList);

        List<TagCustom> tagCustomList = tagService.listTag(1);
        modelAndView.addObject("tagCustomList",tagCustomList);


        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }


    //编辑文章提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editArticleSubmit(ArticleCustom articleCustom) throws Exception {
        Integer id = articleCustom.getArticleId();
        articleCustom.setArticleUpdateTime(new Date());
        articleService.updateArticle(id,articleCustom);
        return "redirect:/admin/article";
    }



}



