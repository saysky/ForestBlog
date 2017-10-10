package com.liuyanzhao.blog.controller.Admin;

import com.liuyanzhao.blog.entity.Options;
import com.liuyanzhao.blog.entity.custom.OptionsCustom;
import com.liuyanzhao.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {

    @Autowired
    private OptionsService optionsService;


    //基本信息显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        OptionsCustom optionCustom = optionsService.getOptions();
        modelAndView.addObject("optionCustom",optionCustom);

        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

    //编辑基本信息显示
    @RequestMapping(value = "/edit")
    public ModelAndView editOptionView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        OptionsCustom optionCustom = optionsService.getOptions();
        modelAndView.addObject("optionCustom",optionCustom);

        modelAndView.setViewName("Admin/Options/edit");
        return modelAndView;
    }

    //编辑基本信息提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options) throws Exception {
        //如果记录不存在，那就新建
        OptionsCustom optionsCustom = optionsService.getOptions();
        if(optionsCustom.getOptionId()==null) {
            optionsService.insertOptions(options);
        } else {
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }

}
