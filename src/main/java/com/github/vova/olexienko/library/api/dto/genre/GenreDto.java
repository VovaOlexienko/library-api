package com.github.vova.olexienko.library.api.dto.genre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;
}
