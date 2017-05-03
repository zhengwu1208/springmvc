package com.song.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.song.ssm.model.User;
import com.song.ssm.servcie.UserService;

/**
 * Created by zhengwu on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    Logger              logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public ModelAndView showUser() {
        logger.info("UserController.showUser()【查询所有用户1】 start");
        List<User> userList = userService.showUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(userList);
        modelAndView.setViewName("showUser");
        logger.info("UserController.showUser()【查询所有用户1】 end");
        return modelAndView;
    }

    @RequestMapping("/testAjax")
    public @ResponseBody List<User> testAjax() {
        logger.info("测试ajax请求 start");
        return userService.showUser();
    }

    @RequestMapping({ "/test", "/home" })
    public String showUser(Map<String, Object> model) {
        logger.info("【查询所有用户2】 start");
        List<User> userList = userService.showUser();
        model.put("userList", userList);
        logger.info("【查询所有用户2】 start");
        return "showUser";
    }

    @RequestMapping("/testGet")
    public String testGet(Model model) {
        logger.info("testGet() start,model={}", model);
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setUserName("song");
        user.setUserPhone("123456789");
        user.setUserEmail("123@123.com");
        userList.add(user);
        model.addAttribute(userList);
        logger.info("testGet() end,model={}", model);
        return "showUser";
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost(Model model) {
        logger.info("testPost() start,model={}", model);
        return "showUser";
    }

    @RequestMapping(value = "/testParams", params = "song")
    public String testParams(Model model) {
        logger.info("testParams() start,model={}", model);
        return "redirect:http://www.baidu.com";
    }

    @RequestMapping("/testUser2")
    public String testUser2(String userName, String userPhone, String userEmail) {
        logger.info("testUser2() start,userName={},userPhone={},userEmail={}", userName, userPhone,
            userEmail);
        return "showUser";
    }

    @RequestMapping("/testUser3")
    public String testUser3(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        logger.info("testUser3() start,userName={},userPhone={},userEmail={}", userName, userPhone,
            userEmail);
        return "showUser";
    }

    @RequestMapping("/insertUser")
    public String insertUser(User user) {
        logger.info("插入用户 start");
        logger.info("userName={},userPhone={},userEmail={}", user.getUserName(),
            user.getUserPhone(), user.getUserEmail());
        userService.insertUser(user);
        logger.info("插入用户 end");
        return "showUser";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        logger.info("删除用户数据ByUserId start");
        userService.deleteUserById(id);
        logger.info("删除用户数据ByUserId end");
        return "showUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user) {
        logger.info("更新用户数据 start");
        userService.updateUser(user);
        logger.info("更新用户数据 end");
        return "showUser";
    }

    //=========================set方法，给单元测试用==========================//
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
