package com.example.cloudstorage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudstorage.entity.Folder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FolderMapper extends BaseMapper<Folder> {
    // 创建文件夹
    @Insert("INSERT INTO Folder (FolderID, UID, FolderName, FolderPath, ParentID, CreateTime, IsDeleted, TagID) " +
            "VALUES (#{folderId}, #{uid}, #{folderName}, #{folderPath}, #{parentId}, #{createTime}, #{isDeleted}, #{tagId})")
    int createFolder(Folder folder);

    // 根据ID查询文件夹
    @Select("SELECT * FROM Folder WHERE FolderID = #{folderId} AND UID = #{uid} AND IsDeleted = 0")
    Folder getFolderById(@Param("folderId") String folderId, @Param("uid") Integer uid);

    // 查询用户的所有文件夹
    @Select("SELECT * FROM Folder WHERE UID = #{uid} AND IsDeleted = 0")
    List<Folder> getFoldersByUser(@Param("uid") Integer uid);

    // 查询子文件夹
    @Select("SELECT * FROM Folder WHERE ParentID = #{parentId} AND UID = #{uid} AND IsDeleted = 0")
    List<Folder> getSubFolders(@Param("parentId") String parentId, @Param("uid") Integer uid);

    // 更新文件夹信息
    @Update("UPDATE Folder SET FolderName = #{folderName}, TagID = #{tagId} WHERE FolderID = #{folderId} AND UID = #{uid}")
    int updateFolder(Folder folder);

    // 移动文件夹
    @Update("UPDATE Folder SET ParentID = #{newParentId}, FolderPath = #{newFolderPath} " +
            "WHERE FolderID = #{folderId} AND UID = #{uid}")
    int moveFolder(@Param("folderId") String folderId,
                   @Param("newParentId") String newParentId,
                   @Param("newFolderPath") String newFolderPath,
                   @Param("uid") Integer uid);

    // 软删除文件夹
    @Update("UPDATE Folder SET IsDeleted = 1 WHERE FolderID = #{folderId} AND UID = #{uid}")
    int deleteFolder(@Param("folderId") String folderId, @Param("uid") Integer uid);

    // 检查文件夹名是否已存在
    @Select("SELECT COUNT(*) FROM Folder WHERE ParentID = #{parentId} AND FolderName = #{folderName} AND UID = #{uid} AND IsDeleted = 0")
    int countByParentAndName(@Param("parentId") String parentId,
                             @Param("folderName") String folderName,
                             @Param("uid") Integer uid);
}