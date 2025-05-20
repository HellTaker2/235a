package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.FileMapper;
import com.example.cloudstorage.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public File getFileById(String fileId) {
        return fileMapper.findByIdAndNotDeleted(fileId);
    }
}