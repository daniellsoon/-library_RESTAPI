package com.library.repository;

import com.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowDao extends CrudRepository<Borrow, Long> {
}
