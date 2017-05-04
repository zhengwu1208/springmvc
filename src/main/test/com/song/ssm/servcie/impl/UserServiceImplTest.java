package com.song.ssm.servcie.impl;

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
 *
 *
 * Created by zhengwu on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
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

    }

    /**
     * 删除数据
     * 要把上面的注解注释掉，否则没效果
     */
    @After
    public void destroy() {
    }

    /**
     * 多线程不会回滚
     * @throws InterruptedException
     */
    @Test
    private void test1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new ExecuteThread()).start();
            latch.countDown();
        }
        Thread.sleep(3000);
    }

    private CountDownLatch latch = new CountDownLatch(10);

    private class ExecuteThread implements Runnable {

        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<User> list = userService.showUser();
            System.out.println(list);
        }
    }

    @Test
    public void test2() {
        MockHttpServletRequest request = new MockHttpServletRequest();

    }
}