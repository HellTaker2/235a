package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.UserMapper;
import com.example.cloudstorage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User hello(String uname){
        return userMapper.selectUserByUname(uname);
    }
}
