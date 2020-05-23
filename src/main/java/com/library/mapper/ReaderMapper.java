package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        Reader reader = new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getLastName()
        );
        reader.setSingIn(readerDto.getSingIn());
        return reader;
    }

    public ReaderDto mapToReaderDto (final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getLastName(),
                reader.getSingIn()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList){
        return readerList.stream()
                .map(e -> new ReaderDto(e.getId(), e.getName(), e.getLastName(), e.getSingIn()))
                .collect(Collectors.toList());
    }
}
