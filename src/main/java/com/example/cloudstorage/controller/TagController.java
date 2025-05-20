package com.example.cloudstorage.controller;

import com.example.cloudstorage.entity.Tag;
import com.example.cloudstorage.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
    public ResponseEntity<Map<String, Object>> createTag(@RequestParam("tagName") String tagName, @RequestHeader("uid") Integer uid) {
        if (tagName == null || tagName.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(400, "标签无效命名", null));
        }

        boolean success = tagService.createTag(tagName, uid);
        if (success) {
            Tag createdTag = tagService.selectTagByTagName(tagName, uid);
            return ResponseEntity.status(HttpStatus.CREATED).body(createResponse(1, "新建成功", createdTag));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(createResponse(409, "标签已存在", null));
        }
    }

    private Map<String, Object> createResponse(int code, String msg, Tag data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("msg", msg);
        response.put("data", data);
        return response;
    }
}