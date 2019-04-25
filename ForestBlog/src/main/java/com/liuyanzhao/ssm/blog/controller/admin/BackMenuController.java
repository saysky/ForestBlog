package com.liuyanzhao.ssm.blog.controller.admin;

import com.liuyanzhao.ssm.blog.entity.Menu;
import com.liuyanzhao.ssm.blog.enums.MenuLevel;
import com.liuyanzhao.ssm.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 后台菜单列表显示
     *
     * @return
     */
    @RequestMapping(value = "")
    public String menuList(Model model)  {
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList",menuList);
        return "Admin/Menu/index";
    }

    /**
     * 添加菜单内容提交
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu)  {
        if(menu.getMenuOrder() == null) {
            menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
        }
        menuService.insertMenu(menu);
        return "redirect:/admin/menu";
    }

    /**
     * 删除菜单内容
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id)  {
        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }

    /**
     * 编辑菜单内容显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Menu menu =  menuService.getMenuById(id);
        modelAndView.addObject("menu",menu);

        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);

        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }


    /**
     * 编辑菜单内容提交
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu)  {
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }



}
