package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.Link;
import com.liuyanzhao.blog.mapper.LinkMapper;
import com.liuyanzhao.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author 言曌
 * @date 2017/9/4
 */
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public Integer countLink(Integer status)  {
		return linkMapper.countLink(status);
	}
	
	@Override
	public List<Link> listLink(Integer status)  {
		return linkMapper.listLink(status);
	}

	@Override
	public void insertLink(Link link)  {
		linkMapper.insert(link);
	}

	@Override
	public void deleteLink(Integer id)  {
		linkMapper.deleteById(id);
	}

	@Override
	public void updateLink(Link link)  {
		linkMapper.update(link);
	}

	@Override
	public Link getLinkById(Integer id)  {
		return linkMapper.selectByPrimaryKey(id);
	}

}
