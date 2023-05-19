package com.github.vova.olexienko.library.api.service.genre;

import com.github.vova.olexienko.library.api.dto.genre.CrudGenreDto;
import com.github.vova.olexienko.library.api.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getGenres();

    void createGenre(CrudGenreDto dto);

    void updateGenre(Long id, CrudGenreDto dto);

    void deleteGenre(Long id);
}
