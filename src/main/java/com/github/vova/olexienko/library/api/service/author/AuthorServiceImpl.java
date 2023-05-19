package com.github.vova.olexienko.library.api.service.author;

import com.github.vova.olexienko.library.api.dto.author.AuthorDto;
import com.github.vova.olexienko.library.api.dto.author.CrudAuthorDto;
import com.github.vova.olexienko.library.api.entity.Author;
import com.github.vova.olexienko.library.api.mapper.AuthorMapper;
import com.github.vova.olexienko.library.api.repository.AuthorRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAuthors() {
        return authorMapper.toDto(authorRepository.findAll());
    }

    @Override
    public void createAuthor(CrudAuthorDto dto) {
        authorRepository.save(authorMapper.toEntity(dto));
    }

    @Override
    public void updateAuthor(Long id, CrudAuthorDto dto) {
        Author oldAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ValidationException(String.format("Author with id = [%s] is absent", id)));
        Author newAuthor = authorMapper.toEntity(dto);
        newAuthor.setId(oldAuthor.getId());
        authorRepository.save(newAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
