package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.Page;

import java.util.List;

/**
 *
 * @author 言曌
 * @date 2017/9/7
 */
public interface PageService {
    /**
     * 获得页面列表
     *
     * @param status 状态
     * @return 列表
     */
    List<Page> listPage(Integer status);

    /**
     * 根据页面key获得页面
     * 
     * @param status 状态
     * @param key 别名
     * @return 页面
     */
    Page getPageByKey(Integer status, String key);

    /**
     * 根据id获取页面
     * 
     * @param id 页面ID
     * @return 页面
     */
    Page getPageById(Integer id);

    /**
     * 添加页面
     * 
     * @param page 页面
     */
    void insertPage(Page page);

    /**
     * 删除页面
     * 
     * @param id 页面ID
     */
    void deletePage(Integer id);

    /**
     * 编辑页面
     * 
     * @param page 分页
     */
    void updatePage(Page page);
}
