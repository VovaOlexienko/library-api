package com.github.vova.olexienko.library.api.controller;

import com.github.vova.olexienko.library.api.dto.genre.CrudGenreDto;
import com.github.vova.olexienko.library.api.dto.genre.GenreDto;
import com.github.vova.olexienko.library.api.service.genre.GenreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping
    public void createGenre(@RequestBody @Valid CrudGenreDto dto) {
        genreService.createGenre(dto);
    }

    @PutMapping("/{id}")
    public void updateGenre(@PathVariable("id") @NotNull Long id, @RequestBody @Valid CrudGenreDto dto) {
        genreService.updateGenre(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") @NotNull Long id) {
        genreService.deleteGenre(id);
    }
}
