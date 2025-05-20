package com.example.cloudstorage.service;

import com.example.cloudstorage.dao.SharingInfoMapper;
import com.example.cloudstorage.entity.SharingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharingService {
    @Autowired
    SharingInfoMapper sharingInfoMapper;
    public SharingInfo getSharingInfo(Integer sharingId,Integer uid) {
        return sharingInfoMapper.getSharingInfo(sharingId,uid);
    }
}