package com.example.cloudstorage.entity;

import lombok.Data;

@Data
public class Folder {
    private String folderId;
    private Integer uid;
    private String folderName;
    private String folderPath;
    private String parentId; // 直接存储父文件夹ID
    private String createTime;
    private Integer isDeleted;
    private Integer tagId; // 允许为null
}
