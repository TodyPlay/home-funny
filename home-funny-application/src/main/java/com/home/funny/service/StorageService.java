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

    public ResponseEntity<Resource> download(Long id, String range) throws Exception {
        HomeFunnyStorage storage = storageRepository.findById(id).orElseThrow();

        StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder().bucket(storage.getStorageGroup()).object(storage.getStoragePath()).build());

        HttpHeaders httpHeaders = new HttpHeaders();

        long rangeStart;

        long rangeEnd;

        long length;

        HttpStatus status;

        if (range != null && range.startsWith("bytes=")) {
            String _range = range.replaceAll("bytes=", "");

            if (_range.startsWith("-")) {
                rangeStart = 0;
                rangeEnd = Long.parseLong(_range.split("-")[0]);
            } else if (_range.endsWith("-")) {
                rangeStart = Long.parseLong(_range.split("-")[0]);
                rangeEnd = stat.size() - 1;
            } else {
                String[] range_2 = _range.split("-");
                rangeStart = Long.parseLong(range_2[0]);
                rangeEnd = Long.parseLong(range_2[1]);
            }
            status = (length = rangeEnd - rangeStart) == 0 ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT;
            httpHeaders.set(HttpHeaders.ACCEPT_RANGES, "bytes");
            httpHeaders.set(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d", rangeStart, rangeEnd, stat.size()));
        } else {
            rangeStart = 0;
            rangeEnd = stat.size() - 1;
            status = HttpStatus.OK;
            length = rangeEnd - rangeStart;
        }

        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(storage.getStorageName(), StandardCharsets.UTF_8)).build());
        httpHeaders.setContentLength(length);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        InputStream stream = length == 0 ? InputStream.nullInputStream() : minioClient.getObject(GetObjectArgs.builder()
                .bucket(storage.getStorageGroup())
                .object(storage.getStoragePath())
                .offset(rangeStart)
                .length(length)
                .build());

        return new ResponseEntity<>(new InputStreamResource(stream), httpHeaders, status);
    }

    public Object download_2_with_ranges(Long id, String range) throws Exception {
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
    }

    @NotNull
    private ResponseEntity<InputStreamResource> partialStorage(HomeFunnyStorage storage, StatObjectResponse stat, HttpRange range) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {

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
    private ResponseEntity<InputStreamResource> fullStorage(HomeFunnyStorage storage, StatObjectResponse stat) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
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
