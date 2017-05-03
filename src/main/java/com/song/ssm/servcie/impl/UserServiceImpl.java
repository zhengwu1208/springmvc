package com.song.ssm.servcie.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.song.ssm.mapper.UserMapper;
import com.song.ssm.model.User;
import com.song.ssm.servcie.UserService;

/**
 * Created by zhengwu on 2017/3/19.
 */
@Service("userServcie")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> showUser() {
        return userMapper.selectByExample(null);
    }

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public void deleteUserById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
