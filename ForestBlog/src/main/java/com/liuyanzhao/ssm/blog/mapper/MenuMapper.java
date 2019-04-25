package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Menu;

import java.util.List;

/**
 * @author liuyanzhao
 */
public interface MenuMapper {

    /**
     * 删除
     *
     * @param menuId 菜单ID
     * @return 影响行数
     */
    int deleteById(Integer menuId);

    /**
     * 添加
     * @param menu 菜单
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 根据ID查询
     *
     * @param menuId 菜单ID
     * @return 菜单
     */
    Menu getMenuById(Integer menuId);

    /**
     * 更新
     * 
     * @param menu 菜单
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 获得菜单列表
     * 
     * @return 列表
     */
    List<Menu> listMenu() ;
}