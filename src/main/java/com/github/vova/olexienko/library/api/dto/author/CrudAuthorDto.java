package com.github.vova.olexienko.library.api.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudAuthorDto {

    @NotBlank
    @Size(max = 100)
    @JsonProperty("name")
    String name;
}
