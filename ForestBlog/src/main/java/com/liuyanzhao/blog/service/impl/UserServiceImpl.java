package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import com.liuyanzhao.blog.mapper.UserMapper;
import com.liuyanzhao.blog.mapper.custom.UserMapperCustom;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.UserCustom;
import com.liuyanzhao.blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户管理
 * Created by 言曌 on 2017/8/24.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapperCustom userMapperCustom;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserCustom> listUser() throws Exception {
		List<UserCustom> userCustomList = userMapperCustom.listUser();
		for(int i=0;i<userCustomList.size();i++) {
			Integer articleCount = userMapperCustom.countArticleByUser(userCustomList.get(i).getUserId());
			userCustomList.get(i).setArticleCount(articleCount);
		}
		return userCustomList;
	}
	
	@Override
	public UserCustom getUserById(Integer id) throws Exception {
		
		User user = userMapper.selectByPrimaryKey(id);
		UserCustom userCustom = new UserCustom();
		BeanUtils.copyProperties(user,userCustom);
		return userCustom;
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void insertUser(User user) throws Exception {
		user.setUserRegisterTime(new Date());
		userMapper.insertSelective(user);
	}

	@Override
	public User getUserByNameOrEmail(String str) throws Exception {
		User user = userMapperCustom.getUserByNameOrEmail(str);
		return user;
	}

	@Override
	public User getUserByName(String name) throws Exception {
		User user = userMapperCustom.getUserByName(name);
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		User user = userMapperCustom.getUserByEmail(email);
		return user;
	}


}
