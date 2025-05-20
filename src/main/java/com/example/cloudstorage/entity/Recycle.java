package com.example.cloudstorage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Recycle {
    @TableId
    private String fid;
    private Integer uid;
    @TableField("DeleteTime")
    private String deleteTime;

}
