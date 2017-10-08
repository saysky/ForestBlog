package com.liuyanzhao.blog.mapper.custom;

import com.liuyanzhao.blog.entity.custom.NoticeCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/4.
 */
public interface NoticeMapperCustom {
	
	//获得公告总数
	public Integer countNotice(@Param(value = "status") Integer status) throws Exception;
	
	//获得公告列表
	public List<NoticeCustom> listNotice(@Param(value = "status") Integer status) throws Exception;


}
