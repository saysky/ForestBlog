package com.hfanss.blog.mapper;

import com.hfanss.blog.entity.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    
    Tag selectByTagName(String tagName);
}