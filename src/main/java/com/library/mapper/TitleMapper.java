package com.library.mapper;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear()
        );
    }

    public TitleDto mapToTitleDao(final Title title){
        return new TitleDto(
                title.getId(),
                title.getBookTitle(),
                title.getAuthor(),
                title.getPublicationYear()
        );
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(e -> new TitleDto(e.getId(), e.getBookTitle(),
                        e.getAuthor(), e.getPublicationYear()))
                .collect(Collectors.toList());
    }
}
