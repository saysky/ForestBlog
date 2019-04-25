package com.liuyanzhao.ssm.blog.controller.home;




import com.liuyanzhao.ssm.blog.entity.Article;
import com.liuyanzhao.ssm.blog.entity.Category;
import com.liuyanzhao.ssm.blog.entity.Page;
import com.liuyanzhao.ssm.blog.entity.Tag;
import com.liuyanzhao.ssm.blog.service.ArticleService;
import com.liuyanzhao.ssm.blog.service.CategoryService;
import com.liuyanzhao.ssm.blog.service.PageService;
import com.liuyanzhao.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 言曌
 * @date 2017/9/7
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
    private TagService tagService;

    /**
     * 页面详情页面
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/{key}")
    public String pageDetail(@PathVariable("key") String key, Model model) {
        Page page = pageService.getPageByKey(1, key);
        if (page == null) {
            return "redirect:/404";
        }
        model.addAttribute("page", page);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/page";

    }


    /**
     * 文章归档页面显示
     *
     * @return
     */
    @RequestMapping(value = "/articleFile")
    public String articleFile(Model model) {
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/articleFile";
    }

    /**
     * 站点地图显示
     *
     * @return
     */
    @RequestMapping(value = "/map")
    public String siteMap(Model model) {
        //文章显示
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        //标签显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/siteMap";
    }

    /**
     * 留言板
     *
     * @return
     */
    @RequestMapping(value = "/message")
    public String message(Model model) {

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/message";
    }
}
