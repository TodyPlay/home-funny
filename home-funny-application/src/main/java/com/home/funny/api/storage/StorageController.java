package com.home.funny.api.storage;

import com.home.funny.service.StorageService;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/download/{id}")
    public Resource download(@PathVariable("id") Long id) throws IOException, MinioException, NoSuchAlgorithmException, InvalidKeyException {
        return storageService.download(id);
    }
}
