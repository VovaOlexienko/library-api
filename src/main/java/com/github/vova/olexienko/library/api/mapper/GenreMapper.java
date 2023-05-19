package com.github.vova.olexienko.library.api.mapper;

import com.github.vova.olexienko.library.api.dto.genre.CrudGenreDto;
import com.github.vova.olexienko.library.api.dto.genre.GenreDto;
import com.github.vova.olexienko.library.api.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    Genre toEntity(CrudGenreDto dto);

    List<GenreDto> toDto(List<Genre> entity);
}