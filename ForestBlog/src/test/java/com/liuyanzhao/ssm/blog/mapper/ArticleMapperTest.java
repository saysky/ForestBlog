package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.BaseTest;
import com.liuyanzhao.ssm.blog.entity.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 言曌
 * @date 2020/10/11 10:53 上午
 */

public class ArticleMapperTest extends BaseTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void countArticleView() {
        int result = articleMapper.countArticleView();
        System.out.println(result);
    }

    @Test
    public void listArticle() {
        List<Article> articleList = articleMapper.listArticle();
        System.out.println(articleList);
    }
}
