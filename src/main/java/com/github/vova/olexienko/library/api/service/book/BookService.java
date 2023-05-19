package com.github.vova.olexienko.library.api.service.book;

import com.github.vova.olexienko.library.api.dto.book.BooksPageDto;
import com.github.vova.olexienko.library.api.dto.book.CrudBookDto;
import com.github.vova.olexienko.library.api.dto.book.GetBooksDto;

public interface BookService {

    BooksPageDto getBooks(GetBooksDto dto);

    void createBook(CrudBookDto dto);

    void updateBook(Long id, CrudBookDto dto);

    void deleteBook(Long id);
}
