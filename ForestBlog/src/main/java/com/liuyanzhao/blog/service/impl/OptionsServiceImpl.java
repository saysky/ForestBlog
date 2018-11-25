package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.Options;
import com.liuyanzhao.blog.mapper.OptionsMapper;
import com.liuyanzhao.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 言曌
 * @date 2017/9/7
 */
public class OptionsServiceImpl implements OptionsService {


    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public Options getOptions() {
        return optionsMapper.getOptions();
    }

    @Override
    public void insertOptions(Options options) {
        optionsMapper.insert(options);
    }

    @Override
    public void updateOptions(Options options) {
        optionsMapper.update(options);
    }
}
