package com.github.vova.olexienko.library.api.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooksPageDto {

    @JsonProperty("books")
    List<BookDto> books;

    @JsonProperty("pagesNumber")
    int pagesNumber;
}
