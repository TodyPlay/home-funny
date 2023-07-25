package com.home.funny;

import io.minio.MinioAsyncClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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

    @Test
    void chongQing() {

        String temp = "500000|    重庆市\r\n" +
                "500100|    重庆市 市辖区\r\n" +
                "500101|    重庆市 万州区\r\n" +
                "500102|    重庆市 涪陵区\r\n" +
                "500103|    重庆市 渝中区\r\n" +
                "500104|    重庆市 大渡口区\r\n" +
                "500105|    重庆市 江北区\r\n" +
                "500106|    重庆市 沙坪坝区\r\n" +
                "500107|    重庆市 九龙坡区\r\n" +
                "500108|    重庆市 南岸区\r\n" +
                "500109|    重庆市 北碚区\r\n" +
                "500110|    重庆市 万盛区\r\n" +
                "500111|    重庆市 双桥区\r\n" +
                "500112|    重庆市 渝北区\r\n" +
                "500113|    重庆市 巴南区\r\n" +
                "500114|    重庆市 黔江区\r\n" +
                "500115|    重庆市 长寿区\r\n" +
                "500200|    重庆市 县\r\n" +
                "500222|    重庆市 綦江县\r\n" +
                "500223|    重庆市 潼南县\r\n" +
                "500224|    重庆市 铜梁县\r\n" +
                "500225|    重庆市 大足县\r\n" +
                "500226|    重庆市 荣昌县\r\n" +
                "500227|    重庆市 璧山县\r\n" +
                "500228|    重庆市 梁平县\r\n" +
                "500229|    重庆市 城口县\r\n" +
                "500230|    重庆市 丰都县\r\n" +
                "500231|    重庆市 垫江县\r\n" +
                "500232|    重庆市 武隆县\r\n" +
                "500233|    重庆市 忠县\r\n" +
                "500234|    重庆市 开县\r\n" +
                "500235|    重庆市 云阳县\r\n" +
                "500236|    重庆市 奉节县\r\n" +
                "500237|    重庆市 巫山县\r\n" +
                "500238|    重庆市 巫溪县\r\n" +
                "500240|    重庆 重庆市 石柱土家族自治县\r\n" +
                "500241|    重庆 重庆市 秀山土家族苗族自治县\r\n" +
                "500242|    重庆 重庆市 酉阳土家族苗族自治县\r\n" +
                "500243|    重庆 重庆市 彭水苗族土家族自治县\r\n" +
                "500381|    重庆 江津市\r\n" +
                "500382|    重庆 合川市\r\n" +
                "500383|    重庆 永川市\r\n" +
                "500384|    重庆 南川市";
        StringReader stringReader = new StringReader(temp);

        BufferedReader bufferedReader = new BufferedReader(stringReader);
        Stream<String> lines = bufferedReader.lines();

        lines.forEach(line -> {

            String[] split = line.split("\\|");

            String code = split[0].substring(0, 6);

            String[] names = split[1].trim().split("\\s");
            String name = names[names.length - 1];

            System.out.printf("CHONG_QING.put(\"%s\",\"%s\");%n", code, name);
        });


    }

}
