package com.home.funny.api.constant;

import com.home.funny.model.po.HomeFunnyMediaTag;
import com.home.funny.service.HomeFunnyMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {
    @Autowired
    private HomeFunnyMediaService homeFunnyMediaService;

    @GetMapping(value = "media-tags")
    public List<HomeFunnyMediaTag> mediaTags() {
        return homeFunnyMediaService.mediaTags();
    }
}
