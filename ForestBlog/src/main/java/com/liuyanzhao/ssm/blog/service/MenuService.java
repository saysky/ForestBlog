package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.Menu;

import java.util.List;

 /**
  * @author liuyanzhao
  */
 public interface MenuService {
     /**
      * 获得菜单列表
      * 
      * @return 列表
      */
     List<Menu> listMenu() ;

     /**
      * 添加菜单项目
      * 
      * @param menu 菜单
      */
     Menu insertMenu(Menu menu) ;

     /**
      * 删除菜单项目
      * 
      * @param id 菜单ID
      */
     void deleteMenu(Integer id) ;

     /**
      * 更新菜单项目
      * 
      * @param menu 菜单
      */
     void updateMenu(Menu menu) ;

     /**
      * 根据id获得菜单项目信息
      * 
      * @param id 菜单ID
      * @return 菜单
      */
     Menu getMenuById(Integer id) ;
}
