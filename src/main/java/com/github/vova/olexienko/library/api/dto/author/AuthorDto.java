package com.github.vova.olexienko.library.api.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;
}