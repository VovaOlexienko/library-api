package com.github.vova.olexienko.library.api.mapper;

import com.github.vova.olexienko.library.api.dto.author.AuthorDto;
import com.github.vova.olexienko.library.api.dto.author.CrudAuthorDto;
import com.github.vova.olexienko.library.api.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(CrudAuthorDto dto);

    List<AuthorDto> toDto(List<Author> entity);
}