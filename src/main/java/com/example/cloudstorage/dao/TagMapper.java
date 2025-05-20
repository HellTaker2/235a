package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    @Insert("INSERT INTO Tags(TagName,UID) VALUES(#{tagName},#{uid})")
    int createTag(@Param("tagName") String tagName,@Param("uid") Integer uid);
    @Select("SELECT COUNT(*) FROM Tags WHERE TagName = #{tagName} AND UID = #{uid}")
    int existTag(@Param("tagName") String tagName,@Param("uid") Integer uid);
    @Select("SELECT * FROM Tags WHERE TagName = #{tagName} AND UID = #{uid}")
    Tag selectTagBytagName(@Param("tagName") String tagName,@Param("uid") Integer uid);
}