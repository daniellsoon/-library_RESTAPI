package com.library.domain.dto;

import com.library.domain.StatusAllowed;
import com.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private long id;
    private Title title;
    private StatusAllowed status;

}
