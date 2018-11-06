package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.UserCustom;

import java.util.List;

/**
 * Created by 言曌 on 2017/8/24.
 */
public interface UserService {
	public List<UserCustom> listUser() throws Exception;
	
	//根据id查询用户信息
	public UserCustom getUserById(Integer id) throws Exception;
	
	//修改用户信息
	public void updateUser(User user) throws Exception;
	
	//删除用户
	public void deleteUser(Integer id) throws  Exception;
	
	//添加用户
	public void insertUser(User user) throws Exception;

	//根据用户名和邮箱查询用户
	public User getUserByNameOrEmail(String str) throws Exception;

	//根据用户名查询用户
	public User getUserByName(String name) throws Exception;

	//根据邮箱查询用户
	public User getUserByEmail(String email) throws Exception;
}
