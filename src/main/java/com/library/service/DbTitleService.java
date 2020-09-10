package com.library.service;

import com.library.domain.Title;
import com.library.repository.TitleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DbTitleService {

    private final TitleDao titleDao;

    public List<Title> getAllTitles() {
        return titleDao.findAll();
    }

    public Title saveTitle(final Title title) {
        return titleDao.save(title);
    }
}
