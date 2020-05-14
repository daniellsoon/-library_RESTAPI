package com.library.repository;

import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleDao extends CrudRepository<Title, Long> {
}
