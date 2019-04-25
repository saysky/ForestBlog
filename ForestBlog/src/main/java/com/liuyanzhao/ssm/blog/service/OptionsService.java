package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.Options;


/**
 *
 * @author 言曌
 * @date 2017/9/7
 */
public interface OptionsService {
    /**
     * 获得基本信息
     *
     * @return 系统设置
     */
    Options getOptions();

    /**
     * 新建基本信息
     * 
     * @param options 系统设置
     */
    void insertOptions(Options options);

    /**
     * 更新基本信息
     * 
     * @param options 系统设置
     */
    void updateOptions(Options options);
}
