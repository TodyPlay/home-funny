package com.home.funny.api.storage;

import com.home.funny.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id, @RequestHeader(value = "Range", required = false) String range) throws Exception {
        return storageService.download(id, range);
    }
}
