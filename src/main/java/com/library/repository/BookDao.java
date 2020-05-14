package com.library.repository;

import com.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
}
