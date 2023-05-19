package com.github.vova.olexienko.library.api.controller;

import com.github.vova.olexienko.library.api.dto.author.AuthorDto;
import com.github.vova.olexienko.library.api.dto.author.CrudAuthorDto;
import com.github.vova.olexienko.library.api.service.author.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("author")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public void createAuthor(@RequestBody @Valid CrudAuthorDto dto) {
        authorService.createAuthor(dto);
    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable("id") @NotNull Long id, @RequestBody @Valid CrudAuthorDto dto) {
        authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") @NotNull Long id) {
        authorService.deleteAuthor(id);
    }
}
