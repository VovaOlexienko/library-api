package com.github.vova.olexienko.library.api.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudBookDto {

    @NotBlank
    @Size(max = 100)
    @JsonProperty("name")
    String name;

    @NotBlank
    @Size(max = 250)
    @JsonProperty("image")
    String image;

    @NotBlank
    @Size(max = 250)
    @JsonProperty("content")
    String content;

    @NotEmpty
    @JsonProperty("authors")
    List<Long> authors;

    @NotEmpty
    @JsonProperty("genres")
    List<Long> genres;
}
