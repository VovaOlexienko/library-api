package com.github.vova.olexienko.library.api.service.author;

import com.github.vova.olexienko.library.api.dto.author.AuthorDto;
import com.github.vova.olexienko.library.api.dto.author.CrudAuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAuthors();

    void createAuthor(CrudAuthorDto dto);

    void updateAuthor(Long id, CrudAuthorDto dto);

    void deleteAuthor(Long id);
}
