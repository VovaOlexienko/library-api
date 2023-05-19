package com.github.vova.olexienko.library.api.dto.genre;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudGenreDto {

    @NotBlank
    @Size(max = 50)
    @JsonProperty("name")
    String name;
}
