package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.FolderMapper;
import com.example.cloudstorage.dao.UserMapper;
import com.example.cloudstorage.entity.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FolderService {
    @Autowired
    private FolderMapper folderMapper;

    // 创建文件夹
    @Transactional
    public Folder createFolder(String folderName, String parentId, Integer uid) {
        // 检查文件夹名是否已存在
        if (folderMapper.countByParentAndName(parentId, folderName, uid) > 0) {
            throw new RuntimeException("文件夹名称已存在");
        }

        // 生成文件夹ID和路径
        String folderId = UUID.randomUUID().toString();
        String folderPath = generateFolderPath(parentId, folderName);

        Folder folder = new Folder();
        folder.setFolderId(folderId);
        folder.setUid(uid);
        folder.setFolderName(folderName);
        folder.setFolderPath(folderPath);
        folder.setParentId(parentId);
        folder.setCreateTime(LocalDateTime.now().toString());
        folder.setIsDeleted(0);
        folder.setTagId(null);

        folderMapper.createFolder(folder);
        return folder;
    }

    // 生成文件夹路径
    private String generateFolderPath(String parentId, String folderName) {
        if ("0".equals(parentId)) {
            return "/" + folderName + "/";
        } else {
            Folder parentFolder = folderMapper.getFolderById(parentId, 0); // 这里uid需要根据实际情况获取
            return parentFolder.getFolderPath() + folderName + "/";
        }
    }

    // 获取用户所有文件夹
    public List<Folder> getUserFolders(Integer uid) {
        return folderMapper.getFoldersByUser(uid);
    }

    // 获取子文件夹
    public List<Folder> getSubFolders(String parentId, Integer uid) {
        return folderMapper.getSubFolders(parentId, uid);
    }

    // 更新文件夹信息
    @Transactional
    public void updateFolder(String folderId, String newName, Integer tagId, Integer uid) {
        Folder folder = folderMapper.getFolderById(folderId, uid);
        if (folder == null) {
            throw new RuntimeException("文件夹不存在");
        }

        folder.setFolderName(newName);
        folder.setTagId(tagId);
        folderMapper.updateFolder(folder);
    }

    // 移动文件夹
    @Transactional
    public void moveFolder(String folderId, String newParentId, Integer uid) {
        Folder folder = folderMapper.getFolderById(folderId, uid);
        if (folder == null) {
            throw new RuntimeException("文件夹不存在");
        }

        String newFolderPath = generateFolderPath(newParentId, folder.getFolderName());
        folderMapper.moveFolder(folderId, newParentId, newFolderPath, uid);
    }

    // 删除文件夹
    @Transactional
    public void deleteFolder(String folderId, Integer uid) {
        folderMapper.deleteFolder(folderId, uid);
    }
}