package com.example.cloudstorage.controller;

import com.example.cloudstorage.entity.Result;
import com.example.cloudstorage.entity.SharingInfo;
import com.example.cloudstorage.service.SharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files/shares")
public class ShareController {
    @Autowired
    SharingService sharingService;
    @GetMapping("/{SharingID}")
    public Result<Object> getSharingService(@PathVariable("SharingID") Integer sharingId,
                                            @RequestHeader("uid") Integer uid, @RequestHeader("token") String token) {
        SharingInfo success= sharingService.getSharingInfo(sharingId,uid);
        if (success!=null) {
            return Result.success(success);
        }
        else {
            return Result.fail(404,"文件不存在");
        }
    }
}