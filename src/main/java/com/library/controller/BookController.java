package com.library.controller;

import com.library.controller.exceptions.TitleNotFoundException;
import com.library.domain.dto.BookDto;
import com.library.mapper.BookMapper;
import com.library.service.DbBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    private DbBookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping(value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping(value = "updateStatus")
    public BookDto updateStatus(@RequestBody BookDto bookDto){
        return bookMapper.mapToBookDto(bookService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    //TitleNotFoundException
    @GetMapping(value = "availableBooks")
    public int availableBooks(@RequestBody Long titleId) throws TitleNotFoundException {
            return bookService.getAvailableBooks(titleId);
    }


}
