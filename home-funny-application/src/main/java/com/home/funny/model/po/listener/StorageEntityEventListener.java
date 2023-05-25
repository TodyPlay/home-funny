package com.home.funny.model.po.listener;

import com.home.funny.model.po.HomeFunnyStorage;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageEntityEventListener {

    @Autowired
    private MinioClient minioClient;

    @PostRemove
    public void postRemove(HomeFunnyStorage store) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(store.getStorageGroup()).object(store.getStoragePath()).build());
    }

    @PostPersist
    void postPersist(HomeFunnyStorage storage) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(storage.getStorageGroup()).object(storage.getStoragePath()).stream(storage.getInputStream(), storage.getSize(), 100 * 1024 * 1024).build());
    }
}
