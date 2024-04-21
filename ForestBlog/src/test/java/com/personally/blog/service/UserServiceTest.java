package com.personally.blog.service;

import com.personally.blog.BaseTest;
import com.personally.blog.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author 言曌
 * @date 2020/10/10 2:32 下午
 */

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        User user = userService.getUserById(122222);
        System.out.println(user);
        Assert.notNull(user, "用户不存在");
    }

    @Test
    public void getUserByName() {
        User user = userService.getUserByName("admin");
        System.out.println(user);
        Assert.notNull(user, "用户名不存在");
    }
}
