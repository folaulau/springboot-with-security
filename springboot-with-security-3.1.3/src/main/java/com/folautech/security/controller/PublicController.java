package com.folautech.security.controller;

import com.folautech.security.model.News;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Public", description = "Public Endpoints")
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/today-news")
    public News getTodayNews(){
        log.info("getTodayNews...");
        return News.builder().author("John").content("Hello").build();
    }
}
