package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.UserMapper;
import com.example.cloudstorage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User hello(String uname){
        return userMapper.selectUserByUname(uname);
    }

    public ResponseEntity<Map<String, Object>> registerUser(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.selectUserByUname(user.getUName());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "用户已存在"));
        }

        // 插入新用户（会自动填充自增ID）
        int result = userMapper.insert(user);
        if (result > 0) {
            // 获取最后一位用户（验证自增ID）
            User lastUser = userMapper.selectLastUser();

            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("lastUserId", lastUser.getUid());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "注册失败"));
        }
    }
}
