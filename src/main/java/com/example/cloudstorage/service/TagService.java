package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.TagMapper;
import com.example.cloudstorage.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;
    public boolean createTag(String tagName, Integer uid) {
        int exists = tagMapper.existTag(tagName,uid);
        if (exists > 0) {
            return false;
        }
        return tagMapper.createTag(tagName,uid) > 0;
    }

    public Tag selectTagByTagName(String tagName, Integer uid) {
        return tagMapper.selectTagBytagName(tagName, uid);
    }
}