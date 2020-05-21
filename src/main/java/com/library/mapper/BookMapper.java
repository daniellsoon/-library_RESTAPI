package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        Book book = new Book(bookDto.getId(), bookDto.getTitle());
        book.setStatus(bookDto.getStatus());
        return book;
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getStatus());
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(e -> new BookDto(e.getId(), e.getTitle(), e.getStatus()))
                .collect(Collectors.toList());
    }

    }
