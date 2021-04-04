package com.kma.specification.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.kma.specification.domain.type.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private String isbn;
    private String title;
    private BookStatus status;
    private LocalDateTime createdTimestamp;
    private CategoryDto category;
    private List<AuthorDto> authors;

}
