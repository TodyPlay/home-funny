package com.home.funny.api.storage;

import com.home.funny.model.dto.HomeFunnyStorageDto;
import com.home.funny.service.HomeFunnyStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/storage")
@Slf4j
public class StorageController {

    @Autowired
    private HomeFunnyStorageService homeFunnyStorageService;

    @GetMapping("{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id, @RequestHeader(value = "Range", required = false) String range) throws Exception {
        return homeFunnyStorageService.download2(id, range);
    }

    @PostMapping
    public Page<HomeFunnyStorageDto> list(@PageableDefault Pageable page) {
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


    @PostMapping("test-upload")
    public void up(@RequestParam("file") MultipartFile file, @RequestParam("taskId") Long taskId, @RequestParam("partNum") Integer partNum) throws IOException {
        log.debug(String.valueOf(taskId));
        log.debug(String.valueOf(partNum));
        log.debug(String.valueOf(file.getInputStream().readAllBytes().length));
    }
}
