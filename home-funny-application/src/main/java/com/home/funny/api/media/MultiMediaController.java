package com.home.funny.api.media;

import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/multi-media")
public class MultiMediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("all")
    public Flux<HomeFunnyMultiMedia> all() {
        return mediaService.all();
    }
}
