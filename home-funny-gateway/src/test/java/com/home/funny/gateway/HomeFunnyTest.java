package com.home.funny.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class HomeFunnyTest {

    DefaultDataBufferFactory defaultDataBufferFactory = new DefaultDataBufferFactory();

    @Test
    void dataBuffer() throws IOException {}
}
