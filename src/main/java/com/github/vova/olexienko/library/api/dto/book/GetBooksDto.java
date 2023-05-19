package com.github.vova.olexienko.library.api.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetBooksDto {

    @Min(0)
    int page;

    Long genreId;

    @Pattern(regexp = "^.{3,}$")
    String searchOption;
}
