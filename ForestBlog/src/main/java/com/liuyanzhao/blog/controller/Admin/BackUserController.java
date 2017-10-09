package com.liuyanzhao.blog.controller.Admin;


import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.UserCustom;
import com.liuyanzhao.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    //后台用户列表显示
    @RequestMapping(value = "")
    public ModelAndView userList() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<UserCustom> userCustomList = userService.listUser();
        modelandview.addObject("userCustomList",userCustomList);

        modelandview.setViewName("Admin/User/index");
        return modelandview;

    }

    //后台添加用户页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertUserView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/User/insert");
        return modelAndView;
    }

    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    //后台添加用户页面提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertUserSubmit(User user) throws Exception {
        User user2 = userService.getUserByName(user.getUserName());
        User user3 = userService.getUserByEmail(user.getUserEmail());
        if(user2==null&&user3==null) {
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }

    //删除用户
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) throws Exception {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    //编辑用户页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        UserCustom userCustom =  userService.getUserById(id);
        modelAndView.addObject("userCustom",userCustom);

        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }


    //编辑用户提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user) throws Exception {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

    //基本信息页面显示
    @RequestMapping(value = "/profile/{id}")
    public ModelAndView userProfileView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        UserCustom userCustom =  userService.getUserById(id);
        modelAndView.addObject("userCustom",userCustom);

        modelAndView.setViewName("Admin/User/profile");
        return modelAndView;
    }
}
