package com.liuyanzhao.blog.controller.home;

import com.liuyanzhao.blog.entity.Article;
import com.liuyanzhao.blog.entity.Notice;
import com.liuyanzhao.blog.service.ArticleService;
import com.liuyanzhao.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;

    /**
     * 公告详情页显示
     *
     * @param noticeId
     * @return
     */
    @RequestMapping(value = "/notice/{noticeId}")
    public String NoticeDetailView(@PathVariable("noticeId") Integer noticeId,
                                         Model model) {
        //公告内容和信息显示
        Notice notice  = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice",notice);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/noticeDetail";
    }
}
