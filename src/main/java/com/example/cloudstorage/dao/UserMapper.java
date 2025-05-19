package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE UName = #{uname}")
    User selectUserByUname(String uname);
}
