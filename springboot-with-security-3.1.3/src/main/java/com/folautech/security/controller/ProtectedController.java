package com.folautech.security.controller;

import com.folautech.security.model.News;
import com.folautech.security.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Protected", description = "Protected Endpoints")
@Slf4j
@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @GetMapping("/user")
    public User getUser(@RequestHeader String token){
        log.info("token:{}", token);
        return User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@gmail.com")
                .build();
    }
}
