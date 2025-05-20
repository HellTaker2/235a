package com.example.cloudstorage.controller;

import com.example.cloudstorage.dao.FavouriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/favorite")
public class FavouriteController {
    @Autowired
    private FavouriteMapper favoriteMapper;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(
            @RequestParam String fid,
            @RequestParam int uid) {

        // 验证文件/文件夹是否存在且属于当前用户
        int fileCount = favoriteMapper.checkFileExists(fid, uid);
        int folderCount = favoriteMapper.checkFolderExists(fid, uid);
        if (fileCount == 0 && folderCount == 0) {
            return ResponseEntity.badRequest().body("文件或文件夹不存在或权限不足");
        }

        // 插入收藏记录
        try {
            favoriteMapper.insertFavorite(fid, uid);
            return ResponseEntity.ok("收藏成功");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("该内容已被收藏");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("服务器错误");
        }
    }
}