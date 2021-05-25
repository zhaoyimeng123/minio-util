package com.zymcloud.minioutil.controller;

import com.zymcloud.minioutil.template.MinioTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaoyimeng
 * @date 2021/05/25
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
    @Resource
    private MinioTemplate minioTemplate;
    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 创建bucket
     * @param name bucket名称
     * @return
     * @throws Exception
     */
    @PostMapping("/createBucket")
    public boolean createBucket(String name) throws Exception{
        return minioTemplate.createBucket(name);
    }
}
