package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TitleDto {

    private long id;
    private String title;
    private String author;
    private int publicationYear;

}
