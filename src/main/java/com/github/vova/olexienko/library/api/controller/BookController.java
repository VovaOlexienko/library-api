package com.github.vova.olexienko.library.api.controller;

import com.github.vova.olexienko.library.api.dto.book.BooksPageDto;
import com.github.vova.olexienko.library.api.dto.book.CrudBookDto;
import com.github.vova.olexienko.library.api.dto.book.GetBooksDto;
import com.github.vova.olexienko.library.api.service.book.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public BooksPageDto getBooks(@Valid GetBooksDto dto) {
        return bookService.getBooks(dto);
    }

    @PostMapping
    public void createBook(@Valid @RequestBody CrudBookDto dto) {
        bookService.createBook(dto);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") @NotNull Long id, @RequestBody @Valid CrudBookDto dto) {
        bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") @NotNull Long id) {
        bookService.deleteBook(id);
    }
}