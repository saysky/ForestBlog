package com.liuyanzhao.blog.controller.Admin;


import com.liuyanzhao.blog.entity.Tag;
import com.liuyanzhao.blog.entity.custom.TagCustom;
import com.liuyanzhao.blog.service.ArticleService;
import com.liuyanzhao.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
    @Autowired
    private ArticleService articleService;


    @Autowired
    private TagService tagService;

    //后台标签列表显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<TagCustom> tagCustomList = tagService.listTag(null);
        modelandview.addObject("tagCustomList",tagCustomList);

        modelandview.setViewName("Admin/Tag/index");
        return modelandview;

    }


    //后台添加分类页面显示
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag) throws Exception {
        tagService.insertTag(tag);
        return "redirect:/admin/tag";
    }

    //删除标签
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) throws Exception {

        //禁止删除有文章的分类
        int count = articleService.countArticleWithTag(null,id);
        if (count == 0) {
            tagService.deleteTag(id);
        }

        return "redirect:/admin/tag";
    }

    //编辑标签页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        TagCustom tagCustom =  tagService.getTagById(id);
        modelAndView.addObject("tagCustom",tagCustom);

        List<TagCustom> tagCustomList = tagService.listTag(null);
        modelAndView.addObject("tagCustomList",tagCustomList);

        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }


    //编辑标签提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag) throws Exception {
        tagService.updateTag(tag);
        return "redirect:/admin/tag";
    }
}
