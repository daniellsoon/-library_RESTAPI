package com.library.service;

import com.library.domain.Title;
import com.library.repository.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbTitleService {

    @Autowired
    private TitleDao titleDao;

    public List<Title> getAllTitles() {
        return titleDao.findAll();
    }

    public Title saveTitle(final Title title) {
        return titleDao.save(title);
    }
}
