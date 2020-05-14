package com.library.controller;

import com.library.domain.dto.BorrowDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {

    //adding new obj
    @PostMapping(value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowDto borrowDto) {
        System.out.println("Book borrowed!");
    }

    @PutMapping(value = "returnBook")
    public BorrowDto returnBook(@RequestBody BorrowDto borrowDtoUser) {
        return new BorrowDto(1L, 1L, 1L, LocalDate.now(), LocalDate.of(0,0,0));
            }

}
