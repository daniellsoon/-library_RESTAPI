package com.library.repository;

import com.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BorrowDao extends CrudRepository<Borrow, Long> {

    @Override
    List<Borrow> findAll();

    Borrow findByBookId(Long bookId);

}
