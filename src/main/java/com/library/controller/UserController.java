package com.library.controller;

import com.library.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping(value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser (@RequestBody UserDto userDto) {
        System.out.println("User added");
    }
}
