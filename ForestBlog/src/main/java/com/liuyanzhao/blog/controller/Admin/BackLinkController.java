package com.liuyanzhao.blog.controller.Admin;


import com.liuyanzhao.blog.entity.Link;
import com.liuyanzhao.blog.entity.custom.LinkCustom;
import com.liuyanzhao.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;

    //后台链接列表显示
    @RequestMapping(value = "")
    public ModelAndView linkList() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelandview.addObject("linkCustomList",linkCustomList);

        modelandview.setViewName("Admin/Link/index");
        return modelandview;

    }

    //后台添加链接页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertLinkView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelAndView.addObject("linkCustomList",linkCustomList);

        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    //后台添加链接页面提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertLinkSubmit(Link link) throws Exception {
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(1);
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    //删除链接
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id) throws Exception {

        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    //编辑链接页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        LinkCustom linkCustom =  linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelAndView.addObject("linkCustomList",linkCustomList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }


    //编辑链接提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editLinkSubmit(Link link) throws Exception {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }
}
