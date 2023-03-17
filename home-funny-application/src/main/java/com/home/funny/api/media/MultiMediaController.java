package com.home.funny.api.media;

import com.home.funny.model.dto.HomeFunnyMultiMediaDto;
import com.home.funny.model.dto.paging.PageableDTO;
import com.home.funny.model.dto.query.MediaQueryDTO;
import com.home.funny.model.dto.HomeFunnyMediaDetailDto;
import com.home.funny.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public HomeFunnyMultiMediaDto media(@PathVariable Long id) {
        return mediaService.findById(id);
    }

    @PostMapping("media-detail-by-media-id/{id}")
    public List<HomeFunnyMediaDetailDto> mediaDetailByMediaId(@PathVariable Long id) {
        return mediaService.mediaDetailByMediaId(id);
    }
}
