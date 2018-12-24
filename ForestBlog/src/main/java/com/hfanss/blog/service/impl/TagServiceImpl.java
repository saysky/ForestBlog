package com.hfanss.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

//import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hfanss.blog.entity.Tag;
import com.hfanss.blog.entity.custom.ArticleCustom;
import com.hfanss.blog.entity.custom.ArticleListVo;
import com.hfanss.blog.entity.custom.CategoryCustom;
import com.hfanss.blog.entity.custom.TagCustom;
import com.hfanss.blog.mapper.CategoryMapper;
import com.hfanss.blog.mapper.TagMapper;
import com.hfanss.blog.mapper.custom.ArticleMapperCustom;
import com.hfanss.blog.mapper.custom.CategoryMapperCustom;
import com.hfanss.blog.mapper.custom.TagMapperCustom;
import com.hfanss.blog.service.TagService;
import com.hfanss.blog.util.others.Page;

/**
 * Created by 言曌 on 2017/9/2.
 */
public class TagServiceImpl implements TagService {
	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	@Autowired
	private TagMapperCustom tagMapperCustom;

	@Autowired
	private ArticleMapperCustom articleMapperCustom;

	//获得标签总数
	@Override
	public Integer countTag(Integer status) throws Exception {
		Integer tagCount = tagMapperCustom.countTag(status);
		return tagCount;
	}
	
	//获得标签列表
	@Override
	public List<TagCustom> listTag(Integer status) throws Exception {
		List<TagCustom> tagList = tagMapperCustom.listTag(status);
		for(int i=0;i<tagList.size();i++) {
			Integer tagId = tagList.get(i).getTagId();
			int count = articleMapperCustom.countArticleByTag(status,tagId);
			tagList.get(i).setArticleCount(count);
		}
		return tagList;
	}

	
	//获得含有该标签的文章列表
	@Override
	public List<ArticleListVo> getArticleListByPage(Integer status,Integer pageNow, Integer pageSize,Integer tagId) throws Exception {
		List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();
		List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();
		
		
		//获得该标签的具体信息
		Tag tag = tagMapper.selectByPrimaryKey(tagId);
		if(tag==null) {
			return null;
		}
		
		//分页显示
		Page page = null;

		int totalCount = articleMapperCustom.countArticleByTag(status,tagId);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			articleCustomList = tagMapperCustom.listArticleWithTagByPage(status,tagId,page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1,pageSize);
			articleCustomList = tagMapperCustom.listArticleWithTagByPage(status,tagId,page.getStartPos(), page.getPageSize());
		}
		
		for(int i=0;i<articleCustomList.size();i++) {
			ArticleListVo articleListVo = new ArticleListVo();
			//1、将文章装入 articleListVo
			ArticleCustom articleCustom = articleCustomList.get(i);
			articleListVo.setArticleCustom(articleCustom);
			//2、将分类信息装到 articleListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer cate =articleCustomList.get(i).getArticleParentCategoryId();
			Integer cate2 =articleCustomList.get(i).getArticleChildCategoryId();
			CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,cate);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,cate2);
			if(categoryCustom!=null) {
				categoryCustomList.add(categoryCustom);
			}
			if(categoryCustom2!=null) {
				categoryCustomList.add(categoryCustom2);
			}
			articleListVo.setCategoryCustomList(categoryCustomList);
			
			articleListVoList.add(articleListVo);
		}
		//确保该标签有文章，防止空指针
		if(totalCount!=0) {
			//3、将Page存储在 articleListVoList 第一个元素中
			articleListVoList.get(0).setPage(page);

			//4、将标签信息 装入  articleListVoList 第一个元素中
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			TagCustom tagCustom = new TagCustom();
			BeanUtils.copyProperties(tag, tagCustom);
			tagCustomList.add(tagCustom);
			articleListVoList.get(0).setTagCustomList(tagCustomList);
		}
		return articleListVoList;
	}

    @Override
    public TagCustom getTagById(Integer id) throws Exception {
	    TagCustom tagCustom = new TagCustom();
	    Tag tag = tagMapper.selectByPrimaryKey(id);
	    if(tag==null) {
	    	return null;
		}
	    BeanUtils.copyProperties(tag,tagCustom);
	    return tagCustom;
    }

    @Override
    public Integer insertTag(Tag tag) throws Exception {
    	tagMapper.insert(tag);
    	return tag.getTagId();
    }

    @Override
    public void updateTag(Tag tag) throws Exception {
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public void deleteTag(Integer id) throws Exception {
        tagMapper.deleteByPrimaryKey(id);
    }

	@Override
	public Tag getTagByName(String name) throws Exception {
		Tag tag = tagMapperCustom.getTagByName(name);
		return tag;
	}
	
	public Integer selectByTagName2(String tagName) throws Exception {
		Tag tag = tagMapper.selectByTagName(tagName);
		if (tag!=null) {
			return tag.getTagId();
		}else {
			return  0;
		}
	}
	

	@Override
	public String getTagIds(String articleTagIds) throws Exception {
		if (StringUtils.isEmpty(articleTagIds)) {
			return "";
		}else {
			String[] tagNames= articleTagIds.replaceAll("，", ",").split(",");
			if (tagNames.length<=1) {
				return tagNames[0];
			}else {
				Tag tag = null;
				String tagIds ="";
				Integer id =0;
				for (int i = 0; i < tagNames.length; i++) {
					id = selectByTagName2(tagNames[i]);
					if (id==0) {
						tag = new Tag();
						tag.setTagName(tagNames[i]);
						tag.setTagStatus(1);
						id =insertTag(tag);
					}
					if (tagIds.isEmpty()) {
						tagIds = tagIds+id;
					}else {
						tagIds = tagIds+","+id;
					}
				}
				return tagIds;
			}
		}
	}


}
