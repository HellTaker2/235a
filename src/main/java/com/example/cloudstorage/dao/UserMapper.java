package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE UName = #{uname}")
    User selectUserByUname(String uname);

    @Insert("INSERT INTO user(uName, password) VALUES(#{uName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "uid") // 获取自增ID
    int insert(User user);

    // 查询最后一位用户（用于测试）
    @Select("SELECT * FROM user ORDER BY uid DESC LIMIT 1")
    User selectLastUser();
}
