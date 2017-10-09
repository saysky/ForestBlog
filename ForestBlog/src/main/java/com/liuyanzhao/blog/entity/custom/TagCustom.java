package com.liuyanzhao.blog.entity.custom;

import com.liuyanzhao.blog.entity.Tag;

/**
 * 文章标签的信息的扩展
 * Created by 言曌 on 2017/9/1.
 */
public class TagCustom extends Tag {
	//标签对应的文章数目
	private Integer articleCount;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
}
