package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReaderDto {

    private long id;
    private String name;
    private String lastName;
    private LocalDate singIn;

}
