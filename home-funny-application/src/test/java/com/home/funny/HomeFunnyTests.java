package com.home.funny;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HomeFunnyTests {
    @Test
    void minio() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient client = MinioClient.builder().endpoint("http://192.168.253.128:9000/")
                .credentials("minioadmin", "minioadmin")
                .build();

        client.downloadObject(DownloadObjectArgs.builder().bucket("video").object("xx.zip").filename("pom2.xml").build());


    }
}
