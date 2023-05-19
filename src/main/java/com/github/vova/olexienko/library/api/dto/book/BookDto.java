package com.github.vova.olexienko.library.api.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("image")
    String image;

    @JsonProperty("content")
    String content;

    @JsonProperty("genres")
    List<Long> genres;

    @JsonProperty("authors")
    List<Long> authors;
}
