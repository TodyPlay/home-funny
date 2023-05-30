package com.home.funny;

import com.home.funny.model.po.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
class HomeFunnyApplicationTests {


    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;

    @Test
    void update() {
        HomeFunnyMultiMedia media = homeFunnyMultiMediaRepository.findById(47L).orElseThrow();

        media.getMediaDetails().remove(0);

        homeFunnyMultiMediaRepository.save(media);

        System.out.println(media);
    }

    @Test
    void save() {

    }

    @Test
    void find() {
        Optional<HomeFunnyMultiMedia> like = homeFunnyMultiMediaRepository.findByMediaTags_NameLike(null);
        log.debug("{}", like);
    }
}
