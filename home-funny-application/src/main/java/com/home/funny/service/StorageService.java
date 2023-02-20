package com.home.funny.service;

import com.home.funny.model.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyStorageRepository;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private HomeFunnyStorageRepository storageRepository;

    public Resource download(Long id) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        HomeFunnyStorage storage = storageRepository.findById(id).orElseThrow();

        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder().bucket(storage.getStorageGroup()).object(storage.getStoragePath()).build());

        return new InputStreamResource(object);
    }
}
