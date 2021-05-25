# minio-util
```markdown
为简化 直接安装的windows版本
https://docs.min.io/docs/minio-quickstart-guide.html
```

```markdown
端口：9960
minio:
    endpoint: http://127.0.0.1:9000
    accessKey: minioadmin
    secretKey: minioadmin
    bucketName: test
    partSize: 20971520   # 20M
```
```markdown
Use the following command to run a standalone MinIO server on the Windows host. Replace D:\ with the path to the drive or directory in which you want MinIO to store data. You must change the terminal or powershell directory to the location of the minio.exe executable, or add the path to that directory to the system $PATH:

<code>minio.exe server D:\ </code>
```
