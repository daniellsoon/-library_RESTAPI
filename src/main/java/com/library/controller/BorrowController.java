package com.library.controller;

import com.library.domain.dto.BorrowDto;
import com.library.mapper.BorrowMapper;
import com.library.service.DbBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {

    @Autowired
    private DbBorrowService borrowService;

    @Autowired
    private BorrowMapper borrowMapper;

    @PostMapping(value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowDto borrowDto) {
        borrowService.saveBorrow(borrowMapper.mapToBorrow(borrowDto));
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long id) {
        borrowService.returnBook(id);
    }
}
