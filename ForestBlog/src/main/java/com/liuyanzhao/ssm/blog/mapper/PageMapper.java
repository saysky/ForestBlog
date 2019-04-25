package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface PageMapper {
    /**
     * 根据ID删除
     * 
     * @param pageId 页面ID
     * @return 影响行数
     */
    int deleteById(Integer pageId);

    /**
     * 添加
     * 
     * @param page 页面
     * @return 影响行数
     */
    int insert(Page page);

    /**
     * 根据ID查询
     * 
     * @param pageId 页面ID
     * @return 页面
     */
    Page getPageById(Integer pageId);

    /**
     * 更新
     * 
     * @param page 页面
     * @return 影响行数
     */
    int update(Page page);

    /**
     * 获得页面列表
     * 
     * @param status 状态
     * @return 页面列表
     */
    List<Page> listPage(@Param(value = "status") Integer status);

    /**
     * 根据key获得页面
     * 
     * @param status 状态
     * @param key 别名
     * @return 页面
     */
    Page getPageByKey(@Param(value = "status") Integer status,
                      @Param(value = "key") String key);
}