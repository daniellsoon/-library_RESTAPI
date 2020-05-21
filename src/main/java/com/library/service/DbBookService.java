package com.library.service;

import com.library.domain.Book;
import com.library.domain.StatusAllowed;
import com.library.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbBookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book saveBook(final Book book) {
        return bookDao.save(book);
    }

    public int getAvailableBooks(Long titleId) {
       List<Book> availableBooks = bookDao.findAllByTitleIdAndStatus(titleId, StatusAllowed.OPEN);
       return availableBooks.size();
    }
}
