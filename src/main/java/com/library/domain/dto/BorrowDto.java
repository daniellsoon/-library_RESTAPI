package com.library.domain.dto;

import com.library.domain.Book;
import com.library.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowDto {

    private long id;
    private Book book;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
