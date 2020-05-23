package com.library.service;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.repository.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbReaderService {

    @Autowired
    private ReaderDao readerDao;

    public List<Reader> getAllReaders() {
        return readerDao.findAll();
    }

    public Reader saveReader(final Reader reader) {
        return readerDao.save(reader);
    }
}
