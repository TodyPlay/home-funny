package com.home.funny.api.constant;

import com.home.funny.constant.MediaType;
import com.home.funny.constant.wrapeper.KVWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/constant")
public class ConstantController {

    @GetMapping("media-types")
    public Flux<KVWrapper<?, ?>> mediaTypes() {
        return Flux.fromArray(MediaType.values()).map(KVWrapper::valueOf);
    }
}
