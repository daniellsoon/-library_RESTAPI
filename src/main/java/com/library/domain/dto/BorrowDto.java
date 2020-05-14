package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowDto {

    private long id;
    private long bookId;
    private long userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
