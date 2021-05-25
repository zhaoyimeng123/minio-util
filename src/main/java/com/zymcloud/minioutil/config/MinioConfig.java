package com.zymcloud.minioutil.config;

import com.zymcloud.minioutil.template.MinioTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoyimeng
 * @date 2021/05/25
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.accessKey}")
    private String ACCESSKEY;
    @Value("${minio.secretKey}")
    private String SECRETKEY;

    @Bean
    public MinioTemplate minioTemplate(){
        return new MinioTemplate(ENDPOINT,ACCESSKEY,SECRETKEY);
    }
}
