package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.SharingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SharingInfoMapper extends BaseMapper<SharingInfo> {
    @Select("select * from sharingInfo where SharingID=#{sharingid} and UID=#{uid}")
    SharingInfo getSharingInfo(@Param("sharingid") Integer sharingid, @Param("uid") Integer uid);
}