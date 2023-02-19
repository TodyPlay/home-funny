package com.home.funny.api.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
