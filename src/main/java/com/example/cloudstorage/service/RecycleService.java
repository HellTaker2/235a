package com.example.cloudstorage.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cloudstorage.dao.FileMapper;
import com.example.cloudstorage.dao.RecycleMapper;
import com.example.cloudstorage.entity.File;
import com.example.cloudstorage.entity.Recycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RecycleService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private RecycleMapper recycleMapper;

    @Transactional
    public boolean moveFileToRecycle(String fileId) {
        File file = fileMapper.findByIdAndNotDeleted(fileId);
        if (file == null) {
            return false;
        }
        Recycle recycle = new Recycle();
        recycle.setFid(fileId);
        recycle.setUid(file.getUid());
        // 格式化当前时间为字符串
        String deleteTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        recycle.setDeleteTime(deleteTime);
        recycleMapper.insert(recycle);
        UpdateWrapper<File> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("FileID", fileId).set("IsDeleted", 1);
        fileMapper.update(null, updateWrapper);
        return true;
    }
}