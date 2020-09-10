package com.library.service;

import com.library.domain.Borrow;
import com.library.repository.BorrowDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DbBorrowService {


    private final BorrowDao borrowDao;

    public Borrow saveBorrow(Borrow borrow) {
        return borrowDao.save(borrow);
    }

    public void returnBook(Long bookId) {
        Borrow returnedBook = borrowDao.findByBookId(bookId);
        returnedBook.setReturnDate(LocalDate.now());
        borrowDao.save(returnedBook);
    }
}
