package com.song.ssm.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.song.ssm.model.User;
import com.song.ssm.servcie.UserService;

/**
 * Created by zhengwu on 2017/3/19.
 */
public class UserControllerTest {
    private UserService userService;
    UserController      userController = new UserController();

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        userController.setUserService(userService);
        assertNotNull(userService);
    }

    @Test
    public void testShowUser1() throws Exception {
        User user1 = new User();
        user1.setUserName("欧阳锋");
        user1.setUserPhone("13212342345");
        user1.setUserEmail("欧阳锋@123.com");

        User user2 = new User();
        user2.setUserName("黄老邪");
        user2.setUserPhone("15552342345");
        user2.setUserEmail("黄老邪@123.com");

        User user3 = new User();
        user3.setUserName("郭靖");
        user3.setUserPhone("18812342345");
        user3.setUserEmail("郭靖@123.com");

        User user4 = new User();
        user4.setUserName("杨过");
        user4.setUserPhone("19012342345");
        user4.setUserEmail("杨过@123.com");

        List<User> userList = Lists.newArrayList(user1, user2, user3, user4);

        when(userService.showUser()).thenReturn(userList);

        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = userController.showUser(model);

        assertEquals("showUser", viewName);
        assertSame(userList, model.get("userList"));
        //TODO verify()用法
    }

}