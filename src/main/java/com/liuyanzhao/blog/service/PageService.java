package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Page;
import com.liuyanzhao.blog.entity.custom.PageCustom;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/7.
 */
public interface PageService {
	//获得页面列表
	public List<PageCustom> listPage(Integer status) throws Exception;

	//根据页面key获得页面
	public PageCustom getPageByKey(Integer status,String key) throws Exception;

	//根据id获取页面
	public PageCustom getPageById(Integer id) throws Exception;

	//添加页面
	public void insertPage(Page page) throws Exception;

	//删除页面
	public void deletePage(Integer id) throws Exception;

	//编辑页面
	public void updatePage(Page page) throws Exception;
}
