package com.home.funny.api.media;

import com.home.funny.model.dto.HomeFunnyMultiMediaDto;
import com.home.funny.model.dto.paging.PageableDTO;
import com.home.funny.model.dto.query.MediaQueryDTO;
import com.home.funny.service.HomeFunnyMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/multi-media")
public class MultiMediaController {

    @Autowired
    private HomeFunnyMediaService homeFunnyMediaService;

    @GetMapping("{id}")
    public HomeFunnyMultiMediaDto media(@PathVariable Long id) {
        return homeFunnyMediaService.findById(id);
    }

    @PostMapping
    public Page<HomeFunnyMultiMediaDto> medias(@RequestBody MediaQueryDTO query, PageableDTO page) {
        return homeFunnyMediaService.findMedias(query, page);
    }

    @PutMapping
    public HomeFunnyMultiMediaDto save(@RequestBody @Validated HomeFunnyMultiMediaDto homeFunnyMediaDetailDto) {
        return homeFunnyMediaService.saveOrUpdate(homeFunnyMediaDetailDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        homeFunnyMediaService.delete(id);
    }


}
