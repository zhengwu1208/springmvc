package com.song.ssm.servcie;

import com.song.ssm.model.User;

import java.util.List;

/**
 * Created by zhengwu on 2017/3/19.
 */
public interface UserService {

    List<User> showUser();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteUsers(List<User> users);

    void deleteUserByName(String name);
}
