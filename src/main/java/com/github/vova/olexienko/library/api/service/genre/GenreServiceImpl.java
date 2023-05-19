package com.github.vova.olexienko.library.api.service.genre;

import com.github.vova.olexienko.library.api.dto.genre.CrudGenreDto;
import com.github.vova.olexienko.library.api.dto.genre.GenreDto;
import com.github.vova.olexienko.library.api.entity.Genre;
import com.github.vova.olexienko.library.api.mapper.GenreMapper;
import com.github.vova.olexienko.library.api.repository.GenreRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getGenres() {
        return genreMapper.toDto(genreRepository.findAll());
    }

    @Override
    public void createGenre(CrudGenreDto dto) {
        genreRepository.save(genreMapper.toEntity(dto));
    }

    @Override
    public void updateGenre(Long id, CrudGenreDto dto) {
        Genre oldGenre = genreRepository.findById(id)
                .orElseThrow(() -> new ValidationException(String.format("Genre with id = [%s] is absent", id)));
        Genre newGenre = genreMapper.toEntity(dto);
        newGenre.setId(oldGenre.getId());
        genreRepository.save(newGenre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
