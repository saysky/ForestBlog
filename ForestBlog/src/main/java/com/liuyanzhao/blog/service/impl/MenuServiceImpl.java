package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.Menu;
import com.liuyanzhao.blog.mapper.MenuMapper;
import com.liuyanzhao.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenu() {
        List<Menu> menuList = menuMapper.listMenu();
        return menuList;
    }

    @Override
    @CachePut(value = "default", key = "'menu:'+#menu.menuId")
    public Menu insertMenu(Menu menu) {
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    @CacheEvict(value = "default", key = "'menu:'+#id")
    public void deleteMenu(Integer id) {
        menuMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "default", key = "'menu:'+#menu.menuId")
    public void updateMenu(Menu menu) {
        menuMapper.update(menu);
    }

    @Override
    @Cacheable(value = "default", key = "'menu:'+#id")
    public Menu getMenuById(Integer id) {
        return menuMapper.getMenuById(id);
    }
}
