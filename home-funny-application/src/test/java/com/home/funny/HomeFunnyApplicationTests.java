package com.home.funny;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.model.po.HomeFunnyMediaDetail;
import com.home.funny.model.po.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class HomeFunnyApplicationTests {

    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Commit
    void update() {
        HomeFunnyMultiMedia media = homeFunnyMultiMediaRepository.findById(47L).orElseThrow();

        media.getMediaDetails().remove(0);

        homeFunnyMultiMediaRepository.save(media);

        System.out.println(media);
    }

    @Test
    void save() {
        HomeFunnyMultiMedia media = new HomeFunnyMultiMedia();
        media.setName("name");

        HomeFunnyMediaDetail detail = new HomeFunnyMediaDetail();
        detail.setMultiMedia(media);
        detail.setDetailName("detail");

        media.setMediaDetails(List.of(detail));

        homeFunnyMultiMediaRepository.save(media);
    }

}
