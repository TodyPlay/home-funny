package com.home.funny.api.storage;

import com.home.funny.model.dto.HomeFunnyStorageDto;
import com.home.funny.model.dto.paging.PageableDTO;
import com.home.funny.service.HomeFunnyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @Autowired
    private HomeFunnyStorageService homeFunnyStorageService;

    @GetMapping("{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id, @RequestHeader(value = "Range", required = false) String range) throws Exception {
        return homeFunnyStorageService.download2(id, range);
    }

    @PostMapping
    public Page<HomeFunnyStorageDto> list(PageableDTO page) {
        return homeFunnyStorageService.findAll(page);
    }

    @PutMapping
    public HomeFunnyStorageDto upload(@RequestParam("file") MultipartFile part) throws Exception {
        return homeFunnyStorageService.upload(part);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        homeFunnyStorageService.delete(id);
    }
}
