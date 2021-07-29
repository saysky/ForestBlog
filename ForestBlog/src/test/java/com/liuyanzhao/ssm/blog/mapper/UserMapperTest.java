package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.BaseTest;
import com.liuyanzhao.ssm.blog.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author 言曌
 * @date 2020/10/10 2:13 下午
 */

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUser() {
        List<User> userList = userMapper.listUser();
        System.out.println(userList);
        Assert.notEmpty(userList, "用户数量为空");
    }


    @Test
    public void insert() {
        User user = new User();
        user.setUserId(1234);
        user.setUserName("test123");
        user.setUserNickname("测试用户");
        user.setArticleCount(0);
        user.setUserEmail("test123@gmail.com");
        user.setUserPass("123456");
        user.setUserRegisterTime(new Date());
        user.setUserUrl("https://www.baidu.com");
        user.setUserStatus(1);
        int row = userMapper.insert(user);
        Assert.isTrue(row == 1, "创建用户失败");
    }


    @Test
    public void deleteById() {
        int row = userMapper.deleteById(1234);
        Assert.isTrue(row == 1, "删除用户失败");
    }

}
