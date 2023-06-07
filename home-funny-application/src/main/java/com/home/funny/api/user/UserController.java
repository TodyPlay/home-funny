package com.home.funny.api.user;

import com.home.funny.model.dto.HomeFunnyUserDetailDto;
import com.home.funny.repository.HomeFunnyUserDetailRepository;
import com.home.funny.service.HomeFunnyUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final HomeFunnyUserDetailsService homeFunnyUserDetailsService;

    public UserController(HomeFunnyUserDetailsService homeFunnyUserDetailRepository) {
        this.homeFunnyUserDetailsService = homeFunnyUserDetailRepository;
    }

    @GetMapping("{username}")
    public HomeFunnyUserDetailDto findUserByName(@PathVariable String username){
        return homeFunnyUserDetailsService.findByUsername(username);
    }

}
