package com.home.funny.service;

import com.home.funny.model.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyStorageRepository;
import io.minio.*;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

        if (range != null && range.startsWith("bytes=")) {
            String range_ = range.replaceAll("bytes=", "");

            if (range_.startsWith("-")) {
                rangeStart = 0;
                rangeEnd = Long.parseLong(range_.split("-")[0]);
            } else if (range_.endsWith("-")) {
                rangeStart = Long.parseLong(range_.split("-")[0]);
                rangeEnd = stat.size() - 1;
            } else {
                String[] range_2 = range_.split("-");
                rangeStart = Long.parseLong(range_2[0]);
                rangeEnd = Long.parseLong(range_2[1]);
            }

        } else {
            rangeStart = 0;
            rangeEnd = stat.size() - 1;
        }

        length = rangeEnd - rangeStart;

        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(storage.getStorageName(), StandardCharsets.UTF_8)).build());
        httpHeaders.set(HttpHeaders.ACCEPT_RANGES, "bytes");
        httpHeaders.set(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d", rangeStart, rangeEnd, stat.size()));
        httpHeaders.setContentLength(length);


        GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                .bucket(storage.getStorageGroup())
                .object(storage.getStoragePath())
                .offset(rangeStart)
                .length(length)
                .build());


        return new ResponseEntity<>(new InputStreamResource(object), httpHeaders, HttpStatus.PARTIAL_CONTENT);
    }

    public void videoPreview(String bucketName, String path, HttpServletRequest request, HttpServletResponse res) throws Exception {

        StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(path).build());
        long fileSize = statObjectResponse.size();

        final long bufferStep = 1024 * 1024 * 3; // 流区间
        long startPos = 0; // 流起始位置
        long endPos = fileSize - 1; // 流结束位置

        String rangeHeader = request.getHeader("Range");
        if (!ObjectUtils.isEmpty(rangeHeader) && rangeHeader.startsWith("bytes=")) {
            try {
                // 断点续传 状态码206
                res.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                // 情景一：RANGE: bytes=2000070- 情景二：RANGE: bytes=2000070-2000970
                String numRang = request.getHeader("Range").replaceAll("bytes=", "");
                if (numRang.startsWith("-")) {
                    startPos = 0;
                    endPos = endPos - Long.parseLong(new String(numRang.getBytes(StandardCharsets.UTF_8), 1, numRang.length() - 1));
                } else if (numRang.endsWith("-")) {
                    startPos = Long.parseLong(new String(numRang.getBytes(StandardCharsets.UTF_8), 0, numRang.length() - 1));
                    endPos = fileSize - 1;
                } else {
                    String[] strRange = numRang.split("-");
                    if (strRange.length == 2) {
                        startPos = Long.parseLong(strRange[0].trim());
                        endPos = Long.parseLong(strRange[1].trim());
                    }
                }

                if (startPos < 0 || endPos < 0 || endPos >= fileSize || startPos > endPos) {
                    // range不满足
                    res.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                    return;
                }

            } catch (NumberFormatException e) {
                startPos = 0;
            }
        }


        if (endPos - startPos + 1 > bufferStep) {
            endPos = startPos + bufferStep - 1; //设置为定长流区间
        }
        if (endPos >= fileSize) {
            endPos = fileSize - 1;
        }
        long resContentLength = bufferStep; // response内容长度
        if (endPos == fileSize - 1) {
            resContentLength = endPos - startPos + 1; // 设置为文件剩余的实际长度
        }

        res.setHeader("Accept-Ranges", "bytes");
        res.setHeader("Content-Range", String.format("bytes %d-%d/%d", startPos, endPos, fileSize));
        res.addHeader("Content-Length", String.valueOf(resContentLength));

        GetObjectArgs objectArgs2 = GetObjectArgs.builder().bucket(bucketName).object(path).offset(startPos).length(bufferStep).build();

        try (InputStream tmpStream = minioClient.getObject(objectArgs2); ServletOutputStream stream = res.getOutputStream()) {
            IOUtils.copy(tmpStream, stream);
        }
    }

    public void video(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HomeFunnyStorage storage = storageRepository.findById(id).orElseThrow();
        this.videoPreview(storage.getStorageGroup(), storage.getStoragePath(), request, response);
    }
}
