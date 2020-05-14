package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private long id;
    private String name;
    private String lastName;
    private LocalDate singIn;

}
