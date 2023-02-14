package com.home.funny;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeFunnyApplicationTests {

    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void query() throws JsonProcessingException {

    }

}
