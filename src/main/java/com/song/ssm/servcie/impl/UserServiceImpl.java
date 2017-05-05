package com.song.ssm.servcie.impl;

import com.song.ssm.mapper.UserMapper;
import com.song.ssm.model.User;
import com.song.ssm.model.UserExample;
import com.song.ssm.servcie.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void deleteUsers(List<User> users) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        for (User user : users) {
            criteria.andUserNameEqualTo(user.getUserName());
            userMapper.deleteByExample(example);
        }

    }

    @Override
    public void deleteUserByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(name);
        userMapper.deleteByExample(example);
    }
}
