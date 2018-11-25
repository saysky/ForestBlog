package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.Page;
import com.liuyanzhao.blog.mapper.PageMapper;
import com.liuyanzhao.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 言曌
 * @date 2017/9/7
 */
public class PageServiceImpl implements PageService {

    @Autowired
    private PageMapper pageMapper;

    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageMapper.getPageByKey(status, key);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Page> listPage(Integer status) {
        return pageMapper.listPage(status);
    }


    @Override
    public void insertPage(Page page) {
        pageMapper.insert(page);
    }

    @Override
    public void deletePage(Integer id) {
        pageMapper.deleteById(id);
    }

    @Override
    public void updatePage(Page page) {
        pageMapper.update(page);
    }
}
