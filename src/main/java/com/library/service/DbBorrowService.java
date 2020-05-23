package com.library.service;

import com.library.domain.Borrow;
import com.library.repository.BorrowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DbBorrowService {

    @Autowired
    private BorrowDao borrowDao;

    public Borrow saveBorrow(Borrow borrow) {
        return borrowDao.save(borrow);
    }

    public void returnBook(Long bookId) {
        Borrow returnedBook = borrowDao.findByBookId(bookId);
        returnedBook.setReturnDate(LocalDate.now());
        borrowDao.save(returnedBook);
    }
}
