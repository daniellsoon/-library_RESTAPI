package com.library.controller;

import com.library.domain.dto.TitleDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/title")
public class TitleController {

    @PostMapping(value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        System.out.println("Title added!");
    }
}
