package com.liuyanzhao.ssm.blog.controller.admin;

import com.liuyanzhao.ssm.blog.entity.Options;
import com.liuyanzhao.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author liuyanzhao
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {

    @Autowired
    private OptionsService optionsService;


    /**
     * 基本信息显示
     *
     * @return
     */
    @RequestMapping(value = "")
    public ModelAndView index()  {
        ModelAndView modelAndView = new ModelAndView();
        Options option = optionsService.getOptions();
        modelAndView.addObject("option",option);

        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

    /**
     * 编辑基本信息显示
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public ModelAndView editOptionView()  {
        ModelAndView modelAndView = new ModelAndView();
        Options option = optionsService.getOptions();
        modelAndView.addObject("option",option);

        modelAndView.setViewName("Admin/Options/edit");
        return modelAndView;
    }

    /**
     * 编辑基本信息提交
     *
     * @param options
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options)  {
        //如果记录不存在，那就新建
        Options optionsCustom = optionsService.getOptions();
        if(optionsCustom.getOptionId()==null) {
            optionsService.insertOptions(options);
        } else {
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }

}
