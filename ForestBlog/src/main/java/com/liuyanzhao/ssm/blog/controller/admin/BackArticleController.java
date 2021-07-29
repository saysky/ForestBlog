package com.liuyanzhao.ssm.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.dto.ArticleParam;
import com.liuyanzhao.ssm.blog.dto.JsonResult;
import com.liuyanzhao.ssm.blog.entity.Article;
import com.liuyanzhao.ssm.blog.enums.UserRole;
import com.liuyanzhao.ssm.blog.service.ArticleService;
import com.liuyanzhao.ssm.blog.service.CategoryService;
import com.liuyanzhao.ssm.blog.service.TagService;

import com.liuyanzhao.ssm.blog.entity.Category;
import com.liuyanzhao.ssm.blog.entity.Tag;
import com.liuyanzhao.ssm.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * @author liuyanzhao
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 后台文章列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status, Model model,
                        HttpSession session) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }

        User user = (User) session.getAttribute("user");
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章, 管理员查询所有的
            criteria.put("userId", user.getUserId());
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "Admin/Article/index";
    }


    /**
     * 后台添加文章页面显示
     *
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "Admin/Article/insert";
    }

    /**
     * 后台添加文章提交操作
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }


    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void deleteArticle(@PathVariable("id") Integer id, HttpSession session) {
        Article dbArticle = articleService.getArticleByStatusAndId(null, id);
        if (dbArticle == null) {
            return;
        }
        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则跳转403
        if (!Objects.equals(dbArticle.getArticleUserId(), user.getUserId()) && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return;
        }
        articleService.deleteArticle(id);
    }


    /**
     * 编辑文章页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editArticleView(@PathVariable("id") Integer id, Model model, HttpSession session) {

        Article article = articleService.getArticleByStatusAndId(null, id);
        if (article == null) {
            return "redirect:/404";
        }
        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则跳转403
        if (!Objects.equals(article.getArticleUserId(), user.getUserId()) && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }

        model.addAttribute("article", article);

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "Admin/Article/edit";
    }


    /**
     * 编辑文章提交
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam, HttpSession session) {
        Article dbArticle = articleService.getArticleByStatusAndId(null, articleParam.getArticleId());
        if (dbArticle == null) {
            return "redirect:/404";
        }
        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则跳转403
        if (!Objects.equals(dbArticle.getArticleUserId(), user.getUserId()) && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }
        Article article = new Article();
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
        return "redirect:/admin/article";
    }

    /**
     * 后台添加文章提交操作
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertDraftSubmit", method = RequestMethod.POST)
    public String insertDraftSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin";
    }

}



