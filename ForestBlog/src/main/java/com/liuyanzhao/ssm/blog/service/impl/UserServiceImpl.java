package com.liuyanzhao.ssm.blog.service.impl;

import com.liuyanzhao.ssm.blog.mapper.ArticleMapper;
import com.liuyanzhao.ssm.blog.mapper.CommentMapper;
import com.liuyanzhao.ssm.blog.mapper.UserMapper;
import com.liuyanzhao.ssm.blog.entity.User;
import com.liuyanzhao.ssm.blog.service.ArticleService;
import com.liuyanzhao.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 * @author 言曌
 * @date 2017/8/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        // 删除用户
        userMapper.deleteById(id);
        // 删除评论
        commentMapper.deleteByUserId(id);
        // 删除文章
        List<Integer> articleIds = articleMapper.listArticleIdsByUserId(id);
        if (articleIds != null && articleIds.size() > 0) {
            for (Integer articleId : articleIds) {
                articleService.deleteArticle(articleId);
            }
        }
    }

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }


}
