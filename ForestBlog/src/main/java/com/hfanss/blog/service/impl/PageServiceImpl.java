package com.hfanss.blog.service.impl;

import com.hfanss.blog.entity.Page;
import com.hfanss.blog.entity.custom.PageCustom;
import com.hfanss.blog.mapper.PageMapper;
import com.hfanss.blog.mapper.custom.PageMapperCustom;
import com.hfanss.blog.service.PageService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/7.
 */
public class PageServiceImpl implements PageService{
	@Autowired
	private PageMapperCustom pageMapperCustom;

	@Autowired
	private PageMapper pageMapper;

	@Override
	public PageCustom getPageByKey(Integer status,String key) throws Exception {
		PageCustom pageCustom = pageMapperCustom.getPageByKey(status,key);
		return pageCustom;
	}

	@Override
	public PageCustom getPageById(Integer id) throws Exception {
		Page page = pageMapper.selectByPrimaryKey(id);
		PageCustom pageCustom = new PageCustom();
		BeanUtils.copyProperties(page,pageCustom);
		return pageCustom;
	}

	@Override
	public List<PageCustom> listPage(Integer status) throws Exception {
		List<PageCustom> pageCustomList = pageMapperCustom.listPage(status);
		return pageCustomList;
	}


	@Override
	public void insertPage(Page page) throws Exception {
		pageMapper.insert(page);
	}

	@Override
	public void deletePage(Integer id) throws Exception {
		pageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePage(Page page) throws Exception {
		pageMapper.updateByPrimaryKeySelective(page);
	}
}
