package com.zymcloud.minioutil.template;

import io.minio.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhaoyimeng
 * @date 2021/05/25
 */
public class MinioTemplate {
    private static final Logger logger = LoggerFactory.getLogger(MinioTemplate.class);

    private MinioClient client;

    @Value("${minio.partSize}")
    private Long partSize;

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

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param file 文件
     * @return 文件名
     */
    public String putObject(String bucketName, MultipartFile file) throws Exception {
        String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        // 将文件的原始名称保存下来，使用 #### 分割， 以便后续下载文件时能够拿到原始文件名称
        String originalFileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        String objectName = originalFileName + "####" + UUID.randomUUID().toString().replaceAll("-", "").substring(0,10) + "." + suffix;
        try {
            return this.putObject(bucketName, objectName, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("上传文件异常");
        }
    }

    /**
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param file       文件
     * @return 文件名称
     * @throws Exception 文件流获取异常
     */
    public String putObject(String bucketName, String objectName, MultipartFile file) throws Exception {
        this.putObject(bucketName, objectName, file.getInputStream(), file.getSize());
        return objectName;
    }

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @param size       文件大小
     */
    public String putObject(String bucketName, String objectName, InputStream stream, Long size) throws Exception {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, partSize)
                .build();
        ObjectWriteResponse objectWriteResponse;
        try {
            objectWriteResponse = client.putObject(putObjectArgs);
            return objectWriteResponse.object();
        } catch (Exception e) {
            logger.error(putObjectArgs.toString() + e.getMessage(), e);
            throw new Exception("上传文件异常");
        }
    }

    /**
     * 获取文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            return client.getObject(getObjectArgs);
        } catch (Exception e) {
            logger.error(getObjectArgs.toString() + e.getMessage(), e);
            throw new Exception("文件不存在");
        }
    }

    /**
     * 删除文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     *                   https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            client.removeObject(removeObjectArgs);
        } catch (Exception e) {
            logger.error(removeObjectArgs.toString() + e.getMessage(), e);
            throw new Exception("移除文件异常");
        }
    }
}
