package com.home.funny.service;

import com.home.funny.model.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyStorageRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class StorageService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private HomeFunnyStorageRepository storageRepository;

    public ResponseEntity<Resource> download2(Long id, String range) throws Exception {
        HomeFunnyStorage storage = storageRepository.findById(id).orElseThrow();

        StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder().bucket(storage.getStorageGroup()).object(storage.getStoragePath()).build());

        List<HttpRange> httpRanges = HttpRange.parseRanges(range);

        if (httpRanges.isEmpty()) {
            return fullStorage(storage, stat);
        }

        if (httpRanges.size() == 1) {
            return partialStorage(storage, stat, httpRanges.get(0));
        }
        // TODO: 2023/2/23 multi partial range
        return ResponseEntity.ok().build();
    }

    @NotNull
    private ResponseEntity<Resource> partialStorage(HomeFunnyStorage storage, StatObjectResponse stat, HttpRange range) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {

        long rangeStart = range.getRangeStart(stat.size());
        long rangeEnd = range.getRangeEnd(stat.size());
        long contentLength = rangeEnd - rangeStart + 1;

        InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(storage.getStorageGroup())
                .object(storage.getStoragePath())
                .offset(rangeStart)
                .length(contentLength)
                .build());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(storage.getStorageName(), StandardCharsets.UTF_8)).build());
        httpHeaders.setContentLength(contentLength);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.set(HttpHeaders.ACCEPT_RANGES, "bytes");
        httpHeaders.set(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d", rangeStart, rangeEnd, stat.size()));

        return new ResponseEntity<>(new InputStreamResource(stream), httpHeaders, HttpStatus.PARTIAL_CONTENT);
    }

    @NotNull
    private ResponseEntity<Resource> fullStorage(HomeFunnyStorage storage, StatObjectResponse stat) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(storage.getStorageName(), StandardCharsets.UTF_8)).build());
        httpHeaders.setContentLength(stat.size());
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(storage.getStorageGroup())
                .object(storage.getStoragePath())
                .build());
        return new ResponseEntity<>(new InputStreamResource(stream), httpHeaders, HttpStatus.OK);
    }

}
