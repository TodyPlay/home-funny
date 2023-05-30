package com.home.funny.api.media;

import com.home.funny.model.dto.HomeFunnyMediaDetailDto;
import com.home.funny.service.HomeFunnyMediaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/multi-media-detail")
public class MultiMediaDetailController {

    @Autowired
    private HomeFunnyMediaDetailsService homeFunnyMediaDetailsService;

    @GetMapping("{id}")
    public HomeFunnyMediaDetailDto get(@PathVariable Long id) {
        return homeFunnyMediaDetailsService.detailById(id);
    }
}
