package com.example.cloudstorage.controller;

import com.example.cloudstorage.entity.Result;
import com.example.cloudstorage.service.RecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recycle")
public class RecycleController {

    @Autowired
    private RecycleService recycleService;

    @PostMapping("/{id}/moveFileToRecycle")
    public Result<Object> moveFileToRecycle(@PathVariable("id") String id) {
        boolean success = recycleService.moveFileToRecycle(id);
        if (success) {
            return Result.success(null);
        } else {
            return Result.fail(404, "文件不存在");
        }
    }
}