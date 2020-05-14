package com.library.controller;

import com.library.domain.dto.BookDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    @PostMapping(value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        System.out.println("Book added");
    }

    @PutMapping(value = "updateStatus")
    public BookDto updateStatus(@RequestBody BookDto bookDto){
        return new BookDto(1, "Title", "UpdatedStatus");
    }

    @GetMapping(value = "availableBooks")
    public int availableBooks(@RequestBody Long TitleId) {
        return 5;
    }


}
