package com.liuyanzhao.blog.controller.Admin;


import com.liuyanzhao.blog.entity.Notice;
import com.liuyanzhao.blog.entity.custom.NoticeCustom;
import com.liuyanzhao.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;

    //后台公告列表显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<NoticeCustom> noticeCustomList = noticeService.listNotice(null);
        modelandview.addObject("noticeCustomList",noticeCustomList);

        modelandview.setViewName("Admin/Notice/index");
        return modelandview;

    }

    //添加公告显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertNoticeView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/Notice/insert");
       return modelAndView;
    }

    //添加公告提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) throws Exception {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(1);
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }

    //删除公告
    @RequestMapping(value = "/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) throws Exception {
        noticeService.deleteNotice(id);

        return "redirect:/admin/notice";
    }

    //编辑公告页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editNoticeView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        NoticeCustom noticeCustom =  noticeService.getNoticeById(id);
        modelAndView.addObject("noticeCustom",noticeCustom);

        modelAndView.setViewName("Admin/Notice/edit");
        return modelAndView;
    }


    //编辑公告提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) throws Exception {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }



}
