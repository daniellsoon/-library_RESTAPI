package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.dto.BorrowDto;
import org.springframework.stereotype.Component;

@Component
public class BorrowMapper {

    public Borrow mapToBorrow(BorrowDto borrowDto) {
        Borrow borrow = new Borrow(borrowDto.getId());
        borrow.setBook(borrowDto.getBook());
        borrow.setReader(borrowDto.getReader());
        borrow.setBorrowDate(borrowDto.getBorrowDate());
        borrow.setReturnDate(borrowDto.getReturnDate());
        return borrow;
    }

    public BorrowDto mapToBorrowDto(Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getBook(),
                borrow.getReader(),
                borrow.getBorrowDate(),
                borrow.getReturnDate()
                );
    }
}
