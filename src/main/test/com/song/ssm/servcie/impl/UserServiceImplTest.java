package com.song.ssm.servcie.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.song.ssm.model.User;
import com.song.ssm.servcie.UserService;

/**
 * 四类：
 * 1.带事务 ：自动会回滚，可以多次执行，数据库中不会有脏数据
 * 2.多线程 ：注意此时的事务不能用上面的方式，要单独写方法删除 @after,且要把上面的注解删除掉
 * 3.外部请求 ：自己虚拟实现一个，配置到配置文件中管理
 * 4.复杂参数 ：用mock，如：MockHttpServletRequest
 * <p>
 * <p>
 * Created by zhengwu on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml"})
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void testShowUser() throws Exception {
        List<User> list = userService.showUser();
        System.out.println(list);
    }

    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setUserName("songzhengwu");
        user.setUserPhone("13758450455");
        userService.insertUser(user);
    }

    /**
     * 删除数据
     * 要把上面的注解注释掉，否则没效果
     */
    @After
    public void destroy() {
        userService.deleteUserByName("songzhengwu");
    }

    private CountDownLatch latch = new CountDownLatch(10);

    /**
     * 多线程不会回滚
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new ExecuteThread()).start();
            latch.countDown();
        }
        Thread.sleep(2000);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private class ExecuteThread implements Runnable {
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            User user1 = new User();
            user1.setUserName("songzhengwu");
            user1.setUserPhone("13758450455");
            userService.insertUser(user1);

            List<User> list = userService.showUser();


            for (User user : list) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "===" + user);
            }
        }
    }

    @Test
    public void test2() {
        MockHttpServletRequest request = new MockHttpServletRequest();
    }
}