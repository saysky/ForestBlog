package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.Link;

import java.util.List;

/**
 *
 * @author 言曌
 * @date 2017/9/4
 */
public interface LinkService {

    /**
     * 获得链接总数
     * 
     * @param status 状态
     * @return 数量
     */
    Integer countLink(Integer status);

    /**
     * 获得链接列表
     * 
     * @param status 状态
     * @return 链接列表
     */
    List<Link> listLink(Integer status);

    /**
     * 添加链接
     * 
     * @param link 链接
     */
    void insertLink(Link link);

    /**
     * 删除链接
     * 
     * @param id 链接ID
     */
    void deleteLink(Integer id);

    /**
     * 更新链接
     * 
     * @param link 链接
     */
    void updateLink(Link link);

    /**
     * 根据id查询链接
     * 
     * @param id 链接ID
     * @return 链接
     */
    Link getLinkById(Integer id);


}
