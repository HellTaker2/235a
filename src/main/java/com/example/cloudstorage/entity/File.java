package com.example.cloudstorage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class File {
    @TableId
    private String fileId;
    private Integer uid;
    private String fileName;
    private Long fileSize; // 使用Long避免溢出
    private String fileType;
    private String filePath;
    private String parentId; // 直接存储父文件夹ID
    private String createTime;
    private Integer isDeleted;
    private Integer tagId; // 允许为null

    // 构造方法、Getter/Setter省略
}
