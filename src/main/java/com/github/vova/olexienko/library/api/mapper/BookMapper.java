package com.github.vova.olexienko.library.api.mapper;

import com.github.vova.olexienko.library.api.dto.book.BookDto;
import com.github.vova.olexienko.library.api.dto.book.CrudBookDto;
import com.github.vova.olexienko.library.api.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book toEntity(CrudBookDto dto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    BookDto toDto(Book entity);
}