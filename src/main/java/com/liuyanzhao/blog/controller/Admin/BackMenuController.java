package com.liuyanzhao.blog.controller.Admin;

import com.liuyanzhao.blog.entity.Menu;
import com.liuyanzhao.blog.entity.custom.MenuCustom;
import com.liuyanzhao.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;

    //后台菜单列表显示
    @RequestMapping(value = "")
    public ModelAndView menuList() throws Exception {
        ModelAndView modelandview = new ModelAndView();
        List<MenuCustom> menuCustomList = menuService.listMenu(null);
        modelandview.addObject("menuCustomList",menuCustomList);
        modelandview.setViewName("Admin/Menu/index");
        return modelandview;
    }

    //添加菜单内容提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu) throws Exception {
        menu.setMenuStatus(1);
        menu.setMenuOrder(1);
        menuService.insertMenu(menu);
        return "redirect:/admin/menu";
    }

    //删除菜单内容
    @RequestMapping(value = "/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id) throws Exception {

        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }

    //编辑菜单内容显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        MenuCustom menuCustom =  menuService.getMenuById(id);
        modelAndView.addObject("menuCustom",menuCustom);

        List<MenuCustom> menuCustomList = menuService.listMenu(null);
        modelAndView.addObject("menuCustomList",menuCustomList);

        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }


    //编辑菜单内容提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu) throws Exception {
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }

    //显示菜单内容
    @RequestMapping(value = "/show/{id}",method = RequestMethod.POST)
    public void showMenu(@PathVariable("id") Integer id) throws Exception {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setMenuStatus(1);
        menuService.updateMenu(menu);
    }

    //隐藏菜单内容
    @RequestMapping(value = "/hide/{id}",method = RequestMethod.POST)
    public void hideMenu(@PathVariable("id") Integer id) throws Exception {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setMenuStatus(0);
        menuService.updateMenu(menu);
    }

}
