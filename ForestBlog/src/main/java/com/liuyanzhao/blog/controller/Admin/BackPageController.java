package com.liuyanzhao.blog.controller.Admin;

import com.liuyanzhao.blog.entity.Page;
import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/page")
public class BackPageController {

    @Autowired
    private PageService pageService;

    //后台页面列表显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<PageCustom> pageCustomList = pageService.listPage(null);
        modelAndView.addObject("pageCustomList",pageCustomList);
        modelAndView.setViewName("Admin/Page/index");
        return modelAndView;
    }

   
    //后台添加页面页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertPageView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("Admin/Page/insert");
        return modelAndView;
    }

    //后台添加页面提交操作
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertPageSubmit(Page page) throws Exception {

        //判断别名是否存在
        PageCustom checkPage = pageService.getPageByKey(null,page.getPageKey());
        if(checkPage==null) {
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            page.setPageStatus(1);
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }

    //删除页面
    @RequestMapping(value = "/delete/{id}")
    public String deletePage(@PathVariable("id") Integer id) throws Exception {
        //调用service批量删除
        pageService.deletePage(id);
        return "redirect:/admin/page";
    }

  
    //编辑页面页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editPageView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        PageCustom pageCustom =  pageService.getPageById(id);
        modelAndView.addObject("pageCustom",pageCustom);

        modelAndView.setViewName("Admin/Page/edit");
        return modelAndView;
    }


    //编辑页面提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editPageSubmit(Page page) throws Exception {
        PageCustom checkPage = pageService.getPageByKey(null,page.getPageKey());
        //判断别名是否存在且不是这篇文章
        if(checkPage.getPageId()==page.getPageId()) {
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return "redirect:/admin/page";
    }


}



