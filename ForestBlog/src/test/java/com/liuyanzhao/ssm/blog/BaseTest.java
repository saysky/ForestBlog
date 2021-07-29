package com.liuyanzhao.ssm.blog;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 响应一个朋友号召，给其讲解项目，加单元测试
 * @author 言曌
 * @date 2020/10/10 2:32 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-mvc.xml", "classpath:mybatis/mybatis-config.xml"})
public class BaseTest {
}
