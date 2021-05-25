package com.zymcloud.minioutil.template;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;

/**
 * @author zhaoyimeng
 * @date 2021/05/25
 */
public class MinioTemplate {

    private MinioClient client;

    public MinioTemplate(String endpoint, String accessKey, String secretKey) {
        this.client = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 创建bucket，如果存在返回false
     * @param name bucket名称
     * @return
     * @throws Exception
     */
    public boolean createBucket(String name) throws Exception {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(name).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(name).build());
            return true;
        }
        return false;
    }
}
