package com.liuyanzhao.blog.controller.Home;

import com.liuyanzhao.blog.entity.custom.NoticeCustom;
import com.liuyanzhao.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //公告详情页显示
    @RequestMapping(value = "/notice/{noticeId}")
    @ResponseBody //适合RESTful
    public ModelAndView NoticeDetailView(@PathVariable("noticeId") Integer noticeId) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //公告内容和信息显示
        NoticeCustom noticeCustom  = noticeService.getNoticeById(noticeId);
        modelAndView.addObject("noticeCustom",noticeCustom);

        modelAndView.setViewName("Home/Page/noticeDetail");
        return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
    }
}
