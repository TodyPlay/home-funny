package com.home.funny;

import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CompletableFuture;

public class HomeFunnyTests {

    @Test
    @SneakyThrows
    void minio() {
        MinioAsyncClient client = MinioAsyncClient.builder()
                .endpoint("http://192.168.241.128:9000")
                .credentials("minioadmin", "minioadmin")
                .build();

        File file = new File("D:\\下载\\NeteaseCloudMusic_Music_official_2.10.10.201297_32.exe");

        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));


        var object = PutObjectArgs.builder().stream(is, file.length(), 1024 * 1024 * 5).bucket("video").object("multiArgs").build();

        CompletableFuture<ObjectWriteResponse> f = client.putObject(object);

        ObjectWriteResponse objectWriteResponse = f.get();

        System.out.println(objectWriteResponse);
    }
}
