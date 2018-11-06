package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Tag;
import com.liuyanzhao.blog.entity.custom.ArticleListVo;
import com.liuyanzhao.blog.entity.custom.TagCustom;
import org.apache.xpath.operations.Bool;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/2.
 */
public interface TagService {
	
	//获得标签总数
	public Integer countTag(Integer status) throws Exception;
	
	//获得标签列表
	public List<TagCustom> listTag(Integer status) throws Exception;

	//获得含有该标签的文章列表
	public List<ArticleListVo> getArticleListByPage(Integer status,Integer pageNow, Integer pageSize,Integer tagId) throws Exception;

	//根据id获得标签信息
	public TagCustom getTagById(Integer id) throws Exception;

	//添加标签
	public void insertTag(Tag tag) throws Exception;

	//修改标签
	public void updateTag(Tag tag) throws Exception;

	//删除标签
    public void deleteTag(Integer id) throws Exception;

    //根据标签名获取标签
	public Tag getTagByName(String name) throws Exception;

}
