package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Link;
import com.liuyanzhao.blog.entity.custom.LinkCustom;
import org.apache.xpath.operations.Bool;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/4.
 */
public interface LinkService {
	
	//获得链接总数
	public Integer countLink(Integer status) throws Exception;

	//获得链接列表
	public List<LinkCustom> listLink(Integer status) throws Exception;

	//添加链接
	public void insertLink(Link link) throws Exception;

	//删除链接
	public void deleteLink(Integer id) throws Exception;

	//更新链接
	public void updateLink(Link link) throws Exception;

	//根据id查询链接
	public LinkCustom getLinkById(Integer id) throws Exception;


}
