package com.library.controller;

import com.library.controller.exceptions.BorrowNotFoundException;
import com.library.domain.dto.BorrowDto;
import com.library.mapper.BorrowMapper;
import com.library.service.DbBorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final DbBorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @PostMapping(value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowDto borrowDto) {
        borrowService.saveBorrow(borrowMapper.mapToBorrow(borrowDto));
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long id) throws BorrowNotFoundException {
        borrowService.returnBook(id);
    }
}
