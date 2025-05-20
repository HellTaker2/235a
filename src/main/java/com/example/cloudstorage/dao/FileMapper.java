package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface FileMapper extends BaseMapper<File> {

    @Select("SELECT * FROM File WHERE FileID = #{fileId} AND IsDeleted = 0")
    File findByIdAndNotDeleted(@Param("fileId") String fileId);
}