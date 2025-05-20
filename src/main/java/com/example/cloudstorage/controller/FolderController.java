package com.example.cloudstorage.controller;


import com.example.cloudstorage.entity.Folder;
import com.example.cloudstorage.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderController {
    @Autowired
    private FolderService folderService;

    // 创建文件夹
    @PostMapping
    public String createFolder(@RequestParam String folderName,
                               @RequestParam String parentId,
                               @RequestHeader("UID") Integer uid) {
        try {
            Folder folder = folderService.createFolder(folderName, parentId, uid);
            return "文件夹创建成功";
        } catch (Exception e) {
            return "400";
        }
    }

    // 获取用户所有文件夹
    @GetMapping
    public String getUserFolders(@RequestHeader("UID") Integer uid) {
        List<Folder> folders = folderService.getUserFolders(uid);
        return "获取成功";
    }

    // 获取子文件夹
    @GetMapping("/sub")
    public String getSubFolders(@RequestParam String parentId,
                                @RequestHeader("UID") Integer uid) {
        List<Folder> subFolders = folderService.getSubFolders(parentId, uid);
        return "获取成功";
    }

    // 更新文件夹
    @PutMapping("/{folderId}")
    public String updateFolder(@PathVariable String folderId,
                               @RequestParam String newName,
                               @RequestParam(required = false) Integer tagId,
                               @RequestHeader("UID") Integer uid) {
        try {
            folderService.updateFolder(folderId, newName, tagId, uid);
            return "文件夹更新成功";
        } catch (Exception e) {
            return "400";
        }
    }
}