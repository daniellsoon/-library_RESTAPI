package com.library.controller;

import com.library.domain.dto.TitleDto;
import com.library.mapper.TitleMapper;
import com.library.service.DbTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/title")
@RequiredArgsConstructor
public class TitleController {

    private final DbTitleService titleService;
    private final TitleMapper titleMapper;

    @PostMapping(value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        titleService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @GetMapping(value = "getTitles")
    public List<TitleDto> getAllTitles(){
        return titleMapper.mapToTitleDtoList(titleService.getAllTitles());
    }
}
