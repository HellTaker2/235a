package com.example.cloudstorage.controller;

import com.example.cloudstorage.entity.File;
import com.example.cloudstorage.entity.Result;
import com.example.cloudstorage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/{id}")
    public Result<File> getFile(@PathVariable("id") String id) {
        File file = fileService.getFileById(id);
        if (file != null) {
            return Result.success(file);
        } else {
            return Result.fail(404, "文件不存在");
        }
    }
}