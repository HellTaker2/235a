package com.example.cloudstorage.entity;

import lombok.Data;

@Data
public class SharingInfo {
    private Integer sharingId;
    private Integer uid;
    private String fileId; // 允许为null
    private String folderId; // 允许为null
    private String sharingTime;
    private String sharelink;
    private Integer access;
}