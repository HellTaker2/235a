package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.Favourite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FavouriteMapper extends BaseMapper<Favourite> {

    int insertFavorite(@Param("fid") String fid, @Param("uid") int uid);
    int checkFileExists(@Param("fid") String fid, @Param("uid") int uid);
    int checkFolderExists(@Param("fid") String fid, @Param("uid") int uid);

}