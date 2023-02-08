package com.home.funny.api.constant;

import com.home.funny.constant.KVEnum;
import com.home.funny.constant.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/constant")
public class ConstantController {

    @GetMapping("")
    public Flux<KVEnum<?, ?>> mediaTypes() {
        return Flux.fromArray(MediaType.values());
    }
}
