package com.home.funny.api.media;

import com.home.funny.dto.HomeFunnyMultiMediaDto;
import com.home.funny.dto.PageableDTO;
import com.home.funny.dto.media.MediaQueryDTO;
import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/multi-media")
public class MultiMediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("medias")
    public Page<HomeFunnyMultiMediaDto> medias(@RequestBody MediaQueryDTO query, PageableDTO page) {
        return mediaService.findMedias(query, page);
    }

    @PostMapping("medias/{id}")
    public HomeFunnyMultiMediaDto media(@PathVariable Long id){
        return mediaService.findById(id);
    }
}
