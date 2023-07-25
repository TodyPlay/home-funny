package com.home.funny.api.constant;

import com.home.funny.model.dto.HomeFunnyMediaTagDto;
import com.home.funny.model.po.HomeFunnyMediaTag;
import com.home.funny.service.HomeFunnyTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {
    @Autowired
    private HomeFunnyTagService homeFunnyTagService;

    @GetMapping(value = "media-tags")
    public List<HomeFunnyMediaTag> mediaTags() {
        return homeFunnyTagService.mediaTags();
    }

    @PutMapping("media-tags")
    public HomeFunnyMediaTagDto putMediaTags(@RequestBody HomeFunnyMediaTagDto tag) {
        return homeFunnyTagService.putMediaTags(tag);
    }

    @DeleteMapping("media-tags/{id}")
    public void deleteMediaTag(@PathVariable Long id) {
        homeFunnyTagService.delete(id);
    }

}
