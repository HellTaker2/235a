package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.Favourite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DuplicateKeyException;

@Mapper
public interface FavouriteMapper extends BaseMapper<Favourite> {

    @Insert("INSERT INTO favorite (FID, UID) VALUES (#{fid}, #{uid})")
    int insertFavorite(@Param("fid") String fid, @Param("uid") int uid) throws DuplicateKeyException;

    @Select("SELECT COUNT(*) FROM file WHERE FileID = #{fid} AND UID = #{uid}")
    int checkFileExists(@Param("fid") String fid, @Param("uid") int uid);

    @Select("SELECT COUNT(*) FROM folder WHERE FolderID = #{fid} AND UID = #{uid}")
    int checkFolderExists(@Param("fid") String fid, @Param("uid") int uid);

}