package com.home.funny.service;

import com.home.funny.config.MinioClientConfiguration;
import com.home.funny.model.converter.HomeFunnyStorageMapper;
import com.home.funny.model.dto.HomeFunnyStorageDto;
import com.home.funny.model.po.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyStorageRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class HomeFunnyStorageService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private HomeFunnyStorageRepository storageRepository;
    @Autowired
    private MinioClientConfiguration minioConfig;
    @Autowired
    private HomeFunnyStorageMapper homeFunnyStorageMapper;

    public ResponseEntity<Resource> download2(Long id, String range) throws Exception {
        HomeFunnyStorage storage = storageRepository.findById(id).orElseThrow();

        StatObjectResponse stat;
        try {
            stat = minioClient.statObject(StatObjectArgs.builder().bucket(storage.getStorageGroup()).object(storage.getStoragePath()).build());
        } catch (ErrorResponseException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

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

    @Transactional
    public HomeFunnyStorageDto upload(MultipartFile part) throws Exception {
        String name = Objects.requireNonNull(part.getOriginalFilename());
        String group = minioConfig.getBudget();

        String[] nameSplit = name.split("\\.");
        String path = UUID.randomUUID().toString();

        if (nameSplit.length >= 2) {
            path += "." + nameSplit[nameSplit.length - 1];
        }

        HomeFunnyStorage homeFunnyStorage = HomeFunnyStorage.builder()
                .storageName(name)
                .storageGroup(group)
                .storagePath(path)
                .size(part.getSize())
                .inputStream(part.getInputStream())
                .build();

        return homeFunnyStorageMapper.toDto(storageRepository.save(homeFunnyStorage));
    }

    public HomeFunnyStorageDto storage(Long id) {
        return storageRepository.findById(id).map(homeFunnyStorageMapper::toDto).orElseThrow();
    }

    @Transactional
    public void delete(Long id) {
        storageRepository.deleteById(id);
    }

    public Page<HomeFunnyStorageDto> findAll(Pageable page) {
        return storageRepository.findAll(page).map(homeFunnyStorageMapper::toDto);
    }
}
